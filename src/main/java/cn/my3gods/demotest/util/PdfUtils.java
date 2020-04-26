package cn.my3gods.demotest.util;

import cn.my3gods.demotest.enums.FontEnum;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfCopy;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.SimpleBookmark;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

@Slf4j
public class PdfUtils {

    // thymeleaf模板所需参数 key - 模板里取值所用名称 value - 对应对象值
    private Map<String, Object> htmlVariableMap;

    // html模板在resource里template文件下的相对路径
    private String templatePath;

    @Resource
    private SpringTemplateEngine springTemplateEngine;

    private PdfUtils(Map<String, Object> htmlVariableMap, String templatePath) {
        this.htmlVariableMap = htmlVariableMap;
        this.templatePath = templatePath;
    }

    /**
     * 传入生成HTML模板
     *
     * @return 处理后的html
     */
    public String processHtmlTemplate() {
        return springTemplateEngine.process(templatePath, new Context(null, htmlVariableMap));
    }

    /**
     * 返回工具类对象
     *
     * @param htmlVariableMap 变量名 - 变量参数 的Map集合
     * @param templatePath resource里template文件下的相对路径
     * @return PdfUtils
     */
    public static PdfUtils build(Map<String, Object> htmlVariableMap, String templatePath) {
        return new PdfUtils(htmlVariableMap, templatePath);
    }

    /**
     * 生成PDF文件
     *
     * @param htmlVariableMap HTML模板所需参数
     * @param templatePath 模板路径
     * @param savePath 导出的PDF文件路径
     * @return 生成结果
     */
    public static boolean createPdfByHtml(Map<String, Object> htmlVariableMap, String templatePath, String savePath) {
        String html = build(htmlVariableMap, templatePath).processHtmlTemplate();
        if (StringUtils.isBlank(html)) {
            log.error("PdfUtils.createPdfByHtml empty html string!");
            return false;
        }
        return createPdfByHtml(html, savePath);
    }

    /**
     * 生成包含指定字体的PDF文件
     *
     * @param htmlVariableMap HTML模板所需参数
     * @param templatePath 模板路径
     * @param savePath 导出的PDF文件路径
     * @param fontPath 字体文件路径
     * @return 生成结果
     */
    public static boolean createPdfByHtml(Map<String, Object> htmlVariableMap, String templatePath, String savePath, String fontPath) {
        String html = build(htmlVariableMap, templatePath).processHtmlTemplate();
        if (StringUtils.isBlank(html)) {
            log.error("PdfUtils.createPdfByHtml empty html string!");
            return false;
        }
        return createPdfByHtml(html, savePath, fontPath);
    }

    /**
     * 根据HTML生成对应PDF到文件夹（默认添加字体simsun）
     *
     * @param html HTML字符串
     * @param savePath 存储路径
     * @return 生成PDF结果[true成功]
     */
    public static boolean createPdfByHtml(String html, String savePath) {
        return createPdfByHtml(html, savePath, FontEnum.SIMSUN.getFilePath());
    }

    /**
     * 根据HTML生成对应PDF到文件夹
     *
     * @param html HTML字符串
     * @param savePath 存储路径
     * @param fontPath 自定义字体文件
     * @return 生成PDF结果[true成功]
     */
    public static boolean createPdfByHtml(String html, String savePath, String fontPath) {
        try (OutputStream outputStream = new FileOutputStream(savePath)) {
            outputStream.flush();
            ITextRenderer renderer = new ITextRenderer();
            ITextFontResolver fontResolver = renderer.getFontResolver();
            renderer.setDocumentFromString(html);
            // 设置字体
            fontResolver.addFont(fontPath, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            renderer.layout();
            renderer.createPDF(outputStream, true);
            renderer.finishPDF();
            return true;
        } catch (FileNotFoundException e) {
            log.error("PdfUtils.createFile FileNotFoundException:{}", e.getMessage(), e);
        } catch (IOException e) {
            log.error("PdfUtils.createFile IOException:{}", e.getMessage(), e);
        } catch (DocumentException e) {
            log.error("PdfUtils.createFile DocumentException:{}", e.getMessage(), e);
        }
        return false;
    }

    /**
     * 合并多个pdf文件
     *
     * @param fileList 文件对象
     * @param savePath 合并成的文件路径
     * @return 成功 - true
     */
    public static boolean mergePdfV1(List<File> fileList, String savePath) {
        Document document = null;
        try {
            document = new Document(new PdfReader(fileList.get(0).getPath()).getPageSize(1));
            PdfCopy copy = new PdfCopy(document, new FileOutputStream(savePath));
            document.open();
            for (File aFileList : fileList) {
                PdfReader reader = new PdfReader(aFileList.getPath());
                int n = reader.getNumberOfPages();// 获得总页码
                for (int j = 1; j <= n; j++) {
                    document.newPage();
                    PdfImportedPage page = copy.getImportedPage(reader, j);// 从当前Pdf,获取第j页
                    copy.addPage(page);
                }
            }
        } catch (Exception e) {
            log.error("PdfUtils.mergePdf error:{}\n{}", e.getMessage(), e);
            return false;
        } finally {
            if (document != null) {
                document.close();
            }
        }
        return true;
    }

    /**
     * 合并PDF文件（参考itext2.1.7源码
     *
     * @param fileList 合并的文件路径集合
     * @param outFilePath 新文件的路径
     * @return 合并结果true - 成功
     */
    public static boolean mergePdf(List<File> fileList, String outFilePath) {
        Document document = null;
        try {
            List<Map<String, Object>> master = new ArrayList<>();
            PdfCopy writer = null;
            for (int fileIndex = 0, pageOffset = 0; fileIndex < fileList.size(); fileIndex++) {
                File pdfFile = fileList.get(fileIndex);
                // we create a reader for a certain document
                PdfReader reader = new PdfReader(pdfFile.getPath());
                reader.consolidateNamedDestinations();
                // we retrieve the total number of pages
                int pdfPages = reader.getNumberOfPages();

                @SuppressWarnings({"unchecked", "rawtypes"})
                List<Map<String, Object>> bookmarks = SimpleBookmark.getBookmark(reader);
                if (bookmarks != null) {
                    if (pageOffset != 0) {
                        SimpleBookmark.shiftPageNumbers(bookmarks, pageOffset, null);
                    }
                    master.addAll(bookmarks);
                }
                pageOffset += pdfPages;
                log.info("There are {} pages in {}", pdfPages, pdfFile.getPath());

                // init when first file
                if (0 == fileIndex) {
                    // step 1: creation of a document-object
                    document = new Document(reader.getPageSizeWithRotation(1));
                    // step 2: we create a writer that listens to the document
                    writer = new PdfCopy(document, new FileOutputStream(outFilePath));
                    // step 3: we open the document
                    document.open();
                }
                // step 4: we add content
                for (int i = 1; i <= pdfPages; i++) {
                    PdfImportedPage page = writer.getImportedPage(reader, i);
                    writer.addPage(page);
                    log.info("Processed {} page {}", pdfFile.getName(), i);
                }
                writer.freeReader(reader);
            }
            if (Objects.nonNull(writer) && !master.isEmpty()) {
                writer.setOutlines(master);
            }
            log.info("Merge Successfully {}", outFilePath);
            return true;
        } catch (Exception e) {
            log.error("PdfUtils.mergePdf Exception:{}\n{}", e.getMessage(), e);
        } finally {
            // step 5: we close the document
            if (Objects.nonNull(document)) {
                document.close();
            }
        }
        log.info("Merge failed");
        return false;
    }

    /**
     * 分割PDF为两个
     *
     * @param originFilePath 原PDF文件路径
     * @param upperPartPath 导出的前半部分PDF
     * @param latterPartPath 导出的后半部分PDF
     * @param splitPage 分割处页面页码，包含在第二部分
     * @return 分割结果 true - 成功
     */
    public static boolean splitPdf(String originFilePath, String upperPartPath, String latterPartPath, int splitPage) {
        try {
            // we create a reader for a certain document
            PdfReader reader = new PdfReader(originFilePath);
            // we retrieve the total number of pages
            int totalPage = reader.getNumberOfPages();

            log.info("There are {} pages in the original file:{}", totalPage, originFilePath);

            if (splitPage < 2) {
                splitPage = 2;
            } else if (splitPage > totalPage) {
                splitPage = totalPage;
            }

            // the upper part origin pdf 1 to (splitPage - 1): subPdfFromOrigin(reader, upperPartPath, 1, splitPage - 1)

            // the latter part of origin pdf splitPage to totalPage: subPdfFromOrigin(reader, latterPartPath, splitPage, totalPage)

            return subPdfFromOrigin(reader, upperPartPath, 1, splitPage - 1) && subPdfFromOrigin(reader, latterPartPath, splitPage, totalPage);
        } catch (Exception e) {
            log.error("PdfUtils.splitPdf Exception:{}\n{}", e.getMessage(), e);
        }
        return false;
    }

    /**
     * 从PDF中截取某一部分页的PDF
     *
     * @param originReader 原PDF的new PdfReader(originFilePath)对象
     * @param outPath 导出的PDF文件路径
     * @param startPage 截取开始的页
     * @param endPage 截取结束的页（包含此页
     * @return 截取导出结果 true - 成功
     */
    public static boolean subPdfFromOrigin(PdfReader originReader, String outPath, int startPage, int endPage) {
        // step 1: creation of a document-object
        Document doc = null;
        try {
            doc = new Document(originReader.getPageSizeWithRotation(startPage));
            // step 2: we create a writer that listens to the document
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(outPath));
            // step 3: we open the document
            doc.open();
            PdfContentByte contentByte = writer.getDirectContent();
            // step 4: we add content
            for (int i = startPage; i <= endPage; i++) {
                doc.setPageSize(originReader.getPageSizeWithRotation(i));
                doc.newPage();
                PdfImportedPage page = writer.getImportedPage(originReader, i);
                int rotation = originReader.getPageRotation(i);
                if (rotation == 90 || rotation == 270) {
                    contentByte.addTemplate(page, 0, -1f, 1f, 0, 0, originReader.getPageSizeWithRotation(i).getHeight());
                } else {
                    contentByte.addTemplate(page, 1f, 0, 0, 1f, 0, 0);
                }
                log.info("Processed page {} to {}", i, outPath);
            }
            return true;
        } catch (FileNotFoundException | DocumentException e) {
            log.error("PdfUtils.subPdfFromOrigin Exception:{}\n{}", e.getMessage(), e);
        } finally {
            if (Objects.nonNull(doc)) {
                doc.close();
            }
        }
        return false;
    }

    /**
     * 截取PDF文件的一部分页
     * @param srcPath 源文件地址
     * @param outPath 导出文件地址
     * @param startPage 开始页
     * @param endPage 结束页（包含
     * @return 截取结果 true - 成功
     */
    public static boolean subPdfFromOrigin(String srcPath, String outPath, int startPage, int endPage) {
        try {
            // we create a reader for a certain document
            PdfReader reader = new PdfReader(srcPath);
            // we retrieve the total number of pages
            int totalPage = reader.getNumberOfPages();

            log.info("There are {} pages in the original file:{}", totalPage, srcPath);

            if (startPage < 1) {
                startPage = 1;
            }
            if (endPage > totalPage) {
                endPage = totalPage;
            }

            return subPdfFromOrigin(reader, outPath, startPage, endPage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}

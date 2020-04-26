package cn.my3gods.demotest.pdf;

import cn.my3gods.demotest.util.PdfUtils;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class PdfTest {

    @Test
    public void testConcatPdf() {
        //E:\mytest\test1.pdf E:\mytest\test2.pdf E:\mytest\aaaaa.pdf
        String[] args = {"E:\\mytest\\test.pdf", "E:\\mytest\\test2.pdf"};
        List<File> fileList = Arrays.stream(args).map(File::new).collect(Collectors.toList());
        PdfUtils.mergePdf(fileList, "E:\\mytest\\aaaaa.pdf");
    }

    @Test
    public void testSplitPdf() {
        PdfUtils.splitPdf("E:\\mytest\\aaaaa.pdf", "E:\\mytest\\aaaaa1.pdf", "E:\\mytest\\aaaaa2.pdf", 2);
    }

    @Test
    public void testSubPdf() {
        PdfUtils.subPdfFromOrigin("E:\\mytest\\aaaaa.pdf", "E:\\mytest\\sub.pdf", 2, 3);
    }
}

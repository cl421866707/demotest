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
        /*if (args.length < 2) {
            System.err.println("arguments: file1 [file2 ...] destfile");
        } else {
            try {
                int pageOffset = 0;
                ArrayList master = new ArrayList();
                int f = 0;
                String outFile = args[args.length - 1];
                Document document = null;
                PdfCopy writer = null;
                while (f < args.length - 1) {
                    // we create a reader for a certain document
                    PdfReader reader = new PdfReader(args[f]);
                    reader.consolidateNamedDestinations();
                    // we retrieve the total number of pages
                    int n = reader.getNumberOfPages();
                    List bookmarks = SimpleBookmark.getBookmark(reader);
                    if (bookmarks != null) {
                        if (pageOffset != 0) {
                            SimpleBookmark.shiftPageNumbers(bookmarks, pageOffset, null);
                        }
                        master.addAll(bookmarks);
                    }
                    pageOffset += n;
                    System.out.println("There are " + n + " pages in " + args[f]);

                    if (f == 0) {
                        // step 1: creation of a document-object
                        document = new Document(reader.getPageSizeWithRotation(1));
                        // step 2: we create a writer that listens to the document
                        writer = new PdfCopy(document, new FileOutputStream(outFile));
                        // step 3: we open the document
                        document.open();
                    }
                    // step 4: we add content
                    PdfImportedPage page;
                    for (int i = 0; i < n; ) {
                        ++i;
                        page = writer.getImportedPage(reader, i);
                        writer.addPage(page);
                        System.out.println("Processed page " + i);
                    }
                    writer.freeReader(reader);
                    f++;
                }
                if (!master.isEmpty()) {
                    writer.setOutlines(master);
                }
                // step 5: we close the document
                document.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/
    }
}

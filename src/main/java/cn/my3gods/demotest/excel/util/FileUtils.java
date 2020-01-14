package cn.my3gods.demotest.excel.util;

public class FileUtils {

    // 工程路径
    private static final String PROJECT_PATH = System.getProperty("user.dir");

    // 导出Excel文件存放目录
    public static final String WRITE_PATH = PROJECT_PATH + "/src/main/resources/excels/";

    private FileUtils() {
    }

    /**
     * 获取随机的文件名
     *
     * @return 基于当前时间戳的文件名
     */
    public static String excelFileName(String prefix) {
        return prefix + System.currentTimeMillis() + ".xlsx";
    }
}

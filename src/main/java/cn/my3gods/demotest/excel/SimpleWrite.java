package cn.my3gods.demotest.excel;

import com.alibaba.excel.EasyExcel;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SimpleWrite {

    private String filePath;

    private Class targetClass;

    private String sheetName;

    private Set<String> excludeColumns;

    private List targetDatas;

    private SimpleWrite(String filePath, Class targetClass, String sheetName, Set<String> excludeColumns, List targetDatas) {
        this.filePath = filePath;
        this.targetClass = targetClass;
        this.sheetName = sheetName;
        this.excludeColumns = excludeColumns;
        this.targetDatas = targetDatas;
    }

    /**
     * 简单写Excel
     */
    public void simpleWrite() {
        EasyExcel.write(filePath, targetClass).sheet(sheetName).excludeColumnFiledNames(excludeColumns).doWrite(this.targetDatas);
    }

    public static SimpleWriteBuilder builder(String filePath) {
        return new SimpleWriteBuilder(filePath);
    }

    public static class SimpleWriteBuilder {

        private String filePath;

        private Class targetClass;

        private String sheetName;

        private Set<String> excludeColumns;

        private List targetDatas;

        private SimpleWriteBuilder(String filePath) {
            this.filePath = filePath;
        }

        public SimpleWriteBuilder filePath(String filePath) {
            this.filePath = filePath;
            return this;
        }

        public SimpleWriteBuilder targetClass(Class targetClass) {
            this.targetClass = targetClass;
            return this;
        }

        public SimpleWriteBuilder sheetName(String sheetName) {
            this.sheetName = sheetName;
            return this;
        }

        public SimpleWriteBuilder excludeColumns(Set<String> excludeColumns) {
            this.excludeColumns = excludeColumns;
            return this;
        }

        public SimpleWriteBuilder targetDatas(List targetDatas) {
            this.targetDatas = targetDatas;
            return this;
        }

        public SimpleWrite build() {
            return new SimpleWrite(this.filePath, this.targetClass, this.sheetName, this.excludeColumns, this.targetDatas);
        }
    }
}

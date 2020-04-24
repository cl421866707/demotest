package cn.my3gods.demotest.enums;

import lombok.Getter;

@Getter
public enum FontEnum {

    // 中文支持
    SIMSUN("SimSun", "/static/style/font/simsun.ttc"),

    // 拉丁文
    ANZIANO("Anziano", "/static/style/font/Anziano-Italic.otf");

    // css样式中font-family
    private String fontFamilyName;

    // 字体文件路径
    private String filePath;

    FontEnum(String fontFamilyName, String filePath) {
        this.fontFamilyName = fontFamilyName;
        this.filePath = filePath;
    }
}

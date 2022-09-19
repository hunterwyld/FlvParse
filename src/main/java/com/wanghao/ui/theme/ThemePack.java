package com.wanghao.ui.theme;

/**
 * 主题包
 */
public class ThemePack {
    /**
     * 主题包名称
     * */
    private String name;

    /**
     * Analyze按钮的主题包
     * */
    private IconThemePack analyzeThemePack;

    /**
     * Setting按钮的主题包
     * */
    private IconThemePack settingThemePack;

    /**
     * 颜色主题包
     * */
    private ColorThemePack colorThemePack;


    public ThemePack(String name, IconThemePack analyzeThemePack, IconThemePack settingThemePack, ColorThemePack colorThemePack) {
        this.name = name;
        this.analyzeThemePack = analyzeThemePack;
        this.settingThemePack = settingThemePack;
        this.colorThemePack = colorThemePack;
    }

    public String getName() {
        return name;
    }

    public IconThemePack getAnalyzeThemePack() {
        return analyzeThemePack;
    }

    public IconThemePack getSettingThemePack() {
        return settingThemePack;
    }

    public ColorThemePack getColorThemePack() {
        return colorThemePack;
    }
}

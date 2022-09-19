package com.wanghao.ui.theme;

import java.awt.*;

/**
 * 颜色主题包
 */
public class ColorThemePack {
    /**
     *  默认颜色
     * */
    private Color color;

    /**
     * 激活后颜色
     * */
    private Color colorEnable;

    public ColorThemePack(Color color, Color colorEnable) {
        this.color = color;
        this.colorEnable = colorEnable;
    }

    public Color getColor() {
        return color;
    }

    public Color getColorEnable() {
        return colorEnable;
    }
}

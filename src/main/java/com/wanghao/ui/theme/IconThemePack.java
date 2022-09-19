package com.wanghao.ui.theme;

import javax.swing.*;

/**
 * 按钮主题包
 * */
public class IconThemePack {
    /**
     * 默认图标
     * */
    private ImageIcon icon;

    /**
     * 激活后图标
     * */
    private ImageIcon iconEnable;

    public IconThemePack(ImageIcon icon, ImageIcon iconEnable) {
        this.icon = icon;
        this.iconEnable = iconEnable;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public ImageIcon getIconEnable() {
        return iconEnable;
    }
}

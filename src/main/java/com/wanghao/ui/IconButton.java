package com.wanghao.ui;

import javax.swing.*;
import java.awt.*;

/**
 * @author wanghao
 * @description 自定义图片按钮
 */
public class IconButton extends JButton {
    // 默认
    private ImageIcon defaultIcon;
    // 点击后
    private ImageIcon firedIcon;
    // 禁用后
    private ImageIcon disabledIcon;
    // 提示
    private String tip;

    public IconButton(ImageIcon defaultIcon, ImageIcon firedIcon, ImageIcon disabledIcon, String tip) {
        super(defaultIcon);
        this.defaultIcon = defaultIcon;
        this.firedIcon = firedIcon;
        this.disabledIcon = disabledIcon;
        this.tip = tip;

        init();
        setUp();
    }

    /**
     * 初始化，设置按钮属性：无边，无焦点渲染，无内容区，各边距0
     */
    private void init() {
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setContentAreaFilled(false);
        this.setFocusable(true);
        this.setMargin(new Insets(0, 0, 0, 0));
    }

    /**
     * 设置按钮图标：鼠标移过、按压、失效的图标 和设置按钮提示
     */
    private void setUp() {
        this.setRolloverIcon(firedIcon);
        this.setPressedIcon(firedIcon);
        this.setDisabledIcon(disabledIcon);

        if (!"".equals(tip)) {
            this.setToolTipText(tip);
        }
    }

    public void changeIcons(ImageIcon defaultIcon, ImageIcon firedIcon, ImageIcon disabledIcon) {
        if (defaultIcon != null) {
            this.setIcon(defaultIcon);
            this.defaultIcon = defaultIcon;
        }
        if (firedIcon != null) {
            this.firedIcon = firedIcon;
        }
        if (disabledIcon != null) {
            this.disabledIcon = disabledIcon;
        }
        setUp();
    }
}

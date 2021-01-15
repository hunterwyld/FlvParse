package com.wanghao.ui;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

/**
 * @author wanghao
 * @description 纯色按钮
 */
public class ColorButton extends JButton {
    private String theme;
    private Color color;

    public ColorButton(Color color, String theme) {
        this.theme = theme;
        this.color = color;

        this.setUI(new BasicButtonUI());

        this.setText("       ");
        this.setBackground(color);
        this.setForeground(UIConstant.MAIN_BACK_COLOR);
        this.setFont(UIConstant.FONT_TITLE);

        this.setBorderPainted(true);
        this.setFocusPainted(true);
        this.setContentAreaFilled(true);
        this.setFocusable(true);
        this.setRolloverEnabled(true);
        this.setMargin(new Insets(0, 0, 0, 0));
    }

    public String getTheme() {
        return theme;
    }

    public Color getColor() {
        return color;
    }
}

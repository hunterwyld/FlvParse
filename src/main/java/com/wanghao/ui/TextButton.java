package com.wanghao.ui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;

/**
 * @author wanghao
 * @description 自定义文字按钮
 */
public class TextButton extends JButton {

    public TextButton(String text) {
        super(text);

        this.setBorderPainted(true);
        this.setFocusPainted(true);
        this.setContentAreaFilled(true);
        this.setFocusable(true);
        this.setRolloverEnabled(true);
        this.setMargin(new Insets(0, 0, 0, 0));

        Color color = UIConstant.DEFAULT_THEME_COLOR;
        Border border = new BasicBorders.ButtonBorder(color, color, color, color);
        this.setBorder(border);

        this.setBackground(UIConstant.MAIN_BACK_COLOR);
        this.setForeground(color);
        this.setFont(UIConstant.FONT_BUTTON);
    }
}

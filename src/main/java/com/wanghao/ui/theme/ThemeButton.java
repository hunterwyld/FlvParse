package com.wanghao.ui.theme;

import com.wanghao.ui.UIConstant;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

/**
 * @author wanghao
 * @description 用来选择主题的按钮，按钮颜色与主题颜色相同
 */
public class ThemeButton extends JButton {
    private ThemePack theme;
    private Color color;

    public ThemeButton(ThemePack theme) {
        this.theme = theme;
        this.color = theme.getColorThemePack().getColor();

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

    public ThemePack getTheme() {
        return theme;
    }

    public String getThemeName() {
        return theme.getName();
    }

    public Color getColor() {
        return color;
    }
}

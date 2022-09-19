package com.wanghao.ui;

import com.wanghao.App;
import com.wanghao.biz.err.BusinessException;
import com.wanghao.ui.theme.ThemeButton;
import com.wanghao.ui.theme.ThemeHelper;
import com.wanghao.ui.theme.ThemePack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wanghao
 * @description 设置主题面板
 */
public class SettingThemePanel extends JPanel {

    /**
     * 用来选择主题的按钮
     * */
    private static final List<ThemeButton> chooseThemeButtons = new ArrayList<>();

    public SettingThemePanel() {
        init();
        addComponent();
        addListener();
    }


    /**
     * 初始化
     */
    private void init() {
        this.setBackground(UIConstant.MAIN_BACK_COLOR);
        this.setLayout(new BorderLayout());
    }

    /**
     * 添加组件
     */
    private void addComponent() {
        this.add(getCenterPanel(), BorderLayout.CENTER);
    }

    /**
     * 中部面板
     */
    private JPanel getCenterPanel() {
        // 中间面板
        JPanel panelCenter = new JPanel();
        panelCenter.setBackground(UIConstant.MAIN_BACK_COLOR);
        panelCenter.setLayout(new FlowLayout(FlowLayout.CENTER, 20*UIConstant.MAIN_H_GAP, UIConstant.MAIN_WINDOW_Y/2));

        List<ThemePack> allThemes = ThemeHelper.getAllThemes();
        if (allThemes == null || allThemes.size() == 0) {
            throw new BusinessException("no theme pack found");
        }

        for (ThemePack themePack : allThemes) {
            ThemeButton themeButton = new ThemeButton(themePack);
            chooseThemeButtons.add(themeButton);
            panelCenter.add(themeButton);
        }

        return panelCenter;
    }

    /**
     * 为相关组件添加事件监听
     */
    private void addListener() {
        for (final ThemeButton themeButton: chooseThemeButtons) {
            themeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    changeTheme(themeButton.getTheme());
                }
            });
        }
    }

    private void changeTheme(ThemePack targetTheme) {
        ThemePack curTheme = ThemeHelper.changeTheme(targetTheme);
//        UIConstant.DEFAULT_THEME_COLOR = curTheme.getColorThemePack().getColor();
//        UIConstant.DEFAULT_THEME_COLOR_ENABLE = UIConstant.themeMap.get(curTheme.getName());
//        UIConstant.ICON_ANALYZE = UIConstant.analyzeIconMap.get(curTheme.getName());
//        UIConstant.ICON_ANALYZE_ENABLE = UIConstant.analyzeIconEnableMap.get(curTheme.getName());
//        UIConstant.ICON_SETTING = UIConstant.settingIconMap.get(curTheme.getName());
//        UIConstant.ICON_SETTING_ENABLE = UIConstant.settingIconEnableMap.get(curTheme.getName());

        App.analyzePanel.changeTheme(curTheme);
        App.settingPanel.changeTheme(curTheme);
        App.toolBarPanel.changeTheme(curTheme);
    }
}
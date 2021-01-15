package com.wanghao.ui;

import com.wanghao.App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author wanghao
 * @description 设置主题面板
 */
public class SettingPanelTheme extends JPanel {

    private static ColorButton curTheme;
    private static ColorButton blueTheme;
    private static ColorButton greenTheme;
    private static ColorButton yellowTheme;
    private static ColorButton redTheme;

    public SettingPanelTheme() {
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

        blueTheme = new ColorButton(UIConstant.BLUE_THEME_COLOR, "blue");
        greenTheme = new ColorButton(UIConstant.GREEN_THEME_COLOR, "green");
        yellowTheme = new ColorButton(UIConstant.YELLOW_THEME_COLOR, "yellow");
        redTheme = new ColorButton(UIConstant.RED_THEME_COLOR, "red");

        panelCenter.add(blueTheme);
        panelCenter.add(greenTheme);
        panelCenter.add(yellowTheme);
        panelCenter.add(redTheme);

        curTheme = blueTheme;
        return panelCenter;
    }

    /**
     * 为相关组件添加事件监听
     */
    private void addListener() {
        blueTheme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeTheme(blueTheme);
            }
        });
        greenTheme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeTheme(greenTheme);
            }
        });
        yellowTheme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeTheme(yellowTheme);
            }
        });
        redTheme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeTheme(redTheme);
            }
        });
    }

    private void changeTheme(ColorButton targetTheme) {
        curTheme = targetTheme;
        UIConstant.DEFAULT_THEME_COLOR = curTheme.getColor();
        UIConstant.DEFAULT_THEME_COLOR_ENABLE = UIConstant.themeMap.get(curTheme.getTheme());
        UIConstant.ICON_ANALYZE = UIConstant.analyzeIconMap.get(curTheme.getTheme());
        UIConstant.ICON_ANALYZE_ENABLE = UIConstant.analyzeIconEnableMap.get(curTheme.getTheme());
        UIConstant.ICON_SETTING = UIConstant.settingIconMap.get(curTheme.getTheme());
        UIConstant.ICON_SETTING_ENABLE = UIConstant.settingIconEnableMap.get(curTheme.getTheme());

        App.analyzePanel.changeTheme(curTheme);
        App.settingPanel.changeTheme(curTheme);
        App.toolBarPanel.changeTheme(curTheme);
    }
}
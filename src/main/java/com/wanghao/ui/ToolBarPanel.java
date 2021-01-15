package com.wanghao.ui;

import com.wanghao.App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author wanghao
 * @description 工具栏面板
 */
public class ToolBarPanel extends JPanel implements ThemeChangeable {
    private static IconButton buttonAnalyze;
    private static IconButton buttonSetting;

    private static JPanel panelUp;
    private static JPanel panelDown;

    public ToolBarPanel() {
        init();
        addButton();
        addListener();
    }

    /**
     * 初始化
     */
    private void init() {
        Dimension preferredSize = new Dimension(48, UIConstant.MAIN_WINDOW_HEIGHT);
        this.setPreferredSize(preferredSize);
        this.setMaximumSize(preferredSize);
        this.setMinimumSize(preferredSize);
        this.setBackground(UIConstant.DEFAULT_THEME_COLOR);
        this.setLayout(new GridLayout(2, 1));
    }

    /**
     * 添加工具按钮
     */
    private void addButton() {

        panelUp = new JPanel();
        panelUp.setBackground(UIConstant.DEFAULT_THEME_COLOR);
        panelUp.setLayout(new FlowLayout(FlowLayout.CENTER, -2, -4));
        panelDown = new JPanel();
        panelDown.setBackground(UIConstant.DEFAULT_THEME_COLOR);
        panelDown.setLayout(new BorderLayout(0, 0));

        buttonAnalyze = new IconButton(UIConstant.ICON_ANALYZE, UIConstant.ICON_ANALYZE_ENABLE,
                UIConstant.ICON_ANALYZE, "FlvParse");
        buttonSetting = new IconButton(UIConstant.ICON_SETTING, UIConstant.ICON_SETTING_ENABLE,
                UIConstant.ICON_SETTING, "Settings");

        panelUp.add(buttonAnalyze);

        panelDown.add(buttonSetting, BorderLayout.SOUTH);
        this.add(panelUp);
        this.add(panelDown);
    }

    /**
     * 为各按钮添加事件动作监听
     */
    private void addListener() {

        buttonAnalyze.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonAnalyze.setIcon(UIConstant.ICON_ANALYZE_ENABLE);
                buttonSetting.setIcon(UIConstant.ICON_SETTING);

                App.mainPanelCenter.removeAll();
                App.mainPanelCenter.add(App.analyzePanel, BorderLayout.CENTER);
                App.mainPanelCenter.updateUI();
            }
        });

        buttonSetting.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonAnalyze.setIcon(UIConstant.ICON_ANALYZE);
                buttonSetting.setIcon(UIConstant.ICON_SETTING_ENABLE);

                App.mainPanelCenter.removeAll();
                App.mainPanelCenter.add(App.settingPanel, BorderLayout.CENTER);
                App.mainPanelCenter.updateUI();
            }
        });
    }

    @Override
    public void changeTheme(ColorButton theme) {
        if (theme == null) {
            return;
        }
        this.setBackground(theme.getColor());
        panelUp.setBackground(theme.getColor());
        panelDown.setBackground(theme.getColor());
        buttonAnalyze.changeIcons(UIConstant.ICON_ANALYZE, UIConstant.ICON_ANALYZE_ENABLE,
                UIConstant.ICON_ANALYZE);
        buttonSetting.changeIcons(UIConstant.ICON_SETTING, UIConstant.ICON_SETTING_ENABLE,
                UIConstant.ICON_SETTING);
        this.updateUI();
    }
}

package com.wanghao.ui;

import com.wanghao.App;
import com.wanghao.ui.theme.ThemeChangeable;
import com.wanghao.ui.theme.ThemeHelper;
import com.wanghao.ui.theme.ThemePack;

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
        this.setBackground(ThemeHelper.getCurTheme().getColorThemePack().getColor());
        this.setLayout(new GridLayout(2, 1));
    }

    /**
     * 添加工具按钮
     */
    private void addButton() {
        ThemePack curTheme = ThemeHelper.getCurTheme();

        panelUp = new JPanel();
        panelUp.setBackground(curTheme.getColorThemePack().getColor());
        panelUp.setLayout(new FlowLayout(FlowLayout.CENTER, -2, -4));
        panelDown = new JPanel();
        panelDown.setBackground(curTheme.getColorThemePack().getColor());
        panelDown.setLayout(new BorderLayout(0, 0));

        buttonAnalyze = new IconButton(curTheme.getAnalyzeThemePack().getIcon(),
                curTheme.getAnalyzeThemePack().getIconEnable(),
                curTheme.getAnalyzeThemePack().getIcon(),
                "FlvParse");
        buttonSetting = new IconButton(curTheme.getSettingThemePack().getIcon(),
                curTheme.getSettingThemePack().getIconEnable(),
                curTheme.getSettingThemePack().getIcon(),
                "Settings");

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
                ThemePack curTheme = ThemeHelper.getCurTheme();
                buttonAnalyze.setIcon(curTheme.getAnalyzeThemePack().getIconEnable());
                buttonSetting.setIcon(curTheme.getSettingThemePack().getIcon());

                App.mainPanelCenter.removeAll();
                App.mainPanelCenter.add(App.analyzePanel, BorderLayout.CENTER);
                App.mainPanelCenter.updateUI();
            }
        });

        buttonSetting.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ThemePack curTheme = ThemeHelper.getCurTheme();
                buttonAnalyze.setIcon(curTheme.getAnalyzeThemePack().getIcon());
                buttonSetting.setIcon(curTheme.getSettingThemePack().getIconEnable());

                App.mainPanelCenter.removeAll();
                App.mainPanelCenter.add(App.settingPanel, BorderLayout.CENTER);
                App.mainPanelCenter.updateUI();
            }
        });
    }

    @Override
    public void changeTheme(ThemePack theme) {
        if (theme == null) {
            return;
        }
        this.setBackground(theme.getColorThemePack().getColor());
        panelUp.setBackground(theme.getColorThemePack().getColor());
        panelDown.setBackground(theme.getColorThemePack().getColor());
        buttonAnalyze.changeIcons(theme.getAnalyzeThemePack().getIcon(),
                theme.getAnalyzeThemePack().getIconEnable(),
                theme.getAnalyzeThemePack().getIcon());
        buttonSetting.changeIcons(theme.getSettingThemePack().getIcon(),
                theme.getSettingThemePack().getIconEnable(),
                theme.getSettingThemePack().getIcon());
        this.updateUI();
    }
}

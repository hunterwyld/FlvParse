package com.wanghao.ui;

import com.wanghao.App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author wanghao
 * @description 设置面板
 */
public class SettingPanel extends JPanel implements ThemeChangeable {
    private static JLabel labelTitle;
    private static JPanel panelTheme;
    private static JPanel panelAbout;
    private static JPanel settingPanelMain;
    private static JPanel settingPanelTheme;
    private static JPanel settingPanelAbout;

    /**
     * 构造
     */
    public SettingPanel() {
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
        this.add(getUpPanel(), BorderLayout.NORTH);
        this.add(getCenterPanel(), BorderLayout.CENTER);
    }

    /**
     * 上部面板
     */
    private JPanel getUpPanel() {
        JPanel panelUp = new JPanel();
        panelUp.setBackground(UIConstant.MAIN_BACK_COLOR);
        panelUp.setLayout(new FlowLayout(FlowLayout.LEFT, UIConstant.MAIN_H_GAP, 5));

        labelTitle = new JLabel("Settings");
        labelTitle.setFont(UIConstant.FONT_TITLE);
        labelTitle.setForeground(UIConstant.DEFAULT_THEME_COLOR);
        panelUp.add(labelTitle);

        return panelUp;
    }

    /**
     * 中部面板
     */
    private JPanel getCenterPanel() {
        // 中间面板
        JPanel panelCenter = new JPanel();
        panelCenter.setBackground(UIConstant.MAIN_BACK_COLOR);
        panelCenter.setLayout(new BorderLayout());

        // 列表Panel
        JPanel panelList = new JPanel();
        Dimension preferredSize = new Dimension(245, UIConstant.MAIN_WINDOW_HEIGHT);
        panelList.setPreferredSize(preferredSize);
        panelList.setBackground(new Color(62, 62, 62));
        panelList.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        panelTheme = new JPanel();
        panelTheme.setBackground(UIConstant.DEFAULT_THEME_COLOR);
        panelTheme.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 13));
        Dimension preferredSizeListItem = new Dimension(245, 48);
        panelTheme.setPreferredSize(preferredSizeListItem);
        panelAbout = new JPanel();
        panelAbout.setBackground(UIConstant.DEFAULT_THEME_COLOR_ENABLE);
        panelAbout.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 13));
        panelAbout.setPreferredSize(preferredSizeListItem);

        JLabel labelOption = new JLabel("Theme");
        JLabel labelAbout = new JLabel("About");
        //Font fontListItem = new Font(PropertyUtil.getProperty("ds.ui.font.family"), 0, 15);
        //labelOption.setFont(fontListItem);
        //labelAbout.setFont(fontListItem);
        labelOption.setForeground(UIConstant.MAIN_BACK_COLOR);
        labelAbout.setForeground(UIConstant.MAIN_BACK_COLOR);
        panelTheme.add(labelOption);
        panelAbout.add(labelAbout);

        panelList.add(panelTheme);
        panelList.add(panelAbout);


        settingPanelTheme = new SettingPanelTheme();
        settingPanelAbout = new SettingPanelAbout();
        // 设置Panel
        settingPanelMain = new JPanel();
        settingPanelMain.setBackground(UIConstant.MAIN_BACK_COLOR);
        settingPanelMain.setLayout(new BorderLayout());
        settingPanelMain.add(settingPanelTheme);
        settingPanelMain.add(settingPanelAbout);

        panelCenter.add(panelList, BorderLayout.WEST);
        panelCenter.add(settingPanelMain, BorderLayout.CENTER);

        return panelCenter;
    }

    /**
     * 为相关组件添加事件监听
     */
    private void addListener() {
        panelTheme.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseClicked(MouseEvent e) {
                panelTheme.setBackground(UIConstant.DEFAULT_THEME_COLOR_ENABLE);
                panelAbout.setBackground(UIConstant.DEFAULT_THEME_COLOR);

                settingPanelMain.removeAll();
                settingPanelMain.add(settingPanelTheme);
                App.settingPanel.updateUI();
            }
        });

        panelAbout.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseClicked(MouseEvent e) {
                panelAbout.setBackground(UIConstant.DEFAULT_THEME_COLOR_ENABLE);
                panelTheme.setBackground(UIConstant.DEFAULT_THEME_COLOR);

                settingPanelMain.removeAll();
                settingPanelMain.add(settingPanelAbout);
                App.settingPanel.updateUI();
            }
        });
    }

    @Override
    public void changeTheme(ColorButton theme) {
        if (theme == null) {
            return;
        }
        labelTitle.setForeground(theme.getColor());
        panelTheme.setBackground(UIConstant.themeMap.get(theme.getTheme()));
        panelAbout.setBackground(theme.getColor());
        this.updateUI();
    }
}

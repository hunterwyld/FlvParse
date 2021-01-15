package com.wanghao.ui;

import javax.swing.*;
import java.awt.*;

/**
 * @author wanghao
 * @description About面板
 */
public class SettingPanelAbout extends JPanel {
    public SettingPanelAbout() {
        init();
        addComponent();
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
        this.add(getDownPanel(), BorderLayout.SOUTH);
    }

    /**
     * 中部面板
     */
    private JPanel getCenterPanel() {
        // 中间面板
        JPanel panelCenter = new JPanel();
        panelCenter.setBackground(UIConstant.MAIN_BACK_COLOR);
        panelCenter.setLayout(new GridLayout(3, 1));

        // 图标、版本Grid
        JPanel panelGridIcon = new JPanel();
        panelGridIcon.setBackground(UIConstant.MAIN_BACK_COLOR);
        panelGridIcon.setLayout(new FlowLayout(FlowLayout.LEFT, UIConstant.MAIN_H_GAP, 0));

        // 初始化组件
        IconButton icon = new IconButton(UIConstant.ICON_APP_MAIN, UIConstant.ICON_APP_MAIN,
                UIConstant.ICON_APP_MAIN, "");
        JLabel labelName = new JLabel(UIConstant.APP_NAME);
        JLabel labelVersion = new JLabel(UIConstant.APP_VERSION);

        // 字体
        labelName.setFont(UIConstant.FONT_NORMAL);
        labelVersion.setFont(UIConstant.FONT_NORMAL);

        // 大小
        Dimension size = new Dimension(200, 30);
        labelName.setPreferredSize(size);
        labelVersion.setPreferredSize(size);

        // 组合元素
        panelGridIcon.add(icon);
        panelGridIcon.add(labelName);
        panelGridIcon.add(labelVersion);

        // 建议帮助 Grid
        JPanel panelGridHelp = new JPanel();
        panelGridHelp.setBackground(UIConstant.MAIN_BACK_COLOR);
        panelGridHelp.setLayout(new FlowLayout(FlowLayout.LEFT, UIConstant.MAIN_H_GAP, 0));

        panelCenter.add(panelGridIcon);
        return panelCenter;
    }

    /**
     * 底部面板
     */
    private JPanel getDownPanel() {
        JPanel panelDown = new JPanel();
        panelDown.setBackground(UIConstant.MAIN_BACK_COLOR);
        panelDown.setLayout(new FlowLayout(FlowLayout.LEFT, UIConstant.MAIN_H_GAP, 15));

        JLabel labelInfo = new JLabel(UIConstant.HOME_PAGE);
        labelInfo.setFont(UIConstant.FONT_NORMAL);
        labelInfo.setForeground(Color.gray);

        panelDown.add(labelInfo);

        return panelDown;
    }

}

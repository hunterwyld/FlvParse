package com.wanghao;

import com.wanghao.ui.AnalyzePanel;
import com.wanghao.ui.SettingPanel;
import com.wanghao.ui.ToolBarPanel;
import com.wanghao.ui.UIConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;

/**
 * @author wanghao
 */
public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    private final JFrame frame;

    public static JPanel mainPanelCenter;
    public static AnalyzePanel analyzePanel;
    public static SettingPanel settingPanel;
    public static ToolBarPanel toolBarPanel;

    public App() {
        logger.info("================== App starting ==================");
        // 设置系统默认样式
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        // 初始化主窗口
        frame = new JFrame();
        frame.setBounds(UIConstant.MAIN_WINDOW_X, UIConstant.MAIN_WINDOW_Y, UIConstant.MAIN_WINDOW_WIDTH,
                UIConstant.MAIN_WINDOW_HEIGHT);
        frame.setTitle(UIConstant.APP_NAME);
        frame.setIconImage(UIConstant.IMAGE_ICON);
        frame.setBackground(UIConstant.MAIN_BACK_COLOR);

        JPanel mainPanel = new JPanel(true);
        mainPanel.setBackground(UIConstant.MAIN_BACK_COLOR);
        mainPanel.setLayout(new BorderLayout());

        toolBarPanel = new ToolBarPanel();
        mainPanel.add(toolBarPanel, BorderLayout.WEST);

        analyzePanel = new AnalyzePanel();
        settingPanel = new SettingPanel();
        mainPanelCenter = new JPanel(true);
        mainPanelCenter.setLayout(new BorderLayout());
        mainPanelCenter.add(analyzePanel, BorderLayout.CENTER);

        mainPanel.add(mainPanelCenter, BorderLayout.CENTER);

        frame.add(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        logger.info("================== App started ==================");
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    App window = new App();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

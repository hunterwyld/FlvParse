package com.wanghao.ui;

import com.wanghao.App;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wanghao
 */
public class UIConstant {
    /**
     * 软件名称,版本,主页
     */
    public final static String APP_NAME = "FlvParse";
    public final static String APP_VERSION = "v1.0.0";
    public final static String HOME_PAGE = "https://github.com/hunterwyld";

    /**
     * 主窗口图标
     */
    public final static Image IMAGE_ICON = Toolkit.getDefaultToolkit().getImage(App.class.getResource("/icon/main.png"));

    /**
     * 主窗口大小
     */
    public final static int MAIN_WINDOW_X = 240;
    public final static int MAIN_WINDOW_Y = 100;
    public final static int MAIN_WINDOW_WIDTH = 1422;
    public final static int MAIN_WINDOW_HEIGHT = 800;

    /**
     * 主窗口背景色
     */
    public final static Color MAIN_BACK_COLOR = Color.WHITE;

    /**
     * 主图标
     */
    public final static ImageIcon ICON_APP_MAIN = new ImageIcon(
            App.class.getResource("/icon/main.png"));
    // 工具栏图标
    /**
     * 分析 默认
     */
    public final static ImageIcon BLUE_ICON_ANALYZE = new ImageIcon(
            App.class.getResource("/icon/blue_analyze.png"));
    public final static ImageIcon GREEN_ICON_ANALYZE = new ImageIcon(
            App.class.getResource("/icon/green_analyze.png"));
    public final static ImageIcon YELLOW_ICON_ANALYZE = new ImageIcon(
            App.class.getResource("/icon/yellow_analyze.png"));
    public final static ImageIcon RED_ICON_ANALYZE = new ImageIcon(
            App.class.getResource("/icon/red_analyze.png"));
    public static ImageIcon ICON_ANALYZE = BLUE_ICON_ANALYZE;
    /**
     * 分析 激活
     */
    public final static ImageIcon BLUE_ICON_ANALYZE_ENABLE = new ImageIcon(
            App.class.getResource("/icon/blue_analyzeEnable.png"));
    public final static ImageIcon GREEN_ICON_ANALYZE_ENABLE = new ImageIcon(
            App.class.getResource("/icon/green_analyzeEnable.png"));
    public final static ImageIcon YELLOW_ICON_ANALYZE_ENABLE = new ImageIcon(
            App.class.getResource("/icon/yellow_analyzeEnable.png"));
    public final static ImageIcon RED_ICON_ANALYZE_ENABLE = new ImageIcon(
            App.class.getResource("/icon/red_analyzeEnable.png"));
    public static ImageIcon ICON_ANALYZE_ENABLE = BLUE_ICON_ANALYZE_ENABLE;
    /**
     * 设置 默认
     */
    public final static ImageIcon BLUE_ICON_SETTING = new ImageIcon(
            App.class.getResource("/icon/blue_setting.png"));
    public final static ImageIcon GREEN_ICON_SETTING = new ImageIcon(
            App.class.getResource("/icon/green_setting.png"));
    public final static ImageIcon YELLOW_ICON_SETTING = new ImageIcon(
            App.class.getResource("/icon/yellow_setting.png"));
    public final static ImageIcon RED_ICON_SETTING = new ImageIcon(
            App.class.getResource("/icon/red_setting.png"));
    public static ImageIcon ICON_SETTING = BLUE_ICON_SETTING;
    /**
     * 设置 激活
     */
    public final static ImageIcon BLUE_ICON_SETTING_ENABLE = new ImageIcon(
            App.class.getResource("/icon/blue_settingEnable.png"));
    public final static ImageIcon GREEN_ICON_SETTING_ENABLE = new ImageIcon(
            App.class.getResource("/icon/green_settingEnable.png"));
    public final static ImageIcon YELLOW_ICON_SETTING_ENABLE = new ImageIcon(
            App.class.getResource("/icon/yellow_settingEnable.png"));
    public final static ImageIcon RED_ICON_SETTING_ENABLE = new ImageIcon(
            App.class.getResource("/icon/red_settingEnable.png"));
    public static ImageIcon ICON_SETTING_ENABLE = BLUE_ICON_SETTING_ENABLE;

    // 样式布局相关
    /**
     * 主面板水平间隔
     */
    public final static int MAIN_H_GAP = 25;

    // 字体
    /**
     * 标题字体
     */
    public final static Font FONT_TITLE = new Font(null, Font.PLAIN, 27);
    /**
     * 普通字体
     */
    public final static Font FONT_NORMAL = new Font(null, Font.PLAIN, 13);
    /**
     * button字体
     */
    public final static Font FONT_BUTTON = new Font(null, Font.PLAIN, 17);
    /**
     * 表头字体
     */
    public final static Font FONT_TABLE_HEAD = new Font(null, Font.BOLD, 13);

    // border
    public final static Border LINE_BORDER = new LineBorder(Color.lightGray, 1, true);


    // 主题
    /**
     * 主题色
     */
    public final static Color BLUE_THEME_COLOR = new Color(54,128,243);
    public final static Color BLUE_THEME_COLOR_ENABLE = new Color(48,119,180);

    public final static Color GREEN_THEME_COLOR = new Color(37,174,96);
    public final static Color GREEN_THEME_COLOR_ENABLE = new Color(69,186,121);

    public final static Color YELLOW_THEME_COLOR = new Color(238,132,78);
    public final static Color YELLOW_THEME_COLOR_ENABLE = new Color(236,143,119);

    public final static Color RED_THEME_COLOR = new Color(218,58,48);
    public final static Color RED_THEME_COLOR_ENABLE = new Color(222,95,83);

    public static Color DEFAULT_THEME_COLOR = BLUE_THEME_COLOR;
    public static Color DEFAULT_THEME_COLOR_ENABLE = BLUE_THEME_COLOR_ENABLE;

    public static final Map<String, Color> themeMap = new HashMap<>(4);
    public static final Map<String, ImageIcon> analyzeIconMap = new HashMap<>(4);
    public static final Map<String, ImageIcon> analyzeIconEnableMap = new HashMap<>(4);
    public static final Map<String, ImageIcon> settingIconMap = new HashMap<>(4);
    public static final Map<String, ImageIcon> settingIconEnableMap = new HashMap<>(4);
    static {
        themeMap.put("blue", BLUE_THEME_COLOR_ENABLE);
        themeMap.put("green", GREEN_THEME_COLOR_ENABLE);
        themeMap.put("yellow", YELLOW_THEME_COLOR_ENABLE);
        themeMap.put("red", RED_THEME_COLOR_ENABLE);

        analyzeIconMap.put("blue", BLUE_ICON_ANALYZE);
        analyzeIconMap.put("green", GREEN_ICON_ANALYZE);
        analyzeIconMap.put("yellow", YELLOW_ICON_ANALYZE);
        analyzeIconMap.put("red", RED_ICON_ANALYZE);
        analyzeIconEnableMap.put("blue", BLUE_ICON_ANALYZE_ENABLE);
        analyzeIconEnableMap.put("green", GREEN_ICON_ANALYZE_ENABLE);
        analyzeIconEnableMap.put("yellow", YELLOW_ICON_ANALYZE_ENABLE);
        analyzeIconEnableMap.put("red", RED_ICON_ANALYZE_ENABLE);

        settingIconMap.put("blue", BLUE_ICON_SETTING);
        settingIconMap.put("green", GREEN_ICON_SETTING);
        settingIconMap.put("yellow", YELLOW_ICON_SETTING);
        settingIconMap.put("red", RED_ICON_SETTING);
        settingIconEnableMap.put("blue", BLUE_ICON_SETTING_ENABLE);
        settingIconEnableMap.put("green", GREEN_ICON_SETTING_ENABLE);
        settingIconEnableMap.put("yellow", YELLOW_ICON_SETTING_ENABLE);
        settingIconEnableMap.put("red", RED_ICON_SETTING_ENABLE);
    }
}

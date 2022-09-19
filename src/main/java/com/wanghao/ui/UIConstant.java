package com.wanghao.ui;

import com.wanghao.App;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Objects;

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
            Objects.requireNonNull(App.class.getResource("/icon/main.png")));


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

}

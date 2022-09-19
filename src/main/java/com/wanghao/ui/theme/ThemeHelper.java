package com.wanghao.ui.theme;

import com.alibaba.fastjson2.JSONObject;
import com.wanghao.App;
import com.wanghao.biz.err.BusinessException;
import com.wanghao.biz.util.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

/**
 * 主题相关
 * */
public class ThemeHelper {

    private static final Logger logger = LoggerFactory.getLogger(ThemeHelper.class);

    private static ThemePack DEFAULT_THEME;

    private static final List<ThemePack> ALL_THEMES = new ArrayList<>();

    private static ThemePack CUR_THEME = null;

    public static List<ThemePack> getAllThemes() {
        return ALL_THEMES;
    }

    public static ThemePack getCurTheme() {
        if (CUR_THEME != null) {
            return CUR_THEME;
        }

        // 从db.json读取themeName
        try {
            File dbFile = new File(Objects.requireNonNull(App.class.getResource("/db.json")).getFile());
            JSONObject db = readFromDB(dbFile);
            String themeName = db.getString("theme");

            ThemePack targetTheme = DEFAULT_THEME;
            for (ThemePack themePack : ALL_THEMES) {
                if (themePack.getName().equals(themeName)) {
                    targetTheme = themePack;
                    break;
                }
            }

            return changeTheme(targetTheme);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    public static ThemePack changeTheme(ThemePack targetTheme) {
        // 把themeName写入文件db.json
        try {
            File dbFile = new File(Objects.requireNonNull(App.class.getResource("/db.json")).getFile());
            JSONObject db = readFromDB(dbFile);
            db.put("theme", targetTheme.getName());
            writeToDB(db, dbFile);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }

        CUR_THEME = targetTheme;
        return CUR_THEME;
    }

    /**
     * 主题初始化
     * */
    static {
        init();
    }

    private static void init() {
        // 读取theme目录下的资源，加载所有的主题包
        String filePath = Objects.requireNonNull(App.class.getResource("/theme")).getFile();
        File themeDir = new File(filePath);
        if (!themeDir.exists() || !themeDir.isDirectory()) {
            throw new BusinessException("no theme pack found");
        }

        File[] themes = themeDir.listFiles();
        if (themes == null || themes.length == 0) {
            throw new BusinessException("no theme pack found");
        }
        for (File theme : themes) {
            String themeName = theme.getName();
            logger.info("init theme:{}", themeName);
            ImageIcon ICON_ANALYZE = null;
            ImageIcon ICON_ANALYZE_ENABLE = null;
            ImageIcon ICON_SETTING = null;
            ImageIcon ICON_SETTING_ENABLE = null;
            Color THEME_COLOR = null;
            Color THEME_COLOR_ENABLE = null;

            for (File f : Objects.requireNonNull(theme.listFiles())) {
                if (f.getName().startsWith("analyze")) {
                    if (f.getName().contains("enable")) {
                        ICON_ANALYZE_ENABLE = new ImageIcon(f.getAbsolutePath());
                    } else {
                        ICON_ANALYZE = new ImageIcon(f.getAbsolutePath());
                    }
                } else if (f.getName().startsWith("setting")) {
                    if (f.getName().contains("enable")) {
                        ICON_SETTING_ENABLE = new ImageIcon(f.getAbsolutePath());
                    } else {
                        ICON_SETTING = new ImageIcon(f.getAbsolutePath());
                    }
                } else if (f.getName().startsWith("color")) {
                    try {
                        String colorJson = FileUtils.readFileToString(f);
                        JSONObject jsonObject = JSONObject.parseObject(colorJson);
                        String[] rgb = jsonObject.getString("color").split(",");
                        THEME_COLOR = new Color(Integer.parseInt(rgb[0]), Integer.parseInt(rgb[1]), Integer.parseInt(rgb[2]));
                        rgb = jsonObject.getString("colorEnable").split(",");
                        THEME_COLOR_ENABLE = new Color(Integer.parseInt(rgb[0]), Integer.parseInt(rgb[1]), Integer.parseInt(rgb[2]));
                    } catch (Exception e) {
                        throw new BusinessException("bad theme pack found");
                    }
                }
            }

            IconThemePack analyzeThemePack = new IconThemePack(ICON_ANALYZE, ICON_ANALYZE_ENABLE);
            IconThemePack settingThemePack = new IconThemePack(ICON_SETTING, ICON_SETTING_ENABLE);
            ColorThemePack colorThemePack = new ColorThemePack(THEME_COLOR, THEME_COLOR_ENABLE);

            ThemePack themePack = new ThemePack(themeName,
                    analyzeThemePack, settingThemePack, colorThemePack);

            ALL_THEMES.add(themePack);
        }

        DEFAULT_THEME = ALL_THEMES.get(0);
    }


    private static JSONObject readFromDB(File dbFile) throws IOException {
        String dbJson = FileUtils.readFileToString(dbFile);
        JSONObject jsonObject = JSONObject.parseObject(dbJson);
        if (jsonObject == null) {
            jsonObject = new JSONObject();
        }
        return jsonObject;
    }

    private static void writeToDB(JSONObject jsonObject, File dbFile) throws IOException {
        FileUtils.writeStringToFile(jsonObject.toJSONString(), dbFile);
    }


}

package com.wanghao.ui;

import com.wanghao.App;
import com.wanghao.biz.FlvParser;
import com.wanghao.biz.err.BusinessException;
import com.wanghao.biz.flv.FlvTag;
import com.wanghao.biz.util.Constant;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import static com.wanghao.biz.FlvParser.*;

/**
 * @author wanghao
 * @description 分析FLV面板
 */
public class AnalyzePanel extends JPanel implements ThemeChangeable {
    private static JLabel labelTitle;
    private static TextButton buttonSelect;
    private static JLabel labelSelect;
    private static TextButton buttonConfirm;

    private static JScrollPane panelCenterLeft;
    private static JScrollPane panelCenterRightUp;
    private static JScrollPane panelCenterRightDown;

    private static DataTable tableLeft;
    private static DataTable tableRightUp;
    private static DataTable tableRightDown;


    public AnalyzePanel() {
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

        labelTitle = new JLabel("FlvParse");
        labelTitle.setFont(UIConstant.FONT_TITLE);
        labelTitle.setForeground(UIConstant.DEFAULT_THEME_COLOR);

        // Flow
        JPanel panelFlow = new JPanel();
        panelFlow.setBackground(UIConstant.MAIN_BACK_COLOR);
        panelFlow.setLayout(new FlowLayout(FlowLayout.CENTER, 2*UIConstant.MAIN_H_GAP, 0));

        buttonSelect = new TextButton("Select");
        labelSelect = new JLabel();
        buttonConfirm = new TextButton("Start");
        panelFlow.add(buttonSelect);
        panelFlow.add(labelSelect);
        panelFlow.add(buttonConfirm);

        panelUp.add(labelTitle);
        panelUp.add(panelFlow);
        return panelUp;
    }

    /**
     * 中部面板
     */
    private JPanel getCenterPanel() {
        JPanel panelCenter = new JPanel();
        panelCenter.setBackground(UIConstant.MAIN_BACK_COLOR);
        panelCenter.setLayout(new GridLayout(1, 2));

        panelCenterLeft = new JScrollPane();
        panelCenterLeft.setBackground(UIConstant.MAIN_BACK_COLOR);
        panelCenterLeft.setBorder(UIConstant.LINE_BORDER);

        JPanel panelCenterRight = new JPanel();
        panelCenterRight.setBackground(UIConstant.MAIN_BACK_COLOR);
        panelCenterRight.setLayout(new GridLayout(2, 1));
        panelCenterRightUp = new JScrollPane();
        panelCenterRightUp.setBackground(UIConstant.MAIN_BACK_COLOR);
        panelCenterRightUp.setBorder(UIConstant.LINE_BORDER);
        panelCenterRightDown = new JScrollPane();
        panelCenterRightDown.setBackground(UIConstant.MAIN_BACK_COLOR);
        panelCenterRightDown.setBorder(UIConstant.LINE_BORDER);
        panelCenterRight.add(panelCenterRightUp);
        panelCenterRight.add(panelCenterRightDown);

        panelCenter.add(panelCenterLeft);
        panelCenter.add(panelCenterRight);

        return panelCenter;
    }

    private void populateData2Left() {
        // 表头（列名）
        String[] columnNames = {"Type", "Info"};

        int numRow = 1 + flvBody.getFlvTagList().size();
        Object[][] data = new Object[numRow][columnNames.length];
        for (int i = 0; i < numRow; i++) {
            for (int j = 0; j < columnNames.length; j++) {
                if (i == 0) {
                    if (j == 0) {
                        data[i][j] = "Header";
                    } else {
                        data[i][j] = flvHeader.getBriefInfo();
                    }
                } else {
                    FlvTag flvTag = flvBody.getFlvTagList().get(i-1);
                    if (j == 0) {
                        data[i][j] = FlvTag.getTagTypeStr(flvTag.getTagType());
                    } else {
                        data[i][j] = flvTag.getBriefInfo();
                    }
                }
            }
        }

        tableLeft = new DataTable(columnNames, data);
        panelCenterLeft.setViewportView(tableLeft);

        tableLeft.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = tableLeft.getSelectedRow();
                if (selectedRow == 0) { // flvHeader
                    populateFlvHeader2RightUp();
                    populateFlvHeader2RightDown();
                } else { // flvTags
                    FlvTag flvTag = flvBody.getFlvTagList().get(selectedRow-1);
                    populateFlvTag2RightUp(flvTag);
                    populateFlvTag2RightDown(flvTag);
                }
            }
        });
    }

    private void populateFlvHeader2RightUp() {
        String[] columnNames = {"Detail", "Value"};
        Object[][] data = flvHeader.getDetailInfo();
        tableRightUp = new DataTable(columnNames, data);
        panelCenterRightUp.setViewportView(tableRightUp);
    }
    private void populateFlvTag2RightUp(FlvTag flvTag) {
        String[] columnNames = {"Detail", "Value"};
        Object[][] data = flvTag.getDetailInfo();
        tableRightUp = new DataTable(columnNames, data);
        panelCenterRightUp.setViewportView(tableRightUp);
    }

    private void populateFlvHeader2RightDown() {
        String[] columnNames = {"No.", "Bytes", "Ascii"};
        Object[][] data = flvHeader.getPrintable(byteBuf);
        tableRightDown = new DataTable(columnNames, data);
        panelCenterRightDown.setViewportView(tableRightDown);
    }
    private void populateFlvTag2RightDown(FlvTag flvTag) {
        String[] columnNames = {"No.", "Bytes", "Ascii"};
        Object[][] data = flvTag.getPrintable(byteBuf);
        tableRightDown = new DataTable(columnNames, data);
        panelCenterRightDown.setViewportView(tableRightDown);
    }

    /**
     * 为各组件添加事件监听
     */
    private void addListener() {
        buttonConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String flvPath = labelSelect.getText();
                if (flvPath == null || "".equals(flvPath)) {
                    JOptionPane.showMessageDialog(App.analyzePanel, "select a file first", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    FlvParser.parse(flvPath);
                    populateData2Left();
                } catch (Exception ex) {
                    if (ex instanceof BusinessException) {
                        // show error dialog
                        JOptionPane.showMessageDialog(App.analyzePanel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        buttonSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fileChooser.setFileFilter(new FileFilter() {
                    @Override
                    public boolean accept(File f) {
                        if (f != null && f.getName().endsWith(Constant.FLV_SUFFIX)) {
                            return true;
                        }
                        return false;
                    }

                    @Override
                    public String getDescription() {
                        return Constant.FLV_SUFFIX;
                    }
                });
                int option = fileChooser.showOpenDialog(App.analyzePanel);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    labelSelect.setText(file.getAbsolutePath());
                } else {
                    labelSelect.setText(null);
                }
            }
        });
    }

    @Override
    public void changeTheme(ColorButton theme) {
        if (theme == null) {
            return;
        }
        labelTitle.setForeground(theme.getColor());
        buttonSelect.setForeground(theme.getColor());
        labelSelect.setForeground(theme.getColor());
        buttonConfirm.setForeground(theme.getColor());
        this.updateUI();
    }
}

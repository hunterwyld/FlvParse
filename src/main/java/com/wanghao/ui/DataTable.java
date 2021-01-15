package com.wanghao.ui;

import javax.swing.*;
import java.awt.*;

/**
 * @author wanghao
 * @description 自定义表格
 */
public class DataTable extends JTable {

    public DataTable(Object[] columnNames, Object[][] data) {
        super(data, columnNames);

        init();
    }

    private void init() {

        // 设置表头
        this.getTableHeader().setFont(UIConstant.FONT_TABLE_HEAD);      // 设置表头名称字体样式
        this.getTableHeader().setForeground(Color.gray);              // 设置表头名称字体颜色
        this.getTableHeader().setResizingAllowed(true);               // 设置允许手动改变列宽
        this.getTableHeader().setReorderingAllowed(false);            // 设置不允许拖动重新排序各列

        // 设置列宽
//        for (int i = 0; i < preferredWidths.length; i++) {
//            this.getColumnModel().getColumn(i).setPreferredWidth(preferredWidths[i]);
//        }
        //this.getColumnModel().getColumn(0).setPreferredWidth(20);
        this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        // 设置滚动面板视口大小（超过该大小的行数据，需要拖动滚动条才能看到）
        this.setPreferredScrollableViewportSize(null);

        this.setRowSelectionAllowed(true);
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //this.setCellSelectionEnabled(false);
//        this.setAlignmentX();
//        this.setAlignmentY();
    }
}

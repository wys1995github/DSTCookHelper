package com.dstch.view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class MainTableCellRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 1L;

	/**
     * 返回默认的表单元格渲染器，此方法在父类中已实现，直接调用父类方法返回，在返回前做相关参数的设置即可
     */
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        // 偶数行背景设置为白色，奇数行背景设置为灰色
    	if (row % 2 == 0) {
            setBackground(Color.WHITE);
        } else {
        	setBackground(Color.LIGHT_GRAY);
        }

        setHorizontalAlignment(SwingConstants.CENTER);

        // 设置提示文本，当鼠标移动到当前(row, column)所在单元格时显示的提示文本
        setToolTipText(table.getColumnName(column) + "：" + table.getValueAt(row, column));
        // 调用父类的该方法完成渲染器的其他设置
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }

}

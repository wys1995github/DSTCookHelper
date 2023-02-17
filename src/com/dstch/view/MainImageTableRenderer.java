package com.dstch.view;

import java.awt.Component;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class MainImageTableRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 1L;

	/**
     * 返回默认的表单元格渲染器，此方法在父类中已实现，直接调用父类方法返回，在返回前做相关参数的设置即可
     */
	@Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		JLabel iconLabel = new JLabel();
		iconLabel.setSize(60, 60);
		if(value != null && value instanceof ImageIcon) {
			ImageIcon imgIcon = (ImageIcon)value;
			iconLabel.setIcon(imgIcon);
			imgIcon.setImage(imgIcon.getImage().
					getScaledInstance(iconLabel.getWidth(), iconLabel.getHeight(), Image.SCALE_DEFAULT));
			iconLabel.setText((String)table.getValueAt(row, 2));
			iconLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			iconLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
		}else {
			iconLabel.setText((String)table.getValueAt(row, 2));
		}
		iconLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		iconLabel.setHorizontalAlignment(JLabel.CENTER);
		iconLabel.setVerticalAlignment(JLabel.CENTER);
		return iconLabel;
    }

}

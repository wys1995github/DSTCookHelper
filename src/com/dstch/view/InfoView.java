package com.dstch.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;

import com.dstch.service.InfoService;
import com.dstch.util.ImageUtil;

public class InfoView extends JFrame {

	private static final long serialVersionUID = 1L;
	private String foodName = new String();
	private JTable infoTable = new JTable();
	private Vector<Vector<Object>> tableData = new Vector<Vector<Object>>();
	private InfoService infoService = new InfoService();
	private InfoTableModel infoTableModel = new InfoTableModel();

	public void init() {
		setTitle("【" + foodName + "】" + "合成列表");//设置窗口标题
		Container jfContainer = this.getContentPane();

		initTable();
        JScrollPane scrollPane = new JScrollPane(
        	infoTable,
        	ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
        	ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );
        jfContainer.add(scrollPane);

		setIconImage(ImageUtil.getIconImage());
        setSize(400, 600);//设置窗口大小
		setLocationRelativeTo(null);//设置窗口居中显示
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);//设置窗口关闭按钮
		setResizable(false);
        setVisible(true);
	}

	private void setTableSettings() {
		infoTable.setRowHeight(65);
		infoTable.setForeground(Color.BLACK);// 字体颜色
		infoTable.setFont(new Font("微软雅黑", Font.PLAIN, 20));// 字体样式
		infoTable.setSelectionForeground(Color.BLUE);
	    infoTable.setGridColor(Color.GRAY);
	    infoTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    //设置表头
	    infoTable.getTableHeader().setFont(new Font("微软雅黑", Font.PLAIN, 20));// 设置表头名称字体样式
	    infoTable.getTableHeader().setForeground(Color.RED);// 设置表头名称字体颜色
	    infoTable.getTableHeader().setResizingAllowed(false);// 设置不允许手动改变列宽
	    infoTable.getTableHeader().setReorderingAllowed(false);// 设置不允许拖动重新排序各列

	    infoTable.getColumnModel().getColumn(0).setMaxWidth(50);
	    infoTable.getColumnModel().getColumn(0).setMinWidth(50);
	    infoTable.getColumnModel().getColumn(0).setPreferredWidth(50);
	    
	    infoTable.getColumnModel().getColumn(5).setMaxWidth(0);
	    infoTable.getColumnModel().getColumn(5).setMinWidth(0);
	    infoTable.getColumnModel().getColumn(5).setPreferredWidth(0);
	    infoTable.getColumnModel().getColumn(6).setMaxWidth(0);
	    infoTable.getColumnModel().getColumn(6).setMinWidth(0);
	    infoTable.getColumnModel().getColumn(6).setPreferredWidth(0);
	    infoTable.getColumnModel().getColumn(7).setMaxWidth(0);
	    infoTable.getColumnModel().getColumn(7).setMinWidth(0);
	    infoTable.getColumnModel().getColumn(7).setPreferredWidth(0);
	    infoTable.getColumnModel().getColumn(8).setMaxWidth(0);
	    infoTable.getColumnModel().getColumn(8).setMinWidth(0);
	    infoTable.getColumnModel().getColumn(8).setPreferredWidth(0);
	    
	    infoTable.getColumn(infoTable.getColumnName(0)).setCellRenderer(new InfoTableCellRenderer());
	    infoTable.getColumnModel().getColumn(1).setCellRenderer(new InfoImageTableRenderer());
	    infoTable.getColumnModel().getColumn(2).setCellRenderer(new InfoImageTableRenderer());
	    infoTable.getColumnModel().getColumn(3).setCellRenderer(new InfoImageTableRenderer());
	    infoTable.getColumnModel().getColumn(4).setCellRenderer(new InfoImageTableRenderer());
	}

	private void initTable() {
		tableData = infoService.getInfoData(foodName);
		infoTableModel.initData(tableData);
		infoTable.setModel(infoTableModel);
		setTableSettings();
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

}

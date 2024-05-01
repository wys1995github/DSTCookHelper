package com.dstch.view;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

/**
 * @description 表格模型实现，表格显示数据时将调用模型中的相应方法获取数据进行表格内容的显示
 * @author wys
 * @createDate 2023年2月10日 下午11:07:56
 */
public class MainFITableModel extends AbstractTableModel{

	private static final long serialVersionUID = 1L;
	private Vector<Vector<Object>> tableData = new Vector<Vector<Object>>();
	private static Vector<String> tableTitle = new Vector<>();
	static {
		tableTitle.add("序号");
		tableTitle.add("图标");
		tableTitle.add("名称");
		tableTitle.add("饥饿值");
		tableTitle.add("精神值");
		tableTitle.add("血量值");
		tableTitle.add("保质期");
		tableTitle.add("备注");
	}

	public void initData(Vector<Vector<Object>> tableData){
		this.tableData = tableData;
    }

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public int getRowCount() {
		return tableData.size();
	}

	@Override
	public int getColumnCount() {
		return tableTitle.size();
	}

	@Override
	public String getColumnName(int column) {
		return tableTitle.get(column);
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Vector<Object> rowData = (Vector<Object>)tableData.get(rowIndex);
		return rowData.get(columnIndex);
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Vector<Object> rowData = (Vector<Object>)tableData.get(rowIndex);
		rowData.set(columnIndex, aValue);
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return getValueAt(0, columnIndex).getClass();
	}

}

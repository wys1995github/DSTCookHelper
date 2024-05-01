package com.dstch.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.Icon;

import com.dstch.util.DBUtil;
import com.dstch.util.ImageUtil;

public class InfoService{

	private Connection conn = DBUtil.getConnection();
	private PreparedStatement ps = null;

	public Vector<Vector<Object>> getInfoData(String foodName) {
		String sql = " select cl_item1, cl_item2, cl_item3, cl_item4 "
				+ "from t_compositeList where cl_name = ? ";
		Vector<Vector<Object>> tableData = new Vector<>();
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, foodName);
			rs = ps.executeQuery();
			int cl_num = 1;
			while(rs.next()) {
				Vector<Object> oneData = new Vector<>();
				oneData.addElement(cl_num++);
				String item1 = rs.getString("cl_item1");
				String item2 = rs.getString("cl_item2");
				String item3 = rs.getString("cl_item3");
				String item4 = rs.getString("cl_item4");
				oneData.addElement(getIconAsItem(item1));
				oneData.addElement(getIconAsItem(item2));
				oneData.addElement(getIconAsItem(item3));
				oneData.addElement(getIconAsItem(item4));
				oneData.addElement(item1);
				oneData.addElement(item2);
				oneData.addElement(item3);
				oneData.addElement(item4);
				tableData.addElement(oneData);
			}
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeConnection(conn);
		}
		return tableData;
	}

	private Icon getIconAsItem(String itemName) {
		String sql = " select fi_image from t_foodIngredients where fi_name = ? ";
		ResultSet rs = null;
		Icon icon = null;
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, itemName);
			rs = ps.executeQuery();
			while(rs.next()) {
				icon = ImageUtil.loadImage(rs.getBinaryStream("fi_image"));
			}
			return icon;
		} catch (SQLException e){
			e.printStackTrace();
		}
		return icon;
	}

}
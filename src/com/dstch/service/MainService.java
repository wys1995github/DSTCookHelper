package com.dstch.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.dstch.util.DBUtil;
import com.dstch.util.ImageUtil;

public class MainService {

	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	public Vector<Vector<Object>> getCDData() {
		conn = DBUtil.getConnection();
		String sql = "select * from t_cookedDishes; ";
		Vector<Vector<Object>> tableData = new Vector<>();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			int cd_num = 1;
			while(rs.next()) {
				Vector<Object> oneData = new Vector<>();
				oneData.addElement(cd_num++);
				oneData.addElement(ImageUtil.loadImage(rs.getBinaryStream("cd_image")));
				oneData.addElement(rs.getString("cd_name"));
				oneData.addElement(rs.getFloat("cd_hunger"));
				oneData.addElement(rs.getFloat("cd_sanity"));
				oneData.addElement(rs.getFloat("cd_health"));
				oneData.addElement(rs.getString("cd_buff"));
				oneData.addElement(rs.getString("cd_comment"));
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

	public Vector<Vector<Object>> getCDData(String searchKey) {
		if(searchKey.equals(null) || "".equals(searchKey.trim())) {
			return getCDData();
		}
		conn = DBUtil.getConnection();
		String sql = "select * from t_cookedDishes where cd_name like ? ";
		Vector<Vector<Object>> tableData = new Vector<>();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + searchKey + "%");
			rs = ps.executeQuery();
			int cd_num = 1;
			while(rs.next()) {
				Vector<Object> oneData = new Vector<>();
				oneData.addElement(cd_num++);
				oneData.addElement(ImageUtil.loadImage(rs.getBinaryStream("cd_image")));
				oneData.addElement(rs.getString("cd_name"));
				oneData.addElement(rs.getFloat("cd_hunger"));
				oneData.addElement(rs.getFloat("cd_sanity"));
				oneData.addElement(rs.getFloat("cd_health"));
				oneData.addElement(rs.getString("cd_buff"));
				oneData.addElement(rs.getString("cd_comment"));
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

	public Vector<Vector<Object>> getFIData() {
		conn = DBUtil.getConnection();
		String sql = "select * from t_foodIngredients; ";
		Vector<Vector<Object>> tableData = new Vector<>();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			int fi_num = 1;
			while(rs.next()) {
				Vector<Object> oneData = new Vector<>();
				oneData.addElement(fi_num++);
				oneData.addElement(ImageUtil.loadImage(rs.getBinaryStream("fi_image")));
				oneData.addElement(rs.getString("fi_name"));
				oneData.addElement(rs.getFloat("fi_hunger"));
				oneData.addElement(rs.getFloat("fi_sanity"));
				oneData.addElement(rs.getFloat("fi_health"));
				oneData.addElement(rs.getString("fi_qualityTime"));
				oneData.addElement(rs.getString("fi_comment"));
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

	public Vector<Vector<Object>> getFIData(String searchKey) {
		if(searchKey.equals(null) || "".equals(searchKey.trim())) {
			return getFIData();
		}
		conn = DBUtil.getConnection();
		String sql = "select * from t_foodIngredients where fi_name like ?  ";
		Vector<Vector<Object>> tableData = new Vector<>();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + searchKey + "%");
			rs = ps.executeQuery();
			int fi_num = 1;
			while(rs.next()) {
				Vector<Object> oneData = new Vector<>();
				oneData.addElement(fi_num++);
				oneData.addElement(ImageUtil.loadImage(rs.getBinaryStream("fi_image")));
				oneData.addElement(rs.getString("fi_name"));
				oneData.addElement(rs.getFloat("fi_hunger"));
				oneData.addElement(rs.getFloat("fi_sanity"));
				oneData.addElement(rs.getFloat("fi_health"));
				oneData.addElement(rs.getString("fi_qualityTime"));
				oneData.addElement(rs.getString("fi_comment"));
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

}
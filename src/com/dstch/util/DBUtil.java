package com.dstch.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @description 数据库连接工具类
 * @author wys
 * @createDate 2023年2月15日 下午3:14:14
 */
public class DBUtil {

	private static String dbDriver;
	private static String dbUrl;
	private static String dbUsername;
	private static String dbPassword;

	static {
		Properties rp = new Properties();
		InputStream ips = DBUtil.class.getResourceAsStream("/com/dstch/db.properties");
		try {
			rp.load(ips);
			dbDriver = rp.getProperty("dbDriver");
			dbUrl = rp.getProperty("dbUrl");
			dbUsername = rp.getProperty("dbUsername");
			dbPassword = rp.getProperty("dbPassword");
			Class.forName(dbDriver);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void closePreparedStatement(PreparedStatement preparedStatement) {
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void closeResultSet(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}

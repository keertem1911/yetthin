package com.yetthin.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.yetthin.web.commit.JdbcUtil;
import com.yetthin.web.domain.BarDataOfDay;

public class AvgDao {
	
	/*
	 * 得到close字段的平均值
	 */
	public static   List<BarDataOfDay> getAvg_Close(){
		String sql1 = "select id,stockcode,datetime,close from bardata";
		Connection connection = JdbcUtil.getconnection();
		PreparedStatement prepareStatement=null;
		ResultSet executeQuery = null;
		 List<BarDataOfDay> arrayList = new ArrayList<BarDataOfDay>();
		try {
			prepareStatement = connection.prepareStatement(sql1);
			executeQuery = prepareStatement.executeQuery();
			while(executeQuery.next()){
				BarDataOfDay bar = new BarDataOfDay();
				bar.setId(executeQuery.getString("id"));
				bar.setStockcode(executeQuery.getString("stockcode"));
				bar.setDatetime(executeQuery.getString("datetime"));
				bar.setClose(executeQuery.getDouble("close"));
				arrayList.add(bar);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUtil.close(connection, prepareStatement);
			try {
				executeQuery.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return arrayList;
	}
	/*
	 * 平均值写入数据库
	 */
	
	public static void setAvg(double avg ,int days, String stockcode){
		String node = "m"+days;
		String sql ="update bardata set "+node+"=? where stockcode=?";
		Connection connection = JdbcUtil.getconnection();
		PreparedStatement prepareStatement =null;
		try {
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setDouble(1, avg);
			prepareStatement.setString(2, stockcode);
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUtil.close(connection, prepareStatement);
		}
	}
	public static void updateBardatalist(List<BarDataOfDay> lists){
		String sql ="update bardata "
				+ "set m5 = ? , "
				+ " m10 = ? , "
				+ " m60 = ? , "
				+ " m120 = ? where id = ? ";
		Connection connection = JdbcUtil.getconnection();
		PreparedStatement prepareStatement =null;
		try {
			prepareStatement = connection.prepareStatement(sql);
			int i=0;
			for (i = 0; i < lists.size(); i++) {
				BarDataOfDay bar = lists.get(i);
				prepareStatement.setDouble(1, bar.getM5());
				prepareStatement.setDouble(2, bar.getM10());
				prepareStatement.setDouble(3, bar.getM60());
				prepareStatement.setDouble(4, bar.getM120());
				prepareStatement.setInt(5,Integer.parseInt(bar.getId()));
				
				prepareStatement.addBatch();
				if(i%100==0){
					 int[] s = prepareStatement.executeBatch();
					prepareStatement.clearBatch();
					if(s.length!=0){
						System.out.println("更新成功  "+Arrays.asList(s)+"----------------------------------------");
					}
				}
					
			}
			if(i%100!=0){
			prepareStatement.executeBatch();
			prepareStatement.clearBatch();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUtil.close(connection, prepareStatement);
		}
	}

}

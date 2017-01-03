package com.yetthin.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.yetthin.web.commit.JdbcUtil;
import com.yetthin.web.domain.BarDataOfDay;
import com.yetthin.web.domain.Group;
import com.yetthin.web.domain.ProfitData;
import com.yetthin.web.domain.Share;

public class jdbcSqlDao {
	// public static void main(String[] args) {
	// ImpDao imp = new ImpDao();
	//
	// ProfitData p = new ProfitData();
	// p.setProfitdataGroupId("iiji");
	// p.setProfitdataId("fdsfs");
	// p.setProfitdataShareFundNum(132989.0);
	// p.setProfitdataStock(8989.0);
	// p.setProfitdataTime("12.2.22");
	// System.out.println(imp.insert(p));
	//
	// }
	//
	//
	public List<Share> selectSharesAll() {
		ArrayList<Share> list = new ArrayList<Share>();
		String sql = "select * from tb_share";
		Connection con = JdbcUtil.getconnection();
		PreparedStatement prepar = null;
		try {
			prepar = con.prepareStatement(sql);
			ResultSet res = prepar.executeQuery();
			while (res.next()) {
				Share s = new Share();
				s.setShareId(res.getString("share_id"));
				s.setShareCurrentIncome(res.getDouble("share_current_income"));
				s.setShareCurrentNum(res.getDouble("share_current_num"));
				s.setShareGroupId(res.getString("share_group_id"));
				s.setShareStartFund(res.getDouble("share_start_fund"));
				s.setShareStocklableId(res.getString("share_stocklable_id"));
				s.setShareStocklableName(res.getString("share_stocklable_name"));
				list.add(s);
			}
		} catch (SQLException e) {
			System.out.println("查询错误");
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(con, prepar);
		}
		return list;
	}

	public int updateByPrimaryKey(Share re) {
		String sql = "update tb_share set share_current_income=?,share_current_num=?,share_group_id=?,share_start_fund=?,share_stocklable_id=?, share_stocklable_name=? where share_id=? ";
		int cont = 0;
		Connection con = JdbcUtil.getconnection();
		PreparedStatement pre = null;
		try {
			pre = con.prepareStatement(sql);
			pre.setDouble(1, re.getShareCurrentIncome());
			pre.setDouble(2, re.getShareCurrentNum());
			pre.setString(3, re.getShareGroupId());
			pre.setDouble(4, re.getShareStartFund());
			pre.setString(5, re.getShareStocklableId());
			pre.setString(6, re.getShareStocklableName());
			pre.setString(7, re.getShareId());
			cont = pre.executeUpdate();
		} catch (SQLException e) {
			System.out.println("更新失败");
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(con, pre);
		}
		return cont;
	}

	public List<Group> selectGroupsAll() {
		ArrayList<Group> list = new ArrayList<Group>();
		String sql = "select * from tb_group";
		Connection con = JdbcUtil.getconnection();
		PreparedStatement prepar = null;
		try {
			prepar = con.prepareStatement(sql);
			ResultSet res = prepar.executeQuery();
			while (res.next()) {
				Group s = new Group();
				s.setGroupCreateId(res.getString("group_create_id"));
				s.setGroupCreateTime(res.getString("group_create_time"));
				s.setGroupEmotionIndex((int) res.getDouble("group_emotion_index"));
				s.setGroupEvaluateLevel(res.getString("group_evaluate_level"));
				s.setGroupId(res.getString("group_id"));
				s.setGroupIncomeTotle(res.getDouble("group_income_totle"));
				s.setGroupInitMoney(res.getDouble("group_init_money"));
				s.setGroupMediaAttentionRate((int) res.getDouble("group_media_attention_rate"));
				s.setGroupName(res.getString("group_name"));
				s.setGroupNetizenAttentionRate((int) res.getDouble("group_netizen_attention_rate"));
				s.setGroupOpen(res.getString("group_open"));
				s.setGroupReferIndex(res.getString("group_refer_index"));
				s.setGroupStrategyId(res.getString("group_strategy_id"));
				s.setGroupWarningLevel((int) res.getDouble("group_warning_level"));
				list.add(s);
			}
		} catch (SQLException e) {
			System.out.println("查询错误");
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(con, prepar);
		}
		return list;
	}

	public int updateByPrimaryKey(Group r) {
		String sql = "update tb_group set group_name=?,group_create_id=?,group_create_time=?,group_init_money=?,"
				+ "group_evaluate_level=?,group_income_totle=?,group_strategy_id=?,group_refer_index=?,group_emotion_index=?,"
				+ "group_warning_level=?,group_media_attention_rate=?,group_netizen_attention_rate=?,group_open=? where  group_id=?";
		int cont = 0;
		Connection con = JdbcUtil.getconnection();
		PreparedStatement pre = null;
		try {
			pre = con.prepareStatement(sql);
			pre.setString(1, r.getGroupName());
			pre.setString(2, r.getGroupCreateId());
			pre.setString(3, r.getGroupCreateTime());
			pre.setDouble(4, r.getGroupInitMoney());
			pre.setString(5, r.getGroupEvaluateLevel());
			pre.setDouble(6, r.getGroupIncomeTotle());
			pre.setString(7, r.getGroupStrategyId());
			pre.setString(8, r.getGroupReferIndex());
			pre.setDouble(9, r.getGroupEmotionIndex());
			pre.setDouble(10, r.getGroupWarningLevel());
			pre.setDouble(11, r.getGroupMediaAttentionRate());
			pre.setDouble(12, r.getGroupNetizenAttentionRate());
			pre.setString(13, r.getGroupOpen());
			pre.setString(14, r.getGroupId());
			cont = pre.executeUpdate();
		} catch (SQLException e) {
			System.out.println("更新异常");
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(con, pre);
		}
		return cont;
	}

	public int insert(ProfitData r) {
		String sql = "insert into tb_profitdata values ('" + r.getProfitdataId() + "','" + r.getProfitdataTime() + "','"
				+ r.getProfitdataGroupId() + "','" + r.getProfitdataShareFundNum() + "'," + r.getProfitdataStock()
				+ ")";
		Connection con = JdbcUtil.getconnection();
		int cont = 0;
		PreparedStatement prepar = null;
		try {
			prepar = con.prepareStatement(sql);
			cont = prepar.executeUpdate();
		} catch (SQLException e) {
			System.out.println("插入异常");
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(con, prepar);
		}
		return cont;
	}

	public   int  insertBarDataOfDay(List<BarDataOfDay> days) {
		String sql = "insert into bardata(dateTime, stockcode, height, low, open, close, stockname,volume) "
				+ "values(?,?,?,?,?,?,?,?)";
		Connection con = JdbcUtil.getconnection();
		int cont = 0;
		PreparedStatement prepar = null;
		try {
			// con.setAutoCommit(false);
			prepar = con.prepareStatement(sql);
			for (int i = 0; i < days.size(); i++) {
				BarDataOfDay day = days.get(i);
				prepar.setString(1, day.getDatetime());
				prepar.setString(2, day.getStockcode());
				prepar.setDouble(3, day.getHeight());
				prepar.setDouble(4, day.getLow());
				prepar.setDouble(5, day.getOpen());
				prepar.setDouble(6, day.getClose());
				prepar.setString(7, day.getStockname());
				prepar.setString(8, day.getVolume());
				prepar.addBatch();
				if (i != 0 && i % 100 == 0){
					prepar.executeBatch();
					prepar.clearBatch();
				}
			}
			prepar.executeBatch();
			prepar.clearBatch();
			System.out.println("insert into "+days.size());
			// con.commit();
		} catch (SQLException e) {
			System.out.println("插入异常");
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(con, prepar);
		}
		return cont;
	}

	public int deleteNumberZ() {
		String sql = " 	delete from tb_share where share_current_num =0 ";
		Connection con = JdbcUtil.getconnection();
		int cont = 0;
		PreparedStatement prepar = null;
		try {
			prepar = con.prepareStatement(sql);
			cont = prepar.executeUpdate();
		} catch (SQLException e) {
			System.out.println("delete 异常");
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(con, prepar);
		}
		return cont;
	}

	public int selectByTime(String datetime) {
		// TODO Auto-generated method stub
		String sql = "select count(*) as num from tb_profitdata where profitdata_time = ?";
		Connection con = JdbcUtil.getconnection();
		PreparedStatement prepar = null;
		int count = 0;
		try {
			prepar = con.prepareStatement(sql);
			prepar.setString(1, datetime);
			ResultSet res = prepar.executeQuery();
			if (res.next())
				count = res.getInt("num");

		} catch (SQLException e) {
			System.out.println("select bydATA ");
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(con, prepar);
		}
		return count;

	}
	public List<BarDataOfDay> selectByStockcode(String stockCode) {
		ArrayList<BarDataOfDay> List = new ArrayList<BarDataOfDay>();
		Connection con = JdbcUtil.getconnection();
		ResultSet executeQuery = null;
		String sql="select dateTime,height,low,open,close,stockname,volume from bardata where stockcode=?";
		PreparedStatement pre = null ;
		try {
			pre = con.prepareStatement(sql);
			pre.setString(1, stockCode);
			executeQuery = pre.executeQuery();
		
			while(executeQuery.next()){
				
				BarDataOfDay barDataOfDay = new BarDataOfDay();
				barDataOfDay.setClose(executeQuery.getDouble("close"));
				barDataOfDay.setDatetime(executeQuery.getString("dateTime"));
				barDataOfDay.setHeight(executeQuery.getDouble("height"));
				barDataOfDay.setLow(executeQuery.getDouble("low"));
				barDataOfDay.setOpen(executeQuery.getDouble("open"));
				barDataOfDay.setStockcode(stockCode);
				barDataOfDay.setVolume(executeQuery.getString("volume"));
				barDataOfDay.setStockname(executeQuery.getString(7));
				List.add(barDataOfDay);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.close(con, pre);
			try {
				executeQuery.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return List;
	}
}

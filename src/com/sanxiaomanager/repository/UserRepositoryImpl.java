package com.sanxiaomanager.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class UserRepositoryImpl extends JdbcDaoSupport implements UserRepository {
	private JdbcTemplate jdbcTemplate;
	
	@Override 
	public boolean insert(UserDO user) {
		int temp = 0;
		String sql = "insert into USERS values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		temp = jdbcTemplate.update(sql, new Object[] {
				user.getId(),
				user.getPwd(),
				user.getName(),
				user.getAge(),
				user.getSex(),
				user.getAc(),
				user.getPs(),
				user.getAdd(),
				user.getTel(),
				user.getCh()
		});
		return temp>0?true:false;
	}

	@Override
	public boolean update(UserDO user) {
		int temp = 0;
		String sql = "update USERS set upwd=?, uname=?, uage=?, usex=?, uac=?, ups=?, uadd=?, utel=?, uch=? where uid=?";
		temp = jdbcTemplate.update(sql, new Object[] {
				user.getPwd(),
				user.getName(),
				user.getAge(),
				user.getSex(),
				user.getAc(),
				user.getPs(),
				user.getAdd(),
				user.getTel(),
				user.getCh(),
				user.getId()
		});
		return temp>0?true:false;
	}

	@Override
	public boolean delete(UserDO user) {
		// TODO Auto-generated method stub
		return false;
	}

	public UserDO selectById(int id) {
		String sql = "select * from USERS where uid=?";
		return jdbcTemplate.queryForObject(sql, new UserRowMapper(), id);
	}

	@Autowired
	public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}
}

final class UserRowMapper implements RowMapper<UserDO>{
	public UserDO mapRow(ResultSet rs, int rowNum) throws SQLException{
		return new UserDO(
				rs.getInt("uid"),
				rs.getString("upwd"),
				rs.getString("uname"),
				rs.getInt("uage"),
				rs.getString("usex"),
				rs.getString("uac"),
				rs.getString("ups"),
				rs.getString("uadd"),
				rs.getString("utel"),
				rs.getInt("uch"));
	}
}

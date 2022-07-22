//package com.mongo.example.mongodemo.repository;
//
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import org.springframework.jdbc.core.RowMapper;
//
//import com.mongo.example.mongodemo.models.apimodel.User;
//
//public class UserRowMapperImpl implements RowMapper<User> {
//	@Override
//	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
//		User topic = new User();
//		topic.setUser_id(rs.getInt("user_id"));
//		topic.setFirst_name(rs.getString("first_name"));
//		topic.setLast_name(rs.getString("last_name"));
//		topic.setEmail(rs.getString("email"));
//		topic.setAddress(rs.getString("address"));
//		topic.setImg(rs.getString("img"));
//		  
//		return topic;
//	}
//}

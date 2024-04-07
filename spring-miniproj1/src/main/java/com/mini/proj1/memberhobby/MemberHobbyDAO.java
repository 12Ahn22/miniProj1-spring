package com.mini.proj1.memberhobby;

import java.sql.PreparedStatement;
import java.sql.SQLException;


public class MemberHobbyDAO {
	private static PreparedStatement deleteAllPs = null;
	private static PreparedStatement insertPs = null;
	
	private static String deleteAllSQL = "delete from tb_member_hobbies where member_id = ?";
	private static String insertSQL= "insert into tb_member_hobbies (member_id, hobby_id) values (?,?)";
}

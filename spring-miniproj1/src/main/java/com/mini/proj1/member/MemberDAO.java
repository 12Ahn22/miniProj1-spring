package com.mini.proj1.member;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberDAO {
	private static PreparedStatement listPs = null;
	private static PreparedStatement viewPs = null;
	private static PreparedStatement memberHobbiesPs = null;
	private static PreparedStatement deletePs = null;
	private static PreparedStatement updatePs = null;
	private static PreparedStatement insertPs = null;
	private static PreparedStatement authenticatePs = null;
	private static PreparedStatement updateUUIDPs = null;
	private static PreparedStatement getMemberFromUUIDPs = null;
	
	private static String listSQL = "select id, name, address, phone, gender from tb_members";
	private static String viewSQL = "select id, name, address, phone, gender from tb_members where id = ?";
	private static String memberHobbiesSQL = "select id, hobby from tb_member_hobbies tmh join tb_hobbies th on tmh.hobby_id = th.id where member_id = ?";
	private static String deleteSQL = "delete from tb_members where id = ?";
	private static String updateSQL = "update tb_members set name = ?, password = ?, address = ?, phone = ? where id = ?";
	private static String insertSQL = "insert into tb_members (id, name, password, address, phone, gender) values(?,?,?,?,?,?)";
	private static String authenticateSQL = "select id from tb_members where id=? and password = ?";
	private static String updateUUIDSQL = "update tb_members set memberUUID = ? where id = ?";
	private static String getMemberFromUUIDSQL = "select id, name, address, phone, gender from tb_members where memberUUID = ?";

}

package com.mini.proj1.boards;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {
	private static PreparedStatement listPs = null;
	private static PreparedStatement viewPs = null;
	private static PreparedStatement increaseViewCountPs = null;
	private static PreparedStatement deletePs = null;
	private static PreparedStatement updatePs = null;
	private static PreparedStatement insertPs = null;
	
	private static String listSQL = "select tb.bno, tb.title, tm.name author, tb.content, tb.created_at, tb.view_count from tb_boards tb left join tb_members tm on tb.author = tm.id where title like ?";
	private static String viewSQL = "select bno, title, author, content, created_at,view_count from tb_boards where bno = ?";
	private static String increaseViewCountSQL = "update tb_boards set view_count = view_count + 1 where bno = ?";
	private static String deleteSQL = "delete from tb_boards where bno = ?";
	private static String updateSQL = "update tb_boards set title = ?, content = ? where bno = ?";
	private static String insertSQL = "insert into tb_boards (title, content, author) values(?,?,?)";
}

package com.mini.proj1.hobby;

import java.sql.PreparedStatement;

public class HobbyDAO{
	private static PreparedStatement listPs = null;

	private static String listSQL = "select id, hobby from tb_hobbies";

}

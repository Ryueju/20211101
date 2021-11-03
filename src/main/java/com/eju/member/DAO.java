package com.eju.member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class DAO {
	// db처리를 위해 필요한 필드.
	protected Connection conn;
	protected PreparedStatement psmt;
	protected Statement stmt;
	protected ResultSet rs;
	
	protected void disconnect() {
		if(rs !=null) {
			try {
				rs.close(); //resultset이사용되어지지않았다면 close해줘야함
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(stmt !=null) {
			try {
				stmt.close(); //resultset이사용되어지지않았다면 close해줘야함
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(psmt !=null) {
			try {
				psmt.close(); //resultset이사용되어지지않았다면 close해줘야함
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(conn !=null) {
			try {
				conn.close(); //resultset이사용되어지지않았다면 close해줘야함
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	protected void connect() {

		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "web", pw = "web";

		// 1) JDBC 드라이버 로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// 2) DB연결(DB url, DB id, DB pw)
		try {
			conn = DriverManager.getConnection(url, user, pw);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public boolean insertMember(String id, String pw, String birth, String gender) {
		String sql = "insert into member(user_id,user_pw,birth_date,gender) values(?,?,?,?)";
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, pw);
			psmt.setString(3, birth);
			psmt.setString(4, gender);
			int r = psmt.executeUpdate();
			System.out.println(r + "건 입력.");
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				conn.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// END of insertMember

	// 조회(id, pw) => id, name /null값을 반환
	public Map<String, String> loginCheck(String id, String pw) {
		String sql = "select * from member where user_id=? and user_pw=?";
		Map<String, String> map = new HashMap<String, String>();
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, pw);
			ResultSet rs = psmt.executeQuery();
			if (rs.next()) {
				map.put(rs.getString("user_id"), rs.getString("user_name"));
				return map;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
	}

}

package com.eju.member;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemDAO extends DAO {
	// DAO에 정의해 둔 필드들을 모두 가지게 됨
	
	//한건 조회
	public MemberVO getMember(String id) {
			String sql = "select * from member where user_id = ?";
			connect();
			MemberVO vo = null;
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1,id);
				rs = psmt.executeQuery();
				if(rs.next()) {
					vo = new MemberVO();
					vo.setAddress(rs.getString("address"));
					vo.setBirthDate(rs.getString("birth_date")); // "칼럼이름"
					vo.setGender(rs.getString("gender"));
					vo.setPhone(rs.getString("phone"));
					vo.setUserId(rs.getString("user_id"));
					vo.setUserName(rs.getString("user_name"));
				
				}
				
			} catch(SQLException e) {
				e.printStackTrace();
				return null;
			} finally {
				disconnect();
			}
			return vo;
	}
	
	//한건 입력.
	public void insertMember(MemberVO vo) {
		String sql = "insert into member(user_id,user_name,address) values(?,?,?)";
		connect();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getUserId());
			psmt.setString(2,  vo.getUserName());
			psmt.setString(3, vo.getAddress());
			int r = psmt.executeUpdate();
			System.out.println(r +" 건 입력됨");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	finally {
			disconnect();
			
		}
	}

	public List<MemberVO> getMemberList() {
		String sql = "select * from member order by 1"; // 오름차순으로 하나 가져옴
		connect(); // DAO안에 있음 그대로 상속받아 쓰는것임
		List<MemberVO> memberList = new ArrayList<>();
		try {
			stmt = conn.createStatement(); // Statement stmt = new Statement(); 객체를 하나 만들어줌
			rs = stmt.executeQuery(sql); //담아주는 것.....
			while (rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setAddress(rs.getString("address"));
				vo.setBirthDate(rs.getString("birth_date")); // "칼럼이름"
				vo.setGender(rs.getString("gender"));
				vo.setPhone(rs.getString("phone"));
				vo.setUserId(rs.getString("user_id"));
				vo.setUserName(rs.getString("user_name"));
				memberList.add(vo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return memberList;
	}
}

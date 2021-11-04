package com.eju.member;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemDAO extends DAO {
	// DAO에 정의해 둔 필드들을 모두 가지게 됨
	// 수정 기능
	public Map<String, Object> updateMember(MemberVO vo){
		//retCode : OK, retVal: vo
		//retCode : NG, retVal : errMsg
		String sql = "update member ";
		sql +="		set		user_name = ?";
		sql +="				,birth_date =?";
		sql +="				,gender =?";
		sql +="			 	,address = ?";
		sql +="				,phone =?";
		sql +=" 		where user_id =?";
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("retCode", "NG");
		map.put("retVal", "Error......ㅠㅠ");
		
		connect();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getUserName());
			psmt.setString(2, vo.getBirthDate());
			psmt.setString(3, vo.getGender());
			psmt.setString(4, vo.getAddress());
			psmt.setString(5, vo.getPhone());
			psmt.setString(6, vo.getUserId());
			int r = psmt.executeUpdate();
			System.out.println(r + "건 수정 완");
			if(r > 0) {
				map.put("retCode", "OK");
				map.put("retVal", vo);//vo: 입력받은 요소를 그대로 돌려주겠다는 뜻.
				return map; //한 건이 정상적으로 진행되면 그대로 리턴해버리고..예외중에 애러가발생하면이제
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("retVal", e.getMessage()); //e:예외가 가지고 있는 에러메시지
			return map;
			
		} finally {
			disconnect();
		}
		return map;
		
	}
	
	
	
	//한건 삭제.
	public boolean deleteMember(String id) {
		String sql = "delete from member where user_id = ?";
		//파라메터로 오는 그 값을 받아서 db에서 지우겠다.
		connect();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id); //12행 id값을 받아서 넣어주겠다는 뜻
			int r = psmt.executeUpdate();
			System.out.println(r+ " 건 삭제 완");
			if(r>0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} 
		return false;
	}
	
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
	public boolean insertMember(MemberVO vo) {
		String sql = "insert into member(user_id,user_name,address,phone,birth_date,gender) values(?,?,?,?,?,?)";
		connect();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getUserId());
			psmt.setString(2,  vo.getUserName());
			psmt.setString(3, vo.getAddress());
			psmt.setString(4, vo.getPhone());
			psmt.setString(5, vo.getBirthDate());
			psmt.setString(6, vo.getGender());
			
			int r = psmt.executeUpdate();
			System.out.println(r +" 건 입력됨");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}	finally {
			disconnect();
			
		}
		return true;
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

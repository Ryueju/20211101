package com.eju.member;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.eju.member.ItemVO;

public class MemDAO extends DAO {
	// DAO에 정의해 둔 필드들을 모두 가지게 됨
	
	//상품리스트
	public List<ItemVO> getItemList(){
		connect();
		String sql = " select * from item order by 1" ;
		List<ItemVO> list = new ArrayList<ItemVO>();
		 try {
			 stmt = conn.createStatement();
			 rs=stmt.executeQuery(sql);
			 while(rs.next()) {
				 ItemVO vo = new ItemVO();
				 vo.setLikeit(rs.getDouble("like_it"));
				 vo.setOriginPrice(rs.getInt("origin_price"));
				 vo.setProdDesc(rs.getString("prod_desc"));
				 vo.setProdId(rs.getInt("prod_id"));
				 vo.setProdImage(rs.getString("prod_image"));
				 vo.setProdName(rs.getString("prod_item"));
				 vo.setSalePrice(rs.getInt("sale_price"));
				 list.add(vo);
			 } 
		 }
			 catch(SQLException e) {
				 e.printStackTrace();
			 } finally {
				 disconnect();
			 }
			 return list;
		 }
	
	
	//페이지 별 토탈 건수
	public int getTotalCount() {
		connect();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(1) from member");
			if(rs.next()) {
				int r = rs.getInt(1);
				System.out.println("조회건수 : " + r);
				return r;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return 0;
	}
	
	//페이지별 데이터 조회
	public List<MemberVO> getMemberByPage(String page){
		connect();
		List<MemberVO> list = new ArrayList<>();
		String sql = "select b. * from\r\n"//
				+ "(select rownum as num, a. * from \r\n"//
				+ "(select * from member order by 1)a)b\r\n"//
				+ "where b.num  >=?\r\n"//
				+ "and b.num <=?";
		
		int start = (Integer.parseInt(page) - 1) * 10 +1; //1을 int타입으로 (1-1)*10 + 1 = 0
		int end = start+9;
		
		
		try {	
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, start);
		psmt.setInt(2, end);
		rs=psmt.executeQuery();
		
		while(rs.next()) {
			MemberVO mem = new MemberVO();
			mem.setAddress(rs.getString("address"));
			mem.setBirthDate(rs.getString("birth_date")); // "칼럼이름"
			mem.setGender(rs.getString("gender"));
			mem.setPhone(rs.getString("phone"));
			mem.setUserId(rs.getString("user_id"));
			mem.setUserName(rs.getString("user_name"));
			list.add(mem);
		}
		
	}catch(SQLException e) {
		e.printStackTrace();
		
	}finally {
		disconnect();
	}return list;
	}
	//조회기능
	public List<MemberVO> searchMemberList(MemberVO vo){
		connect();
		List<MemberVO> list = new ArrayList<>();
		String sql = "select * from member k\r\n"
				+ "where user_id = nvl(?,user_id)\r\n"
				+ "and nvl(user_name,'1') like '%'||?||'%'\r\n"
				+ "and nvl(address,'1') like '%'||?||'%'\r\n"
				+ "and nvl(phone,'1') like '%'||?||'%'"; //||변수조건||
		
		if( vo.getGender() !=null && !vo.getGender().equals("all") && vo.getGender() !="" ) {
			//all이들어오면 젠더조건상관없이 가져옴
			sql += "and  gender =?"; 
			//all이 아니면 남/여라는 값이들어올 거니까 비교해주면되고, all이들어오면 젠더조건을 아예 주지 않으면 됨...
		}
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getUserId());
			psmt.setString(2, vo.getUserName());
			psmt.setString(3, vo.getAddress());
			psmt.setString(4, vo.getPhone());
			if(vo.getGender() !=null && !vo.getGender().equals("all") && vo.getGender() !="") {
				psmt.setString(5, vo.getGender());
			}
			rs = psmt.executeQuery();
			while(rs.next()) {
				MemberVO mem = new MemberVO();
				mem.setAddress(rs.getString("address"));
				mem.setBirthDate(rs.getString("birth_date")); // "칼럼이름"
				mem.setGender(rs.getString("gender"));
				mem.setPhone(rs.getString("phone"));
				mem.setUserId(rs.getString("user_id"));
				mem.setUserName(rs.getString("user_name"));
				list.add(mem);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	finally {
			disconnect();
		}
		return list;
		
	}
	
	
	
	
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

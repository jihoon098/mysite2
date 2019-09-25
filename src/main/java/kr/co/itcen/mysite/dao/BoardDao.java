package kr.co.itcen.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.itcen.mysite.vo.BoardVo;

public class BoardDao {
	
	public List<BoardVo> getList(String kwd) {
		List<BoardVo> result = new ArrayList<BoardVo>();
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			connection = getConnection();
			
			String sql = 
				"   select a.no, a.title, b.name, a.hit, date_format(a.reg_date, '%Y-%m-%d %h:%i:%s'), a.user_no, a.o_no, a.depth" +
				"     from board a, user b" +
				" where a.user_no = b.no" +
				" and (title like concat('%',?,'%')"+ 
				" or contents like concat('%',?,'%'))" +
				" order by g_no desc, o_no asc";
			pstmt = connection.prepareStatement(sql);
			
			if(kwd == null) {
				kwd = "";
			}
			pstmt.setString(1, kwd);
			pstmt.setString(2, kwd);				
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String user_name = rs.getString(3);
				Long hit = rs.getLong(4);
				String regDate = rs.getString(5);
				Long user_no = rs.getLong(6);
				Long o_no = rs.getLong(7);
				Long depth = rs.getLong(8);
				
				
				BoardVo vo= new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setUser_name(user_name);
				vo.setHit(hit);
				vo.setReg_date(regDate);
				vo.setUser_no(user_no);
				vo.setO_no(o_no);
				vo.setDepth(depth);
				
				result.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	

	
	public void insertBoard(BoardVo vo) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			connection = getConnection();
			long OnoVal = vo.getO_no()+1;
			long depVal = vo.getDepth()+1;
			
			sql = "update board set o_no=o_no+1 where g_no = ? and o_no >= ? ";
			pstmt = connection.prepareStatement(sql);
			pstmt.setLong(1, vo.getG_no());
			pstmt.setLong(2, OnoVal);
			pstmt.executeUpdate();
			pstmt.close();
			
			if(vo.getG_no() == 0) {
				sql = "insert into board values(null, ?, ?, 0, now(), (select ifnull (max(g_no+1), 1) from board as a), 1, 0, ?, 0)";
			}else {
				sql = "insert into board values(null, ?, ?, 0, now(), ?, ?, ?, ?, 0)";
			}
			
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			if(vo.getG_no() != 0) {
				pstmt.setLong(3, vo.getG_no());
				pstmt.setLong(4, OnoVal);
				pstmt.setLong(5, depVal);				
				pstmt.setLong(6, vo.getUser_no());
			}else {
				pstmt.setLong(3, vo.getUser_no());
				
			}

			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	
	
	
	
	public BoardVo getBoard(long no) {
		BoardVo vo = null;
		String sql = null;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			connection = getConnection();
			sql = "update board set hit=hit+1 where no = ? ";
			pstmt = connection.prepareStatement(sql);
			pstmt.setLong(1, no);
			pstmt.executeUpdate();
			pstmt.close();
			
			sql = 
				"   select no, title, contents, user_no, g_no, o_no, depth" +
				"     from board" +
				" where no = ?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setLong(1, no);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				Long no1 = rs.getLong(1);
				String title = rs.getString(2);
				String contents = rs.getString(3);
				Long user_no = rs.getLong(4);
				Long g_no = rs.getLong(5);
				Long o_no = rs.getLong(6);
				Long depth = rs.getLong(7);
				
				vo = new BoardVo();
				vo.setNo(no1);
				vo.setTitle(title);
				vo.setContents(contents);
				vo.setUser_no(user_no);
				vo.setG_no(g_no);
				vo.setO_no(o_no);
				vo.setDepth(depth);
			}
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return vo;
	}
	
	
	public void deleteBoard(long no) {
		//게시물 삭제여부를 알려주는 status를 1(삭제)로 변경
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = getConnection();
			
			String sql = 
				"   update board set title = '삭제된 게시물 입니다.', contents = '',status = 1" +
				" where no = ?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setLong(1, no);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	
	
	
	public void modifyContents(Long no, String title, String contents, Long user_no) {
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = getConnection();

			String sql = 
					"   update board set title = ?, contents = ?" +
							" where no = ? and user_no = ?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, contents);
			pstmt.setLong(3, no);
			pstmt.setLong(4, user_no);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	private Connection getConnection() throws SQLException {
		Connection connection = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		
			String url = "jdbc:mariadb://192.168.1.124:3306/webdb?characterEncoding=utf8";
			connection = DriverManager.getConnection(url, "webdb", "000000");
		
		} catch (ClassNotFoundException e) {
			System.out.println("Fail to Loading Driver:" + e);
		}
		
		return connection;
	}


	

}

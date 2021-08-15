package com.example.emaillist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.emaillist.vo.EmailVo;

public class EmaillistDaoImpl implements EmaillistDao {
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;

		try {
			// 드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// Connection 가져오기
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", // DBURL
					"C##BITUSER", // DB User
					"bituser"); // DB Pass
		} catch (ClassNotFoundException e) {
			System.err.println("드라이버 로드 실패!");
			e.printStackTrace();
		}

		return conn;
	}// private Connection getConnection() throws SQLException {}  end

	@Override
	public List<EmailVo> getList() {
		List<EmailVo> list = new ArrayList<>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			stmt = conn.createStatement(); //데이터베이스로 SQL 문을 보내기 위한 SQLServerStatement 개체를 만듦.

			String sql = "SELECT no, last_name, first_name, email, createdAt " + 
						" FROM emaillist ORDER BY no DESC";

			// 쿼리 수행
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				// 데이터 받기
				Long no = rs.getLong(1);
				String lastName = rs.getString(2);
				String firstName = rs.getString(3);
				String email = rs.getString(4);
				Date createdAt = rs.getDate(5);

				// VO 객체 생성
				EmailVo vo = new EmailVo();
				vo.setNo(no);
				vo.setLastName(lastName);
				vo.setFirstName(firstName);
				vo.setEmail(email);
				vo.setCreatedAt(createdAt);

				// 리스트에 추가
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 자원 정리
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	} //public List<EmailVo> getList() end
	

	@Override
	public int insert(EmailVo vo) {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			//	실행 계획
			String sql = "INSERT INTO emaillist " +
					"(no, last_name, first_name, email) " +
					" VALUES(seq_emaillist_pk.NEXTVAL, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			//	파라미터 바인딩
			pstmt.setString(1, vo.getLastName()); // 첫번째 ?의 값을 지정
			pstmt.setString(2, vo.getFirstName()); // 두번째 ?의 값을 지정
			pstmt.setString(3, vo.getEmail()); // 세번째 ?의 값을 지정
			
			//	쿼리 수행
			count = pstmt.executeUpdate();
			
//			<execute, executeQuery, executeUpdate 메서드>
//			JDBC Type 4 드라이버는 쿼리를 실행 할 수 있도록 execute, executeQuery, executeUpdate 3개의 메서드를 제공
//
//			1. execute
//			execute 메서드는 모든 유형의 SQL 문장과 함께 사용할 수 있으며, boolean 값을 반환합니다. 
//			반환 값이 ‘true’이면, getResultSet 메서드를 사용함으로써 결과 집합을 얻을 수 있습니다. 
//			반대로 반환 값이 ‘false’이면, 업데이트 개수 또는 결과가 없는 경우입니다.
//
//			execute 메서드는 Select, Insert, Update, Delete, DDL 문을 모두 실행할 수 있는 특징이 있습니다.
//
//			execute 메서드
//			public boolean execute(String sql) throws SQLException;
//			반환 값
//			true : result
//			false : update count or no result
			
			
//			2. executeUpdate
//			executeUpdate 메서드는 데이터베이스에서 데이터를 추가(Insert), 삭제(Delete), 수정(Update)하는 SQL 문을 실행
//			메서드의 반환 값은 해당 SQL 문 실행에 영향을 받는 행 수를 반환합니다.
//
//			데이터베이스 개발 툴(SQLGate, Toad, Sequel Pro)를 사용하다 보면, 
//			쿼리 실행 후 메시지로 affectedRows 값을 사용자에게 보여줍니다.
//			affectedRows 값은 사용자가 수행한 DML 문이 영향을 끼친 행 수를 나타내는데, 바로 executeUpdate 메서드의 반환 값입니다.
//
//			executeUpdate 메서드
//			public int executeUpdate(String sql) throws SQLException;
//			반환 값
//			int : row count
			
			
//			3. executeQuery
//			executeQuery 메서드는 데이터베이스에서 데이터를 가져와서 결과 집합을 반환합니다. 
//			이 메서드는 Select 문에서만 실행하는 특징이 있습니다.
//
//			executeQuery 메서드
//			public ResultSet executeQuery(String sql) throws SQLException;
//			반환 값
//			ResultSet : object that contains the data produced by the given query
//
//			출처: https://lelecoder.com/2 [lelecoder]
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return count;
	}// public int insert(EmailVo vo) end

	@Override
	public int delete(Long pk) {
		int deletedCount = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			String sql = "DELETE FROM emaillist WHERE no = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, pk);
			
			//	쿼리 수행
			deletedCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return deletedCount;
	} // public int delete(Long pk) end
	

}
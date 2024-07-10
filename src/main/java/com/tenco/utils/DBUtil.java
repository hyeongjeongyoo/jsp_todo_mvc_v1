package com.tenco.utils;

import javax.sql.DataSource;

public class DBUtil {
	
	private static DataSource dataSource;
	
	// 정적 초기화 블록
//	static {
//		// TODO - 삭제 예정
//		System.out.println("11111111");
//		try {
//			// InitialContext 객체를 생성하여 JNDI API 기술을 통해 존재하는 리소스를 찾는 방법
//			InitialContext ctx = new InitialContext();
//			dataSource = (DataSource)ctx.lookup("java:comp/env/jdbc/MyDB");
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public static Connection getConnection() throws SQLException {
//		return dataSource.getConnection();
//	}
	
}

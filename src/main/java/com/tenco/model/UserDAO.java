package com.tenco.model;

import java.util.List;

public interface UserDAO {
	
	int addUser(UserDTO userDTO);
	UserDTO getUserById(int id);
	UserDTO getUserByUsername(String username);
	List<UserDTO> getAllUsers();
	
	// 권한(마이정보 나만 바꿔야한다 -> 인증검사(세션 아이디) -> 수정할 데이터, 권한 )
	int updateUser(UserDTO user, int principalId);	
	
	int deleteUser(int id); // 인증 -> 세션
	
}

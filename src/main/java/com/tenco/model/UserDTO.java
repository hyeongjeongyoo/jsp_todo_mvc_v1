package com.tenco.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 데이터 변환, 담는 개념, 메서드를 사용할 수 있다.
 * 데이터 변환, 담는 개념 -> VO
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserDTO {
	
	private int id;
	private String username;
	private String password;
	private String email;
	private String created_at;

	// 필요하다면 메서드 정의 가능
	
}

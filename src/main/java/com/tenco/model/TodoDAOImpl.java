package com.tenco.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class TodoDAOImpl  implements TodoDAO{
	
	private DataSource dataSource;
	
	public TodoDAOImpl() {
		try {
			InitialContext ctx = new InitialContext();
			dataSource = (DataSource)ctx.lookup("java:comp/env/jdbc/MyDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addTodo(TodoDTO dto, int principalId) {
		
		String sql = " INSERT INTO todos(user_id, title, description, due_date, completed) VALUES (?, ?, ?, ?, ?) ";
		try (Connection conn = dataSource.getConnection()){
			System.out.println(dto.toString());
			conn.setAutoCommit(false);
			
			try (PreparedStatement pstmt = conn.prepareStatement(sql)){
				pstmt.setInt(1, principalId);
				pstmt.setString(2, dto.getTitle());
				pstmt.setString(3, dto.getDescription());
				pstmt.setString(4, dto.getDueDate());
				pstmt.setInt(5, dto.getCompleted() == "true" ? 1 : 0);
				pstmt.executeUpdate();
				
				conn.commit();
			} catch (Exception e) {
				e.printStackTrace();
				conn.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public TodoDTO getTodoById(int id) {
		
		String sql = " SELECT * FROM todos WHERE id = ? ";
		TodoDTO todoDTO = null;
		
		try (Connection conn = dataSource.getConnection()){
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			try (ResultSet rs = pstmt.executeQuery()){
				
				if(rs.next()) {
					todoDTO = new TodoDTO();
					todoDTO.setId(rs.getInt("id"));
					todoDTO.setUserId(rs.getInt("user_id"));
					todoDTO.setTitle(rs.getString("title"));
					todoDTO.setDescription(rs.getString("description"));
					todoDTO.setDueDate(rs.getString("due_date"));
					todoDTO.setCompleted(rs.getString("completed"));
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return todoDTO;
	}

	@Override
	public List<TodoDTO> getTodoByUserId(int userId) {
		
		String sql = " SELECT * FROM todos WHERE user_id = ? ";
		List<TodoDTO> list = new ArrayList<>();
		
		try (Connection conn = dataSource.getConnection()){
			
			try (PreparedStatement pstmt = conn.prepareStatement(sql)){
				
				pstmt.setInt(1, userId);
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					TodoDTO todoDTO = new TodoDTO();
					
//					String tempId = rs.getString("id");
//					todoDTO.setId(Integer.parseInt(tempId));
					
					todoDTO.setId(rs.getInt("id"));
					todoDTO.setTitle(rs.getString("title"));
					todoDTO.setDescription(rs.getString("description"));
					todoDTO.setDueDate(rs.getString("due_date"));
//					int tempC = rs.getInt("completed");
//					todoDTO.setCompleted(String.valueOf(tempC));
					todoDTO.setCompleted(rs.getString("completed"));
					todoDTO.setUserId(rs.getInt("user_id"));
					list.add(todoDTO);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	
	// List 는 인터페이스, 프로젝트에 맞는 구현 클래스를 선택해서 활용할 수 있어서
	
	@Override
	public List<TodoDTO> getAllTodos() {
		
		String sql = " SELECT * FROM todos ";
		List<TodoDTO> list = new ArrayList<TodoDTO>();
		
		try (Connection conn = dataSource.getConnection()){
			
			try (PreparedStatement pstmt = conn.prepareStatement(sql)){
				
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
					TodoDTO todoDTO = new TodoDTO();
					
					todoDTO.setId(rs.getInt("id"));
					todoDTO.setTitle(rs.getString("title"));
					todoDTO.setDescription(rs.getString("description"));
					todoDTO.setDueDate(rs.getString("due_date"));
					todoDTO.setCompleted(rs.getString("completed"));
					todoDTO.setUserId(rs.getInt("user_id"));
					
					list.add(todoDTO);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("todoList All : " + list.toString());
		
		return list;
	}

	@Override
	public void updateTodo(TodoDTO dto, int principalId) {
		
		int rowCount = 0;
		String sql = " UPDATE todos SET title = ?, description = ?, "
				+ " due_date = ?, completed = ? "
				+ " WHERE id = ? and user_id = ? ";
		
		try (Connection conn = dataSource.getConnection()){
			conn.setAutoCommit(false);
			
			try (PreparedStatement pstmt = conn.prepareStatement(sql)){
				pstmt.setString(1, dto.getTitle());
				pstmt.setString(2, dto.getDescription());
				pstmt.setString(3, dto.getDueDate());
				pstmt.setInt(4, dto.getCompleted() == "true" ? 1 : 0);
				pstmt.setInt(5, dto.getId());
				pstmt.setInt(6, dto.getUserId());
				rowCount = pstmt.executeUpdate();
				conn.commit();
				
			} catch (Exception e) {
				conn.rollback();
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("rowCount : " + rowCount);
		
	}
	
	
/**
 * 삭제 기능
 * id : Todos Pk
 * principalId : sesstion ID
 */
	@Override
	public void deleteTodo(int id, int principalId) {
		
		String sql = " DELETE FROM todos WHERE id = ? and user_id = ? ";
		
		try (Connection conn = dataSource.getConnection()){
			
			conn.setAutoCommit(false);
			
			try (PreparedStatement pstmt = conn.prepareStatement(sql)){
				
				pstmt.setInt(1, id);
				pstmt.setInt(2, principalId);
				pstmt.executeUpdate();
				conn.commit();
				
			} catch (Exception e) {
				conn.rollback();
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}

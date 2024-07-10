package com.tenco.controller;

import java.io.IOException;

import com.tenco.model.TodoDAO;
import com.tenco.model.TodoDAOImpl;
import com.tenco.model.TodoDTO;
import com.tenco.model.UserDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


// ../mvc/todo/xxx
@WebServlet("/todo/*")
public class todoController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private TodoDAO todoDAO;
	
	
	public todoController() {
		todoDAO = new TodoDAOImpl();
	}

	// http://localhost:8080/mvc/todo/todoForm
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getPathInfo();
		System.out.println("action : " + action);
		switch (action) {
		case "/todoForm":
			todoFormPage(request, response);
			break;
		case "/list":
			todolistPage(request, response);
			break;

		default:
			break;
		}
	}

	// http://localhost:8080/mvc/todo/list
	private void todolistPage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// 인증 검사
		HttpSession session = request.getSession();
		UserDTO principal = (UserDTO)session.getAttribute("principal");
		request.getContextPath();
		System.out.println("request.getContextPath() : " + request.getContextPath());

		if(principal == null) {
			response.sendRedirect(request.getContextPath() + "/user/signIn?message=invalid");
			return;
		}
		
		// todoList.jsp 페이지로 내부에서 이동 처리
		request.setAttribute("todoList", todoDAO.getTodoByUserId(principal.getId()));
		System.out.println(todoDAO.getTodoByUserId(principal.getId()));
		request.getRequestDispatcher("/WEB-INF/views/todoList.jsp").forward(request, response);
	}

	private void todoFormPage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// 로그인한 사용자만 접근을 하도록 설계
		HttpSession session = request.getSession();
		UserDTO principal = (UserDTO)session.getAttribute("principal");
		
		// 인증검사
		if(principal == null) {
			// 로그인을 안한 상태
			response.sendRedirect("/user/signIn?message=invalid");
			return;
		} 
		request.getRequestDispatcher("/WEB-INF/views/todoForm.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// form --> 데이터 추출(name 속성 기준)
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String dueDate = request.getParameter("dueDate");
		String completed = request.getParameter("completed");
		
		TodoDTO todoDTO = TodoDTO.builder()
				.title(title)
				.description(description)
				.dueDate(dueDate)
				.completed(completed)
				.build();
		
		// TODO - 추가 예정
		HttpSession session = request.getSession();
		UserDTO principal = (UserDTO)session.getAttribute("principal");
		
		
		
		//pricipal -- null 이라면 -- 로그인 페이지 이동 처리
		todoDAO.addTodo(todoDTO, principal.getId());
	}

}

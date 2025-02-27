package com.tenco.controller;

import java.io.IOException;
import java.util.List;

import com.tenco.model.TodoDAO;
import com.tenco.model.TodoDTO;
import com.tenco.model.UserDAO;
import com.tenco.model.UserDAOImpl;
import com.tenco.model.UserDTO;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/test/*")
public class TestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UserDAO userDAO;
	private TodoDAO todoDAO;
	
    public TestController() {
        super();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
    	userDAO = new UserDAOImpl();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getPathInfo();
//		switch (action) {
//		case "/byId":
//			// http://localhost:8080/mvc/test/byId
//			//userDAO.getUserByUsername("홍길동");
//			UserDTO dto = UserDTO.builder().password("999").email("h@naver.com").build();
//			int count = userDAO.updateUser(dto, 3);
//			
//			System.out.println("count : " + count);
//			break;
//		default:
//			break;
//		}
		
		todoDAO.deleteTodo(1, 1);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

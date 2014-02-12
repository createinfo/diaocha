package com.ec.survey.ctrl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ec.survey.dao.DAOFactory;
import com.ec.survey.dao.UserDAO;
import com.ec.survey.dto.User;
import com.swufe.util.StringUtil;

public class UserManage extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String mutex1 = "";
		String op = request.getParameter("op");
		if ("addUser".equals(op)) {
			String email = request.getParameter("email");
			String nickname = StringUtil.encodeString(request.getParameter("nickname"));
			String password = request.getParameter("password");
			UserDAO dao = DAOFactory.getUserDAO();
			User user = new User();
			user.setEmail(email);
			user.setNickname(nickname);
			user.setPassword(password);
			try {
				dao.addUser(user);
				request.getSession().setAttribute("user", user);

				response.sendRedirect("../OpResult.jsp?op=reg&ret=true");
			} catch (Exception e) {
				response.sendRedirect("../OpResult.jsp?op=reg&ret=false");
			}

		} else if ("userLogin".equals(op)) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			UserDAO dao = DAOFactory.getUserDAO();
			User user;
			try {
				user = dao.findOneUserByEmail(email);
				if (user.getPassword().equals(password)) {
					request.getSession().setAttribute("user", user);
					response.sendRedirect("../OpResult.jsp?op=login&ret=true");
				} else {
					response.sendRedirect("../OpResult.jsp?op=login&ret=false");
				}
			} catch (Exception e) {
				response.sendRedirect("../OpResult.jsp?op=login&ret=false");
			}

		}
	}
}

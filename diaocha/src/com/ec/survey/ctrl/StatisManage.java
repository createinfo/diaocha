package com.ec.survey.ctrl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ec.survey.dao.AnswersheetDAO;
import com.ec.survey.dao.DAOFactory;

public class StatisManage extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			String sid=request.getParameter("sid");
			String op=request.getParameter("op");
			
			if("DelSheet".equals(op)){
				Long asid=Long.valueOf(request.getParameter("asid"));
				AnswersheetDAO adao=DAOFactory.getAnswersheetDAO();
				boolean ret=adao.delAnswersheet(asid);
				if(ret==true)
					response.sendRedirect("../admin/ShowSheets.jsp?sid="+sid);
				else
					response.sendRedirect("../admin/OpResult.jsp?op=DelSheet&ret=false");
			}
	}

}

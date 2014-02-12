package com.ec.survey.ctrl;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ec.survey.dao.AdminDAO;
import com.ec.survey.dao.DAOFactory;
import com.ec.survey.dto.Admin;
import com.swufe.util.StringUtil;

public class AdminManage1 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 
		String mutex1="";
		String op=request.getParameter("op");
		if("AddAdmin".equals(op)){
			String username=StringUtil.encodeString(request.getParameter("username"));
			String pwd=StringUtil.encodeString(request.getParameter("pwd"));
			String email = StringUtil.encodeString(request.getParameter("email"));
			String phone = StringUtil.encodeString(request.getParameter("phone"));
			AdminDAO dao=DAOFactory.getAdminDAO();
			Admin admin=new Admin();
			admin.setA_user(username);
			admin.setA_pass(pwd);
			admin.setA_email(email);
			admin.setA_phone(phone);
			boolean ret1=dao.addAdmin(admin);
			if(ret1)
				response.sendRedirect("../admin/AdminList.jsp");
			else
				response.sendRedirect("../admin/OpResult.jsp?op=default&ret=false&words="+URLEncoder.encode("增加管理员出错！请联系管理员", "UTF-8") );
		}
		else if("DelAdmin".equals(op)){
			Long aid=Long.valueOf(request.getParameter("aid"));
			AdminDAO dao=DAOFactory.getAdminDAO();
			
			boolean ret1=dao.delAdmin(aid);
			if(ret1)
				response.sendRedirect("../admin/AdminList.jsp");
			else
				response.sendRedirect("../admin/OpResult.jsp?op=default&ret=false&words="+URLEncoder.encode("删除管理员出错！请联系管理员", "UTF-8"));
			
		}else if("EditAdmin".equals(op)){
			Long aid=Long.valueOf(request.getParameter("aid"));
			String oldpwd=request.getParameter("oldpwd");
			String pwd=request.getParameter("pwd");
			String username=request.getParameter("username");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			AdminDAO dao=DAOFactory.getAdminDAO();
			if(dao.checkPwd(username, oldpwd)!=true){
				response.sendRedirect("../admin/OpResult.jsp?op=default&ret=false&words="+URLEncoder.encode("原始密码错误,修改失败！", "UTF-8"));
				return;
			}
			synchronized(mutex1){
			Admin admin=dao.findAdmin(aid);
			admin.setA_email(email);
			admin.setA_phone(phone);
			admin.setA_pass(pwd);
			boolean ret1=dao.updateAdmin(admin);
			
			if(ret1)
				response.sendRedirect("../admin/AdminList.jsp");
			else
				response.sendRedirect("../admin/OpResult.jsp?op=default&ret=false&words="+URLEncoder.encode("编辑管理员出错！请联系管理员", "UTF-8"));
			}
		}
	}

}

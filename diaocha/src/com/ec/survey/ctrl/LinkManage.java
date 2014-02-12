package com.ec.survey.ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ec.survey.dao.AdminDAO;
import com.ec.survey.dao.DAOFactory;
import com.ec.survey.dao.LinkDAO;
import com.ec.survey.dto.Admin;
import com.ec.survey.dto.Link;
import com.swufe.util.StringUtil;

public class LinkManage extends HttpServlet {
	public LinkManage() {
		super();
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String mutex1="";
		String op=request.getParameter("op");
		if("AddLink".equals(op)){
			String url=StringUtil.encodeString(request.getParameter("url"));
			String sitename=StringUtil.encodeString(request.getParameter("sitename"));
			String description=StringUtil.encodeString(request.getParameter("description"));
			 
			LinkDAO dao=DAOFactory.getLinkDAO();
			Link link=new Link();
			link.setLAddtime(new java.sql.Date(new java.util.Date().getTime()));
			link.setLInfo(description);
			link.setLIsLock(false);
			link.setLName(sitename);
			link.setLUrl(url);
			
			boolean ret1=dao.addLink(link);
			if(ret1)
				response.sendRedirect("../admin/FriendLinkAdmin.jsp");
			else
				response.sendRedirect("../admin/OpResult.jsp?op=default&ret=false&words="+URLEncoder.encode("增加友情链接出错！请联系管理员", "UTF-8"));
		}
		else if("DelLink".equals(op)){
			Long lid=Long.valueOf(request.getParameter("lid"));
			LinkDAO dao=DAOFactory.getLinkDAO();
			
			boolean ret1=dao.delLink(lid);
			if(ret1)
				response.sendRedirect("../admin/FriendLinkAdmin.jsp");
			else
				response.sendRedirect("../admin/OpResult.jsp?op=default&ret=false&words="+URLEncoder.encode("删除友情链接出错！请联系管理员", "UTF-8"));
			
		}else if("EditLink".equals(op)){
			Long lid=Long.valueOf(request.getParameter("lid"));
			String url=StringUtil.encodeString(request.getParameter("url"));
			String sitename=StringUtil.encodeString(request.getParameter("sitename"));
			String description=StringUtil.encodeString(request.getParameter("description"));
			LinkDAO dao=DAOFactory.getLinkDAO();
			synchronized(mutex1){
			Link link=dao.findLink(lid);
			link.setLId(lid);
			link.setLInfo(description);
			link.setLIsLock(false);
			link.setLName(sitename);
			link.setLUrl(url);
			boolean ret1=dao.updateLink(link);
			if(ret1)
				response.sendRedirect("../admin/FriendLinkAdmin.jsp");
			else
				response.sendRedirect("../admin/OpResult.jsp?op=default&ret=false&words=编辑友情链接出错！");
			}
		}
	}
 
}

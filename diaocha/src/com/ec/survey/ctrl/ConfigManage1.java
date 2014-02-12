package com.ec.survey.ctrl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ec.survey.dao.ConfigDAO;
import com.ec.survey.dao.DAOFactory;
import com.ec.survey.dto.Config;
import com.swufe.util.StringUtil;

public class ConfigManage1 extends HttpServlet {

	private static final long serialVersionUID = 1L;	
	public ConfigManage1() {
		super();
	}
	public void destroy() {
		super.destroy(); 
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ConfigDAO configdao=DAOFactory.getConfigDAO();
		Config config=new Config();
		config.setCSiteName(StringUtil.encodeString(request.getParameter("siteName")));
		config.setCSiteUrl(StringUtil.encodeString(request.getParameter("siteURL")));
		config.setCIsOpen(Boolean.valueOf(request.getParameter("siteOpen")));
		config.setCCloseWord(StringUtil.encodeString(request.getParameter("closeWord")));
		config.setCopyright(StringUtil.encodeString(request.getParameter("copyright")));
		boolean ret=configdao.updateConfig(config);
		if (ret==true)
			response.sendRedirect("../admin/OpResult.jsp?op=SysConfig&ret=true");
		else
			response.sendRedirect("../admin/OpResult.jsp?op=SysConfig&ret=false");
	}
	public void init() throws ServletException {
	}
}

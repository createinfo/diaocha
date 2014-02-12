package com.ec.survey.ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ec.survey.dao.AnswersheetDAO;
import com.ec.survey.dao.DAOFactory;
import com.ec.survey.dao.QuestionDAO;
import com.ec.survey.dao.SurveyDAO;
import com.ec.survey.dto.Survey;
import com.swufe.util.StringUtil;

@SuppressWarnings("serial")
public class SurveyManage extends HttpServlet {
 
	public SurveyManage() {
		super();
	}
	public void destroy() {
		super.destroy(); 
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		String mutex1="";
		String mutex2="";
		if("AddSurvey".equals(request.getParameter("op"))){ // 问卷添加
			SurveyDAO surveydao=DAOFactory.getSurveyDAO(); 
			Survey survey=new Survey();
			survey.setSName(StringUtil.encodeString(request.getParameter("Survey_name")));
			survey.setSAuthor(StringUtil.encodeString(request.getParameter("Survey_author")));
			survey.setSDesc(StringUtil.encodeString(request.getParameter("Survey_description")));
			survey.setSCreateDate(new Date());
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			try {
				survey.setSExpireDate(sdf.parse(request.getParameter("Survey_ExpireDate")));
			} catch (ParseException e) {
				 out.println("wrong DATE format.please check it!");
			}
			survey.setSIsOpen(Boolean.valueOf(request.getParameter("Survey_isOpen")));
			if(request.getParameter("Survey_isImg")!=null)
				survey.setSImg(request.getParameter("imgfilepath"));
			if(request.getParameter("Survey_isPassword")!=null)
				survey.setSPassword(request.getParameter("Survey_Password1"));
		
			survey.setSIsAudited(false);
			survey.setSUsehits(0L);
			boolean ret=surveydao.addSurvey(survey);
			if(ret==true)
				response.sendRedirect("../admin/OpResult.jsp?op=SurveyAdd&ret=true");
			else
				response.sendRedirect("../admin/OpResult.jsp?op=SurveyAdd&ret=false");
 
			 
		}
		else if("SurveyAudi".equals(request.getParameter("op"))){ //  问卷审核
			Boolean audit=Boolean.valueOf(request.getParameter("audit"));
			SurveyDAO surveydao=DAOFactory.getSurveyDAO();
			synchronized(mutex1){
			Survey  survey=surveydao.findSurvey(Long.valueOf(request.getParameter("sid")));
			if(audit==true)
				survey.setSIsAudited(true);
			else
				survey.setSIsAudited(false);
			boolean ret=surveydao.updateSurvey(survey);
			if(ret==true)
				response.sendRedirect("../admin/SurveyAudi.jsp");
			else
				response.sendRedirect("../admin/OpResult.jsp?op=SurveyAudi&ret=false");
			}
		}else if("EditSurvey".equals(request.getParameter("op"))){ //  问卷修改
			SurveyDAO surveydao=DAOFactory.getSurveyDAO(); 
			String sid=request.getParameter("Survey_id");
			synchronized(mutex2){
			Survey survey=surveydao.findSurvey(Long.valueOf(request.getParameter("Survey_id")));
			survey.setSName(StringUtil.encodeString(request.getParameter("Survey_name")));
			survey.setSAuthor(StringUtil.encodeString(request.getParameter("Survey_author")));
			survey.setSDesc(StringUtil.encodeString(request.getParameter("Survey_description")));
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			try {
				survey.setSExpireDate(sdf.parse(request.getParameter("Survey_ExpireDate")));
			} catch (ParseException e) {
				 out.println("wrong DATE format.please check it!");
			}
			survey.setSIsOpen(Boolean.valueOf(request.getParameter("Survey_isOpen")));
			if(request.getParameter("Survey_isImg")!=null){
				if(request.getParameter("imgfilepath")!=null)
				survey.setSImg(request.getParameter("imgfilepath"));
			}else
				survey.setSImg(null);
			
			if(request.getParameter("Survey_isPassword")!=null){
				survey.setSPassword(request.getParameter("Survey_isPassword"));
			}else
				survey.setSPassword(null);
			
			boolean ret=surveydao.updateSurvey(survey);
			if(ret==true)
				response.sendRedirect("../admin/SurveyAdmin.jsp");
			else
				response.sendRedirect("../admin/OpResult.jsp?op=SurveyEdit&ret=false");
			}
		}else if("DelSurvey".equals(request.getParameter("op"))){  //   问卷删除
			Long surveyId=Long.valueOf(request.getParameter("sid"));
			SurveyDAO surveydao=DAOFactory.getSurveyDAO(); 
			boolean ret1=surveydao.delSurvey(surveyId);
			QuestionDAO questiondao=DAOFactory.getQuestionDAO();
			
			if(ret1==true)
				ret1=questiondao.delQuestions(surveyId);
		
			if(ret1==true){
				AnswersheetDAO adao=DAOFactory.getAnswersheetDAO();
				ret1=adao.delAnswersheets(surveyId);
			}
			if(ret1==true)
				response.sendRedirect("../admin/SurveyAdmin.jsp");
			else
				response.sendRedirect("../admin/OpResult.jsp?op=SurveyDel&ret=false");
		}
			
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doGet(request,response);
	}
	public void init() throws ServletException {
	}

}

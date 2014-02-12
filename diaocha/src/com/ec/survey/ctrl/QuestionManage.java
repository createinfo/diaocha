package com.ec.survey.ctrl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ec.survey.dao.DAOFactory;
import com.ec.survey.dao.QuestionDAO;
import com.ec.survey.dto.Question;
import com.swufe.util.StringUtil;

@SuppressWarnings("serial")
public class QuestionManage extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 String mutex = ""; 
		String op=request.getParameter("op");
		if("AddQuestion".equals(op)){
			String sid=request.getParameter("sid");
			String type=request.getParameter("type");
			String qhead=StringUtil.encodeString(request.getParameter("qHead"));
			String qbody=StringUtil.encodeString(request.getParameter("qBody")); 
			String qresult=StringUtil.encodeString(request.getParameter("qResult"));
			String qimg=request.getParameter("qImg");
			Question question=new Question();
			question.setSurvey(Long.valueOf(sid));
			question.setQType(Long.valueOf(type));
			question.setQHead(qhead);
			question.setQBody(qbody);
			question.setQResult(qresult);
			question.setQImg(qimg);
			question.setQOrder(0L);
			if(qbody==null){
				qbody="";
			}
			String [] qbodys=qbody.split("&\\$\\$&");
			String spliter="";
			for(int i=1;i<qbodys.length;i++)
				if(i==qbodys.length-1)
					spliter=spliter+"null&null";
				else
					spliter=spliter+"null&";
			question.setQJdtz(spliter);
			QuestionDAO dao=DAOFactory.getQuestionDAO();
			boolean ret=dao.addQuestion(question);
			if(ret==true)
				response.sendRedirect("../admin/OpResult.jsp?op=Question&ret=true&sid="+sid);
			else{
				response.sendRedirect("../admin/OpResult.jsp?op=Question&ret=false");
			}
		}else if("DelQuestion".equals(op)){
			String sid=request.getParameter("sid");
			String qid=request.getParameter("qid");
			QuestionDAO dao=DAOFactory.getQuestionDAO();
			boolean ret=dao.delQuestion(Long.valueOf(qid));
			if(ret==true)
				response.sendRedirect("../admin/QuestionAdmin.jsp?sid="+sid);
			else
				response.sendRedirect("../admin/OpResult.jsp?op=Question&ret=false");
		}else if("EditQuestion".equals(op)){
			String qhead=StringUtil.encodeString(request.getParameter("qHead"));
			String qbody=StringUtil.encodeString(request.getParameter("qBody")); 
			String qresult=StringUtil.encodeString(request.getParameter("qResult"));
			String sid=request.getParameter("sid");
			String qid=request.getParameter("qid");
			String type=request.getParameter("type");
			String qimg=request.getParameter("qImg");
			QuestionDAO dao=DAOFactory.getQuestionDAO();
			synchronized(mutex){
			Question question=dao.findQuestion(Long.valueOf(qid));
			
			question.setQType(Long.valueOf(type));
			question.setQHead(qhead);
			question.setQBody(qbody);
			question.setQImg(qimg);
			String [] qbodys=qbody.split("&\\$\\$&");
			String spliter="";
			for(int i=1;i<qbodys.length;i++)
				if(i==qbodys.length-1)
					spliter=spliter+"null&null";
				else
					spliter=spliter+"null&";
			question.setQJdtz(spliter);
			question.setQOrder(0L);
			boolean ret=dao.updateQuestion(question);
			if(ret==true)
				response.sendRedirect("../admin/OpResult.jsp?op=Question&ret=true&sid="+sid);
			else
				response.sendRedirect("../admin/OpResult.jsp?op=Question&ret=false");
			}
		}
	}

}

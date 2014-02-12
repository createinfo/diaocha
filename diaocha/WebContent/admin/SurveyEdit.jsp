<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.ec.survey.dao.*" %>
<%@ page import="com.ec.survey.dto.Survey" %>
<%@ page import="com.swufe.util.*" %>

<%
	String Survey_id=request.getParameter("sid");
	SurveyDAO surveydao=DAOFactory.getSurveyDAO();
	Survey survey=surveydao.findSurvey(Long.valueOf(Survey_id));
 %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <script language="JavaScript" src="../Js/Func.js"></script>
<script language="javascript">window.onload=tableFix;</script>
<link rel="stylesheet" href="../Css/Admin.css" type="text/css" />
<script language="javascript" type="text/javascript" src="../Js/Date.js"></script>
<script type="text/javascript">
var words="<%=StringUtil.encodeString(request.getParameter("words") ) %>";
if (words!="null" && words!="") 
	alert(words);
</script>
</head>
<body>
<div id="admin_surveyAdd_main">
<form name="from1" action="../servlet/SurveyManage.do?op=EditSurvey" method="post" onSubmit="return CheckForm();">

<table width="585" border="0" cellspacing="0" cellpadding="0" class="table">
  <tr>
    <th>问卷编辑</th>
    <th>&nbsp;</th>
    <th width="374"><span class="R">*</span> 为必填项目 </th>
  </tr>
  <tr>
    <td>问卷编号</td>
    <td><input name="Survey_id"  value=<%=Survey_id %> type="text" size="50" id="Survey_id" readonly></td>
    <td width="374">  </td>
  </tr>
  <tr>
    <td>问卷名称</td>
    <td><input id="Survey_name" name="Survey_name" type="text" size="50" ></td>
    <td width="374"><span class="R">*</span> 问卷的名称，既问卷的总标题</td>
  </tr>
  <tr>
    <td>问卷发起人(单位)</td>
    <td><input id="Survey_author" name="Survey_author" type="text" size="50" ></td>
    <td width="374"><span class="R">*</span> 问卷发起人，此问卷的所有单位</td>
  </tr>
  <tr>
    <td>问卷描述</td>
    <td><textarea rows="2" name="Survey_description" cols="60" onpropertychange="this.style.posHeight=this.scrollHeight"><%=survey.getSDesc() %></textarea></td>
    <td> &nbsp;&nbsp;对此问卷的简单描述，这段描述将放在问卷前</td>
  </tr>
  <tr>
    <td>问卷结束日期</td>
    <td><input type="text" name="Survey_ExpireDate"   readOnly="" onClick="setDay(this);" value="<%=survey.getSExpireDate() %>"></td>
    <td><span class="R">*</span> 问卷结束日期，到期后该问卷将不能接受</td>
  </tr>
  <tr>
    <td>公开此问卷调查</td>
    <td><input name="Survey_isOpen" type="checkbox" value=true <%=survey.getSIsOpen()?"checked":"" %>></td>
    <td><span class="R">*</span> 不公开的问卷，只能通过编号进行访问</td>
  </tr>
  <tr>
    <td width="">为问卷添加主题图片</td>
    <td width=""><input type="checkbox" name="Survey_isImg"  value="true" onClick="SwitchHidden('hidden_isImg');">
	    <font color=red><%=survey.getSImg()==null||"".equals(survey.getSImg())?"":"已上传图片"+survey.getSImg() %></font>
		<div id="hidden_isImg" style="display:none;"> 
		
		<input type=hidden name="imgfilepath" id="imgfilepath" value=<%=survey.getSImg() %>>
		   <iframe name=myiframe src="upload.jsp" frameborder="0" width="400" height="50"></iframe>
      </div>	</td>
    <td width=""> &nbsp;&nbsp;主题图片的格式必须为.JPG或GIF</td>
  </tr>
  
  <tr>
    <td>设置问卷密码</td>
    <td><input type="checkbox" name="Survey_isPassword" value="<%=survey.getSPassword() %>" onClick="SwitchHidden('hidden_isPassword');">
	   <%=survey.getSPassword()==null||"".equals(survey.getSPassword())?"":"<font color=red>已设置密码,不改请留空</font>" %>
	<div id="hidden_isPassword" style="display:none;"> 
		
           输入密码：<input type="password" name="Survey_Password1" id="Survey_Password1"><br/>
           确认密码：<input type="password" name="Survey_Password2" id="Survey_Password2" onBlur="return CheckPassword('Survey_Password1','Survey_Password2','4');">
      </div>	</td>
    <td> &nbsp;&nbsp;选中此项，只有输入正确的密码才能完成问卷</td>
  </tr>
  <tr>
    <td width="179">&nbsp;</td>
    <td>
	<div id="button">
	<input type="submit" name="submit" value="完成"><input type="Button" name="reset1" value="取消" onclick="history.back()">
	</div>	</td>
    <td>&nbsp;</td>
  </tr>
</table>  
</form>
</div>
<div id="admin_surveyAdd_bottom">
</div>
</body>
<script>
document.forms[0].Survey_name.value="<%=survey.getSName().replace("\"","\\\"") %>";
document.forms[0].Survey_author.value="<%=survey.getSAuthor().replace("\"","\\\"") %>";

</script>
<%=survey.getSImg()==null||"".equals(survey.getSImg())?"":"<script>document.forms[0].Survey_isImg.click();</script>" %>
<%=survey.getSPassword()==null||"".equals(survey.getSPassword())?"":"<script>document.forms[0].Survey_isPassword.click();</script>" %>
</html>
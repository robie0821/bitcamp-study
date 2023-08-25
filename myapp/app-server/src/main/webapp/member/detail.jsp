<%@ page
  language="java"
  pageEncoding="UTF-8"
  contentType="text/html;charset=UTF-8"
  trimDirectiveWhitespaces="true"
  errorPage="/error.jsp"%>

<%@ page import="bitcamp.myapp.vo.Member"%>

<jsp:useBean id="memberDao" type="bitcamp.myapp.dao.MemberDao" scope="application"/>
<%
    Member member = memberDao.findBy(Integer.parseInt(request.getParameter("no")));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>회원</title>
</head>
<body>

<jsp:include page="../header.jsp"/>

<h1>회원</h1>

<%
  if (member == null) {
%>
<p>해당 번호의 회원이 없습니다!</p>
<%
  } else {
%>
<form action='/member/update.jsp' method='post' enctype='multipart/form-data'>
<table border='1'>
<%
    out.println(String.format("<tr><th style='width:120px;'>사진</th>"
        + " <td style='width:300px;'>"
        + (member.getPhoto() == null ? "<img style='height:80px' src='/images/avatar.png'>":
          "<a href='https://kr.object.ncloudstorage.com/bitcamp/member/%s'>"
          + "<img style='height:80px' src='http://fxnxwqenirtk19010936.cdn.ntruss.com/member/%1$s?type=f&w=60&h=80&faceopt=true&ttype=jpg'>"
          + "</a>")
        + "<input type='file' name='photo'>"
        + "</td></tr>\n", member.getPhoto()));
%>
<tr><th style='width:120px;'>번호</th>
<td style='width:300px;'><input type='text' name='no' value='<%=member.getNo()%>' readonly></td></tr>
<tr><th>이름</th>
<td><input type='text' name='name' value='<%=member.getName()%>'></td></tr>
<tr><th>이메일</th>
<td><input type='email' name='email' value='<%=member.getEmail()%>'></td></tr>
<tr><th>암호</th>
<td><input type='password' name='password'></td></tr>
<%
    out.println(String.format("<tr><th>성별</th>\n"
        + " <td><select name='gender'>\n"
        + " <option value='M' %s>남자</option>\n"
        + " <option value='W' %s>여자</option></select></td></tr>\n",
        (member.getGender() == 'M' ? "selected" : ""),
        (member.getGender() == 'W' ? "selected" : "")));
%>
</table>

<div>
<button>변경</button>
<button type='reset'>초기화</button>
<a href='/member/delete.jsp?no=<%=member.getNo()%>'>삭제</a>
<a href='/member/list.jsp'>목록</a>
</div>
</form>
<%
  }
%>
<jsp:include page="../footer.jsp"/>

</body>
</html>
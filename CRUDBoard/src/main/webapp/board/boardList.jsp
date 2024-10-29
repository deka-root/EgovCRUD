<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" 		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" 	uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" 		uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="spring" 	uri="http://www.springframework.org/tags" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board List</title>
</head>

<style>
body {
	font-size:9px;
}
button{
	font-size:9px;
}
table {
	width:600px;
	border-collapse:collapse; /*  테두리를 합치기 설정 */
}
th,td {
	border:1px solid #cccccc; /* 테두리, 색상 */
	padding:3px; /* 셀 내부 여백 */
}
.input {
	width:98%; /* input 너비 */
}
.textarea {
	width:98%; /* 텍스트 영역 너비 */
	height:70px; /* 텍스트 영역 높이 */
}
.custom-select {
    width: 50px; /* 너비 조정 */
    height: 18px; /* 높이 조정 */
    font-size: 9px; /* 글자 크기 조정 */
    margin-bottom: 7px;
}
.custom-input {
	width: 100px; /* 너비 조정 */
    height: 12px; /* 높이 조정 */
    font-size: 9px; /* 글자 크기 조정 */
}
</style>

<body>
	<div style="width:600px; margin-bottom: 10px; text-align:center; font-size:15px; margin-bottom: 20px;">게시판</div>
	<div style="width:600px; margin-top:5px; text-align:left; margin-bottom: -18px;">Total: ${ total }</div>
	<div style="width:600px; text-align:right; ">
	
	<form name="searchFrm" method="post" action="boardList.do">
		<select name="searchBox" id="searchBox" class="custom-select">
			<option value="title">제목</option>
			<option value="author">글쓴이</option>
			<option value="content">내용</option>
		</select>
		<input type="text" name="searchText" id="searchText" class="custom-input" />
		<button type="submit">검색</button>
	</form>
	
	</div>
<table>
	
	<tr>
		<th width="15%">번호</th>
		<th width="40%">제목</th>
		<th width="15%">글쓴이</th>
		<th width="20%">작성일</th>
		<th width="10%">조회수</th>
	</tr>
	
	<c:set var="cnt" value="${ reverseRowNo }" />
	
	<c:forEach var="result" items="${ resultList }">
	<tr align="center">
		<td><c:out value="${ cnt }" /></td>
		<td align="left">
			<a href="boardDetail.do?boardNo=${ result.boardNo }"><c:out value="${ result.title }" /></a>
		</td>
		<td><c:out value="${ result.author }" /></td>
		<td><c:out value="${ result.createdDate }" /></td>
		<td><c:out value="${ result.viewCount }" /></td>
	</tr>
	
		<c:set var="cnt" value="${ cnt-1 }" />
	
	</c:forEach>
</table>

<div style="width:600px; margin-top:5px; text-align:center; ">
	<c:forEach var="i" begin="1" end="${totalPage}">
    	<a href="boardList.do?page=${i}">${i}</a>
	</c:forEach>
</div>

<div style="width:600px; margin-top:5px; text-align:right; ">
	<button type="button" onclick="location='boardWrite.do'">글쓰기</button>
</div>
	
	
</body>
</html>
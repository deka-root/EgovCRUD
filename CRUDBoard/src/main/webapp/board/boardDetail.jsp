<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" 		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" 		uri="http://java.sun.com/jsp/jstl/functions" %>

<!-- 게시글 내용 개행처리 -->
<% pageContext.setAttribute("newline", "\n"); %>
<c:set var="content" value="${ fn:replace(boardVO.content, newline, '<br>' ) }"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board Detail</title>

<!-- jQuery 추가 (CDN) -->
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>

<!-- jQuery UI 추가 (CDN) -->
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<!-- jQuery UI CSS 추가 -->
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

</head>

<style>
caption {
	margin-bottom: 10px; /* 캡션과 테이블 사이의 간격 설정 */
	font-size: 15px;
}
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
.textarea {
	width:98%; /* 텍스트 영역 너비 */
	height:70px; /* 텍스트 영역 높이 */
}
</style>

<body>

<form id="frm">
<table>
	<caption>게시판 상세</caption>
	<tr>
		<th width="20%">제목</th>
		<td width="35%">${ boardVO.title }</td>
		<th width="20%">글쓴이</th>
		<td width="25%">${ boardVO.author }</td>
	</tr>
	<tr>
		<th>등록일</th>
		<td>${ boardVO.createdDate }</td>
		<th>수정일</th>
		<td>${ boardVO.updatedDate }</td>
	</tr>
	<tr>
		<th>내용</th>
		<td colspan="3" height="60">
			${ content }
		</td>
	</tr>
	<tr>
    	<c:if test="${not empty boardVO.file}">
    		<th>첨부파일</th>
    		<td colspan="3">${boardVO.file.fileName}
    		<button type="button" onclick="location='download.do?fileNo=${boardVO.file.fileNo}'">다운로드</button>
    		</td>
		</c:if>
	</tr>
	<tr>
		<td colspan="4" style="text-align: center;">
			<button type="button" onclick="location='boardList.do'">목록</button>
			<button type="button" onclick="location='boardModify.do?boardNo=${boardVO.boardNo}'">수정</button>
			<button type="button" onclick="location='passWrite.do?boardNo=${boardVO.boardNo}'">삭제</button>
		</td>
	</tr>
</table>
</form>

</body>
</html>
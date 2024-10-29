<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" 		uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board Modify</title>

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

<!-- script -->
<script>
function fn_submit() {
	/* 게시글 작성 시 공백란 처리(jQuery) */
	$("#title").val( $.trim($("#title").val()) );
	if( $("#title").val() == "") {
		alert("제목을 입력해주세요.");
		$("#title").focus();
		return false;
	}
	if( $.trim($("#pass").val() ) == "") {
		alert("암호를 입력해주세요.");
		$("#pass").focus();
		return false;
	}
	
	// 암호는 공백 사용불가 처리
	if ($("#pass").val().indexOf(' ') !== -1) {
	    alert("암호는 공백을 사용할 수 없습니다.");
	    $("#pass").focus();
	    return false;
	}
	
	var formData = new FormData($("#frm")[0]); // FormData 객체 생성

    $.ajax({
        type: "POST",
        data: formData,
        url: "boardModifySave.do",
        dataType: "text", // 리턴 타입
        processData: false, // jQuery가 데이터를 처리하지 않도록 설정
        contentType: false, // jQuery가 Content-Type 헤더를 설정하지 않도록 설정

        success: function(result) { // controller -> 1
            if(result == "1") {
                alert("등록했습니다.");
                location = "boardList.do";
            } else if(result == "-1") {
                alert("암호가 일치하지 않습니다.")
            } else {
                alert("등록 실패입니다.");
            }
        },
        error: function() {
            alert("오류발생");
        }
    });
}
</script>

<body>

<form id="frm" enctype="multipart/form-data">

<!-- 게시글 번호 -->
<input type="hidden" name="boardNo" value="${ boardVO.boardNo }">

<table>
	<caption>게시판 상세</caption>
	
	<tr>
		<th width="20%">제목</th>
		<td width="80%"><input type="text" name="title" id="title" class="input" value="${ boardVO.title }"></td>
	</tr>
	<tr>
		<th>암호</th>
		<td><input type="password" name="pass" id="pass"></td>
	</tr>
	<tr>
		<th>글쓴이</th>
		<td><input type="text" name="author" id="author" value="${ boardVO.author }"></td>
	</tr>
	<tr>
		<th>내용</th>
		<td><textarea name="content" id="content" class="textarea">${ boardVO.content }</textarea></td>
	</tr>
	
	<tr>
    <th>첨부파일</th>
    <td colspan="3">
        <input type="file" name="file.uploadFile" id="fileInput" style="display:none;" onchange="updateFileName();">
        <label for="fileInput" style="cursor:pointer;">
            <span id="fileName">${not empty boardVO.file ? boardVO.file.fileName : '선택된 파일 없음'}</span>
        </label>
    </td>
</tr>

	<pre>${boardVO}</pre>
	<pre>${boardVO.file}</pre>
	<tr>
		<th colspan="2">
		<button type="submit" onclick="fn_submit(); return false;">저장</button>
		<button type="button" onclick="location='boardList.do'">취소</button>
		</th>
	</tr>
</table>
</form>

</body>
<script>
function updateFileName() {
    var input = document.getElementById('fileInput');
    var fileNameDisplay = document.getElementById('fileName');
    
    if (input.files.length > 0) {
        fileNameDisplay.textContent = input.files[0].name; // 선택된 파일 이름으로 업데이트
    } else {
        fileNameDisplay.textContent = '선택된 파일 없음'; // 파일이 없을 경우 메시지
    }
}
</script>
</html>
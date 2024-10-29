<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board Write</title>

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
.input {
	width:98%; /* input 너비 */
}
.textarea {
	width:98%; /* 텍스트 영역 너비 */
	height:70px; /* 텍스트 영역 높이 */
}

/* 파일 버튼 높이 설정 */
input[type="file"] {
    height: 18px;
    font-size: 9px;
}
</style>

<!-- script -->
<script>
function fn_submit() {
	/* 게시글 작성 시 공백란 처리(jQuery) */
	if( $.trim($("#title").val() ) == "") {
		alert("제목을 입력해주세요.");
		$("#title").focus();
		return false;
	}
	if( $.trim($("#pass").val() ) == "") {
		alert("제목을 입력해주세요.");
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
		/* 전송 전 */
		type: "POST",
		data: formData,
		url: "boardWriteSave.do",
		dataType: "text",
		processData: false, // jQuery가 데이터 처리하지 않도록 설정
        contentType: false, // 콘텐츠 유형을 설정하지 않도록 설정
		
		/* 전송 후 */
		success: function(data) {	// controller -> "ok", "fail"
			if(data == "ok") {
				alert("등록했습니다.");
				location = "boardList.do";
			} else {	// "fail"
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

<form name="frm" id="frm" enctype="multipart/form-data">
<table>
	<caption>게시판 등록</caption>
	<tr>
		<th width="20%">제목</th>
		<td width="80%"><input type="text" name="title" id="title" class="input"></td>
	</tr>
	<tr>
		<th>암호</th>
		<td><input type="password" name="pass" id="pass"></td>
	</tr>
	<tr>
		<th>글쓴이</th>
		<td><input type="text" name="author" id="author"></td>
	</tr>
	<tr>
		<th>내용</th>
		<td><textarea name="content" id="content" class="textarea"></textarea></td>
	</tr>
	<tr>
        <th>첨부파일</th>
        <td><input type="file" name="file.uploadFile"></td>    
    </tr>
	<tr>
		<th colspan="2">
		<button type="submit" onclick="fn_submit(); return false;">저장</button>
		<button type="button" onclick="location='boardList.do'">취소</button>
		</th>
	</tr>
	
</table>
</form>

</body>
</html>
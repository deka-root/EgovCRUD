<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" 		uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>암호입력</title>

<!-- jQuery 추가 (CDN) -->
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>

<!-- jQuery UI 추가 (CDN) -->
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<!-- jQuery UI CSS 추가 -->
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

</head>

<script>
$(function() {
	
	$("#delBtn").click(function() {
		var pass = $("#pass").val();
		pass = $.trim(pass);
		if(pass == "") {
			alert("암호를 입력하세요.");
			$("#pass").focus();
			return false;
		}
		var sendData = "boardNo=${boardNo}&pass="+pass;
		
		$.ajax({
		    type: "POST",
		    data: sendData,		//JSON 설정
		    url: "boardDelete.do",
		    dataType: "text", // 리턴 타입

		    success: function(result) { // controller -> 1
		        if(result == "1") {
		            alert("삭제 완료됐습니다.");
		            location = "boardList.do";
		        } else if(result == "-1") {
		        	alert("암호가 일치하지 않습니다.")
		        } else {
		            alert("삭제 실패입니다.");
		        }
		    },
		    error: function() {
		        alert("오류발생");
		    }
		});
	});
});


</script>

<body>

<table>
	<tr>
		<th>암호입력</th>
		<td><input type="password" id="pass"></td>
		<td><button type="button" id="delBtn">삭제</button></td>
	</tr>
</table>

</body>
</html>
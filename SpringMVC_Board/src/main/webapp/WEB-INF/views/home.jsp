<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Home</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>

<script type="text/javascript">
	$(function() {
		let chk = '${check}';
		if (chk === '1') {
			Swal.fire({         //작은 경고창필요시 참고 : https://wooncloud.tistory.com/12
				icon : 'success', // Alert 타입
				title : '회원가입 성공', // Alert 제목
				text : '로그인해 주세요!!!', // Alert 내용
			});
		} else if (chk === '2') {
			Swal.fire({         
				icon : 'error', // Alert 타입
				title : '로그인 실패', // Alert 제목
				text : '아이디 또는 비번이 오류입니다.!!!', // Alert 내용
			});
		}
	});
</script>
</head>
<body>
<h3>${exMsg}</h3>
	<h1>Home.jsp-뭐라고쓸까</h1>
	<form action="access" name="logFrm" method="post">
		<table border="1">
			<tr>
				<td colspan="2" align="center" bgcolor="skyblue">로그인</td>
			</tr>
			<tr>
				<td><input type="text" name="m_id"></td>
				<td rowspan="2"><button>로그인</button>
			</tr>
			<tr>
				<td><input type="password" name="m_pwd"></td>
			</tr>
			<tr>
				<td colspan="2" align="center" bgcolor="skyblue">com.board.icia</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><a href="./joinFrm">회원가입</a></td>
			</tr>
		</table>
	</form>
</body>
</html>

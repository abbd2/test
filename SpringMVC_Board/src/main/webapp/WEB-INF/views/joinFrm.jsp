<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.subject {
	text-align: center;
	height: 50px;
}
</style>
</head>
<body>
	<form name="joinFrm" action="memberJoin" method="post"
		onsubmit="return check()">
		<table border="1">
			<tr>
				<td colspan="2" class="subject">회원가입</td>
			</tr>
			<tr>
				<td width="100">ID</td>
				<td><input type="text" id="id" name="m_id"> <input
					type="button" id="checkId" value="중복검사" />
					<div id="result"></div></td>
			</tr>
			<tr>
				<td width="100">PW</td>
				<td><input type="password" id="pw" name="m_pwd"></td>
			</tr>
			<tr>
				<td width="100">NAME</td>
				<td><input type="text" id="name" name="m_name"></td>
			</tr>
			<tr>
				<td width="100">BIRTH</td>
				<td><input type="text" id="birth" name="m_birth"></td>
			</tr>
			<tr>
				<td width="100">ADDR</td>
				<td><input type="text" id="addr" name="m_addr"></td>
			</tr>
			<tr>
				<td width="100">PHONE</td>
				<td><input type="text" id="phone" name="m_phone"></td>
			</tr>
			<tr>
				<td colspan="2" class="subject"><input type="submit"
					value="회원가입"></td>
			</tr>
		</table>
	</form>


	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script>
		$('#checkId').on('click',function() {
					if ($('#id').val() != '') {
						$.ajax({
							method : 'get',
							url : 'member/userid',
							data : 'm_id=' + $('#id').val(),
							//dataType : 'text',
							success : function(data, status, xhr) {
								$('#result').html(data).css('color', 'blue');
								console.log("data=", data);
								console.log("status=", status);
								console.log("xhr=", xhr);

							},
							error : function(xhr, status) {
								$('#result').html(xhr.responseText).css(
										'color', 'red');
								console.log("xhr=", xhr);
								console.log("status=", status);
							}
						});
					}
				}); //on End

		function check() { //jQuery validate(유효성 검사)
			let frm = document.joinFrm;
			let length = frm.length - 1;
			for (var i = 0; i < length; i++) {
				if (frm[i].value == "") {
					alert(frm[i].name + "을 입력하세요!!!");
					frm[i].focus();
					return false; //실패시
				}
			}
			return true; //성공시 서버로 전송
		}
	</script>

</body>
</html>







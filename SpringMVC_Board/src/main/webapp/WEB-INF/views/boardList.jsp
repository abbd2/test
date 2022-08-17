<!-- boardList.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="resources/js/jquery.serializeObject.js"></script>
<script>
console.log(${bList2}); //JSON.parse(${bList2}) //json-->js객체
function logout(){
	$('#logoutFrm').submit();
}
function articleView(num){
	$('#articleView_layer').addClass('open'); //모달박스 나타남
	$.ajax({
		type:'get',
		url:'contents',
		data:{bNum:num},
		//dataType:'html', //생략 가능
		success:function(data){
			console.log(data);
			$('#contents_layer').html(data);
		},
		error:function(error){
			console.log(error);
		}
		
	}); //ajax End
}//end


$(function(){  
	
//모달박스 해제
	var $layerWindow=$('#articleView_layer');
	$layerWindow.find("#bg_layer").on('mousedown',function(evt){
		console.log(evt);
		$layerWindow.removeClass('open');
	}); //on End
	$(document).keydown(function(evt){
		console.log(evt);
		if(evt.keyCode!=27) return;
		else if($layerWindow.hasClass('open')){
			$layerWindow.removeClass('open');
		}
	}); //keydown End
	
});//ready End

$(function(){
	var result='${bNum}';
	if(result===''){
		return;
	}
	else if(parseInt(result)>0){
		alert('${bNum}'+'번 글을 삭제하였다');
	}
});


</script>
<style>
html, body {
	height: 100%;
	margin: 0
}

#articleView_layer {
	display: none;
	position: fixed;
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%
}

#articleView_layer.open {
	display: block;
	color: red;
}

#articleView_layer #bg_layer {
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background: #000;
	opacity: .5;
	filter: alpha(opacity = 50);
	z-index: 100
}

#contents_layer {
	position: absolute;
	top: 40%;
	left: 40%;
	width: 400px;
	height: 400px;
	margin: -150px 0 0 -194px;
	padding: 28px 28px 0 28px;
	border: 2px solid #555;
	background: #fff;
	font-size: 12px;
	z-index: 200;
	color: #767676;
	line-height: normal;
	white-space: normal;
	overflow: scroll
}
</style>
</head>
<body>
${msg}<br>
	<h3>게시판 리스트</h3>
	<c:if test="${!empty id}">
		<div align="right">
			<!-- <a href="logout"> 로그아웃</a> -->
			<form id="logoutFrm" action="logout" method="post">
				<a href="javascript:logout()">로그아웃</a>
			</form>
		</div>
	</c:if>
	<table id="one_table">
		<tr height="30">
			<td width="80" bgcolor="pink" align="center">ID</td>
			<td>${mb.m_id}</td>
		</tr>
		<tr height="30">
			<td width="80" bgcolor="pink" align="center">NAME</td>
			<td>${mb.m_name}</td>
		</tr>
		<tr height="30">
			<td width="80" bgcolor="pink" align="center">GNAME</td>
			<td>${mb.m_grade}</td>
		</tr>
		<tr height="30">
			<td width="80" bgcolor="pink" align="center">POINT</td>
			<td>${mb.m_point}</td>
		</tr>
	</table>
	<br>
	<table>
		<tr bgcolor="skyblue" height="30">
			<th width="100">번호</th>
			<th width="100">제목</th>
			<th width="100">작성자</th>
			<th width="100">작성일</th>
			<th width="100">조회수</th>
		</tr>
		<c:forEach var="board" items="${bList}">
			<tr height="25">
				<td align="center">${board.b_num}</td>
				<!-- href="#" 페이지 맨위로 이동뒤 이벤트 발생
				     href="#;" 페이지 현재위치에서 이벤트 발생 -->
				<td align="center"><a href="#"
					onclick="articleView(${board.b_num})"> ${board.b_title}</a></td>
				<td align="center">${board.b_id}</td>
				<td align="center">${board.b_date}</td>
				<td align="center">${board.b_views}</td>
			</tr>
		</c:forEach>
	</table>
	
	<!-- 페이징 -->
	<div align="center"> ${paging}</div>
	
	<!-- 모달(Modal) 박스 -->
	<div id="articleView_layer">
		<div id="bg_layer"></div>
		<div id="contents_layer"></div>
	</div>
</body>

</html>









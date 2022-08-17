<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
 //console.log(${board});   //json----> js로 제어
</script>
</head>
<body>
	<h3>BoardContents * ReplyContents</h3>
	<a href="boardDelete?bNum=${board.b_num}">삭제</a>
	<table>
		<tr height="30">
			<td width="100" bgcolor="lightgray" align="center">NUM</td>
			<td colspan="5">${board.b_num}</td>
		</tr>
		<tr height="30">
			<td bgcolor="lightgray" align="center">WRITER</td>
			<td width="150">${board.b_id}</td>
			<td bgcolor="lightgray" align="center">DATE</td>
			<td width="150">${board.b_date}</td>
			<td bgcolor="lightgray" align="center">VIEWS</td>
			<td width="150">${board.b_views}</td>
		</tr>
		<tr height="30">
			<td bgcolor="lightgray" align="center">TITLE</td>
			<td colspan="5">${board.b_title}</td>
		</tr>
		<tr height="30">
			<td bgcolor="lightgray" align="center">CONTENTS</td>
			<td colspan="5">${board.b_contents}</td>
		</tr>

	</table>

	<form name="rFrm" id="rFrm">
		<!-- 댓글 입력 -->
		<table>
			<tr>
				<td><textarea rows="3" cols="50" name="r_contents"
						id="r_contents"></textarea></td>
				<td><input type="button" value="댓글전송"
					onclick="replyInsert(${board.b_num})"
					style="width: 70px; height: 50px"></td>
			</tr>
		</table>
	</form>
	<!-- 댓글 출력 -->
	<table id="rTable">
		<c:forEach var="reply" items="${rList}">
			<tr height="25" align="center">
				<td width="100">${reply.r_id}</td>
				<td width="100">${reply.r_contents}</td>
				<td width="100">${reply.r_date}</td>
			</tr>
		</c:forEach>
	</table>

	<script type="text/javascript">
 function replyInsert(bNum){
	 
	 //let obj={};
	 //obj.r_bnum=bNum;
	 //obj.r_contents=$('#r_contents').val();
	 let obj=$("#rFrm").serializeObject();   //form태그 데이터를 js객체로 변환(단 file태그가 있을땐 FormData객체사용)
	 obj.r_bnum=bNum;  //데이터 추가
	 
	 console.log(obj);  // 확인
	 
	  $.ajax({
		 method:'post',  //json넘길시 post
		 //url: 'board/str',  //produces테스트
		 url: 'board/replyInsert3',
		 //1.get, post : urlencoded방식
		 //data: obj,
		 //data: {r_bnum:bNum, r_contents:$('#r_contents').val()},
	     //data: {r_bnum:bNum, $('#rFrm').serialize()},
		 //contentType: 'application/x-www-form-urlencoded;charset=UTF-8', //기본값
		 //2.json방식으로 넘김(서버에서 @ResquestBody로 받아야됨)
		 data: JSON.stringify(obj), 		 
		 contentType:'application/json; charset=UTF-8',    //서버에서 @RequestBody로 받을것.
		 //dataType: 'json',//xml, html... //생략 가능 서버에서 리턴타입을 결정함
		 success: function(data){
			 console.log("1:",data);
			 let rlist='';
			 for(let reply of data ){
					rlist+='<tr height="25" align="center">'
					+'<td width="100">'+reply.r_id+'</td>'
					+'<td width="200">'+reply.r_contents+'</td>'
					+'<td width="200">'+reply.r_date+'</td></tr>';
			 }; 
			/*  $.each(data, function(i,reply) {
				 rlist+='<tr height="25" align="center">'
						+'<td width="100">'+reply.r_id+'</td>'
						+'<td width="200">'+reply.r_contents+'</td>'
						+'<td width="200">'+reply.r_date+'</td></tr>';	
			}); */
			 /* $.each(data.rList, function(i,reply) {
			 rlist+='<tr height="25" align="center">'
					+'<td width="100">'+reply.r_id+'</td>'
					+'<td width="200">'+reply.r_contents+'</td>'
					+'<td width="200">'+reply.r_date+'</td></tr>';	
			}); 
			$('#rTable').html(rlist); */
			
			$('#r_contents').val('').focus();
		},error: function(err){
			console.log(err);
			$('#rTable').html(err.responseText); //예외 메세지 출력
 	    }

	 }); //ajax End	 
 }//replyInsert End
 
 
</script>
</body>
</html>












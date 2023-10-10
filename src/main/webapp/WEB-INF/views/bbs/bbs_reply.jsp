<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자료실 답변폼</title>
<link rel="stylesheet" type="text/css" href="./css/bbs.css" />
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script src="./js/bbs.js"></script>
</head>
<body>
   <div id="bsW_wrap">
      <h2 class="bsW_title">자료실 답변</h2>
      <form method="post" action="bbs_reply_ok"
         onsubmit="return write_check();" >
         <%--답변글 히든값 --%>
         <input type="hidden" name="bbs_ref" value="${b.bbs_ref}">
         <%--bbs_ref는 원본글과 답변글을 묶어주는 글 그룹번호 역할 --%>
         <input type="hidden" name="bbs_step" value="${b.bbs_step}">
         <%--원본글이면 0, 첫번째 답변글이면 1, 두번째 답변글이면 2. 즉 원본글과 답변글을 구분하는
         번호값이면서 몇번째 답변글인가를 알려준다. --%>
         <input type="hidden" name="bbs_level" value="${b.bbs_level}" >
         <%--답변글 정렬순서 --%>
         
         <%--페이징에서 책갈피 기능을 만들기 위해서 쪽번호를 전달 --%>
         <input type="hidden" name="page" value="${page}" >
         
         <table id="bsW_t">
            <tr>
               <th>글쓴이</th>
               <td><input name="bbs_name" id="bbs_name" size="14" /></td>
            </tr>
            <tr>
               <th>글제목</th>
               <td><input name="bbs_title" id="bbs_title" size="33" 
               value="Re:${b.bbs_title}"/></td>
            </tr>
            <tr>
               <th>비밀번호</th>
               <td><input type="password" name="bbs_pwd" id="bbs_pwd"   size="14" /></td>
            </tr>
            <tr>
               <th>글내용</th>
               <td><textarea name="bbs_cont" id="bbs_cont" rows="8" cols="34"></textarea></td>
            </tr>
            
         </table>
         <div id="bsW_menu">
            <input type="submit" value="답변" /> <input type="reset" value="취소"
               onclick="$('#bbs_name').focus();"> <input type="button"
               value="목록" onclick="location='bbs_list?page=${page}';">
         </div>
      </form>
   </div>
</body>
</html>
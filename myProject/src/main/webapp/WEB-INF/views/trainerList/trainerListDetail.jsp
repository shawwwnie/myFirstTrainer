<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<style>


.wrap{
	text-align:center;
	margin-left:35%;
	margin-right:35%;
	background-color:#CEF6EC;
	margin-bottom:1%;
	border-radius:40px;
	box-shadow: 10px 10px #D8D8D8;
	padding: 10px;
}
.info{
	width:100%;
	height:220px;
	flex-wrap:wrap;
}

.profile_matching{
margin-top:2%;
width:50%;
height:200px;
float:left;
}

.trainer_info{

width:35%;
float:left;
height:130px;
margin-top:10%;
margin-left:5%;
}

.btns{
background-color: white;
  border: none;
  border-radius: 1rem;
  color: @gray-dark;
  padding: 0.5rem;
  text-transform: lowercase;
  margin-top:2%;
  margin-bottom:1%;
}

.btns:hover{
	color:#0de9ef;
	cursor:pointer;
}

/*댓글*/
.commentArea{
	padding:15px;
	text-align: left;
}

input.form-control{
	width:70%; 
	height:30px; 
	border-radius: 15px; 
	border:none;"
}

</style>


<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	
	var writer_mem_auth = ${user.mem_auth};
	var writer_mem_num = ${user.mem_num};
	var tl_mem_num = ${trainer.mem_num};
	
	/* 댓글 입력하기 */
	$(document).ready(function(){
		
		commentList();
		
		$('#submit_comment').click(function(){
			if($('#comment').val()==''){
				alert('댓글 내용을 입력해주세요!');			
				return;
			}
			
			$.ajax({
				url:'submit_tlcomment.do',
				type:'post',
				data:{comment:$('#comment').val(),
					  'tl_mem_num':tl_mem_num,
					  'writer_mem_num':writer_mem_num},
				dataType:'json',
				cache:false,
				timeout:30000,
				success:function(data){
					if(data.result){
						$('#comment').val('');
					}
					commentList();
				},
				error:function(){
					alert('댓글 입력 네트워크 오류 발생!');
				}
			});
		});	
		

		/* //다음 댓글 보기 버튼 클릭시 데이터 추가
		$('.paging-button input').click(function(){
			var pageNum = currentPage + 1;
			selectData(pageNum);
		}); */
		
	});
	
	/* 댓글리스트 불러오기 */
	function commentList(){
	    $.ajax({
	    	url : 'trainer_comment.do',
	        type : 'post',
	        dataType : 'json',
	        cache:false,
	        timeout:30000,
	        data : {tl_mem_num:tl_mem_num},
	        success : function(data){	
	            var output ='';
	            count = data.count;
	            rowCount = data.rowCount;
	            var list = data.list;
	            
	            if(count < 0 || list == null){
					alert('목록 호출 오류 발생!');
				} else {
		            $(list).each(function(index, item){
		            	output += '<div class="commentArea" style="border-top:1px solid darkgray;">';
		            	
		            	if(item.writer_mem_num == writer_mem_num){
		            		output += '<div><b><a href="${pageContext.request.contextPath}/boardFeed/feedList.do">'+item.mem_id +'</a></b>';
		            	} else{
		            		output += '<div><b><a href="${pageContext.request.contextPath}/boardFeed/otherFeedList.do?mem_num='+item.writer_mem_num+'">'+item.mem_id + '</a></b>';
		            	}
		            	
		            	output += '&nbsp;&nbsp;' + item.str_date + '&nbsp;';
		            	
						if(item.writer_mem_num == writer_mem_num){
							output += '<span class="commentModify'+item.tlc_num+'">&nbsp;|&nbsp;<a onclick="commentUpdate('+item.tlc_num+',\''+item.tl_comment+'\');">수정</a>';
							output += '&nbsp;|&nbsp;<a onclick="commentDelete('+item.tlc_num+');">삭제</a>&nbsp;|&nbsp;</span>'; 
							
						} else if(writer_mem_auth == 0){ //댓글쓴이와 로그인한 사람이 같거나 관리자 로그인일 경우 삭제 버튼 활성화)	
							output += '&nbsp;|&nbsp;<a onclick="commentDelete('+item.tlc_num+');">삭제</a>&nbsp;|&nbsp;</span>'; 
							
						}	            	                	                
						output += '<div class="commentContent'+item.tlc_num+'"> <p> <sub style="color:#d0d0d0;">>  </sub>'+item.tl_comment +'</p></div>';						
						output += '</div></div>';
		            });
	    
		         /* //paging button 처리
		        	if(currentPage>=Math.ceil(count/rowCount)){
		        		//다음 페이지가 없음
		        		$('.paging-button').hide();
		        	}else{
		        		//다음 페이지가 존재
		        		$('.paging-button').show();
		        	}
				 */	            
	            $(".commentList").html(output);
				}
	        }
	    });
	};
	
	
	function commentUpdate(tlc_num, tl_comment){
		var output1 = '';
		var output2 = '';
		output1 += '<a onclick="commentUpdateCommit('+tlc_num+');">수정완료</a>';
		output1 += '&nbsp;|&nbsp;<a onclick="commentDelete('+tlc_num+');">삭제</a>';
		output2 += '<div class="input-group">';
		output2 += '<input type="text" style="margin-top:10px;" class="form-control" id="content_'+tlc_num+'" value="'+tl_comment+'"/>';
		output2 += '</div>';
	    $('.commentModify'+tlc_num).html(output1);
	    $('.commentContent'+tlc_num).html(output2);		
	}
	
	
	function commentUpdateCommit(tlc_num){
		var update_comment = $('#content_'+tlc_num).val();
		$.ajax({
			url:'update_tlcomment.do',
			type:'post',
			data:{'tlc_num':tlc_num,
				  'update_comment':update_comment},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				if(data.result){
					alert('댓글 수정 완료');
					commentList();
				}
			},
			error:function(){
				alert('댓글 수정 네트워크 오류 발생!');
			}
		});
	}
	
	function commentDelete(tlc_num){
		var choice = window.confirm('삭제하시겠습니까?');
		if(!choice){
			return;
		}
		$.ajax({
			url:'delete_tlcomment.do',
			type:'post',
			data:{'tlc_num':tlc_num},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				if(data.result){
					commentList();
				}
			},
			error:function(){
				alert('댓글삭제 네트워크오류');
			}
		});
	}
	
	

	
</script>
<div class="wrap">
	<div class="info">
		<div class="profile_matching">
			<ul style="text-align:right; margin-top:30px;">
				<li>
					<c:if test="${empty trainer.mem_picName}">
						<img src="../resources/images/cameraImage.jpg" style="max-width:200px; max-height:200px;">
					</c:if>
					<c:if test="${!empty trainer.mem_picName}">
			        <img src="${pageContext.request.contextPath}/trainerList/trainerImage.do?mem_num=${trainer.mem_num}" 
							width="250" height="200">
			        </c:if>
				</li>	
			</ul>
		</div>
		
		<div class="trainer_info">
			<ul style="text-align:left;">
				<li><input type="hidden" id="tl_mem_num" name="tl_mem_num" value="${trainer.mem_num}"></li>
				<li>이름 : ${trainer.mem_name}</li>
				<li>
					<c:if test="${trainer.mem_gender == 1}">성별: 남자</c:if> 
					<c:if test="${trainer.mem_gender == 2}">성별: 여자</c:if>
				</li>
		
				<li>
					<c:if test="${trainer.exp == 0}">신입 트레이너</c:if> 
					<c:if test="${trainer.exp > 0 && trainer.exp < 5}">경력 : ${trainer.exp}년 </c:if>
					<c:if test="${trainer.exp == 5}">경력 : ${trainer.exp}년 이상 </c:if>
				</li>
		
				<li>이메일 : ${trainer.mem_email}</li>
				
				<li>근무지 : ${trainer.g_addr}</li>
		
			</ul>
		</div>
	</div>
	<hr style="height:4px; background-color:black;">
	<div class="trainer_info2">
		<ul>
			<li style="margin-top:1%; margin-bottom:1%; color:red;"> 
					<!-- 일반회원일 경우 && 매칭한 사람이 없을 경우 -->
					<c:if test="${user.mem_auth == 1 && user.t_num == 0 && empty matching.mat_from}">
						<button id="tButton" class="btns" type="button">
			        	트레이닝 신청하기
			        	</button>
			        	<script>
							var tButton = document.getElementById('tButton');
							tButton.onclick=function(){
								var choice = window.confirm('신청하시겠습니까?');
								if(choice){
									location.href='matching.do?mem_num=${trainer.mem_num}';
								}
							};
						</script>
			        </c:if>
			        <!-- 일반회원일 경우  && 현재 트레이너가 아닌 다른사람과 매칭관계인 경우 -->	
		        	<c:if test="${user.mem_auth == 1 && user.t_num != trainer.mem_num && user.t_num != 0}">
		        	현재 다른 트레이너와 트레이닝중입니다.
		        	</c:if>
		        	<!-- 트레이너일 경우 -->
		        	<c:if test="${user.mem_auth == 2}">
		        	일반회원만 트레이닝 신청이 가능합니다.
		        	</c:if>
		        	<!-- 일반회원일경우 && 해당 트레이너와 이미 매칭관계일 경우 -->
		        	<c:if test="${user.mem_auth ==1 && user.t_num == trainer.mem_num}">
					현재 ${trainer.mem_name}에게 트레이닝 받는 중입니다.        	
		        	</c:if>
		        	<!-- 일반회원일경우 && 해당 트레이너에게 이미 매칭신청을 했을 경우 -->
		        	<c:if test="${user.mem_auth ==1 && matching.mat_to == trainer.mem_num}">
		        	트레이너의 수락을 기다리는 중입니다.
		        	</c:if>
		        	<br>		
			</li>
			
			<li>
				<c:if test="${trainer.mem_intro == null}">자기소개: 최선을 다해 몸짱을 만들어 드리겠습니다!!</c:if>
				<c:if test="${trainer.mem_intro != null}">자기소개: ${trainer.mem_intro}</c:if>
			</li>
			
			<li>
				<c:if test="${trainer.career == null}">작성된 이력 정보가 없습니다.</c:if>
				<c:if test="${trainer.career != null}">이력: ${trainer.career}</c:if>
			</li>
		</ul>
	</div>
	
	<!-- 본인이 로그인 한 상태일 경우에만 보여질 3개의 버튼 -->
	<div class="buttons">
		<c:if test="${user.mem_num == trainer.mem_num}">
			<div class="modify_btns">
				<ul>
					<li>
						<button class="btns" type="button" onclick="location.href='prUpdate.do'">프로필사진 변경</button>
						<button class="btns" type="button" onclick="location.href='introUpdate.do'">자기소개 수정</button>
						<button class="btns" type="button" onclick="location.href='${pageContext.request.contextPath}/member/myPage.do'">마이페이지</button>
					</li>
				</ul>
			</div>
		</c:if>
	</div>
	<div class="goback">
		<ul>
			<li>
				<button class="btns" type="button" onclick="location.href='trainerList.do'">돌아가기</button>
			</li>
		</ul>
	</div>
	
	<!-- -----------------------------댓글------------------------------ -->
	
	<form id="commentbox">
		<input type="hidden" id="sq_num" value="${boardSysqna.sq_num}">
		<input type="text" class="form-control" id="comment" name="comment" placeholder="  댓글을 입력하세요.">
		<input class="btns" type="button" id="submit_comment" value="등록">
	</form>
	<h3 style="text-align: left; maring-left:50px;">댓글</h3>
	<div class="container">
        <div class="commentList"></div>
    </div>
    <!-- -----------------------------댓글------------------------------ -->
</div>




<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>MainPage</title>
<!--     <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1"> -->

    <!-- Swiper CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/js/swiper-bundle.min.css">

    <!--style -->
    <style>
        html,
        body {
            position: relative;
            height: 100%;
        }

        body {
            font-family: Helvetica Neue, Helvetica, Arial, sans-serif;
            font-size: 14px;
            color: #000;
            margin: 0;
            padding: 0;
        }

        .swiper-container {
            width: 100%;
            height: 100%;
            margin-top:0;
            margin-bottom:0;
            margin-left: auto;
            margin-right: auto;
            padding: 0;
        }

        .swiper-slide {
            text-align: center;
            font-size: 18px;
            background: #fff;
            margin:0;

            /* Center slide text vertically */
            display: -webkit-box;
            display: -ms-flexbox;
            display: -webkit-flex;
            display: flex;
            -webkit-box-pack: center;
            -ms-flex-pack: center;
            -webkit-justify-content: center;
            justify-content: center;
            -webkit-box-align: center;
            -ms-flex-align: center;
            -webkit-align-items: center;
            align-items: center;
        }
    </style>
    
    <!-- main menu ul,li style -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mainPageStyle.css">
</head>

<body>

	<!-- ------------------------------------헤더------------------------------------- -->
	<!-- 우측 상단 메뉴 --> 
	<div id="sub-header" style="float:right">
		<!-- 로그인 상태 : 회원등급아이콘,id,알림아이콘,mypage,로그아웃,QnA 활성화 -->
		<c:if test="${!empty user}">
			<c:if test="${user.mem_auth == 0 }">
				[ <img class="grade-icon" alt="" src="${pageContext.request.contextPath}/resources/images/header/grade_a.png"> ]
			</c:if>
			<c:if test="${user.mem_auth == 1 }">
				[ <img class="grade-icon" alt="" src="${pageContext.request.contextPath}/resources/images/header/grade_m.png"> ]
			</c:if>
			<c:if test="${user.mem_auth == 2 }">
				[ <img class="grade-icon" alt="" src="${pageContext.request.contextPath}/resources/images/header/grade_t.png"> ]
			</c:if>
			[<span>${user.mem_id}</span>]
			<a href="${pageContext.request.contextPath}/boardNotice/list.do">알림</a>
			<c:if test="${noticeCount > 0 }">
			<span style="color:#0de9df; font-weight:bold">[${noticeCount}]</span>
			</c:if>
			<a href="${pageContext.request.contextPath}/member/myPage.do">MyPage</a>
			<a href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a>
			<a href="${pageContext.request.contextPath}/boardSysqna/list.do">QnA</a>
		</c:if>
			
		<!-- 비로그인 상태 : 회원가입,로그인 활성화 -->
		<c:if test="${empty user}">
			<a href="${pageContext.request.contextPath}/member/userType.do">회원가입</a>
			<a href="${pageContext.request.contextPath}/member/login.do">로그인</a>	
		</c:if>
	</div>
	<!-- 우측 상단 메뉴 -->

 
	<!-- 헤더 메인 메뉴 없는 링크 임시로 main으로 통일 해 둠 -->
	<div id="main-header" class="align-center" style="z-index: 2;">
		<ul class="main-menu">
			<li class="main-logo">
				<a href="${pageContext.request.contextPath}/main/main.do">메인로고</a>
			</li>
			<li class="homeTraining">
				<a href="${pageContext.request.contextPath}/homeTraining/hwList.do">홈트레이닝</a>
				<ul class="ht-sub submenu" style="z-index: 2;">
					<li><a href="${pageContext.request.contextPath}/homeTraining/hwPartList.do?keyword=전신">전신</a></li>
					<li><a href="${pageContext.request.contextPath}/homeTraining/hwPartList.do?keyword=어깨">어깨</a></li>
					<li><a href="${pageContext.request.contextPath}/homeTraining/hwPartList.do?keyword=가슴">가슴</a></li>
					<li><a href="${pageContext.request.contextPath}/homeTraining/hwPartList.do?keyword=등">등</a></li>
					<li><a href="${pageContext.request.contextPath}/homeTraining/hwPartList.do?keyword=하체">하체</a></li>
					<li><a href="${pageContext.request.contextPath}/homeTraining/hwPartList.do?keyword=스트레칭">스트레칭</a></li>
				</ul>
			</li>
			<li>
				<img class="bar" alt="" src="${pageContext.request.contextPath}/resources/images/header/bar.png">
			</li>
			<li class="nutriment">
				<a href="${pageContext.request.contextPath}/nutriment/nutrimentGuide.do">식단</a>
				<ul class="nutri-sub submenu" style="z-index: 2;">
					<li><a href="${pageContext.request.contextPath}/nutriment/nutrimentGuide.do">식단 가이드</a></li>
					<li><a href="${pageContext.request.contextPath}/nutriment/nutriList.do">영양성분 검색</a></li>
					<li><a href="${pageContext.request.contextPath}/nutriment/nutrimentCal.do">일일 권장 영양소</a></li>
				</ul>
			</li>
			<li>
				<img class="bar" alt="" src="${pageContext.request.contextPath}/resources/images/header/bar.png">
			</li>
			<li class="training-diary">
				<a href="${pageContext.request.contextPath}/boardFeed/myFeed.do">트레이닝 다이어리</a>
				<ul class="dt-sub submenu" style="z-index: 2;">
					<li><a href="${pageContext.request.contextPath}/boardFeed/myFeed.do">피드</a></li>
					<c:if test="${user.mem_auth!=1}">
						<li><a href="${pageContext.request.contextPath}/boardFeed/myMembersFeed.do">내 회원의 피드</a></li>
					</c:if>
					<li><a href="${pageContext.request.contextPath}/boardFeed/feedList.do">마이 퍼스널</a></li>
					<li><a href="${pageContext.request.contextPath}/boardFeed/feedReco.do">탐색</a></li>
				</ul>
			</li>
			<li>
				<img class="bar" alt="" src="${pageContext.request.contextPath}/resources/images/header/bar.png">
			</li>
			<li class="board">
				<a href="${pageContext.request.contextPath}/boardFree/list.do">게시판</a>
				<ul class="board-sub submenu" style="z-index: 2;">
					<li><a href="${pageContext.request.contextPath}/boardFree/list.do">자유 게시판</a></li>
					<li><a href="${pageContext.request.contextPath}/boardTip/list.do">팁 게시판</a></li>
				</ul>
			</li>
			<li>
				<img class="bar" alt="" src="${pageContext.request.contextPath}/resources/images/header/bar.png">
			</li>
			<li class="trainer">
				<a href="${pageContext.request.contextPath}/trainerList/trainerList.do">트레이너</a>
				<ul class="trainer-sub submenu" style="z-index: 2;">
					<li><a href="${pageContext.request.contextPath}/trainerList/trainerList.do">트레이너 리스트</a></li>
					<li><a href="${pageContext.request.contextPath}/boardTranqna/list.do">트레이너 Q&A</a></li>
				</ul>
			</li>
		</ul>
	</div>

	<!-- ------------------------------------헤더------------------------------------- -->
	
	<!-- ------------------------------------바디------------------------------------- -->
    <!-- Swiper -->
  	<div class="swiper-container" style="z-index: 1;">
        <div class="swiper-wrapper">
            <div class="swiper-slide"><img class="swiper-image" alt="" src="${pageContext.request.contextPath}/resources/images/swiper/image1.png"></div>
            <div class="swiper-slide"><img class="swiper-image" alt="" src="${pageContext.request.contextPath}/resources/images/swiper/image2.png"></div>
            <div class="swiper-slide"><img class="swiper-image" alt="" src="${pageContext.request.contextPath}/resources/images/swiper/image3.png"></div>
            <div class="swiper-slide"><img class="swiper-image" alt="" src="${pageContext.request.contextPath}/resources/images/swiper/image4.png"></div>
            <div class="swiper-slide"><img class="swiper-image" alt="" src="${pageContext.request.contextPath}/resources/images/swiper/image5.png"></div>
        </div>
        <!-- Add Pagination -->  
        <div class="swiper-pagination"></div>
    </div>
      

    <!-- Swiper JS 링크-->
    <script src="${pageContext.request.contextPath}/resources/js/swiper-bundle.min.js"></script>

    <!-- Swiper script  swiper 스크립트는 바디에 있어야 동작 하므로 옮기지 말 것-->
    <script>  
        var swiper = new Swiper('.swiper-container', {
            direction: 'vertical',
            slidesPerView: 1,
            spaceBetween: 1,
            mousewheel: true,
            pagination: {
                el: '.swiper-pagination',
                clickable: true,
            },
        });
    </script>
    
    <!-- ------------------------------------바디------------------------------------- -->
    
    <!-- ------------------------------------푸터------------------------------------- -->
    <div class="align-center">
		<hr style="border:solid 3px #0de9df;">
		<img class="mainLogo" alt="" src="${pageContext.request.contextPath}/resources/images/header/main_logo_for_header.png">
	&copy; 2020 My First Trainner
	</div>
	<!-- ------------------------------------푸터------------------------------------- -->
	
</body>
</html>
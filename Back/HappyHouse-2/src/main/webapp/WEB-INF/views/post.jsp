<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Happy House</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Font Awesome icons (free version)-->
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
        <!-- Google fonts-->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet" type="text/css" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="/css/styles.css" rel="stylesheet" />
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
		  <link rel="stylesheet" href="/css/login.css">
		  <link rel="stylesheet" href="/css/styles2.css">
		      <link rel="stylesheet" href="/css/common.css">
		  <link href="/css/styles.css" rel="stylesheet" />
    <style>
        .table-wrapper {
            display: grid;
            grid-template-columns: minmax(0, auto) minmax(0, 1fr);
            grid-gap: 0;
        }

        .header-item,
        .description-item {
            padding: 1rem;
            border: px solid #aaa;
        },
        * {
		font-family: SUIT-Medium;
		}
    </style>
    <style>
		* {
		font-family: SUIT-Medium;
		}
		</style>
	<style>
	td:nth-child(3) {
		width: 150px;
	}
	
	#cover {
		width: 150px;
	}
</style>
</head>
<body>
		<c:if test="${!empty msg}">
		<script type="text/javascript">
		alert("${msg}");
		</script>
		</c:if>
		
		<script type="text/javascript">
        $(document).ready(function () {
        	
        	if("${key}")
        		$("#skey").val("${key}").prop("selected", true);

        	$("#mvRegisterBtn").click(function () {
                location.href = "${root}/board/register";
            });
        	
        	$("#searchBtn").click(function () {
                var word = $("#sword").val();
                if(word == "") {
                	alert("모든 목록 조회!!!");
                }
                $("#searchform").attr("action", "${root}/board/list").submit();
            });
        	
        	$(".page-item").click(function() {
				$("#pg").val(($(this).attr("data-pg")));
				$("#pageform").attr("action", "${root}/board/list").submit();
			});
        	
        });
    </script>
		
        <!-- Navigation-->
        <div id="page-top">
        <nav class="navbar navbar-expand-lg navbar-dark fixed-top navbar-shrink" id="mainNav">
            <div class="container">
                <a class="navbar-brand" href="${root }/">Happy House</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    Menu
                    <i class="fas fa-bars ms-1"></i>
                </button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav text-uppercase ms-auto py-4 py-lg-0">
                        <li class="nav-item"><a class="nav-link" href="index.html" style="font-family: SUIT-Medium;">Home</a></li>
                      
                        <c:if test="${!empty userInfo }">
                        <li class="nav-item"><a class="nav-link" href="${root }/board/list" style="font-family: SUIT-Medium;">공지사항</a></li> 
                        <li class="nav-item"><a class="nav-link" href="${root }/list" style="font-family: SUIT-Medium;">실거래가 조회</a></li>
                        <li class="nav-item"><a class="nav-link" href="#team" style="font-family: SUIT-Medium;">관심 지역 둘러보기</a></li>
                        <li class="nav-item"><a class="nav-link" href="#contact" style="font-family: SUIT-Medium;">관심 지역 둘러보기</a></li>
                        </c:if>
                        
    
                        <li>
                        <c:if test="${empty userInfo }">
                        <div class="dropdown">
                                <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" style="font-family: SUIT-Medium;">로그인
                                </button>
                                <div class="dropdown-menu">
                                  <a class="dropdown-item" href="${root }/user/login" style="font-family: SUIT-Medium;">로그인</a>
                                  <a class="dropdown-item" href="${root }/user/regist" style="font-family: SUIT-Medium;">회원가입</a>
                                </div>
                        	</div>
                        </c:if>
                        <c:if test="${!empty userInfo }">
                        <div class="dropdown">
                                <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" style="font-family: SUIT-Medium;">로그아웃
                                </button>
                                 <div class="dropdown-menu">
                                  <a class="dropdown-item" href="${root }/user/logout" style="font-family: SUIT-Medium;">로그아웃</a>
                                  <a class="dropdown-item" href="${root }/user/mypage" style="font-family: SUIT-Medium;">마이페이지</a>
                                </div>
                        	</div>
                        </c:if>
                        </li>
                        
                    </ul>
                </nav>
        </div>
        <br><br><br><br><br>
        
		<div class="container">
		<h1>글 상세 정보</h1>
		<div><a href="${root }/board/list">글 목록 보기</a></div>
		<%-- c:if 태그를 이용해 request 영역에 book이 있다면 내용을 출력한다. --%>
		<c:if test="${!empty board }">
			<table class="table">
				<thead>
					<tr>
						<th>항목</th>
						<th colspan="2">내용</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>글 번호</td>
						<td id="num" name="num">${board.num }</td>
						<td rowspan="5"></td>
					</tr>
					<tr>
						<td>글 제목</td>
						<td>${board.title }</td>
					</tr>
					<tr>
						<td>글쓴이</td>
						<td>${board.id }</td>
					</tr>
					<tr>
						<td>글 작성 시간</td>
						<td>${board.createtime }</td>
					</tr>
					<tr>
						<td>글 내용</td>
						<td colspan="2">${board.content }</td>
					</tr>
				</tbody>
			</table>
			<c:if test="${!empty userInfo}">
				<div class="box-header with-border">
					<h3>Comment</h3>
				</div>
				<br />
				<div class="box-body">
					<form class="form-horizontal">
						<div class="form-group margin">
							<div class="col-sm-2">
								<h4 id="bookUserId" name="bookUserId">${userInfo.id}</h4>
							</div>
							<br />
							<div class="col-sm-10">
								<textarea class="form-control" id="bookComment"
									name="bookComment" rows="3" placeholder="Comment..."
									style="resize: none"></textarea>
							</div>
							<br />

							<div class="col-sm-2">
								<button type="button"
									class="btn btn-primary btn-block replyAddBtn">저장</button>
							</div>
						</div>
					</form>
				</div>
			</c:if>
			<table>
			<tbody id = "commentlist">
			</tbody>
			</table>
		</c:if>
	</div>

<!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>


</body>
</html>
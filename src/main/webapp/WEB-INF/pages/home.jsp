<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../../../favicon.ico">

     <title>主页</title>

<%--	  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/semantic/dist/semantic.min.css">--%>
<%--	  <script--%>
<%--			  src="https://code.jquery.com/jquery-3.1.1.min.js"--%>
<%--			  integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="--%>
<%--			  crossorigin="anonymous"></script>--%>
<%--	  <script src="${pageContext.request.contextPath}/css/semantic/dist/semantic.min.js"></script>--%>



	<!-- Bootstrap CSS -->
<%--	  <link rel="stylesheet" type="text/css" href="/css/bootstrap.css">--%>

	  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css">
	
	<!-- simple-line-icons CSS http://www.bootcdn.cn/simple-line-icons/-->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/simple-line-icons.css" >
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/iconfont/iconfont.css">


	  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/carousel.css" >

	  <!-- Animate CSS https://daneden.github.io/animate.css/-->
	  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/animate.css" >
	  <!-- simple-line-icons CSS http://www.bootcdn.cn/simple-line-icons/-->

	  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/player.css">


	  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/audio.css">
	  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/demo.css">
	  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cool.css">
    
<%--    <style>--%>
<%--	body{--%>
<%--		padding-bottom: 6rem;--%>
<%--	}--%>
<%--	.hide{--%>
<%--		display: none;--%>
<%--	}--%>
<%--	.ahover{--%>
<%--		color:#818182;--%>
<%--	}--%>
<%--	.img-logo{--%>
<%--		height:50px;--%>
<%--		width:50px;--%>
<%--		margin-top:1px;--%>
<%--	}--%>
<%--	.fl{--%>
<%--		float:left;--%>
<%--	}--%>
<%--	.float-right{--%>
<%--		float:right;--%>
<%--	}--%>
<%--	.media-left, .media-right, .media-body{--%>
<%--		display: table-cell;--%>
<%--		vertical-align: top;--%>
<%--	}--%>
<%--	.media-heading {--%>
<%--	   margin-top: 0;--%>
<%--	   margin-bottom: 5px;--%>
<%--	}--%>
<%--	.ds-time{--%>
<%--		color: #999;--%>
<%--	}--%>
<%--	.border-line{--%>
<%--		border-bottom: 1px solid #cfcfcf;--%>
<%--	}--%>
<%--	.border-line-color{--%>
<%--		border-bottom: 2px solid #2AD980;--%>
<%--	}--%>
<%--	.margin-left-m{--%>
<%--		margin-left:15px;--%>
<%--	}--%>
<%--	</style>--%>
    
  </head>
  <body style="padding: 0px;margin: auto">
    <header id="headerId">
    </header>
    
    <h1>&nbsp;</h1>
<%--    <div id="hot-delete">--%>
<%--    </div><!-- hot-delete End -->--%>
<%--	<h1>&nbsp;</h1>--%>
	
      <!-- Marketing messaging and featurettes
      ================================================== -->
      <!-- Wrap the rest of the page in another container to center all the content. -->
	<div id="hot">
      <div class="container marketing">
		<c:forEach items="${rowList}" var="row">
        <div class="row">
          	<c:forEach items="${row}" var="user">
		          <div class="col-lg-4">
		            <div class="card" style="width: 18rem;">
						  <img class="card-img-top" src="${pageContext.request.contextPath}/${user.photoAddress}" alt="Card image cap">
						  <div class="card-body">
						    <h5 class="card-title">Make myself known</h5>
						    <p class="card-text">${user.selfInfo}</p>
						  </div>
						  <div class="card-body">
						    <h5 class="card-title">Expected character</h5>
						    <p class="card-text">${user.expectedInfo}</p>
						  </div>
						  <div class="card-body">
						    <a href="javascript:;" onclick="reviewUserLoad(${user.userId})" class="card-link">${user.userName}</a>
						    <a href="javascript:;" title="admire this person" class="card-link <c:if test="${user.whetherLiking}">text-danger</c:if>" onclick="likingFunc(${user.userId})" id="userId${user.userId}" ><i class="icon-heart"></i></a>
						  </div>
					  </div>
		          </div><!-- /.col-lg-4 -->
          	 </c:forEach>
         </div><!-- /.row -->
        </c:forEach>
      </div><!-- /.container -->
      
      </div><!-- hot End -->




	<!-- 播放器栏 -->
	<nav class=" fixed-bottom hide" id="playerId">
		<div class="audio-box">
			<div class="audio-container">
				<div class="audio-cover" style="background-image: url("${pageContext.request.contextPath}/images/cover.jpg")></div>
				<div class="audio-view">
					<h1 class="audio-title">track stacking</h1>
					<!-- 进度条 -->
					<div class="audio-body">
						<div class="audio-backs">
							<div class="audio-this-time">00:00</div>
							<div class="audio-count-time">00:00</div>
							<div class="audio-setbacks">
								<i class="audio-this-setbacks" style="width: 0.0%;">
									<span class="audio-backs-btn"></span>
								</i>
								<span class="audio-cache-setbacks" style="width: 100%;">
								</span>
							</div>
						</div>
					</div>
					<!-- 控制选项 -->
					<div class="audio-btn">
						<div class="audio-select">
							<div class="audio-prev"></div>
							<!-- <div class="audio-play audio-stop"></div> -->
							<div class="audio-play"></div>
							<div class="audio-next"></div>
							<div class="icon-shuffle icon-loop" id="play-pattern" title="单曲循环"></div>
							<div class="audio-menu"></div>
							<div class="audio-volume"></div>
						</div>
						<div class="audio-set-volume">
							<div class="volume-box">
								<i style="height: 80%;"><span></span></i>
							</div>
						</div>
						<!-- 播放列表 -->
						<div class="audio-list">
							<div class="audio-list-head">
								<p>播放列表</p>
								<span class="menu-close">关闭</span>
							</div>
							<ul class="audio-inline"><li></li></ul>
						</div>
					</div><!-- 控制选项 End-->

				</div>
			</div>
		</div>
	</nav><!-- 播放器栏 End-->

      <!-- FOOTER -->
       <!--分页 -->
<%--    <nav class="navbar navbar-expand-md navbar-light navbar-static-bottom bg-light">--%>
<%--	<ul class="pagination">--%>
<%--		<li><a href="#">&laquo;</a></li>--%>
<%--		<li><a href="#">1</a></li>--%>
<%--		<li><a href="#">2</a></li>--%>
<%--		<li><a href="#">3</a></li>--%>
<%--		<li><a href="#">4</a></li>--%>
<%--		<li><a href="#">5</a></li>--%>
<%--		<li><a href="#">&raquo;</a></li>--%>
<%--	</ul>--%>
<%--	</nav>--%>
      <footer class="container fixed-bottom">
        <p class="float-right"><a href="#">返回顶部</a></p>
        <p>&copy; 2020 Sun, School of Big Data & Software Engineering. &middot; <a href="https://github.com/wangruns/Roommate-Recommender-System">GitHub</a></p>
      </footer>
    

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
	<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	
	<script src="${pageContext.request.contextPath}/js/home.js"></script>


	<script src="${pageContext.request.contextPath}/js/audio.js"></script>
	<script src="${pageContext.request.contextPath}/js/add2list.js"></script>
	<script src="${pageContext.request.contextPath}/js/navbar.js"></script>

	<script>
	$(function(){
		$('#headerId').load("headerFrameLoad.domain");
	});
	</script>
  </body>
</html>

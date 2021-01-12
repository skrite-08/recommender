<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <!--	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">-->
    <!--	<meta name="description" content="">-->
    <!--	<meta name="author" content="">-->
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>


<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <h3 class="text-center">
                h3. 这是一套可视化布局系统.
            </h3>
            <nav class="navbar navbar-default navbar-fixed-bottom" role="navigation">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button> <a class="navbar-brand" href="#">管理系统</a>
                </div>

                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
<%--                    <ul class="nav navbar-nav">--%>
<%--                        <li class="active">--%>
<%--                            <a href="#">Link</a>--%>
<%--                        </li>--%>
<%--                        <li>--%>
<%--                            <a href="#">Link</a>--%>
<%--                        </li>--%>
<%--                        <li class="dropdown">--%>
<%--                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown<strong class="caret"></strong></a>--%>
<%--                            <ul class="dropdown-menu">--%>
<%--                                <li>--%>
<%--                                    <a href="#">Action</a>--%>
<%--                                </li>--%>
<%--                                <li>--%>
<%--                                    <a href="#">Another action</a>--%>
<%--                                </li>--%>
<%--                                <li>--%>
<%--                                    <a href="#">Something else here</a>--%>
<%--                                </li>--%>
<%--                                <li class="divider">--%>
<%--                                </li>--%>
<%--                                <li>--%>
<%--                                    <a href="#">Separated link</a>--%>
<%--                                </li>--%>
<%--                                <li class="divider">--%>
<%--                                </li>--%>
<%--                                <li>--%>
<%--                                    <a href="#">One more separated link</a>--%>
<%--                                </li>--%>
<%--                            </ul>--%>
<%--                        </li>--%>
<%--                    </ul>--%>
                    <form class="navbar-form navbar-left" role="search">
                        <select class="custom-select-override " id="inputGroupSelect01" name="mode">
                            <option value="0" selected>音乐管理</option>
                            <option value="1">用户管理</option>
                            <option value="2">评论管理</option>
                        </select>
<%--                        <c:if test="${sessionScope.isHasPrivilege}">--%>
<%--                            <select class="custom-select-override " id="inputGroupSelect01" name="mode">--%>
<%--                                <option value="0" selected>音乐管理</option>--%>
<%--                                <option value="1">用户管理</option>--%>
<%--                                <option value="2">评论管理</option>--%>
<%--                            </select>--%>
<%--                        </c:if>--%>
                        <div class="form-group">
                            <input class="form-control mr-sm-2" id="searchInputId" type="text" placeholder="音乐" aria-label="Search" name="keyword" required autofocus oninvalid="this.setCustomValidity('搜索内容不能为空')"
                                   oninput="setCustomValidity('')">

                            <!--							<input type="text" class="form-control" />-->
                        </div>
                        <!--                            <button type="submit" class="btn btn-default">Submit</button>-->
                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">搜索</button>
                    </form>
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <a href="#">Link</a>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown<strong class="caret"></strong></a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="#">Action</a>
                                </li>
                                <li>
                                    <a href="#">Another action</a>
                                </li>
                                <li>
                                    <a href="#">Something else here</a>
                                </li>
                                <li class="divider">
                                </li>
                                <li>
                                    <a href="#">Separated link</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>

            </nav>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-8 column">
            <blockquote class="pull-right">
                <p>
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer posuere erat a ante.
                </p> <small>Someone famous <cite>Source Title</cite></small>
            </blockquote>
            <div class="row clearfix">
                <div class="col-md-6 column">
                    <div class="row clearfix">
                        <div class="col-md-8 column">
                            <ul class="nav nav-tabs nav-stacked">
                                <li id="usermanageId" >
                                    <a class="" href="#">用户管理</a>
                                </li>
                                <li id="reviewmanagId">
                                    <a class="" href="#">评论管理</a>
                                </li>
                                <li id="songmanageId">
                                    <a class="" href="#">歌曲管理</a>
                                </li>
                                <li class="dropdown pull-right">
                                    <a href="#" data-toggle="dropdown" class="dropdown-toggle">下拉<strong class="caret"></strong></a>
                                    <ul class="dropdown-menu">
                                        <li>
                                            <a href="#">操作</a>
                                        </li>
                                        <li>
                                            <a href="#">设置栏目</a>
                                        </li>
                                        <li>
                                            <a href="#">更多设置</a>
                                        </li>
                                        <li class="divider">
                                        </li>
                                        <li>
                                            <a href="#">分割线</a>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                        <div class="col-md-4 column">
                        </div>
                    </div>
                </div>
                <div class="col-md-6 column">
                    <div id="main">

                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-4 column">
            <div class="row clearfix">
                <div class="col-md-2 column">
                </div>
                <div class="col-md-6 column">
                    <div class="panel-group" id="panel-958655">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <a class="panel-title collapsed" data-toggle="collapse" data-parent="#panel-958655" href="#panel-element-789662">Collapsible Group Item #1</a>
                            </div>
                            <div id="panel-element-789662" class="panel-collapse collapse">
                                <div class="panel-body">
                                    Anim pariatur cliche...
                                </div>
                            </div>
                        </div>
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <a class="panel-title collapsed" data-toggle="collapse" data-parent="#panel-958655" href="#panel-element-186139">Collapsible Group Item #2</a>
                            </div>
                            <div id="panel-element-186139" class="panel-collapse collapse">
                                <div class="panel-body">
                                    Anim pariatur cliche...
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="carousel slide" id="carousel-722841">
                        <ol class="carousel-indicators">
                            <li class="active" data-slide-to="0" data-target="#carousel-722841">
                            </li>
                            <li data-slide-to="1" data-target="#carousel-722841">
                            </li>
                            <li data-slide-to="2" data-target="#carousel-722841">
                            </li>
                        </ol>
                        <div class="carousel-inner">
                            <div class="item active">
                                <img alt="" src="v3/default.jpg" />
                                <div class="carousel-caption">
                                    <h4>
                                        First Thumbnail label
                                    </h4>
                                    <p>
                                        Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.
                                    </p>
                                </div>
                            </div>
                            <div class="item">
                                <img alt="" src="v3/default1.jpg" />
                                <div class="carousel-caption">
                                    <h4>
                                        Second Thumbnail label
                                    </h4>
                                    <p>
                                        Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.
                                    </p>
                                </div>
                            </div>
                            <div class="item">
                                <img alt="" src="v3/default2.jpg" />
                                <div class="carousel-caption">
                                    <h4>
                                        Third Thumbnail label
                                    </h4>
                                    <p>
                                        Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.
                                    </p>
                                </div>
                            </div>
                        </div> <a class="left carousel-control" href="#carousel-722841" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a> <a class="right carousel-control" href="#carousel-722841" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
                    </div>
                </div>
                <div class="col-md-4 column">
                </div>
            </div>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <h3 class="text-center">
                h3. 这是一套可视化布局系统.
            </h3>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-6 column">
        </div>
        <div class="col-md-6 column">
            <div class="row clearfix">
                <div class="col-md-8 column">
                </div>
                <div class="col-md-4 column">
                    <blockquote class="pull-right">
                        <p>
                            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer posuere erat a ante.
                        </p> <small>Someone famous <cite>Source Title</cite></small>
                    </blockquote>
                </div>
            </div>
        </div>
    </div>
</div>
<script>

    $(function() {
        $('#usermanageId').on('click', function (e) {
            $('#main').load("userManageFrameLoad.domain");
        });

        $('#reviewmanageId').on('click', function (e) {
            $('#main').load("reviewSongManageFrameLOad.domain");
        });

        $('#songmanageId').on('click', function (e) {
            $('#main').load("songManageFrameLoad.domain");
        });
    });
</script>

</body>
</html>
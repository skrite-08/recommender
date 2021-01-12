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
    <title>Track Stacking</title>

</head>
<body>

<!-- 评论模块 -->
<div class="container">
    <h1></h1>
    <h3>搜索结果 ${oneDayOneWord}</h3>

    <c:if test="${not empty reviewSongManageList}">
        <div>
            <button id="deleteSelectId" class="btn btn-outline-success my-2 my-sm-0">删除选中</button>
        </div>
    </c:if>

    <div class="row content">
        <!-- 前DIV -->


        <!--中DIV -->
        <div class="col-sm-10">
            <h6>&nbsp;</h6>
            <h3 class="border-line">评论管理</h3>
            <div class="container">
                <div class="row content">
                    <ul class="list-group list-group-flush">
                        <c:forEach items="${reviewSongManageList}" var="review" varStatus="status">
                            <li class="media list-group-item list-group-item-light">
                                <input type="checkbox" value="${reviewsong.reviewsongId}" aria-label="Checkbox for following text input">
                                <div class="media-left">
                                    <a href="#">
                                        <img class="rounded img-logo" src="${pageContext.request.contextPath}/image/defaultUserLogo.jpg" alt="菜鸟" >
                                    </a>
                                </div>
                                <div class="media-body">
                                    <h6 class="media-heading text-primary">${reviewsong.userName}</h6>

                                    <p>${reviewsong.review}</p>
                                    <div class="ds-comment-footer">
                                        <span class="ds-time" title="${reviewsong.reviewTime}">${reviewsong.reviewTime}</span>&nbsp;
                                    </div>
                                </div>
                            </li>

                        </c:forEach>
                    </ul>
                </div>
            </div><!-- container  End-->

        </div><!-- 中DIV End-->

    </div><!-- row -->

    <!-- 后DIV -->
    <div class="col-sm-1">
    </div><!-- 该后DIV为了中DIV居中 -->

</div><!-- 评论模块 End-->

<script>
    $(function(){
        //处理删除选中
        $('#deleteSelectId').on('click', function (e) {
            var selectedIds = [];
            $('input:checkbox:checked').each(function() {
                selectedIds.push($(this).val());
            });
            //删除
            var data = {
                "reviewIds": selectedIds,
            };
            url = "deleteReviewSong.domain";
            $.ajax({
                //traditional: true[https://www.cnblogs.com/ybyi/p/6297600.html]
                traditional: true,
                type:"POST",
                url:url,
                data:data
            });
            //删除后变成禁止选中状态
            $('input:checkbox:checked').each(function() {
                $(this).attr("disabled", "disabled");
            });


        });
    });
</script>

</body>
</html>

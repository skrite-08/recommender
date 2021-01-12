
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

<!-- 用户搜索模块 -->
<div class="container">
    <h1></h1>
    <h3>搜索结果 ${oneDayOneWord}</h3>

    <c:if test="${not empty userManageList}">
        <div>
            <button id="deleteSelectId" class="btn btn-outline-success my-2 my-sm-0">删除选中</button>
        </div>
    </c:if>

    <div class="row content">
        <!-- 前DIV -->


        <!--中DIV -->
        <div class="col-sm-10">
            <h6>&nbsp;</h6>
            <h3 class="border-line-delete">用户管理</h3>
            <div class="container">
                <div class="row content">

                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th >用户ID</th>
                            <th >用户昵称</th>
                            <th >用户邮箱</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${userManageList}" var="user" varStatus="status">
                            <tr>
                                <th scope="row">${status.index+1}<input type="checkbox" value="${user.userId}" aria-label="Checkbox for following text input"></th>
                                <td>${user.userId}</td>
                                <td>${user.userName}</td>
                                <td>${user.email}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

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
                "userIds": selectedIds,
            };
            url = "deleteUser.domain";
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

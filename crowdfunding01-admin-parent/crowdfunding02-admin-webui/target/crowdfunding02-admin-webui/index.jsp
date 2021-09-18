<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/9/15 0015
  Time: 16:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>
    <script src="static/jquery/jquery-3.6.0.min.js"></script>
    <script>
        $(function (){

            let array = [5,5,1,7];
            let json_array = JSON.stringify(array);
            $("#btn").click(function (){
                $.ajax({
                    url: "send/array.html",
                    type: "POSt",
                    data: json_array,
                    contentType: "application/json;charset=utf-8",
                    dataType: "text",
                    success: function (response){
                        alert(response);
                    },
                    error: function (response){
                        alert(response);
                    }
                });
            });

            let complicateObject = {
                id: 1,
                name: "wanghu",
                address: {
                    country: "zh",
                    province: "sc",
                    city: "my"
                },
                schools: [
                    {
                        schoolId: 11,
                        schoolName: "四川大学锦江学院"
                    },
                    {
                        schoolId: 22,
                        schoolName: "首都师范大学"
                    }
                ],
                map: {
                    "ex1": "zj1",
                    "ex2": "zj2",
                    "ex3": "zj3"
                }
            };
            let json_complicateObject = JSON.stringify(complicateObject);
            $("#btn1").click(function (){
                $.ajax({
                    url: "send/complicateObject.json",
                    type: "POSt",
                    data: json_complicateObject,
                    contentType: "application/json;charset=utf-8",
                    dataType: "json",
                    success: function (response){
                        console.log(response);
                    },
                    error: function (response){
                        console.log(response);
                    }
                });
            });

        });
    </script>
</head>
<body>
    <a href="test/ssm.html">测试ssm整合</a>
    <br>
    <br>
    <button id="btn">ajax测试</button>
    <br>
    <br>
    <button id="btn1">ajax测试发送复杂对象</button>

</body>
</html>

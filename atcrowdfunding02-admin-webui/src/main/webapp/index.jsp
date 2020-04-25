<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
    <script type="text/javascript" src="jquery/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="layer/layer.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#btn").click(function () {
                layer.msg("你好");
            });
        });
    </script>
</head>
<body>
<%--<a href="test/ssm.html">测试整合环境</a>--%>
<a href="test/ssm.html">测试整合环境</a>
<button id="btn">点击</button>
</body>
</html>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="transferobject.ArticleDTO" %>
<html>
<head>
    <title>Title</title>

</head>
<body>
<div>
    <h1>Artikelliste</h1>
    <jsp:useBean id="stocklist" type="java.util.ArrayList<transferobject.ArticleDTO>" class="java.util.ArrayList" scope="request"/>
    <%
        Iterator i = stocklist.iterator();
        System.out.println(stocklist.size());
        while (i.hasNext()) {
            ArticleDTO article = (ArticleDTO)i.next();
    %>
    <div>
        <a href="${pageContext.request.contextPath}/frontend?action=articledetails&id=<%=article.getId()%>">
            <%=article.getName()%>
        </a>
    </div>

    <%
        }
    %>
</div>
</body>
</html>

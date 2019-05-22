
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="transferobject.ArticleDTO" %>
<html>
<head>
    <title>Artikelliste</title>

</head>
<body>
<div style="display:flex;">
    <jsp:include page="navigation.jsp" />
    <div style="flex:80%;">
        <h1>Artikelliste</h1>
        <table>
        <jsp:useBean id="stocklist" type="java.util.ArrayList<transferobject.ArticleDTO>" class="java.util.ArrayList" scope="request"/>
        <%
            Iterator i = stocklist.iterator();
            while (i.hasNext()) {
                ArticleDTO article = (ArticleDTO)i.next();
        %>
            <tr>
                <td>
                    <a href="${pageContext.request.contextPath}/frontend?action=articledetails&id=<%=article.getId()%>">
                        <%=article.getName()%>
                    </a>
                </td>
                <td>
                    <%=article.getAmount()%>
                </td>
            </tr>
        <%
            }
        %>
        </table>
    </div>
</div>
</body>
</html>

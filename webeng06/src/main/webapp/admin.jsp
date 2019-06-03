<%@ page import="transferobject.ArticleDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Backend</title>
</head>
<body>
    <div style="display:flex;">
        <jsp:include page="navigation.jsp" />
        <div style="flex:80%;">
            <h1>Admin Artikel Backend</h1>
            <table>
                <thead>
                <tr>
                    <th>
                        ID
                    </th>
                    <th>
                        Name
                    </th>
                    <th>
                        Preis
                    </th>
                    <th>
                        Menge
                    </th>
                    <th>
                        Aktion
                    </th>
                </tr>
                </thead>
                <tbody>
                <jsp:useBean id="stocklist" type="java.util.ArrayList<transferobject.ArticleDTO>" class="java.util.ArrayList" scope="request"/>
                <%
                    for(ArticleDTO article: stocklist) {

                %>
                <tr>
                    <form action="${pageContext.request.contextPath}/frontend?action=admin&authtoken=secretPassword" method="post">
                        <td>
                            <input type="hidden" name="articleId" value="<%=article.getId()%>">
                            <%=article.getId()%>
                        </td>
                        <td>
                            <input type="text" name="articleName" value="<%=article.getName()%>">
                        </td>
                        <td>
                            <input type="number" name="articlePrice" min=0.1 step=0.10 value="<%=article.getPrice()%>">
                        </td>
                        <td>
                            <input type="number" name="articleAmount" min=1 value="<%=article.getAmount()%>">
                        </td>
                        <td>
                            <button type="submit" name="articleAction" value="edit">Edit Article</button>
                            <button type="submit" name="articleAction" value="delete">Delete Article</button>
                        </td>
                    </form>
                </tr>

                <%
                    }
                %>
                <tr>
                    <form action="${pageContext.request.contextPath}/frontend?action=admin&authtoken=secretPassword" method="post">
                        <td>
                            X
                        </td>
                        <td>
                            <input type="text" name="articleName" value="" placeholder="Artikel Name">
                        </td>
                        <td>
                            <input type="number" name="articlePrice" min=0 step=0.10 value="" placeholder="10.50">
                        </td>
                        <td>
                            <input type="number" name="articleAmount" min=0 value="" placeholder="10">
                        </td>
                        <td>
                            <button type="submit" name="articleAction" value="add">Add Article</button>
                        </td>
                    </form>
                </tr>
                </tbody>
            </table>
            <div style="margin-top: 5%; color:green;">
                ${actionMessage}
            </div>
        </div>
    </div>
</body>
</html>

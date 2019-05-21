
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <jsp:useBean id="article" type="transferobject.ArticleDTO" class="transferobject.ArticleDTO" scope="request"/>
    <% if(article.getName() != null) {
    %>
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
                    Preis in €
                </th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>
                    <jsp:getProperty property="id" name="article"/>
                </td>
                <td>
                    <jsp:getProperty property="name" name="article"/>
                </td>
                <td>
                    <jsp:getProperty property="price" name="article"/>
                </td>
            </tr>
        </tbody>
    </table>
    <form action="/frontend?action=shoppingcart" method="post">
        <input type="hidden" value="${article.id}" name="articleId">
        <label>Anzahl</label><input type="number" name="articleAmount" max="${article.amount}">
        <button type="submit">Add To Cart</button>
    </form>
    <%
        }
    %>
</body>
</html>

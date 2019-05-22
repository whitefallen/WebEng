
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ArtikelDetail</title>
</head>
<body>
    <div style="display:flex;">
        <jsp:include page="navigation.jsp" />
        <div style="flex:80%;">
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
                        <th>
                            Menge
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
                        <td>
                            <jsp:getProperty property="amount" name="article"/>
                        </td>
                    </tr>
                </tbody>
            </table>
            <form action="${pageContext.request.contextPath}/frontend?action=shoppingcart" method="post">
                <input type="hidden" value="${article.id}" name="articleId">
                <label>Anzahl</label><input type="number" name="articleAmount" max="${article.amount}">
                <button type="submit">Add To Cart</button>
            </form>
            <%
                }
            %>
        </div>
    </div>
</body>
</html>

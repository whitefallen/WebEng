<%@ page import="transferobject.ArticleDTO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div style="display:flex;">
        <jsp:include page="navigation.jsp" />
        <div style="flex:80%;">
            <h1>Warenkorb-Checkout</h1>
            <jsp:useBean id="shoppingCart" type="transferobject.ShoppingCartDTO" class="transferobject.ShoppingCartDTO" scope="session"/>
            <%
                ArrayList<ArticleDTO> cartList = shoppingCart.getArticleList();
                for (ArticleDTO _article: cartList) {

            %>
            <div>
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
                    </tr>
                    </thead>
                    <tr>
                        <td>
                            <%=_article.getId()%>
                        </td>
                        <td>
                            <%=_article.getName()%>
                        </td>
                        <td>
                            <%=_article.getPrice()%>
                        </td>
                    </tr>
                    <tbody>
                    </tbody>
                </table>
            </div>
            <%
                }
            %>
            <div>
                Gesammtsumme: <jsp:getProperty property="sum" name="shoppingCart"/>
            </div>
            <form method="post" action="${pageContext.request.contextPath}/frontend?action=checkout">
                <button type="submit" value="checkout" name="checkoutAction">Bezahlen!</button>
            </form>
        </div>
    </div>
</body>
</html>

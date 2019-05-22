<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div style="flex:auto; border-right: 2px solid #666666; margin-right: 2%; background: rgba(172,224,230,0.5); height: 100vh;">
    <nav>
        <h1> Navigation </h1>
        <ul>
            <li>
                <a href="${pageContext.request.contextPath}/frontend?action=index">Home</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/frontend?action=articlelist">Artikelliste</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/frontend?action=articledetails">Artikel-Details</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/frontend?action=checkout">Checkout</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/frontend?action=admin">Admin(Locked)</a>
            </li>
        </ul>
    </nav>
</div>

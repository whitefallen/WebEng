<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Webeng05 JSP</title>
</head>
<body>

<h1>Hello JSP</h1>

<%
    java.util.Date date = new java.util.Date();
    int sum = 3*5;
%>

<h2>
    Heute ist der: <%=date.toString()%>
</h2>
<div>
    <div>
        Das ergebniss von 3 x 5 ist : <%=sum%>
    </div>
</div>
<div>
    <form>
        <label>Password: </label><input type="text" name="password" required>
        <label>Offset: </label><input type="text" name="offset" required>
        <button type="submit">Cypher Me</button>
    </form>
    <%!
        public String caesarCode(String _password, int offset) {
            char[] passAlphabet = _password.toCharArray();
            for (int i = 0; i < passAlphabet.length; i++) {
                passAlphabet[i] += offset;
            }
            return new String(passAlphabet);
        }
    %>
    <%
        String cryptedPass = null;
        if(request.getParameter("password") != null && request.getParameter("offset") != null) {
            cryptedPass = this.caesarCode(request.getParameter("password"), Integer.parseInt(request.getParameter("offset")));
			out.print(cryptedPass);
        }
    %>
    <div>
        Methode: <%= request.getMethod()%>
    </div>
    <div>
        Parameter: <%= config.getInitParameter("Template")%>
    </div>
</div>
</body>
</html>
<%--
  Created by IntelliJ IDEA.
  User: mouhamed
  Date: 06/08/2019
  Time: 10:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/spinners.css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<div class="panel-heading">
    <h2>Login </h2>
</div>

<div class="row col-md-4 col-md-offset-4">
    <form method="post" action="${pageContext.request.contextPath}/login">
        <input type="hidden" name="action" value="logon" />

        <div class="form-group">
            <label class="control-label">Login</label>
            <input class="form-control" type="text" name="login" placeholder="Entrez votre login" required />
        </div>

        <div class="form-group">
            <label class="control-label">Mot de passe</label>
            <input class="form-control" type="password" name="password"  placeholder="Entrez votre mot de passe" required />
        </div>

        <div class="form-group">
            <center><button class="btn btn-primary" type="submit" name="connexion">Se connecter</button></center>
        </div>

        <p class="text text-center text-danger">

            ${message}

        </p>
    </form>
</div>
</body>
</html>

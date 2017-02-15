<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <%@include file="header.jsp" %>
        <link href="<c:url value="/resources/css/login-register.css" />" rel="stylesheet" />
    </head>
    <body style="background-color: #2e2873">
        <div class="container">
            <div class="row text-center " style="padding-top:100px;">
                <div>
                    <h2>Document Management System</h2>
                    <div class="upozorenje"></div>
                </div>
            </div>
            <div class="row ">

                <div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-10 col-xs-offset-1">

                    <div class="panel-body">

                        <form role="form" action="login" method="POST">
                            <hr />
                            <c:choose>
                                <c:when test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
                                    <font color="red">
                                    Your login attempt was not successful due to <br/><br/>
                                    <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
                                    </font>
                                </c:when>
                                <c:otherwise>
                                    <h5>Log in:</h5>
                                </c:otherwise>
                            </c:choose>
                            <div class="form-group input-group">
                                <span class="input-group-addon"><i class="fa fa-tag"  ></i></span>
                                <input type="text" class="form-control" placeholder="Username" name="username" />
                            </div>
                            <div class="form-group input-group">
                                <span class="input-group-addon"><i class="fa fa-lock"  ></i></span>
                                <input type="password" class="form-control"  placeholder="Password" name="password" />
                            </div>
                            <div class="login">
                                <input type="submit" value="LOGIN" class="btn input-block-level form-control" />
                            </div>
                        </form>                       
                    </div>

                </div>     
            </div>
        </div>
    </body>
</html>

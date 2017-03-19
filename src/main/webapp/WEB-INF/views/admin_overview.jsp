<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Admin overview</title>
        <%@include file="header.jsp" %>
    </head>

    <body>
        <sec:authentication var="admin" property="principal"/>

        <div id="wrapper">
            <nav class="navbar navbar-default navbar-cls-top " role="navigation" style="margin-bottom: 0">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="/dms/">Document Management System</a>
                </div>

                <div class="header-right">
                    <form role="form" action="/dms/logout" method="POST">
                        <label for="mySubmit" class="btn"><i class="fa fa-sign-out fa-2x" style="padding-top: 15px"></i></label>
                        <input id="mySubmit" type="submit" value="" class="hidden" />
                    </form>
                </div>
            </nav>

            <nav class="navbar-default navbar-side" role="navigation">
                <div class="sidebar-collapse">
                    <ul class="nav" id="main-menu">
                        <li>
                            <div class="user-img-div">
                                <div class="inner-text">
                                    ${admin.ime} ${admin.prezime}
                                    <br />
                                </div>
                            </div>
                        </li>
                        <li>
                            <a class="active-menu" href="/dms/"><i class="fa fa-dashboard "></i>Dashboard</a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-sitemap"></i>Subsystems<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="/dms/subsystems/overview">Subsystem overview</a>
                                </li>
                                <li>
                                    <a href="/dms/subsystems/add_new_subsystem">Add new subsystem</a>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-sitemap "></i>Admin users<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="/dms/admins/overview">Admin overview</a>
                                </li>
                                <li>
                                    <a href="/dms/admins/add_new_admin">Add new Admin</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </nav> 

            <div id="page-wrapper">
                <div id="page-inner">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="table-responsive">
                                <div class="panel panel-default">
                                    <div class="panel-heading"><h3>Admin overview</h3></div>
                                    <div class="panel-body">
                                        <table class="table table-condensed" style="border-collapse:collapse;">
                                            <thead>
                                                <tr>
                                                    <th>&nbsp;</th>
                                                    <th>Subsystem</th>                                              
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="subsystem" items="${subsystems}">
                                                    <tr data-toggle="collapse" data-target="#${subsystem.id}" class="accordion-toggle">
                                                        <td style="width: 7%"><button class="btn btn-default btn-xs"><span class="glyphicon glyphicon-user"></span></button></td>
                                                        <td style="width: 93%">${subsystem.naziv}</td>
                                                    </tr>
                                                    <tr>                   
                                                            <td colspan="12" class="hiddenRow"><div class="accordian-body collapse" id="${subsystem.id}"> 
                                                                    <table class="table table-striped">
                                                                        <thead>
                                                                            <tr>
                                                                                <td>Username</td>
                                                                                <td>Name</td>
                                                                                <td>Surname</td>
                                                                                <td>Role</td>
                                                                                <td>Action</td>
                                                                            </tr>
                                                                        </thead>
                                                                        <tbody>
                                                                            <c:set var="admins" value="${subsystem.userList}"/>
                                                                            <c:forEach var="users" items="${admins}">
                                                                               <c:set var="rola" value="${users.idRole}"/>
                                                                               <c:if test="${rola.nazivRole == 'ADMIN'}">
                                                                            <tr>
                                                                                <td>${users.username}</td>
                                                                                <td>${users.ime}</td>
                                                                                <td>${users.prezime}</td>
                                                                                <td>${rola.nazivRole}</td>
                                                                                <td><a href="#" class="btn btn-default btn-sm"><i class="glyphicon glyphicon-cog"></i></a></td>
                                                                            </tr>
                                                                            </c:if>
                                                                            </c:forEach>
                                                                        </tbody>
                                                                    </table>
                                                                </div>
                                                            </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>

            </div>
            <!-- /. PAGE INNER  -->
        </div>
        <!-- /. PAGE WRAPPER  -->
    </div>
    <!-- /. WRAPPER  -->

    <%@include file="footer.jsp" %>
</body>
</html>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Document Management System</title>
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
                        <c:if test="${admin.rola.idRole  == 1 }">
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
                        </c:if>
                        <c:if test="${admin.rola.idRole  == 2 }">
                            <li>
                                <a href="#"><i class="fa fa-sitemap "></i>Processes<span class="fa arrow"></span></a>
                                <ul class="nav nav-second-level">
                                    <li>
                                        <a href="/dms/process/overview">Processes overview</a>
                                    </li>
                                    <li>
                                        <a href="/dms/process/add_new">Add new process</a>
                                    </li>
                                    <li>
                                        <a href="/dms/process/add_new_sub">Add new subprocess</a>
                                    </li>
                                    <li>
                                        <a href="/dms/activity/add_new">Add new activity</a>
                                    </li>                                
                                </ul>
                            </li>
                            <li>
                                <a href="#"><i class="fa fa-file"></i>Document Types<span class="fa arrow"></span></a>
                                <ul class="nav nav-second-level">
                                    <li>
                                        <a href="/dms/documenttypes/overview">Document types overview</a>
                                    </li>
                                    <li>
                                        <a href="/dms/documenttypes/add_new">Add new document type</a>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <a href="#"><i class="fa fa-users"></i>Users<span class="fa arrow"></span></a>
                                <ul class="nav nav-second-level">
                                    <li>
                                        <a href="/dms/admins/user_overview">Users overview</a>
                                    </li>
                                    <li>
                                        <a href="/dms/admins/add_new_user">Add new user</a>
                                    </li>
                                </ul>
                            </li>
                        </c:if>
                        <c:if test="${admin.rola.idRole  == 3 }">
                            <li>
                                <a href="#"><i class="fa fa-sitemap "></i>Processes<span class="fa arrow"></span></a>
                                <ul class="nav nav-second-level">
                                    <li>
                                        <a href="/dms/process/overviewusers">Processes overview</a>
                                    </li>
                                </ul>
                            </li>
                        </c:if>                            
                    </ul>
                </div>
            </nav>
            <div id="page-wrapper">
                <div id="page-inner">
                    <div class="row">
                        <div class="col-md-12">
                            <h1 class="page-head-line">ERROR</h1>
                        </div>
                    </div>
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-lg-6">
                                <h1>${error}</h1>
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

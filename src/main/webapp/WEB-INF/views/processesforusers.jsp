<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Processes - overview</title>
        <%@include file="header.jsp" %>
        <link rel="stylesheet" href="../resources/dist/themes/default/style.min.css" />
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
                        <a href="#"><i class="fa fa-sitemap "></i>Processes<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a href="/dms/process/overviewusers">Processes overview</a>
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
                        <h1 class="page-head-line">Processes overview</h1>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <div class="table-responsive">
                            <div id="jstree_demo_div">
                                <c:forEach var="proces" items="${processes}">
                                    <ul>
                                        <li>
                                            ${proces.naziv}

                                            <c:if test="${proces.procesList != null}" >
                                                <c:forEach var="subproces" items="${proces.procesList}">
                                                    <ul> 
                                                        <li>
                                                            ${subproces.naziv}
                                                            <c:if test="${subproces.aktivnostList != null}" >
                                                                <c:forEach var="aktivnost" items="${subproces.aktivnostList}">
                                                                    <ul> 
                                                                        <li>
                                                                            
                                                                            ${aktivnost.naziv}
                                                                            <a href="/dms/documents/add_new/${aktivnost.id}"> Add document</a>
                                                                        </li>
                                                                    </ul>
                                                                </c:forEach>
                                                            </c:if>
                                                        </li>
                                                    </ul>
                                                </c:forEach>
                                            </c:if>
                                        </li>
                                    </ul>
                                </c:forEach>
                            </div>


                            -----------
                            <form action="sve-vesti" method="POST">
                                <table class="table table-striped table-bordered table-hover">
                                    <thead>
                                        <tr>
                                            <th>Name</th>
                                            <th>Code</th>
                                            <th>Description</th>
                                            <th>Details</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="proces" items="${processes}">
                                            <tr>
                                                <td>${proces.naziv}</td>
                                                <td>${proces.oznaka}</td>
                                                <td>${proces.opis}</td>
                                                <td><a href="/dms/processes/details/${subsystem.id}">Details</a></td>
                                            </tr>                                            
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </form>
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

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
    <head>
        <%@include file="header.jsp" %>
        <c:if test="${documenttype == null}">
            <title>Add new document type</title>>
        </c:if>
        <c:if test="${documenttype != null}">
            <title>
                ${documenttype.nazivTipa}
            </title>
        </c:if>
        <script src="../resources/js/scripts.js"></script>
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
                            <a href="#"><i class="fa fa-sitemap "></i>Document Types<span class="fa arrow"></span></a>
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
                    </ul>
                </div>
            </nav> 

            <div id="page-wrapper">
                <div id="page-inner">
                    <div class="row">
                        <div class="col-md-12">
                            <c:if test="${documenttype == null}">
                                <h1 class="page-head-line">Add new document type</h1>
                            </c:if>
                            <c:if test="${documenttype != null}">
                                <h1 class="page-head-line">
                                    ${documenttype.nazivTipa}
                                </h1>
                            </c:if>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-6">
                                    <form role="form" method="POST" id="add_doctype_form" action="<c:if test="${documenttype == null}">
                                          /dms/documenttypes/add_new                            
                                        </c:if>
                                        <c:if test="${documenttype != null}">
                                            /dms/documenttypes/add_new_documenttype/${documenttype.idTipaDokumenta}
                                        </c:if>">
                                        <div class="form-group">
                                            <label>Document type name</label>
                                            <c:if test="${documenttype == null}">
                                                <input class="form-control" name="documenttypename">
                                                <p class="help-block">Example: Order</p>
                                            </c:if>
                                            <c:if test="${documenttype != null}">
                                                <input class="form-control" name="documenttypename" value="${documenttype.nazivTipa}" id="documenttypename">
                                                <p class="help-block">Example: ${documenttype.nazivTipa}</p>
                                            </c:if>                                            
                                        </div>
                                        <c:if test="${documenttype == null}">
                                            <button type="submit" class="btn btn-default">Create new document type</button>
                                            <button type="reset" class="btn btn-default">Reset</button>                                                         </c:if>
                                        <c:if test="${documenttype != null}">
                                            <div id="editdiv">
                                                <button type="submit" class="btn btn-default">Edit data about ${documenttype.nazivTipa}</button>
                                                <div>
                                                </c:if>                                                                                              
                                                </form>
                                            </div>
                                            <!-- /.col-lg-6 (nested) -->
                                            <div class="col-lg-6">

                                            </div>
                                            <!-- /.col-lg-6 (nested) -->
                                        </div>
                                        <!-- /.row (nested) -->
                                </div>
                            </div>

                        </div>
                        <!-- /. PAGE INNER  -->
                    </div>
                    <!-- /. PAGE WRAPPER  -->
                </div>
                <!-- /. WRAPPER  -->
            </div>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>
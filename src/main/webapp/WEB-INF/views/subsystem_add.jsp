<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
    <head>
        <%@include file="header.jsp" %>
        <c:if test="${subsystem == null}">
            <title>Add new subsystem</title>>
        </c:if>
        <c:if test="${subsystem != null}">
            <title>
                ${subsystem.naziv}
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
                            <c:if test="${subsystem == null}">
                                <h1 class="page-head-line">Add new subsystem</h1>
                            </c:if>
                            <c:if test="${subsystem != null}">
                                <h1 class="page-head-line">
                                    ${subsystem.naziv}
                                </h1>
                            </c:if>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-6">
                                    <form role="form" method="POST" id="add_sub_form" action="<c:if test="${subsystem == null}">
                                          /dms/subsystems/add_new_subsystem                            
                                        </c:if>
                                        <c:if test="${subsystem != null}">
                                            /dms/subsystems/add_new_subsystem/${subsystem.id}
                                        </c:if>">
                                        <div class="form-group">
                                            <label>Subsystem name</label>
                                            <c:if test="${subsystem == null}">
                                                <input class="form-control" name="subsystemname">
                                                <p class="help-block">Example: NLB bank</p>
                                            </c:if>
                                            <c:if test="${subsystem != null}">
                                                <input class="form-control" name="subsystemname" value="${subsystem.naziv}" id="naziv">
                                                <p class="help-block">Example: ${subsystem.naziv}</p>
                                            </c:if>                                            
                                        </div>
                                        <div class="form-group">
                                            <label>Subsystem sign</label>
                                            <c:if test="${subsystem == null}">
                                                <input class="form-control" name="subsystemsign">
                                                <p class="help-block">Example: NLBBNK</p>
                                            </c:if>
                                            <c:if test="${subsystem != null}">
                                                <input class="form-control" name="subsystemsign" value="${subsystem.oznaka}" disabled="true">
                                            </c:if>                                            
                                        </div>
                                        <div class="form-group">
                                            <label>Subsystem description</label>
                                            <c:if test="${subsystem == null}">
                                                <textarea class="form-control" rows="5" name="subsystemdescription" onfocus="clearContents(this);">Default description</textarea>
                                            </c:if>
                                            <c:if test="${subsystem != null}">
                                                <textarea class="form-control" rows="5" name="subsystemdescription" id="opis">${subsystem.opis}</textarea>
                                            </c:if>      
                                        </div>
                                        <c:if test="${subsystem == null}">
                                            <button type="submit" class="btn btn-default">Create new subsystem</button>
                                            <button type="reset" class="btn btn-default">Reset</button>                                                         </c:if>
                                        <c:if test="${subsystem != null}">
                                            <div id="editdiv">
                                                <button type="submit" class="btn btn-default">Edit data about ${subsystem.naziv}</button>
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
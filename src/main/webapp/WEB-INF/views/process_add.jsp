<%-- 
    Document   : process_add
    Created on : Feb 20, 2017, 4:50:10 PM
    Author     : nevenac
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Document Management System</title>
        <%@include file="header.jsp" %>
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
                        <h1 class="page-head-line">ADD NEW PROCESS</h1>
                    </div>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-6">
                            <form role="form" method="POST" id="add_process_form" action="/dms/process/add_new">
                                <div class="form-group">
                                    <label>Name</label>
                                    <input class="form-control" name="procesname">
                                    <p class="help-block">Example: process1</p>                                         
                                </div>
                                <div class="form-group">
                                    <label>Sign</label>
                                    <input class="form-control" name="processign">
                                    <p class="help-block">Example: P1</p>                                         
                                </div>
                                <div class="form-group">
                                    <label>Process description</label>
                                    <textarea class="form-control" rows="5" name="procesdescription" id="opis" onfocus="clearContents(this);">Process is for...</textarea>
                                </div>                           
                                
                                <button type="submit" class="btn btn-default">Create new process</button>
                                <button type="reset" class="btn btn-default">Reset</button>                                                                                                                                                   
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
    </div>
    <!-- /. PAGE INNER  -->
</div>
<!-- /. PAGE WRAPPER  -->
</div>
<!-- /. WRAPPER  -->
<%@include file="footer.jsp" %>
</body>
</html>

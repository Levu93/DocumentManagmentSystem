<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Document Management System</title>
        <%@include file="header.jsp" %>
        <script src="../resources/js/bootstrap-select.js"></script>
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
                                    <a href="/dms/processes/usr/overviewusers">Processes overview</a>
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
                            <h1 class="page-head-line">ADD NEW DOCUMENT FOR ${activity.naziv}</h1>
                        </div>
                    </div>
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-lg-6">
                                <form role="form" method="POST" id="add_document_form" enctype="multipart/form-data" action="/dms/documents/add_new/${activity.id}">
                                    <div class="form-group">
                                        <label>Name</label>
                                        <input class="form-control" name="documentname">
                                        <p class="help-block">Example: Equipment Order</p>                                         
                                    </div>
                                    <div class="form-group">
                                        <label>Description</label>
                                        <input class="form-control" name="documentdescritption">
                                        <p class="help-block">Example: Order for equipment</p>                                         
                                    </div>
                                    <div class="form-group">
                                        <label>Document type</label>
                                        <select class="form-control" rows="5" name="documenttype" id="documenttype">
                                            <c:forEach var="documenttype" items="${documenttypes}">
                                                <option value="${documenttype.idTipaDokumenta}">${documenttype.nazivTipa}</option>                                              
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label>Upload file</label><br/>
                                        <label class="custom-file">
                                            <input type="file" id="file" name="file" class="btn btn-default">
                                            <span class="custom-file-control"></span>
                                        </label>
                                    </div>
                                    <button type="submit" class="btn btn-default">Add new document</button>
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
<script>
    $.('.chosen-select').chosen();
</script>
<%@include file="footer.jsp" %>
</body>
</html>

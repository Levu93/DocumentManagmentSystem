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
                        <c:if test="${admin.rola.idRole  == 2 }">
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
                        </c:if>
                        <li>
                            <c:if test="${admin.rola.idRole  == 1 }">
                                <a href="#"><i class="fa fa-sitemap "></i>Admin users<span class="fa arrow"></span></a>
                                <ul class="nav nav-second-level">
                                    <li>
                                        <a href="/dms/admins/overview">Admin overview</a>
                                    </li>
                                    <li>
                                        <a href="/dms/admins/add_new_admin">Add new Admin</a>
                                    </li>
                                </ul>
                            </c:if>
                            <c:if test="${admin.rola.idRole  == 2 }">                            
                                <a href="#"><i class="fa fa-users"></i>Users<span class="fa arrow"></span></a>
                                <ul class="nav nav-second-level">
                                    <li>
                                        <a href="/dms/admins/user_overview">Users overview</a>
                                    </li>
                                    <li>
                                        <a href="/dms/admins/add_new_user">Add new user</a>
                                    </li>
                                </ul>
                            </c:if>
                        </li>              
                    </ul>
                </div>
            </nav>
            <div id="page-wrapper">
                <div id="page-inner">
                    <div class="row">
                        <div class="col-md-12">
                            <c:if test="${admin.rola.idRole  == 2 }">
                                <h1 class="page-head-line">ADD NEW USER</h1>
                            </c:if>
                            <c:if test="${admin.rola.idRole  == 1 }">    
                                <h1 class="page-head-line">ADD NEW ADMIN</h1>
                            </c:if>
                        </div>
                    </div>
                    <div class="panel-body">
                        <div class="row">                            
                            <div class="col-lg-6">
                                <c:if test="${not empty error}">
                                    <h1 style="color: red; font-weight: bold">${error}</h1>
                                        <br>
                                </c:if>
                                <form role="form" method="POST" id="add_admin_form" action="/dms/admins/add_new_admin">
                                    <div class="form-group">
                                        <label>First Name</label>
                                        <input class="form-control" name="adminname" value="<c:if test="${not empty ime}">${ime}</c:if>">
                                            <p class="help-block">Example: User</p>                                         
                                        </div>
                                        <div class="form-group">
                                            <label>Last Name</label>
                                            <input class="form-control" name="adminlastname" value="<c:if test="${not empty prezime}">${ime}</c:if>">
                                            <p class="help-block">Example: User</p>                                         
                                        </div>
                                        <div class="form-group">
                                            <label>Username</label>
                                            <input class="form-control" name="adminusername">
                                            <p class="help-block">Example: username</p>                                         
                                        </div>
                                        <div class="form-group">
                                            <label>Password</label>
                                            <input type="password" class="form-control" name="adminpass">
                                            <p class="help-block" style="color: red">8 characters minimum!!!</p>                                         
                                        </div>
                                    <c:if test="${admin.rola.idRole  == 1 }">
                                        <div class="form-group">
                                            <label>Subsystem</label>
                                            <select name="adminsubsystem" class="form-control">
                                                <c:forEach var="subs" items="${subsystems}">
                                                    <option data- value="${subs.id}">${subs.naziv}</option>
                                                </c:forEach>
                                            </select>       
                                        </div>
                                    </c:if>
                                    <c:if test="${admin.rola.idRole  == 1 }">
                                        <button type="submit" class="btn btn-default">Add new admin</button>
                                    </c:if>      
                                    <c:if test="${admin.rola.idRole  == 2 }">
                                        <button type="submit" class="btn btn-default">Add new user</button>
                                    </c:if>
                                    <button type="reset" class="btn btn-default">Reset</button>                                                                                                                                                   
                                </form>
                            </div>

                            <div class="col-lg-6">

                            </div>

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

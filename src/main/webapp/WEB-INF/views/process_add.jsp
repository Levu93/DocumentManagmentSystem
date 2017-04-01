<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Document Management System</title>
        <%@include file="header.jsp" %>
        <script>
            function clearContents(element) {
                if (element.value == 'Process is for...') {
                    element.value = '';
                }
            }
            function backContents(element) {
                if (element.value == '') {
                    element.value = 'Process is for...';
                }
            }
        </script>
        <script>
            function confirmDelete() { 
                        var answer = confirm("Are you sure you want to delete ${trazeni.naziv}???");
                if (answer) {
                            document.myForm.action = "/dms/processes/adm/delete/${trazeni.id}";
                    document.myForm.submit();
                    return true;
                } else
                    return false;
            }
        </script>
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
                                    <a href="/dms/processes/adm/overview">Processes overview</a>
                                </li>
                                <li>
                                    <a href="/dms/processes/adm/add_new">Add new process</a>
                                </li>
                                <li>
                                    <a href="/dms/processes/adm/add_new_sub">Add new subprocess</a>
                                </li>
                                <li>
                                    <a href="/dms/activity/adm/add_new">Add new activity</a>
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
                                    <a href="/dms/admins/usr/user_overview">Users overview</a>
                                </li>
                                <li>
                                    <a href="/dms/admins/usr/add_new_user">Add new user</a>
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
                            <h1 class="page-head-line">
                                <c:choose>
                                    <c:when test="${trazeni != null}">
                                        ${trazeni.naziv} DETAILS    
                                        <form onsubmit="confirmDelete()" method="GET" name="myForm">
                                            <input type="submit" class="btn btn-default" value="Delete process">
                                        </form>
                                    </c:when>
                                    <c:otherwise>
                                        ADD NEW PROCESS
                                    </c:otherwise>
                                </c:choose>                              
                            </h1>
                        </div>
                    </div>
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-lg-6">
                                <form role="form" method="POST" id="add_process_form" action="
                                      <c:choose>
                                          <c:when test="${trazeni != null}">
                                              /dms/processes/adm/update/${trazeni.id}
                                          </c:when>
                                          <c:otherwise>
                                              /dms/processes/adm/add_new
                                          </c:otherwise>
                                      </c:choose>
                                      ">
                                    <div class="form-group">
                                        <label>Name</label>
                                        <input class="form-control" name="procesname" <c:if test="${trazeni != null}">value="${trazeni.naziv}"</c:if> >
                                            <p class="help-block">Example: process1</p>                                         
                                        </div>
                                        <div class="form-group">
                                            <label>Sign</label>
                                            <input class="form-control" name="processign" <c:if test="${trazeni != null}">value="${trazeni.oznaka}" disabled="disabled"</c:if>>
                                            <p class="help-block">Example: P1</p>                                         
                                        </div>
                                        <div class="form-group">
                                            <label>Process description</label>
                                            <textarea class="form-control" rows="5" name="procesdescription" id="opis" onfocus="clearContents(this);" onblur="backContents(this)"><c:if test="${trazeni != null}">${trazeni.opis}</c:if><c:if test="${trazeni == null}">Process is for...</c:if></textarea>
                                        </div>
                                        <div class="form-group">
                                            <div class="checkbox">
                                                <label><input type="checkbox" name = "isprimitive" 
                                                    <c:if test="${trazeni != null}">
                                                        <c:if test="${trazeni.primitivan == true}">
                                                            checked="checked"
                                                        </c:if>
                                                        <c:if test="${not empty trazeni.procesList || not empty trazeni.aktivnostList }">
                                                            disabled="disabled"
                                                        </c:if>
                                                    </c:if>
                                                    >
                                                Is primitive?
                                            </label>
                                        </div>
                                    </div>  
                                    <button type="submit" class="btn btn-default">
                                        <c:choose>
                                            <c:when test="${trazeni != null}">
                                                Edit ${trazeni.naziv}
                                            </c:when>
                                            <c:otherwise>
                                                Create new process
                                            </c:otherwise>
                                        </c:choose>                                        
                                    </button>
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

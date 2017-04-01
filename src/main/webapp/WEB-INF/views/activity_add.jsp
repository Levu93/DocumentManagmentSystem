<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Document Management System</title>
        <%@include file="header.jsp" %>
        <script>
            function confirmDelete() { // <--- changed here
                var answer = confirm("Are you sure you want to delete ${aktivnost.naziv}? It may have documents connected!!!");
                if (answer) {
                    document.myForm.action = "/dms/activity/adm/delete/${aktivnost.id}";
                    document.myForm.submit();
                    return true;
                } else
                    return false;
            }
        </script>
        <script>
            function clearContents(element) {
                if (element.value == 'Activity is for...') {
                    element.value = '';
                }
            }

            function backContents(element) {
                if (element.value == '') {
                    element.value = 'Activity is for...';
                }
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
                                    <c:when test="${process != null}">
                                        ADD NEW ACTIVITY FOR ${process.naziv}
                                    </c:when>
                                    <c:when test="${aktivnost != null}">
                                        ${aktivnost.naziv} DETAILS
                                        <form onsubmit="confirmDelete()" method="GET" name="myForm">
                                            <input type="submit" class="btn btn-default" value="Delete activity">
                                        </form>
                                    </c:when>
                                    <c:otherwise>
                                        ADD NEW ACTIVITY
                                    </c:otherwise>
                                </c:choose>
                            </h1>
                            <c:if test="${aktivnost != null}">
                            </c:if>
                        </div>
                    </div>
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-lg-6">
                                <form role="form" method="POST" id="add_activity_form" action="
                                      <c:choose>
                                          <c:when test="${aktivnost == null}">
                                              <c:if test="${process == null}">
                                                  /dms/activity/adm/add_new
                                              </c:if>
                                              <c:if test="${process != null}">
                                                  /dms/activity/adm/add_new/${process.id}
                                              </c:if>

                                          </c:when>
                                          <c:when test="${aktivnost != null}">
                                              /dms/activity/adm/update/${aktivnost.id}
                                          </c:when>
                                      </c:choose>">
                                    <div class="form-group">
                                        <label>Name</label>
                                        <c:if test="${aktivnost == null}">
                                            <input class="form-control" name="activityname" id="ajdi">
                                            <p class="help-block">Example: activity 1</p> 
                                        </c:if>        
                                        <c:if test="${aktivnost != null}">
                                            <input class="form-control" name="activityname" id="ajdi" value="${aktivnost.naziv}">
                                            <p class="help-block">Example: ${aktivnost.naziv}</p> 
                                        </c:if>
                                    </div>
                                    <div class="form-group">
                                        <label>Sign</label>
                                        <c:if test="${aktivnost == null}">
                                            <input class="form-control" name="activitysign">
                                            <p class="help-block">Example: A1</p> 
                                        </c:if>
                                        <c:if test="${aktivnost != null}">
                                            <input class="form-control" name="activitysign" value="${aktivnost.oznaka}">
                                            <p class="help-block">Example: ${aktivnost.oznaka}</p> 
                                        </c:if>                                        
                                    </div>
                                    <div class="form-group">
                                        <label>Activity description</label>
                                        <c:if test="${aktivnost == null}">
                                            <textarea class="form-control" rows="5" name="activitydescription" id="opis" onfocus="clearContents(this);" onblur="backContents(this);">Activity is for...</textarea>
                                        </c:if>
                                        <c:if test="${aktivnost != null}">
                                            <textarea class="form-control" rows="5" name="activitydescription" id="opis" onfocus="clearContents(this);">${aktivnost.opis}</textarea>
                                        </c:if>
                                    </div>

                                    <div class="form-group">
                                        <label>Activity is for process</label>
                                        <c:if test="${aktivnost == null}">
                                            <select name="procesactivity" class="form-control" <c:if test="${process != null}">disabled="disabled"</c:if>>
                                                <c:forEach var="p" items="${procesi}">
                                                    <option data- value="${p.id}" ${p.id == process.id ? 'selected="selected"' : ''}>${p.naziv}</option>
                                                </c:forEach>
                                            </select>
                                        </c:if>
                                        <c:if test="${aktivnost != null}">
                                            <select name="procesactivity" class="form-control" disabled="disabled">
                                                <c:forEach var="p" items="${procesi}">
                                                    <option data- value="${p.id}" ${p.id == aktivnost.idProcesa.id ? 'selected="selected"' : ''}>${p.naziv}</option>
                                                </c:forEach>
                                            </select>
                                        </c:if>
                                    </div>
                                    <button type="submit" class="btn btn-default"><c:choose>
                                            <c:when test="${aktivnost != null}">
                                                Edit ${aktivnost.naziv}
                                            </c:when>
                                            <c:otherwise>
                                                Create new activity
                                            </c:otherwise>
                                        </c:choose></button>
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

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Processes - overview</title>
        <%@include file="header.jsp" %>

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/themes/default/style.min.css" />

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
                            <h1 class="page-head-line">Test strana</h1>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div id="jstree_demo_div">
                            </div>
                            <form action="#" method="get">
                                <input type="submit" value="Add"> </input>
                                <input style="margin-left: 10px;" type="text" name="procName">
                                <input id="selectedProc" type="hidden" name="selectedProc">
                            </form>

                            <script src="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/jstree.min.js" type="text/javascript"></script>
                            <script>
                                var req;
                                var isIE;

                                function init() {
                                    completeField = document.getElementById("complete-field");
                                }
                                function doCompletion() {
                                    var url = "/dms/api/processes";
                                    req = initRequest();
                                    req.open("GET", url, true);
                                    req.onreadystatechange = callback;
                                    req.send(null);
                                }

                                function initRequest() {
                                    if (window.XMLHttpRequest) {
                                        if (navigator.userAgent.indexOf('MSIE') != -1) {
                                            isIE = true;
                                        }
                                        return new XMLHttpRequest();
                                    } else if (window.ActiveXObject) {
                                        isIE = true;
                                        return new ActiveXObject("Microsoft.XMLHTTP");
                                    }
                                }
                                function callback() {
                                    if (req.readyState == 4) {
                                        if (req.status == 200) {
                                            console.log(parseMessages(req.responseXML));
                                        }
                                    }
                                }
                            </script>
                            <script>
                                $('#jstree_demo_div').jstree({
                                    'core': {
                                        'url': '<spring:url value="/api/processes"/>',
                                        'data': function (node) {
                                            return {'id': node.id};
                                        },
                                        "multiple": false,
                                        "themes": {
                                            "variant": "large"
                                        },
                                        "plugins": ["wholerow"]

                                    }});

                                $('#jstree_demo_div').on("changed.jstree", function (e, data) {
                                    console.log(data.selected);
                                    $('#selectedProc').val(data.selected);
                                });

                            </script>

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

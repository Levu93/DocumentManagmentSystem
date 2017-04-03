<%@page contentType="text/html" pageEncoding="UTF-8"%>
<form action="/dms/activity/user/userdetails" method="GET" id="tree-form-activity-details" style="display: none">
    <input name="idActivity" id="idAct" type="hidden">
    <input name="parent" id="parentAct" type="hidden">
    <div class="form-group">
        <input class="form-control" name="nameAct" id="nameAct">
    </div>
    <div class="form-group">
        <input class="form-control" name="signAct" id="signAct">
    </div>
    <div class="form-group">
        <textarea class="form-control" rows="5" name="description" id="descriptionAct" onfocus="clearContents(this);" onblur="backContents(this);">Activity is for...</textarea>
    </div>
    <!--
    <table class="table table-striped table-bordered table-hover" >
        <thead>
            <tr>
                <th>Name</th>
                <th>Code</th>
                <th>Description</th>
                <th>Download</th>
                <th>Delete</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="dokument" items="${aktivnost.dokumentList}">
            <tr>
                <td>${dokument.naziv}</td>
                <td>${dokument.datumKreiranja}</td>
                <td>${dokument.napomena}</td>
                <td><a href="/dms/documents/download/${dokument.idDokumenta}">Download</a></td>
                <td><a href="/dms/documents/delete/${dokument.idDokumenta}">Delete</a></td>
            </tr>                                            
        </c:forEach>
        </tbody>
    </table>-->
    <input type="submit" value="More data"/>
</form>

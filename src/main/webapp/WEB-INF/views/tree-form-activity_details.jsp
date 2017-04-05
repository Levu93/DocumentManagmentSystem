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
    <input type="submit" class="btn btn-default" value="More data"/>
</form>

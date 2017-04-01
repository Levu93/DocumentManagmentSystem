<%@page contentType="text/html" pageEncoding="UTF-8"%>
<form role="form" method="POST" id="tree-form-activity" action="/dms/activity/adm/update" style="display: none;">
    <input name="id" id="idAct" type="hidden">
    <input name="parent" id="parentAct" type="hidden">
    <div class="form-group">
        <label>Name</label>
        <input class="form-control" name="name" id="nameAct">
        <p class="help-block">Example: activity 1</p> 
    </div>
    <div class="form-group">
        <label>Sign</label>
        <input class="form-control" name="sign" id="signAct" readonly>
        <p class="help-block">Example: A1</p> 
    </div>
    <div class="form-group">
        <label>Activity description</label>
        <textarea class="form-control" rows="5" name="description" id="descriptionAct" onfocus="clearContents(this);" onblur="backContents(this);">Activity is for...</textarea>
    </div>

    <button type="submit" class="btn btn-default" id="button-activity">Edit</button>
    <button type="reset" class="btn btn-default">Reset</button>  
</form>

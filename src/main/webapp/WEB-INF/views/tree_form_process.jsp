<%@page contentType="text/html" pageEncoding="UTF-8"%>
<form role="form" method="POST" id="tree-form-process" action="/dms/processes/adm/update" style="display: none;">
    <input name="id" id="id" type="hidden">
    <input name="parent" id="parent" type="hidden">
    <div class="form-group">
        <label>Name</label>
        <input class="form-control" name="name" id="name">
        <p class="help-block">Example: process1</p>                                         
    </div>
    <div class="form-group">
        <label>Sign</label>
        <input class="form-control" name="sign" id="sign" readonly>
        <p class="help-block">Example: P1</p>                                         
    </div>
    <div class="form-group">
        <label>Description</label>
        <textarea class="form-control" rows="5" name="description" id="description" onfocus="clearContents(this);" onblur="backContents(this)"></textarea>
    </div>
    <div class="form-group" id="form-group-primitive">
        <div class="checkbox">
            <label ><input type="checkbox" name = "primitive" id="primitive">
                Is primitive?
            </label>
        </div>
    </div>  
    <button type="submit" class="btn btn-default" id="button-process">Edit</button>
    <button type="reset" class="btn btn-default">Reset</button>  
</form>

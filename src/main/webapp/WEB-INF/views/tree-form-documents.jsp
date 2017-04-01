<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

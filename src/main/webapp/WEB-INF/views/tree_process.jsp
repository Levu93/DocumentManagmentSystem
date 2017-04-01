<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/themes/default/style.min.css" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/jstree.min.js"></script>

<div id="jstree">
</div>

<script>
    var selectedNode = null;
    $('#jstree').jstree({
        'core': {
            'data': {
                'url': "/dms/api/processes",
                'data': function (node) {
                    return {'id': node.id};
                }
            },
            "multiple": false,
            "themes": {
                "variant": "large"
            },
            "plugins": ["wholerow"]
        }}).on('activate_node.jstree', function (e, data) {
        if (selectedNode !== null && selectedNode.id === data.node.original.id) {
            data.instance.deselect_node(data.node, true);
            $("#tree-form-activity").hide();
            $("#tree-form-process").hide();
            selectedNode = null;
            return;
        }
        selectedNode = data.node.original;
        if (data.node.original.activity) {
            $("#tree-form-process").hide();
            $("#idAct").val(data.node.original.id.substring(0, data.node.original.id.length - 1));
            $("#nameAct").val(data.node.original.text);
            $("#signAct").val(data.node.original.sign);
            $("#descriptionAct").val(data.node.original.description);
            $("#tree-form-activity").show();
        } else {
            $("#tree-form-activity").hide();
            $("#id").val(data.node.original.id.substring(0, data.node.original.id.length - 1));
            $("#name").val(data.node.original.text);
            $("#sign").val(data.node.original.sign);
            $("#description").val(data.node.original.description);
            $("#primitive").prop('checked', data.node.original.primitive);
            console.log("deca "+data.node.original.hasChildren)
            if (data.node.original.hasChildren) {
                $("#form-group-primitive").hide();
            } else {
                $("#form-group-primitive").show();
            }
            $("#tree-form-process").show();
        }
    });
    function add() {
        if (selectedNode === null) {
            //dodaje root
            $("#tree-form-activity").hide();
            $("#id").val(null);
            $("#parent").val(null);
            $("#name").val(null);
            $("#sign").val(null);
            $("#sign").prop("readonly", false);
            $("#description").val(null);
            $("#primitive").prop('checked', false);
            $("#form-group-primitive").show();
            $("#button-process").html("Add");
            $('#tree-form-process').prop('action', '/dms/processes/adm/add_new_tree');
            $("#tree-form-process").show();
        } else if (selectedNode.activity) {
            //nista
            $("#tree-form-activity").hide();
            $("#tree-form-process").hide();
            $('#tree-form-process').prop('action', '/dms/processes/adm/update');
            $('#tree-form-activity').prop('action', '/dms/activity/adm/update');
            $("#button-process").html("Edit");
            $("#button-activity").html("Edit");
        } else if (selectedNode.primitive) {
            //dodaje aktivnost
            $("#tree-form-process").hide();
            $("#idAct").val(null);
            $("#parentAct").val(selectedNode.id.substring(0, selectedNode.id.length - 1));
            $("#nameAct").val(null);
            $("#signAct").val(null);
            $("#signAct").prop("readonly", false);
            $("#descriptionAct").val(null);
            $("#button-activity").html("Add");
            $('#tree-form-activity').prop('action', '/dms/activity/adm/add_new_tree');
            $("#tree-form-activity").show();
        } else {
            //dodaje obican proces
            $("#tree-form-activity").hide();
            $("#id").val(null);
            $("#parent").val(selectedNode.id.substring(0, selectedNode.id.length - 1));
            $("#name").val(null);
            $("#sign").val(null);
            $("#sign").prop("readonly", false);
            $("#description").val(null);
            $("#primitive").prop('checked', false);
            $("#form-group-primitive").show();
            $("#button-process").html("Add");
            $('#tree-form-process').prop('action', '/dms/processes/adm/add_new_sub_tree');
            $("#tree-form-process").show();
        }
    }

</script>
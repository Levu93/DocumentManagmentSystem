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
            selectedNode = null;
            return;
        }
        selectedNode = data.node.original;
        if (data.node.original.activity) {
            //test odavde
            var urlrest = "/dms/api/documents/"+data.node.original.id.substring(0, data.node.original.id.length - 1); 
            $(document).ready(function () {
                $.ajax({
                    url: urlrest
                }).then(function (datadoc) {
                    console.log(datadoc);
                });
            });
            //do ovde
            $("#idAct").val(data.node.original.id.substring(0, data.node.original.id.length - 1));
            $("#nameAct").val(data.node.original.text);
            $("#signAct").val(data.node.original.sign);
            $("#descriptionAct").val(data.node.original.description);
            $("#tree-form-activity-details").show();
            $("#tree_form_documents").hide();
        } else {
            $("#tree-form-activity-details").hide();
            $("#tree_form_documents").hide();
        }
    });
    function addDocument() {
        if (selectedNode === null) {
            //ne prikazuje nista
            $("#tree_form_documents").hide();
            $("#tree-form-activity-details").hide();

        } else if (selectedNode.activity) {
            //upload dokumenata
            $("#tree-form-activity-details").hide();

            $("#parent").val(selectedNode.id.substring(0, selectedNode.id.length - 1));
            $("#name").val(null);
            $("#description").val(null);

            $("#tree_form_documents").show();

        } else if (selectedNode.primitive) {
//nista
            $("#tree-form-activity-details").hide();
            $("#tree_form_documents").hide();

        } else {
            //dodaje obican proces
            $("#tree-form-activity-details").hide();
            $("#tree_form_documents").hide();
        }
    }

</script>
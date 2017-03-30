<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/themes/default/style.min.css" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/jstree.min.js"></script>

<div id="using_json_2" o    nload="<    ">

</div>

<form action="helpFunctions.php" met        hod="POST">
    <input type="submit" value="Add        "> </input>
    <input style="margin-left: 10px;" type="text" name=        "procName">
    <input id="selectedProc" type="hidden" name="sel    ectedPro    c">
</form>
<script>
    $(document).ready(function () {
        $.ajax({
            url: "/dms/api/processes"
        }).then(function (data) {
            $('#using_json_2').jstree({
                "core": {
                   // "url": '/dms/api/processes',
                    'data': function (node) {
                        return {'id': node.id};
                    },
                    "themes": {
                        "variant": "large"
                    }
                },
                "checkbox": {
                    "keep_selected_style": false
                },
                "plugins": ["wholerow", "checkbox"]
            });
            $('#using_json_2').on("changed.jstree", function (e, data) {
                console.log(data.selected);
                $('#selectedProc').val(data.selected);
            });
        });
    });


    /* $(function () {
     $('#using_json_2').jstree({
     "core": {
     "url": '/dms/api/processes',
     'data': [
                 
     ],
     "themes": {
     "variant": "large"
     }
     },
     "checkbox": {
     "keep_selected_style": false
     },
     "plugins": ["wholerow", "checkbox"]
     });
     $('#using_json_2').on("changed.jstree", function (e, data) {
     console.log(data.selected);
     $('#selectedProc').val(data.selected);
     });
     });*/
</script>
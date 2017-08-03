{

    var types = [];
    var div = {};
    var id = 0;

    function loadTypes() {
        $.ajax({
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            type: 'GET',
            url: 'http://localhost:8080/api/find-all-types',
            success: function (data) {
                types = data;
                div = document.getElementById('types');
                for (var i = 0; i < types.length; i++)
                    div.options
                        [div.options.length] = new Option(types[i].name,
                        types[i].id);
            },
            error: function (xhr) {
                alert('Responceble: ' + xhr.responseCode);
            }
        });
    }


    function viborka() {
        for (var i = 0; i < types.length; i++)
            if (document.getElementById('types').value == types[i].name) id = types[i].id;
        id = document.getElementById('types').value;
        $.ajax({
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            type: 'GET',
            url: 'http://localhost:8080/api/viborka/' + id,
            dataType: 'text',
            success: function (data) {
                var response = $.parseJSON(data);
                $.each(response, function (i, item) {
                    var $tr = $('<tr>').append(
                        $('<td>').text(item.name + ' | '),
                        $('<td>').text(item.oficial + ' | '),
                        $('<td>').text(item.english + ' | '), $('<PRE>')
                    );
                    document.write($tr.wrap('<p>').html());
                });
            },
            error: function (xhr) {
                alert(' Responceble read-task :' + xhr.responseCode);
            }
        });


    }


}





const main = {
    init: function () {
        $('.card-link').on('click', function() {
            var id = $(this).attr('id');
            $.ajax({
                type: 'DELETE',
                url: '/delete/notifDelete/' + id,
                dataType: 'json',
                contentType: 'application/json; charset-utf-8'
            }).done(function() {
                alert("Removed Notification ID: " + id);
                window.location.href = '/';
            }).fail(function(error) {
                alert(JSON.stringify(error));
            });
        });

        $('.card-link-all').on('click', function() {
            $.ajax({
                type: 'DELETE',
                url: '/delete/notifDelete/all',
                contentType: 'application/json; charset-utf-8'
            }).done(function() {
                alert('Removed All Notification!');
                window.location.href = '/'
            }).fail(function(error) {
                alert(JSON.stringify(error));
            })
        });
    }
};

main.init();
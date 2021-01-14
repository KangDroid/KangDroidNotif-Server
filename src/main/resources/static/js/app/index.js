const main = {
    init: function () {
        const _this = this;

        // Click Listener
        $('#notification_table').on('click', '.btn-select' , function () {
            var row = $(this).closest('tr');
            const id = row.find('td:eq(0)').text();

            $.ajax({
                type: 'DELETE',
                url: '/delete/notifDelete/' + id,
                dataType: 'json',
                contentType: 'application/json; charset-utf-8'
            }).done(function() {
                alert('Removed ID: ' + id)
                window.location.href = '/';
            }).fail(function(error) {
                alert(JSON.stringify(error));
            });
        });
    }
};

main.init();
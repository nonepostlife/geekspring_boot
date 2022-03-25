$(document).ready(function () {

    document.getElementsByName('quantity')[0].addEventListener('change', updateQuantities);

    function updateQuantities() {
        console.log('sweet');
        $.post("/app/cart/update");
        $.ajax({
            type: "POST",
            url: "http://localhost:8189/app/cart/update",
            success: function () {
                if (xhr.readyState === 4) {
                    console.log(xhr.status);
                    console.log(xhr.responseText);
                }},
            dataType: "json"
        });
        //location.reload();
    }

    $('.removeBtn').on('click', function (event) {
        console.log(123);
        let studentId = $(this).attr('entryIndex');
        $.get("/app/students/remove/" + studentId);
        location.reload();
    });

    $('#consoleTestBtn').on('click', function (event) {
        console.log($('#myInput').val());
    });

    $('#testBtn').on('click', function (event) {
        event.preventDefault();
        console.log('111' + 2);
        $.get("/hello").done(function () {
            alert("AAA");
        });
    });

    $('.myTableRow').on('click', function (event) {
        // event.preventDefault();
        console.log($(this).attr('entryIndex'));
    });

    $('.table .editBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $.get(href, function (book, status) {
            $('.myForm #id').val(book.id);
            $('.myForm #title').val(book.title);
            $('.myForm #author').val(book.author);
        });
        $('.myForm #editModal').modal();
    });

    $('.addNewBookBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $.get(href, function (book, status) {
            $('.myForm #id').val(book.id);
            $('.myForm #title').val(book.title);
            $('.myForm #author').val(book.author);
        });
        $('.myForm #editModal').modal();
    });
});
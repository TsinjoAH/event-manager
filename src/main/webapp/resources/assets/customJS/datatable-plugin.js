$(function () {
    var table = $("#liste-compte")
        .DataTable({
            columnDefs: [
                {
                    searchable: false,
                    targets: 2,
                },
            ],
            responsive: true,
            lengthChange: false,
            autoWidth: false,
            buttons: ["copy", "csv", "excel", "pdf", "print", "colvis"],
        })
        .buttons()
        .container()
        .appendTo("#liste-compte_wrapper .col-md-6:eq(0)");
});

$(function () {
    var table = $("#table")
        .DataTable({
            scrollX: true,
            aaSorting: [],
            buttons: ["copy", "csv", "excel", "pdf", "print", "colvis"],
        })
        .buttons()
        .container()
        .appendTo("#table_wrapper .col-md-6:eq(0)");
});

$(function () {
    var table = $("#liste-exercice")
        .DataTable({
            columnDefs: [
                {
                    searchable: false,
                    targets: 3,
                },
            ],
            responsive: true,
            lengthChange: false,
            autoWidth: false,
            buttons: ["copy", "csv", "excel", "pdf", "print", "colvis"],
        })
        .buttons()
        .container()
        .appendTo("#liste-exercice_wrapper .col-md-6:eq(0)");
});

$(function () {
    var table = $("#liste-journal-par-exercice")
        .DataTable({
            columnDefs: [
                {
                    searchable: false,
                    targets: 2,
                },
            ],
            responsive: true,
            lengthChange: false,
            autoWidth: false,
            buttons: ["copy", "csv", "excel", "pdf", "print", "colvis"],
        })
        .buttons()
        .container()
        .appendTo("#liste-journal-par-exercice_wrapper .col-md-6:eq(0)");
});

$(function () {
    var table = $("#liste-journal")
        .DataTable({
            columnDefs: [
                {
                    searchable: false,
                    targets: 1,
                },
            ],
            responsive: true,
            lengthChange: false,
            autoWidth: false,
            buttons: ["copy", "csv", "excel", "pdf", "print", "colvis"],
        })
        .buttons()
        .container()
        .appendTo("#liste-journal_wrapper .col-md-6:eq(0)");
});

$(function () {
    var table = $("#liste-mouvement")
        .DataTable({
            responsive: true,
            lengthChange: false,
            autoWidth: false,
            buttons: ["copy", "csv", "excel", "pdf", "print", "colvis"],
        })
        .buttons()
        .container()
        .appendTo("#liste-mouvement_wrapper .col-md-6:eq(0)");
});

$(function () {
    var table = $("#liste-ecriture")
        .DataTable({
            responsive: true,
            lengthChange: false,
            autoWidth: false,
            buttons: ["copy", "csv", "excel", "pdf", "print", "colvis"],
        })
        .buttons()
        .container()
        .appendTo("#liste-ecriture_wrapper .col-md-6:eq(0)");
});

$(function () {
    var table = $("#liste-tier")
        .DataTable({
            responsive: true,
            lengthChange: false,
            autoWidth: false,
            buttons: ["copy", "csv", "excel", "pdf", "print", "colvis"],
        })
        .buttons()
        .container()
        .appendTo("#liste-tier_wrapper .col-md-6:eq(0)");
});
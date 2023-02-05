<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>${pageTitle}</title>
    <base href="/">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback" />
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/resources/assets/dist/img/AdminLTELogo.png">

    <%-- css links --%>
    <div>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/plugins/fontawesome-free/css/all.min.css">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/plugins/datatables-bs4/css/dataTables.bootstrap4.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/plugins/datatables-responsive/css/responsive.bootstrap4.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/plugins/datatables-buttons/css/buttons.bootstrap4.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/plugins/select2/css/select2.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/dist/css/adminlte.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/customCSS/style.css">
    </div>
</head>

<body class="">
<div class="">
    <!-- top navbar -->
    <nav class="main-header navbar navbar-white navbar-light" style="margin: 0">
        <ul class="navbar-nav">
            <li class="nav-item">
                Front office
            </li>
        </ul>
    </nav>

    <%-- Main content --%>
    <div class=''>
        <section class='content'>
            <div class='container-fluid'>
                <jsp:include page="${mainPage}" />
            </div>
        </section>
    </div>

    <%-- Footer --%>
    <footer class="main-footer" style="margin: 0">
        <strong>Copyright &copy; 2014-2021<a href="https://adminlte.io">AdminLTE.io</a>.</strong>
        All rights reserved.
        <div class="float-right d-none d-sm-inline-block">
            Events-Manager
        </div>
    </footer>
</div>

<%-- js scripts --%>
<div>
    <script src="${pageContext.request.contextPath}/resources/assets/plugins/jquery/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/assets/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/assets/plugins/datatables/jquery.dataTables.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/assets/plugins/datatables-bs4/js/dataTables.bootstrap4.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/assets/plugins/datatables-responsive/js/dataTables.responsive.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/assets/plugins/datatables-responsive/js/responsive.bootstrap4.min.js"></script>

    <script src="${pageContext.request.contextPath}/resources/assets/plugins/pdfmake/vfs_fonts.js"></script>
    <script>
        $(function () {
            var table = $("#list-table")
                .DataTable({
                    responsive: true,
                    lengthChange: false,
                    autoWidth: false,
                    order: []
                });
        });
    </script>
    <script src="${pageContext.request.contextPath}/resources/assets/plugins/select2/js/select2.full.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/assets/dist/js/adminlte.min.js"></script>
    <script>
        $(function () {
            $('.select2').select2()
        });
    </script>
</div>
</body>

</html>
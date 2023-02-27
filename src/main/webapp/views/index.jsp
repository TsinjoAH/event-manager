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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/dist/css/adminlte.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/customCSS/style.css">
    </div>
    <style>
        .choice {
            max-width: 300px;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
        }
    </style>
</head>

<body>

<div class="container">
    <div class="card choice">
        <h2 class="card-header">
            Event-Manager
        </h2>
        <div class="card-body">
            <div class="row">
                <div class="col-12 mt-2">
                    <a href="${pageContext.request.contextPath}/admin/login" class="btn btn-primary w-100">Admin</a>
                </div>
                <div class="col-12 mt-2">
                    <a href="${pageContext.request.contextPath}/author/login" class="btn btn-primary w-100">Auteur</a>
                </div>
                <div class="col-12 mt-2">
                    <a href="${pageContext.request.contextPath}/front-office" class="btn btn-primary w-100">Visiteur</a>
                </div>
            </div>
        </div>
    </div>
</div>

<%-- js scripts --%>
<div>
    <script src="${pageContext.request.contextPath}/resources/assets/plugins/jquery/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/assets/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/assets/dist/js/adminlte.min.js"></script>
</div>
</body>

</html>
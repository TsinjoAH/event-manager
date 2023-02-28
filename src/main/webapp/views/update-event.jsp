<%@ page import="com.management.events.services.LoginService" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Modification</title>
    <base href="/">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback" />
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/resources/assets/dist/img/AdminLTELogo.png">

    <%-- css links --%>
    <div>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/dist/css/adminlte.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/customCSS/style.css">
    </div>
</head>

<body class="">
<div class="">

    <%-- Main content --%>
    <div class=''>
        <section class='content'>
            <div class='container-fluid'>
                <br>

                <div class="card bg-gradient-light">

                    <div class="card-header border-0">
                        <h2 class="card-title pt-1">
                            Modification
                        </h2>
                    </div>
                    <div class="card-header m-0 p-0">
                        <div class="row text-danger">
                            <div class="col">
                            </div>
                        </div>
                    </div>

                    <form action="${pageContext.request.contextPath}/update/${event.id}" method="POST" class="card-body" id="form" enctype="multipart/form-data">
                        <div class="card-body">
                            <div class="row">

                                <div class="form-group col-md-4 mb-3">
                                    <label>Titre</label>
                                    <input name="title" value="${event.title}" type="text" class="form-control" placeholder="Titre" required>
                                </div>

                                <div class="form-group col-md-4 mb-3">
                                    <label>Type</label>
                                    <select name="type.id" class="custom-select" id="type" required>
                                        <option value="">-- choisir le type --</option>
                                        <c:forEach items="${formData.getTypes()}" var="type">
                                            <option value="${type.getId()}" ${type.getId() == event.type.id ? "selected": ""}>${type.getName()}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="form-group col-md-4 mb-3">
                                    <label>Lieu</label>
                                    <select name="city.id" class="custom-select" required>
                                        <option value="">-- choisir le lieu --</option>
                                        <c:forEach items="${formData.getCityList()}" var="lieu">
                                            <option value="${lieu.getId()}" ${lieu.getId() == event.city.id ? "selected": ""} >${lieu.getName()}</option>
                                        </c:forEach>
                                    </select>
                                </div>


                                <div class="form-group col-md-12 mb-3">
                                    <label>Description</label>
                                    <textarea name="description" class="form-control" placeholder="Description" required>${event.description}</textarea>
                                </div>


                                <div class="form-group col-md-6 mb-3">
                                    <label>Date debut</label>
                                    <input name="startDate" value="${event.startDate != null ? event.startDate.toString().substring(0,16) : "" }" type="datetime-local" class="form-control" required>
                                </div>

                                <div class="form-group col-md-6 mb-3">
                                    <label>Date fin </label>
                                    <input name="endDate" id="endDate" value="${event.endDate != null ? event.endDate.toString().substring(0,16) : "" }" type="datetime-local" class="form-control" >
                                </div>

                                <% if (LoginService.isAdmin(request.getSession())) { %>
                                <div class="form-group col-md-6">
                                    <label>Date de publication</label>
                                    <input type="datetime-local" class="form-control" name="publishedDate" value="${event.publishedDate != null ? event.publishedDate.toString().substring(0,16) : "" }">
                                </div>

                                <div class="form-group col-md-6">
                                    <label>Etat</label>
                                    <select name="status" class="custom-select" required>
                                        <option value="">-- choisir l'etat --</option>
                                        <option value="10" ${event.status == 10 ? "selected": ""}>PUBLISHED</option>
                                        <option value="0" ${event.status == 0 ? "selected": ""}>DRAFT</option>
                                    </select>
                                </div>
                                <% } %>

                                <div class="form-group col-md-6">
                                    <label>Image</label>
                                    <div class="input-group">
                                        <input name="image" type="file" class="custom-file-input" id="image">
                                        <label for="image" class="custom-file-label" >Choisir une image</label>
                                    </div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label>On HomePage</label>
                                    <%-- select --%>
                                    <select name="homeStatus" class="custom-select" required>
                                        <option value="">-- choisir --</option>
                                        <option value="10" ${event.homeStatus == 10 ? "selected": ""}>Oui</option>
                                        <option value="0" ${event.homeStatus == 0 ? "selected": ""}>Non</option>
                                    </select>
                                </div>

                                <div id="preview" class="col-md-4">
                                    <img src="${pageContext.request.contextPath}/resources/images/${event.image}" class="img-fluid" alt="">
                                </div>
                            </div>
                        </div>


                        <div class="card-footer bg-transparent">
                            <button id="btn" class="btn btn-primary" >Modifier</button>
                        </div>
                    </form>
                </div>

                <br>
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
    <script>

        let endDate = $("#endDate");
        let type = $("#type");
        let check = () => {
            if (type.val() === "2") {
                endDate.val("");
                endDate.attr("disabled", true);
            }
            else {
                endDate.attr("disabled", false);
            }
        }

        check();

        type.change(() => {
            check();
        });

    </script>
    <script>
        let fileState = true;
        $("#image").change(() => {
            let file = document.getElementById('image').files[0];
            let fileName = file.name;
            let fileSize = file.size;
            let fileExtension = fileName.split('.').pop();
            if (fileExtension === 'jpg' || fileExtension === 'jpeg' || fileExtension === 'png') {
                if (fileSize < 1000000) {
                    fileState = true;
                    $('.custom-file-label').text(fileName);
                    getBase64((base64) => {
                        $("#preview").html(`<img src="\${base64}" alt="" class="img-fluid">`);
                    });
                } else {
                    fileState = false;
                    alert('Le fichier est trop volumineux');
                }
            } else {
                fileState = false;
                alert('Le fichier n\'est pas une image');
            }
        });

        const getBase64 = (get) => {
            let reader = new FileReader();
            let file = document.getElementById('image').files[0];
            if (file) {
                reader.readAsDataURL(file);
                reader.onload = () => {
                    get(reader.result);
                }
            }
            get("");
        }

        const getData = (get) => {
            let form = $('#form');
            let data = new FormData(form[0]);
            let json = {};
            for (let [key, value] of data.entries()) {
                if (key === 'image') continue;
                if (key === 'type.id' || key === 'city.id') {
                    json[key.split('.')[0]] = {id: value}
                }
                json[key] = value;
            }
            // skip if no image
            getBase64((base64) => {
                json["image"] = base64;
                get(json);
            })
        }

        let btn = $("#btn");
        let form = $("#form");

        form.submit((e) => {
            e.preventDefault();
            btn.attr('disabled', true);
            if (fileState) {
                getData((data) => {
                    $.ajax({
                        url: form.attr('action'),
                        type: 'PUT',
                        data: JSON.stringify(data),
                        processData: false,
                        contentType: 'application/json',
                        success: function (response) {
                            window.location.replace("${pageContext.request.contextPath}/update/${event.id}");
                        },
                        error: function (error) {
                            let err = JSON.parse(error.responseText);
                            alert(err.message);
                            btn.attr('disabled', false)
                        }
                    });
                });
            } else {
                alert('Veuillez choisir une image valide');
                btn.attr('disabled', false)
            }
        });

    </script>
    <script src="${pageContext.request.contextPath}/resources/assets/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/assets/dist/js/adminlte.min.js"></script>
</div>
</body>

</html>
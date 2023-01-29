<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<br>

<div class="card bg-gradient-light">

    <div class="card-header border-0">
        <h2 class="card-title pt-1">
            Ajout
        </h2>
    </div>
    <div class="card-header m-0 p-0">
        <div class="row text-danger">
            <div class="col">
            </div>
        </div>
    </div>

    <form action="${pageContext.request.contextPath}/save-event" method="POST" class="card-body" id="form" enctype="multipart/form-data">
        <div class="card-body">
            <div class="row">

                <div class="form-group col-md-4 mb-3">
                    <label>Titre</label>
                    <input name="title" value="Evenement titre" type="text" class="form-control" placeholder="Titre" required>
                </div>

                <div class="form-group col-md-4 mb-3">
                    <label>Type</label>
                    <select name="type.id" class="custom-select" required>
                        <option value="">-- choisir le type --</option>
                        <c:forEach items="${formData.getTypes()}" var="type">
                            <option value="${type.getId()}">${type.getName()}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group col-md-4 mb-3">
                    <label>Lieu</label>
                    <select name="city.id" class="custom-select" required>
                        <option value="">-- choisir le lieu --</option>
                        <c:forEach items="${formData.getCityList()}" var="lieu">
                            <option value="${lieu.getId()}">${lieu.getName()}</option>
                        </c:forEach>
                    </select>
                </div>


                <div class="form-group col-md-12 mb-3">
                    <label>Description</label>
                    <textarea name="description" class="form-control" placeholder="Description" required>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Adipisci aliquam cupiditate, distinctio eius illum, iste laboriosam laudantium maiores mollitia numquam obcaecati odio pariatur placeat quidem quisquam recusandae temporibus voluptatem voluptates?</textarea>
                </div>


                <div class="form-group col-md-6 mb-3">
                    <label>Date debut</label>
                    <input name="startDate" value="2023-01-10 13:00" type="datetime-local" class="form-control" required>
                </div>

                <div class="form-group col-md-6 mb-3">
                    <label>Date fin</label>
                    <input name="endDate"  value="2023-01-10 17:00" type="datetime-local" class="form-control" required>
                </div>

                <div class="form-group col-md-12">
                    <label>Image</label>
                    <div class="input-group">
                        <input name="image" type="file" class="custom-file-input" id="image" required>
                        <label for="image" class="custom-file-label" >Choisir une image</label>
                    </div>
                </div>

                <div id="preview" class="col-md-4">

                </div>
            </div>
        </div>

        <div class="card-footer bg-transparent">
            <button id="btn" class="btn btn-primary" >Ajouter</button>
        </div>
    </form>
</div>

<br>

<script src="${pageContext.request.contextPath}/resources/assets/plugins/jquery/jquery.min.js"></script>
<script>

    let fileState = false;
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
        reader.readAsDataURL(document.getElementById('image').files[0]);
        reader.onload = () => {
            get(reader.result);
        }
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
                    type: form.attr('method'),
                    data: JSON.stringify(data),
                    processData: false,
                    contentType: 'application/json',
                    success: function (response) {
                        window.location.replace("${pageContext.request.contextPath}/list-event");
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

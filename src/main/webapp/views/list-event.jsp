<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/plugins/fontawesome-free/css/all.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/plugins/select2/css/select2.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/dist/css/adminlte.min.css">

<br>

<form>
    <div class="row">
        <div class="col-md-10 offset-md-1">
            <div class="row">
                <div class="col-6">
                    <div class="form-group">
                        <label>Type:</label>
                        <select name="type" class="select2" data-placeholder="Any" style="width: 100%;">
                            <option value="0" >-- choisir type --</option>
                            <c:forEach items="${formData.getTypes()}" var="item">
                                <option value="${item.id}" ${filter.getType() == item.id ? "selected" : ""} >${item.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="col-3">
                    <div class="form-group">
                        <label>Order:</label>
                        <select name="desc" class="select2" style="width: 100%;">
                            <option value="0" ${filter.getDesc() ? "" : "selected"}>ASC</option>
                            <option value="1" ${filter.getDesc() ? "selected" : ""} >DESC</option>
                        </select>
                    </div>
                </div>
                <div class="col-3">
                    <div class="form-group">
                        <label>Order By:</label>
                        <select name="orderedByStart" class="select2" style="width: 100%;">
                            <option value="1" ${filter.getOrderedByStart() ? "selected" : ""}>Debut</option>
                            <option value="0" ${filter.getOrderedByStart() ? "" : "selected"}>Fin</option>
                        </select>
                    </div>
                </div>
                <input type="hidden" name="page" value="${filter.getPage()}" >
            </div>

            <div class="form-group">
                <div class="input-group input-group-lg">
                    <input name="keyword" value="${filter.getKeyword()}" type="search" class="form-control form-control-lg" placeholder="Type your keywords here" >
                    <div class="input-group-append">
                        <button onclick="search()" class="btn btn-lg btn-default">
                            <i class="fa fa-search"></i>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>

<div class="row mt-3">
    <div class="col-md-10 offset-md-1">
        <div class="list-group">
            <c:if test="${events.size() == 0}" >
                <div class="alert alert-info">
                    Aucune resultat
                </div>
            </c:if>
            <c:forEach items="${events}" var="item">
                <%-- set color var --%>
                <c:set var="color" value='${!(item.type.id == 1) ? "success" : "primary"}' />

                <div class="list-group-item">
                    <div class="row">
                        <div class="col-auto">
                            <img class="img-fluid" src="${pageContext.request.contextPath}/resources/images/${item.image}" alt="Photo" style="max-height: 160px;">
                        </div>
                        <div class="col px-4">
                            <div>
                                <div class="float-right">
                                    <span class="badge badge-${color}">${item.type.name}</span>
                                </div>
                                <h3 class="text-${color}" >${item.title}</h3>
                                <p class="mb-0">${item.description}</p>
                                <br>
                                <p>
                                    <u>Date:</u> ${item.getStartDate().toString().replace("T", " ").substring(0, 16)} <br>
                                    <c:if test="${item.getEndDate() != null}" >
                                        <u>Fin:</u> ${item.getEndDate().toString().replace("T", " ").substring(0, 16)} <br>
                                    </c:if>
                                    <u>Date de creation:</u> ${item.getCreationDate().toString().replace("T", " ").substring(0, 16)} <br>
                                </p>
                                <div class="d-flex">
                                    <%-- validate action btn if request attribute allowedValidation and isValidated false --%>
                                    <c:if test="${allowedValidation != null && allowedValidation && !item.isValidated()}" >
                                        <form class="mr-2" action="${pageContext.request.contextPath}/admin/events/validate/${item.id}" method="POST">
                                            <button class="btn btn-primary">Publier</button>
                                        </form>
                                    </c:if>

                                    <c:if test="${allowedValidation != null && allowedValidation && item.getPublishedDate() == null}" >
                                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modal${item.getId()}">
                                            Ajoutez une date de publication
                                        </button>
                                    </c:if>
                                </div>
                                <!-- Modal -->
                                <div class="modal fade" id="modal${item.getId()}" tabindex="-1" role="dialog" aria-labelledby="modal${item.getId()}Title" aria-hidden="true">
                                    <div class="modal-dialog modal-dialog-centered" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="exampleModalLongTitle">${item.getTitle()}</h5>
                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <form action="${pageContext.request.contextPath}/admin/events/update-date/${item.id}" method="post">
                                                <div class="modal-body">
                                                        <div class="form-group">
                                                            <label>Date de publication</label>
                                                            <input type="datetime-local" class="form-control" name="publishedDate" required>
                                                        </div>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                    <button type="submit" class="btn btn-primary">Save changes</button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>



<div class="row mt-3">
    <div class="col-md-10 offset-md-1 d-flex justify-content-between">
        <button class="btn btn-outline-primary" onclick="goto(-1)" ${filter.getPage() > 0 ? "": "disabled"} >Previous</button>
        <button class="btn btn-outline-primary" onclick="goto(1)" ${events.size() > 0 && events.size() == 4 ? "": "disabled"} >Next</button>
    </div>
</div>
<br>

<script>
    const form = document.forms[0];
    function goto(page) {
        form.page.value = parseInt(form.page.value) + page;
        form.submit();
    }

    function search() {
        form.page.value = 0;
        form.submit();
    }
</script>

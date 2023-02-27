
<br>
<div class="card">
    <div class="card-header">Nombre d'element par pagination</div>
    <div class="card-body">
        <form action="${pageContext.request.contextPath}/params/save" method="POST">
            <div class="row">
                <div class="form-group col-md-6">
                    <label>Home page</label>
                    <input type="number" name="value" value="${currVal}" class="form-control">
                </div>
            </div>
            <button class="btn btn-primary">Valider</button>
        </form>
    </div>
</div>
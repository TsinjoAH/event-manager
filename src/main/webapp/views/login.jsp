<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <title>Admin | Log in</title>

  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">

  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/plugins/fontawesome-free/css/all.min.css" >
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/plugins/icheck-bootstrap/icheck-bootstrap.min.css" >
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/dist/css/adminlte.min.css" >
</head>

<body class="hold-transition login-page">
  <div class="login-box">
    <div class="login-logo">
      <a href="${pageContext.request.contextPath}/resources/admin/connect" ><b>Events-Manager | </b>Admin</a>
    </div>

    <div class="card">
      <div class="card-body login-card-body">
        <p class="login-box-msg">Sign in to start your session</p>

        <form action="${pageContext.request.contextPath}/admin/do-login" method="post">
          <div class="input-group mb-3">
            <input value="admin@gmail.com" type="email" class="form-control" name="email" placeholder="Log in" required>
            <div class="input-group-append">
              <div class="input-group-text">
                <span class="fas fa-envelope"></span>
              </div>
            </div>
          </div>
          <div class="input-group mb-3">
            <input value="admin" type="password" class="form-control" name="password" placeholder="Password" required>
            <div class="input-group-append">
              <div class="input-group-text">
                <span class="fas fa-lock"></span>
              </div>
            </div>
          </div>
            <c:if test="${not empty inputError}">
              <div class="alert alert-danger">
                <c:out value="${inputError}"/>
              </div>
            </c:if>
          <div class="row">
            <div class="col-8">
              <div class="icheck-primary">
                <input type="checkbox" id="remember">
                <label for="remember">
                  Remember Me
                </label>
              </div>
            </div>

            <div class="col-4">
              <button type="submit" class="btn btn-primary btn-block">Sign In</button>
            </div>

          </div>
        </form>

      </div>
    </div>
  </div>

  <script src="${pageContext.request.contextPath}/resources/assets/plugins/jquery/jquery.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/assets/plugins/bootstrap/js/bootstrap.bundle.min.js" ></script>
  <script src="${pageContext.request.contextPath}/resources/assets/dist/js/adminlte.min.js" ></script>

</body>
</html>

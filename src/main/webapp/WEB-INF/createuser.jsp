<%--
  Created by IntelliJ IDEA.
  User: mouhamed
  Date: 06/08/2019
  Time: 13:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Page liste</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<form method="POST" class="form" action="${pageContext.request.contextPath}/UserServlet" enctype="multipart/form-data">
    <input type="hidden" name="action" value="listeb"/>

    <div class="box box-primary">
        <div class="box-body">
            <div class="row">
                <div class="col-md-offset-1 col-md-5">
                    <div class="col-md-8 col-xs-12 col-md-offset-2" style="margin-top:150px;">
                        <div class="panel panel-primary">
                            <div class="panel-heading">INSCRIPTION</div>
                            <div class="panel-body">

                                <form style="margin-left: 50px" class="form" action="" method="POST"
                                      enctype="multipart/form-data">

                                    <input type="hidden" name="action" value="inscription"/>
                                    <div class="row">
                                        <div class="col-md-7">
                                            <div class="form-group">
                                                <label>Nom</label>
                                                <input type="text" name="nom" placeholder="Nom" class="form-control">
                                            </div>
                                            <div class="form-group">
                                                <label>Login</label>
                                                <input type="text" name="username" placeholder="Login" class="form-control">
                                            </div>
                                            <div class="form-group">
                                                <label>Password</label>
                                                <input type="text" name="pwd" placeholder="Password" class="form-control">
                                            </div>
                                            <div class="form-group">
                                                <label>photo</label>
                                                <input type="file" id="photo" name="photo" class="form-control">
                                            </div>
                                            <div class="form-group">
                                                <label>role</label>
                                                <select name="role" class="form-control" required="true">
                                                    <option value="">Selectionner le role</option>
                                                    <c:forEach items="${roles}" var="r">
                                                        <option value="${r.id}">${r.libelle}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>

                                        </div>
                                        <div class="col-md-5">
                                            <img style="text-align: right"
                                                 src="${pageContext.request.contextPath}/images/default.png" alt="OOoops!!"
                                                 id="image" sizes="" width="60%" srcset="">
                                        </div>
                                    </div>
                                    <button type="submit" class="btn btn-success btn-lg">Valider</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-6 col-xs-12" style="margin-top:150px;">
                    <div class="box box-primary">
                        <div class="box-header">LISTE DES UTILISATEURS</div>
                        <div class="box-body">
                            <c:if test="${users != null && users.size() > 0}">
                                <table class="table table-bordered table-striped">
                                    <tr>
                                        <th>Nom</th>
                                        <th>Login</th>
                                        <th>Role</th>
                                        <th>Action</th>
                                    </tr>
                                    <c:forEach items="${users}" var="u">

                                        <tr>
                                            <td>${u.nom}</td>
                                            <td>${u.login}</td>
                                            <td>${u.idrole.libelle}</td>
                                            <td>
                                                <a class="btn btn-danger" href="${u.id}">Supprimer</a>
                                                <button type="button" class="btn btn-success" data-toggle="modal">Edit</button>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


</form>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function () {
        $("#message").hide();

        function imageIsLoaded(e) {
            //$("#file").css("color","green");
            //$('#image_preview').css("display", "block");
            $('#image').attr('src', e.target.result);
            $('#image').attr('width', '300px');
            $('#image').attr('height', '300px');
        }
        ;
        $('#photo').change(function () {
            //alert('ok');
            var assetsBaseDir = "images/";

            $("#message").empty(); // To remove the previous error message
            var file = this.files[0];

            var imagefile = file.type;
            //alert(imagefile);
            var match = ["image/jpeg", "image/png", "image/jpg"];
            if (!((imagefile == match[0]) || (imagefile == match[1]) || (imagefile == match[2]))) {
                //alert('no match');
                $('#image').attr('src', assetsBaseDir + 'default.png');
                $("#message").show();
                $("#message").html("Selectionnez une image valide, Note : Seules jpeg, jpg et png Images sont autorisés");
                return false;
            } else {
                //alert('match');
                var reader = new FileReader();
                reader.onload = imageIsLoaded;
                reader.readAsDataURL(this.files[0]);
            }
        });
    });

</script>
</body>

</html>

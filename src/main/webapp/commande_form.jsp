<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Créer une commande</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">

<div class="container mt-5">
    <h3>Créer une commande</h3>

    <form action="CommandeServlet" method="post" class="card p-4">

        <div class="mb-3">
            <label>Client :</label>
            <select name="id_client" class="form-control" required>
                <c:forEach var="c" items="${clients}">
                    <option value="${c.id}">${c.nom} ${c.prenom}</option>
                </c:forEach>
            </select>
        </div>

        <div class="mb-3">
            <label>Date de commande :</label>
            <input type="date" name="date_commande" class="form-control" required>
        </div>

        <button class="btn btn-primary w-100">Créer la commande</button>
    </form>

</div>

</body>
</html>

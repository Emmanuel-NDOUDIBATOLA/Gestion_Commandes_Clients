<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Ajouter un produit</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">

<div class="container mt-4">

    <h2>Ajouter un produit à la commande #${idCommande}</h2>

    <form action="LigneCommandeServlet" method="post" class="card p-4">

        <input type="hidden" name="id_commande" value="${idCommande}">

        <div class="mb-3">
            <label>Produit :</label>
            <select name="id_produit" class="form-control" required>
                <c:forEach var="p" items="${produits}">
                    <option value="${p.id}">${p.nom} - ${p.prix} €</option>
                </c:forEach>
            </select>
        </div>

        <div class="mb-3">
            <label>Quantité :</label>
            <input type="number" name="quantite" class="form-control" min="1" required>
        </div>

        <button class="btn btn-primary w-100">Ajouter</button>
    </form>

</div>

</body>
</html>
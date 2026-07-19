<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Détails commande</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">

<div class="container mt-4">

    <h2>Détails de la commande #${commande.id}</h2>

    <p><strong>Client :</strong> ${commande.idClient}</p>
    <p><strong>Date :</strong> ${commande.dateCommande}</p>
    <p><strong>Total :</strong> ${commande.total} €</p>

    <hr>

    <h4>Produits</h4>

    <table class="table table-bordered">
        <thead>
            <tr>
                <th>ID Ligne</th>
                <th>ID Produit</th>
                <th>Quantité</th>
                <th>Prix unitaire</th>
                <th>Total ligne</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="lc" items="${lignes}">
                <tr>
                    <td>${lc.id}</td>
                    <td>${lc.idProduit}</td>
                    <td>${lc.quantite}</td>
                    <td>${lc.prixUnitaire} €</td>
                    <td>${lc.quantite * lc.prixUnitaire} €</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <a href="LigneCommandeServlet?action=add&id_commande=${commande.id}" class="btn btn-success">
        Ajouter un produit
    </a>

    <a href="CommandeServlet" class="btn btn-secondary">Retour</a>

</div>

</body>
</html>

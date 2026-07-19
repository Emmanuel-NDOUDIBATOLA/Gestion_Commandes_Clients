<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, models.Produit" %>

<!DOCTYPE html>
<html>
<head>
    <title>Produits</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-4">
    <h2>Liste des Produits</h2>

    <a href="ProduitServlet?action=add" class="btn btn-primary mb-3">Ajouter un produit</a>
    <form action="ProduitServlet" method="get" class="mb-3">
    <input type="hidden" name="action" value="search">
    <div class="input-group">
        <input type="text" name="keyword" class="form-control" placeholder="Rechercher un produit...">
        <button class="btn btn-primary">Rechercher</button>
    </div>
</form>
    

    <table class="table table-bordered table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Nom</th>
            <th>Prix</th>
            <th>Stock</th>
            <th>Actions</th>
        </tr>
        </thead>

        <tbody>
        <%
            List<Produit> produits = (List<Produit>) request.getAttribute("produits");
            if (produits != null) {
                for (Produit p : produits) {
        %>
        <tr>
            <td><%= p.getId() %></td>
            <td><%= p.getNom() %></td>
            <td><%= p.getPrix() %> €</td>
            <td><%= p.getStock() %></td>
            <td>
                <a href="ProduitServlet?action=edit&id=<%= p.getId() %>" class="btn btn-warning btn-sm">Modifier</a>
                <a href="ProduitServlet?action=delete&id=<%= p.getId() %>" class="btn btn-danger btn-sm">Supprimer</a>
            </td>
        </tr>
        <%      }
            }
        %>
        </tbody>
    </table>
</div>

</body>
</html>

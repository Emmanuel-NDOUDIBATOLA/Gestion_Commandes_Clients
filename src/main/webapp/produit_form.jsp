<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="models.Produit" %>

<%
    Produit produit = (Produit) request.getAttribute("produit");
    boolean edit = (produit != null);
%>

<!DOCTYPE html>
<html>
<head>
    <title><%= edit ? "Modifier Produit" : "Ajouter Produit" %></title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-4">

    <h2><%= edit ? "Modifier un Produit" : "Ajouter un Produit" %></h2>

    <form action="ProduitServlet" method="post" class="mt-4">

        <% if (edit) { %>
            <input type="hidden" name="id" value="<%= produit.getId() %>">
        <% } %>

        <div class="mb-3">
            <label>Nom du produit</label>
            <input type="text" name="nom_produit" class="form-control"
                   value="<%= edit ? produit.getNom() : "" %>" required>
        </div>

        <div class="mb-3">
            <label>Prix (€)</label>
            <input type="number" step="0.01" name="prix" class="form-control"
                   value="<%= edit ? produit.getPrix() : "" %>" required>
        </div>

        <div class="mb-3">
            <label>Quantité en stock</label>
            <input type="number" name="quantite_stock" class="form-control"
                   value="<%= edit ? produit.getStock() : "" %>" required>
        </div>

        <button class="btn btn-success">
            <%= edit ? "Mettre à jour" : "Ajouter" %>
        </button>

        <a href="ProduitServlet" class="btn btn-secondary">Annuler</a>

    </form>

</div>

</body>
</html>

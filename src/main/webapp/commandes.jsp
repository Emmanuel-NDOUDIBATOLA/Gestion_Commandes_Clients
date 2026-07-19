<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, models.Commande" %>
<%@ page import="java.text.SimpleDateFormat" %>

<%
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Commandes</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-4">
    <h2>Liste des Commandes</h2>

    <a href="CommandeServlet?action=add" class="btn btn-primary mb-3">Ajouter une commande</a>

    <table class="table table-bordered table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Client</th>
            <th>Date</th>
            <th>Montant Total (€)</th>
            <th>Actions</th>
        </tr>
        </thead>

        <tbody>
        <%
            List<Commande> commandes = (List<Commande>) request.getAttribute("commandes");
            if (commandes != null) {
                for (Commande c : commandes) {
        %>
        <tr>
            <td><%= c.getId() %></td>
            <td><%= c.getIdClient() %></td>
            <td><%= sdf.format(c.getDateCommande()) %></td>
            <td><%= String.format("%.2f", c.getMontantTotal()) %> €</td>
            <td>
                <a href="CommandeServlet?action=delete&id=<%= c.getId() %>" 
                   class="btn btn-danger btn-sm">Supprimer</a>
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

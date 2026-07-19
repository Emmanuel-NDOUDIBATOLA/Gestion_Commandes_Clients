<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, models.Client" %>

<!DOCTYPE html>
<html>
<head>
    <title>Clients</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-4">
    <h2>Liste des Clients</h2>

    <a href="ClientServlet?action=add" class="btn btn-primary mb-3">Ajouter un client</a>
    <form action="ClientServlet" method="get" class="mb-3">
    <input type="hidden" name="action" value="search">
    <div class="input-group">
        <input type="text" name="keyword" class="form-control" placeholder="Rechercher un client...">
        <button class="btn btn-primary">Rechercher</button>
    </div>
</form>
    

    <table class="table table-bordered table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Nom</th>
            <th>Prénom</th>
            <th>Email</th>
            <th>Téléphone</th>
            <th>Adresse</th>
            <th>Actions</th>
        </tr>
        </thead>

        <tbody>
        <%
            List<Client> clients = (List<Client>) request.getAttribute("clients");
            if (clients != null) {
                for (Client c : clients) {
        %>
        <tr>
            <td><%= c.getId() %></td>
            <td><%= c.getNom() %></td>
            <td><%= c.getPrenom() %></td>
            <td><%= c.getEmail() %></td>
            <td><%= c.getTelephone() %></td>
            <td><%= c.getAdresse() %></td>
            <td>
                <a href="ClientServlet?action=edit&id=<%= c.getId() %>" class="btn btn-warning btn-sm">Modifier</a>
                <a href="ClientServlet?action=delete&id=<%= c.getId() %>" class="btn btn-danger btn-sm">Supprimer</a>
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

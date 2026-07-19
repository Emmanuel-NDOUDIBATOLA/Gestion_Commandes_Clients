<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="models.Client" %>

<%
    Client client = (Client) request.getAttribute("client");
    boolean edit = (client != null);
%>

<!DOCTYPE html>
<html>
<head>
    <title><%= edit ? "Modifier Client" : "Ajouter Client" %></title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-4">

    <h2><%= edit ? "Modifier un Client" : "Ajouter un Client" %></h2>

    <form action="ClientServlet" method="post" class="mt-4">

        <% if (edit) { %>
            <input type="hidden" name="id" value="<%= client.getId() %>">
        <% } %>

        <div class="mb-3">
            <label>Nom</label>
            <input type="text" name="nom" class="form-control"
                   value="<%= edit ? client.getNom() : "" %>" required>
        </div>

        <div class="mb-3">
            <label>Prénom</label>
            <input type="text" name="prenom" class="form-control"
                   value="<%= edit ? client.getPrenom() : "" %>" required>
        </div>

        <div class="mb-3">
            <label>Email</label>
            <input type="email" name="email" class="form-control"
                   value="<%= edit ? client.getEmail() : "" %>" required>
        </div>

        <div class="mb-3">
            <label>Téléphone</label>
            <input type="text" name="telephone" class="form-control"
                   value="<%= edit ? client.getTelephone() : "" %>" required>
        </div>

        <div class="mb-3">
            <label>Adresse</label>
            <input type="text" name="adresse" class="form-control"
                   value="<%= edit ? client.getAdresse() : "" %>" required>
        </div>

        <button class="btn btn-success">
            <%= edit ? "Mettre à jour" : "Ajouter" %>
        </button>

        <a href="ClientServlet" class="btn btn-secondary">Annuler</a>

    </form>

</div>

</body>
</html>

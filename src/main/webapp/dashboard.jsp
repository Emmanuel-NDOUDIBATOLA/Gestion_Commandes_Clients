<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="models.Admin" %>

<%
    Admin admin = (Admin) session.getAttribute("admin");
    if (admin == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>

<nav class="navbar navbar-dark bg-dark">
    <div class="container-fluid">
        <span class="navbar-brand">Gestion Commerciale</span>
        <a href="LogoutServlet" class="btn btn-danger">Déconnexion</a>
    </div>
</nav>

<div class="container mt-4">

    <h3>Bienvenue, <%= admin.getUsername() %>!</h3>

    <div class="mt-4">
        <a href="ClientServlet" class="btn btn-primary">Gérer les Clients</a>
        <a href="ProduitServlet" class="btn btn-success">Gérer les Produits</a>
        <a href="CommandeServlet" class="btn btn-warning">Gérer les Commandes</a>
    </div>
</div>
<div class="container mt-4">
    <h2>Tableau de Bord</h2>

    <div class="row mt-4">

        <div class="col-md-3">
            <div class="card text-center bg-primary text-white">
                <div class="card-body">
                    <h3><%= request.getAttribute("totalClients") %></h3>
                    <p>Clients</p>
                </div>
            </div>
        </div>

        <div class="col-md-3">
            <div class="card text-center bg-success text-white">
                <div class="card-body">
                    <h3><%= request.getAttribute("totalProduits") %></h3>
                    <p>Produits</p>
                </div>
            </div>
        </div>

        <div class="col-md-3">
            <div class="card text-center bg-warning text-white">
                <div class="card-body">
                    <h3><%= request.getAttribute("totalCommandes") %></h3>
                    <p>Commandes</p>
                </div>
            </div>
        </div>

        <div class="col-md-3">
            <div class="card text-center bg-danger text-white">
                <div class="card-body">
                    <h3><%= request.getAttribute("totalVentes") %> €</h3>
                    <p>Total des ventes</p>
                </div>
            </div>
        </div>

    </div>
</div>


</body>
</html>

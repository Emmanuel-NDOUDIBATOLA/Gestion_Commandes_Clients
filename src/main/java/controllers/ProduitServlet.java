package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProduitDAO;
import models.Produit;

@WebServlet("/ProduitServlet")
public class ProduitServlet extends HttpServlet {

    private ProduitDAO dao = new ProduitDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {

            case "add":
                request.getRequestDispatcher("produit_form.jsp").forward(request, response);
                break;

            case "edit":
                int id = Integer.parseInt(request.getParameter("id"));
                Produit p = dao.getById(id);
                request.setAttribute("produit", p);
                request.getRequestDispatcher("produit_form.jsp").forward(request, response);
                break;

            case "delete":
                dao.delete(Integer.parseInt(request.getParameter("id")));
                response.sendRedirect("ProduitServlet");
                break;
            case "search":
                String keyword = request.getParameter("keyword");
                List<Produit> results = dao.search(keyword);
                request.setAttribute("produits", results);
                request.getRequestDispatcher("produits.jsp").forward(request, response);
                break;

            default:
                List<Produit> produits = dao.getAll();
                request.setAttribute("produits", produits);
                request.getRequestDispatcher("produits.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = request.getParameter("id") != null && !request.getParameter("id").isEmpty()
                ? Integer.parseInt(request.getParameter("id"))
                : 0;

        Produit p = new Produit(
                id,
                request.getParameter("nom_produit"),
                Double.parseDouble(request.getParameter("prix")),
                Integer.parseInt(request.getParameter("quantite_stock"))
        );

        if (id == 0)
            dao.insert(p);
        else
            dao.update(p);

        response.sendRedirect("ProduitServlet");
    }
}

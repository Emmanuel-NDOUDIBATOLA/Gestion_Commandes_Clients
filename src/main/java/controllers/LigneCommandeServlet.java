package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.LigneCommandeDAO;
import dao.ProduitDAO;
import dao.CommandeDAO;
import models.LigneCommande;
import models.Produit;

@WebServlet("/LigneCommandeServlet")
public class LigneCommandeServlet extends HttpServlet {

    private LigneCommandeDAO dao = new LigneCommandeDAO();
    private ProduitDAO produitDAO = new ProduitDAO();
    private CommandeDAO commandeDAO = new CommandeDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("add".equals(action)) {
            int idCommande = Integer.parseInt(request.getParameter("id_commande"));

            request.setAttribute("idCommande", idCommande);
            request.setAttribute("produits", produitDAO.getAll());

            request.getRequestDispatcher("ligne_commande_form.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idCommande = Integer.parseInt(request.getParameter("id_commande"));
        int idProduit = Integer.parseInt(request.getParameter("id_produit"));
        int quantite = Integer.parseInt(request.getParameter("quantite"));

        Produit p = produitDAO.getById(idProduit);

        LigneCommande lc = new LigneCommande(idCommande, idProduit, quantite, p.getPrix());
        dao.insert(lc);

        // Mise à jour du montant total
        commandeDAO.updateMontantTotal(idCommande);

        response.sendRedirect("CommandeServlet?action=details&id=" + idCommande);
    }
}


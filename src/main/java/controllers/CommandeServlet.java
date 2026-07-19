package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CommandeDAO;
import dao.ClientDAO;
import dao.LigneCommandeDAO;   // 🔥 AJOUTER ICI
import models.Commande;
import models.Client;
import models.LigneCommande;   // 🔥 AJOUTER ICI


@WebServlet("/CommandeServlet")
public class CommandeServlet extends HttpServlet {

    private CommandeDAO dao = new CommandeDAO();
    private ClientDAO clientDAO = new ClientDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {

            case "add":
                // Charger la liste des clients pour le formulaire
                List<Client> clients = clientDAO.getAll();
                request.setAttribute("clients", clients);
                request.getRequestDispatcher("commande_form.jsp").forward(request, response);
                break;

            case "delete":
                int id = Integer.parseInt(request.getParameter("id"));
                dao.delete(id);
                response.sendRedirect("CommandeServlet");
                break;
                
            case "details":
                int idCmd = Integer.parseInt(request.getParameter("id"));
                Commande commande = dao.getById(idCmd);
                LigneCommandeDAO lcDAO = new LigneCommandeDAO();
                List<LigneCommande> lignes = lcDAO.getByCommande(idCmd);

                request.setAttribute("commande", commande);
                request.setAttribute("lignes", lignes);

                request.getRequestDispatcher("commande_details.jsp").forward(request, response);
                break;


            default:
                List<Commande> commandes = dao.getAll();
                request.setAttribute("commandes", commandes);
                request.getRequestDispatcher("commandes.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idClient = Integer.parseInt(request.getParameter("id_client"));
        String dateStr = request.getParameter("date_commande");

        // Montant initial = 0
        Commande c = new Commande(idClient, java.sql.Date.valueOf(dateStr), 0);

        int idCommande = dao.insertAndReturnId(c);

        response.sendRedirect("LigneCommandeServlet?action=add&id_commande=" + idCommande);
    }
    

}

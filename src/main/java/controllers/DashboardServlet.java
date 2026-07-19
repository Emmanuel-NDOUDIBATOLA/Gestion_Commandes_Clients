package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import dao.ClientDAO;
import dao.ProduitDAO;
import dao.CommandeDAO;

@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {

    private ClientDAO clientDAO = new ClientDAO();
    private ProduitDAO produitDAO = new ProduitDAO();
    private CommandeDAO commandeDAO = new CommandeDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int totalClients = clientDAO.countClients();
        int totalProduits = produitDAO.countProduits();
        int totalCommandes = commandeDAO.countCommandes();
        double totalVentes = commandeDAO.totalVentes();

        request.setAttribute("totalClients", totalClients);
        request.setAttribute("totalProduits", totalProduits);
        request.setAttribute("totalCommandes", totalCommandes);
        request.setAttribute("totalVentes", totalVentes);

        request.getRequestDispatcher("dashboard.jsp").forward(request, response);
    }
}

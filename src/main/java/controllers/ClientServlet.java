package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ClientDAO;
import models.Client;

@WebServlet("/ClientServlet")
public class ClientServlet extends HttpServlet {

    private ClientDAO dao = new ClientDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) action = "list";

        switch (action) {
            case "add":
                request.getRequestDispatcher("client_form.jsp").forward(request, response);
                break;

            case "edit":
                int id = Integer.parseInt(request.getParameter("id"));
                Client c = dao.getById(id);
                request.setAttribute("client", c);
                request.getRequestDispatcher("client_form.jsp").forward(request, response);
                break;

            case "delete":
                dao.delete(Integer.parseInt(request.getParameter("id")));
                response.sendRedirect("ClientServlet");
                break;
            case "search":
                String keyword = request.getParameter("keyword");
                List<Client> results = dao.search(keyword);
                request.setAttribute("clients", results);
                request.getRequestDispatcher("clients.jsp").forward(request, response);
                break;
            default:
                List<Client> clients = dao.getAll();
                request.setAttribute("clients", clients);
                request.getRequestDispatcher("clients.jsp").forward(request, response);
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = request.getParameter("id") != null && !request.getParameter("id").isEmpty()
                ? Integer.parseInt(request.getParameter("id"))
                : 0;

        Client c = new Client(
                id,
                request.getParameter("nom"),
                request.getParameter("prenom"),
                request.getParameter("email"),
                request.getParameter("telephone"),
                request.getParameter("adresse")
        );

        if (id == 0)
            dao.insert(c);
        else
            dao.update(c);

        response.sendRedirect("ClientServlet");
    }
}

package models;

import java.util.Date;

public class Commande {
    private int id;
    private int idClient;
    private Date dateCommande;
    private double total;

    public Commande() {}

    public Commande(int id, int idClient, Date dateCommande, double total) {
        this.id = id;
        this.idClient = idClient;
        this.dateCommande = dateCommande;
        this.total = total;
    }

    public Commande(int idClient, Date dateCommande, double total) {
        this.idClient = idClient;
        this.dateCommande = dateCommande;
        this.total = total;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdClient() { return idClient; }
    public void setIdClient(int idClient) { this.idClient = idClient; }

    public Date getDateCommande() { return dateCommande; }
    public void setDateCommande(Date dateCommande) { this.dateCommande = dateCommande; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    public double getMontantTotal() {
        return total;
    }
}



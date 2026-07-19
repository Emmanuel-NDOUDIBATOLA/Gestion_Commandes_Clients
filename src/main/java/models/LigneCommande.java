package models;

public class LigneCommande {

    private int id;
    private int idCommande;
    private int idProduit;
    private int quantite;
    private double prixUnitaire;

    public LigneCommande() {}

    public LigneCommande(int id, int idCommande, int idProduit, int quantite, double prixUnitaire) {
        this.id = id;
        this.idCommande = idCommande;
        this.idProduit = idProduit;
        this.quantite = quantite;
        this.prixUnitaire = prixUnitaire;
    }

    public LigneCommande(int idCommande, int idProduit, int quantite, double prixUnitaire) {
        this.idCommande = idCommande;
        this.idProduit = idProduit;
        this.quantite = quantite;
        this.prixUnitaire = prixUnitaire;
    }

    public int getId() { 
        return id; 
    }
    public void setId(int id) { 
        this.id = id; 
    }

    public int getIdCommande() { 
        return idCommande; 
    }
    public void setIdCommande(int idCommande) { 
        this.idCommande = idCommande; 
    }

    public int getIdProduit() { 
        return idProduit; 
    }
    public void setIdProduit(int idProduit) { 
        this.idProduit = idProduit; 
    }

    public int getQuantite() { 
        return quantite; 
    }
    public void setQuantite(int quantite) { 
        this.quantite = quantite; 
    }

    public double getPrixUnitaire() { 
        return prixUnitaire; 
    }
    public void setPrixUnitaire(double prixUnitaire) { 
        this.prixUnitaire = prixUnitaire; 
    }
}

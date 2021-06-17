package fr.eni.bo;

import java.io.Serializable;

public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String nom;
    private int id_article;

    public Article(){

    }
    public Article(String nom) {
        this.nom = nom;
    }

    public Article(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Article(int id, String nom, int id_article) {
        this.id = id;
        this.nom = nom;
        this.id_article = id_article;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getId_article() {
        return id_article;
    }

    public void setId_article(int id_article) {
        this.id_article = id_article;
    }
}

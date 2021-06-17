package fr.eni.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListeCourse implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String nom;
    private List<Article> articles;

    public ListeCourse(){
        this.articles=new ArrayList<>();
    }

    public ListeCourse(int id,String nom) {
        this.id=id;
        this.nom = nom;
    }

    public ListeCourse(String nom) {
        this.nom = nom;
    }

    public ListeCourse(int id, String nom, List<Article> articles) {
        this.id = id;
        this.nom = nom;
        this.articles = articles;
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


    public List<Article> getArticles() {
         return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles=articles;
    }
}

package com.example.ex2.model;

import javafx.beans.NamedArg;
import javafx.fxml.FXML;

public class Person {
    @FXML
    private String prenom;
    private String nom;
    private String tel;
    private String email;

    public Person() {
    }

    public Person(@NamedArg("prenom") String prenom, @NamedArg("nom") String nom, @NamedArg("tel") String tel, @NamedArg("email") String email) {
        this.prenom = prenom;
        this.nom = nom;
        this.tel = tel;
        this.email = email;
    }

    @FXML
    @Override
    public String toString() {
        return this.prenom + " " + this.nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public String getTel() {
        return tel;
    }

    public String getEmail() {
        return email;
    }

//    public void setPrenom(String prenom) {
//        this.prenom = prenom;
//    }
//
//    public void setNom(String nom) {
//        this.nom = nom;
//    }
//
//    public void setTel(String tel) {
//        this.tel = tel;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
}

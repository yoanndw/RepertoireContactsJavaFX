package com.example.ex2.controller;

import com.example.ex2.model.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditController {
    private Person person = null;

    @FXML
    private TextField fldPrenom;

    @FXML
    private TextField fldNom;

    @FXML
    private TextField fldTel;

    @FXML
    private TextField fldEmail;

    @FXML
    private Button btnClose;

    public void initPerson(Person p) {
        this.fldPrenom.setText(p.getPrenom());
        this.fldNom.setText(p.getNom());
        this.fldTel.setText(p.getTel());
        this.fldEmail.setText(p.getEmail());
    }

    public Person getPerson() {
        return this.person;
    }

    @FXML
    public void close() {
        Stage window = (Stage) this.btnClose.getScene().getWindow();
        window.close();
    }

    @FXML
    public void confirm() {
        this.person = new Person(
                this.fldPrenom.getText(),
                this.fldNom.getText(),
                this.fldTel.getText(),
                this.fldEmail.getText()
        );

        this.close();
    }
}

package com.example.ex2.controller;

import com.example.ex2.ListApplication;
import com.example.ex2.model.Person;
import com.example.ex2.model.RepertoryList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.control.action.Action;

import java.io.*;
import java.util.Optional;

public class RepertoryController {
    private RepertoryList repertoryList;

    @FXML
    private ListView<Person> peopleListView;

    // Partie de droite
    @FXML
    private TextField fldPrenom;

    @FXML
    private TextField fldNom;

    @FXML
    private TextField fldTel;

    @FXML
    private TextField fldEmail;

    // Bouton d'aide du menu
    //@FXML
    //private Menu menuAide;

    public void initialize() {
        this.repertoryList = new RepertoryList();

        this.peopleListView.setItems(this.repertoryList.getPeopleList());

        // Aide
        //Button btnAide = new Button("Aide");
        //btnAide.setOnMouseClicked(e -> this.onClickAide(e));
        //this.menuAide.setGraphic(btnAide);
    }

    @FXML
    public void onSelectPerson(MouseEvent evt) {
        Person selected = this.peopleListView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            this.fldPrenom.setText(selected.getPrenom());
            this.fldNom.setText(selected.getNom());
            this.fldTel.setText(selected.getTel());
            this.fldEmail.setText(selected.getEmail());
        }
    }

    private void clearFieldsAndList() {
        this.repertoryList.clear();
        this.peopleListView.setItems(this.repertoryList.getPeopleList());

        this.fldPrenom.clear();
        this.fldNom.clear();
        this.fldTel.clear();
        this.fldEmail.clear();
    }

    /* MENU */
    @FXML
    public void onClickNouveau(ActionEvent evt) {
        this.clearFieldsAndList();
    }

    // TODO: L'ouverture de fichier ne fonctionne pas. La liste est toujorus vide
    @FXML
    public void onClickOuvrir(ActionEvent evt) throws IOException {
        this.clearFieldsAndList();

        // Fenetre de sélection de fichier
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Ouvrir");
        File file = fileChooser.showOpenDialog(this.fldPrenom.getScene().getWindow());

        // Désérialiser
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            RepertoryList rep = (RepertoryList) ois.readObject();

            this.repertoryList = rep;
            this.peopleListView.setItems(this.repertoryList.getPeopleList());
        } catch (FileNotFoundException e) {
            // Le fichier est toujours trouvé
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            // La classe est toujours trouvée
            System.out.println(e);
        }
    }

    @FXML
    public void onClickSauvegarder(ActionEvent evt) throws IOException {
        // Fenetre de sélection de fichier
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Sauvegarder");
        File file = fileChooser.showSaveDialog(this.fldPrenom.getScene().getWindow());

        // Sérialiser
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(this.repertoryList);
        } catch(InvalidClassException e) {
            // Ne rien faire
        } catch (NotSerializableException e) {
            // Ne rien faire
        }
    }

    @FXML
    public void onClickAide(ActionEvent evt) {
        StringBuffer sb = new StringBuffer();
        sb.append("Cette application est un répertoire.\n\n");
        sb.append("Vous pouvez sélectionner une personne et obtenir ses coordonnées.");
        sb.append("Vous pouvez ajouter des personnes au réperetoire, mais également\nmodifier les informations des personnes enregistrées.\nVous pouvez aussi supprimer une personne du répertoire.\n\n");
        sb.append("Enfin, vous pouvez sauvegarder votre répertoire dans un fichier, et ouvrir un fichier de sauvegarde.\n");
        sb.append("ATTENTION: Dû à un problème, l'ouverture d'un fichier de sauvegarde ne fonctionne pas.");

        Alert dialog = new Alert(Alert.AlertType.INFORMATION);
        dialog.setTitle("Aide");
        dialog.setContentText(sb.toString());
        dialog.setResizable(true);

        dialog.show();
    }

    @FXML
    public void onClickAjouter(MouseEvent evt) throws IOException {
        FXMLLoader loader = new FXMLLoader(ListApplication.class.getResource("add-people-view.fxml"));

        Parent root = loader.load();
        AddController controller = loader.getController();

        Scene scene = new Scene(root);
        Stage addWindow = new Stage();
        addWindow.initModality(Modality.APPLICATION_MODAL);
        addWindow.setTitle("Ajouter");

        addWindow.setScene(scene);
        addWindow.showAndWait();

        Person added = controller.getPerson();
        if (added != null) {
            this.repertoryList.addPerson(added);
        }
    }

    @FXML
    public void onClickFermer(ActionEvent evt) {
        Stage window = (Stage) this.fldPrenom.getScene().getWindow();
        window.close();
    }

    @FXML
    public void onClickModifier(MouseEvent evt) throws IOException {
        Person selected = this.peopleListView.getSelectionModel().getSelectedItem();

        if (selected == null) {
            Alert dialog = new Alert(Alert.AlertType.ERROR);

            dialog.setTitle("Erreur modification");
            dialog.setContentText("Veuillez sélectionner une personne à modifier.");

            dialog.show();
        } else {
            int index = this.peopleListView.getSelectionModel().getSelectedIndex();

            FXMLLoader loader = new FXMLLoader(ListApplication.class.getResource("edit-people-view.fxml"));

            Parent root = loader.load();
            EditController controller = loader.getController();
            controller.initPerson(selected);

            Scene scene = new Scene(root);

            Stage editWindow = new Stage();
            editWindow.initModality(Modality.APPLICATION_MODAL);
            editWindow.setTitle("Modifier");

            editWindow.setScene(scene);
            editWindow.showAndWait();

            Person newPerson = controller.getPerson();
            // Modifie la liste uniquement si la personne a été modifiée
            if (newPerson != null) {
                this.repertoryList.editPerson(index, newPerson);
            }
        }
    }

    @FXML
    public void onClickSupprimer(MouseEvent evt) {
        Person selected = this.peopleListView.getSelectionModel().getSelectedItem();

        if (selected == null) {
            Alert dialog = new Alert(Alert.AlertType.ERROR);

            dialog.setTitle("Erreur suppression");
            dialog.setContentText("Veuillez sélectionner une personne à supprimer.");

            dialog.show();
        } else {
            // Demande de validation
            Alert dialog = new Alert(Alert.AlertType.CONFIRMATION);

            dialog.setTitle("Suppression");
            dialog.setContentText("Êtes-vous sûr de vouloir supprimer '" + selected + "' ?");

            // Traitement du bouton
            Optional<ButtonType> pressed = dialog.showAndWait();
            if (pressed.isPresent() && pressed.get() == ButtonType.OK) {
                int index = this.peopleListView.getSelectionModel().getSelectedIndex();
                this.repertoryList.removePerson(index);
            }
        }
    }
}
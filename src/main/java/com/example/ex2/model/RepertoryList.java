package com.example.ex2.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;

public class RepertoryList implements Serializable {
    private ObservableList<Person> peopleList;

    public RepertoryList() {
        this.peopleList = FXCollections.observableArrayList();

        this.peopleList.add(new Person("Yoann", "DEWILDE", "01 23 45 67 89", "dewilde.e1900976@etud.univ-ubs.fr"));
        this.peopleList.add(new Person("Ariel", "", "01 23 45 67 89", "ariel.sirene@ocean.org"));
        this.peopleList.add(new Person("Horacio", "PAGANI", "01 23 45 67 89", "horacio.pagani@etud.univ-ubs.fr"));
    }

    public ObservableList<Person> getPeopleList() {
        return this.peopleList;
    }

    public void addPerson(Person p) {
        this.peopleList.add(p);
    }

    public void editPerson(int index, Person p) {
        this.peopleList.remove(index);
        this.peopleList.add(index, p);
    }

    public void removePerson(int index) {
        this.peopleList.remove(index);
    }

    public void clear() {
        this.peopleList.clear();
    }
}

/**********************************************
Workshop 6
Course: JAC444 - Semester 4
Last Name: Abdi
First Name: Tareq
ID: 123809196
This assignment represents my own work in accordance with Seneca Academic Policy.
Signature - TA
Date: 14/03/2022
**********************************************/

package com.nameapp;

import javafx.scene.control.TextField;

import javafx.fxml.FXML;

public abstract class Field {
    // textfields from the first scene (primaryController)
    @FXML
    private TextField year, gender, name;

    // default constructor
    Field() {
    }

    // get functions that will get the text inside the textfields
    String getYear() {
        return year.getText();
    }

    String getGender() {
        return gender.getText().toUpperCase();
    }

    String getName() {
        return name.getText();
    }

    // functions that will get the fields and return it
    TextField getYearField() {
        return year;
    }

    TextField getGenderField() {
        return gender;
    }

    TextField getNameField() {
        return name;
    }

    // empty checker functions
    boolean validCheck() {
        return getYear().isEmpty() || getGender().isEmpty() || getName().isEmpty();
    }

    // function that sets the style of the fields to red
    void colorField(TextField cc) {
        cc.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
    }

    // function that resets the style of the fields
    void colorFieldReset(TextField cc) {
        cc.setStyle("");
    }

    // checks if the field is empty
    boolean isEmptyField(String cc) {
        return cc.isEmpty();
    }

    // outlines or resets the textfield border color depending on the conditions
    void outlineFields() {
        if (isEmptyField(getYear())) {
            colorField(getYearField());
        } else
            colorFieldReset(getYearField());

        if (isEmptyField(getGender())) {
            colorField(getGenderField());
        } else
            colorFieldReset(getGenderField());

        if (isEmptyField(getName())) {
            colorField(getNameField());
        } else
            colorFieldReset(getNameField());
    }
}

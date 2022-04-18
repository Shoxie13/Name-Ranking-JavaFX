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

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

import javafx.fxml.FXML;

public class SecondaryController {
    // variables that hold the data from the primary controller
    private String name, gender, year, ranking;
    // result label which will show our final result
    @FXML
    private Label resultLabel;

    // if yes is selected it will lead you to search another name
    @FXML
    private void switchToMain(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("primary.fxml"));
        Scene scene = new Scene(root);

        // This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    // exits the application
    @FXML
    private void exitApp() throws IOException {
        Platform.exit();
    }

    // receives the data from the primary controller
    public void initData(String name, String gender, String year, String ranking) {
        this.name = name.trim();
        this.gender = gender.trim();
        this.year = year.trim();
        this.ranking = ranking.trim();
        setData();
    }

    // sets the final result which will be shown on the application
    void setData() {
        String result = "";

        if (this.gender.matches("M")) {
            result = "Boy name " + this.name + " is ranked #" + this.ranking + " in " +
                    this.year + " year";
        } else {
            result = "Girl name " + this.name + " is ranked #" + this.ranking + " in " +
                    this.year + " year";
        }

        resultLabel.setText(result);
    }
}
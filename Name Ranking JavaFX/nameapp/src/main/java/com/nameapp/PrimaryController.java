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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert.AlertType;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import javafx.fxml.FXML;

public class PrimaryController extends Field {
    private String filename = "nameapp/src/main/java/docs/";
    private List<String> str = new ArrayList<String>();
    private String boy = "";
    private String girl = "";

    // validates all input data
    // checks if gender and name match
    // shows error messages if any of the inputs don't meet the conditions
    // loads the next scene if all data is in the expected format
    @FXML
    private void submitQuery(ActionEvent event) throws IOException {
        if (!validCheck()) {
            if (getYear().matches("^(2009|201[0-8])$")) {
                colorFieldReset(getYearField());
                if (getGender().matches("^(?:m|M|f|F)$")) {
                    colorFieldReset(getGenderField());
                    if (getName().matches("^[A-Z][a-z]+$") && (getName().length() >= 3 && getName().length() <= 11)
                            && searchData(getName())) {
                        if (getName().matches(boy) && getGender().matches("M")) {
                            loadScene(event);
                        } else if (getName().matches(girl) && getGender().toUpperCase().matches("F")) {
                            loadScene(event);
                        } else {
                            colorField(getNameField());
                            colorField(getGenderField());
                            App.showAlert(AlertType.ERROR, "Error!",
                                    "Oops, the name and gender doesn't match!",
                                    "Validation Error!");
                        }

                    } else {
                        colorField(getNameField());
                        if (getName().length() < 3) {
                            App.showAlert(AlertType.ERROR, "Error!",
                                    "The name must start with UPPERCASE and has to be\nmore than " + getName().length()
                                            + " character/s!",
                                    "Validation Error!");
                        } else if (getName().length() > 11) {
                            App.showAlert(AlertType.ERROR, "Error!",
                                    "The name must start with UPPERCASE and has to be\nless than 11 characters!",
                                    "Validation Error!");
                        } else {
                            App.showAlert(AlertType.ERROR, "Error!",
                                    "The name " + getName() + " doesn't exist in our database!",
                                    "Validation Error!");
                        }
                    }
                } else {
                    colorField(getGenderField());
                    App.showAlert(AlertType.ERROR, "Error!",
                            "The gender must be M/m or F/f!",
                            "Validation Error!");
                }
            } else {
                colorField(getYearField());
                App.showAlert(AlertType.ERROR, "Error!",
                        "The year must be from 2009 to 2018!",
                        "Validation Error!");
            }
        } else {
            outlineFields();
        }
    }

    // searches the selected file from the user if the name exists
    // returns true if it does exist and false if not
    boolean searchData(String name) throws FileNotFoundException {
        Boolean found = false;
        try {
            File file = new File(filename + getYear() + ".txt");
            Scanner scan = new Scanner(file);
            while (scan.hasNext()) {
                String line = scan.nextLine().toString();
                if (line.contains(name)) {
                    str = Collections.list(new StringTokenizer(line)).stream()
                            .map(token -> (String) token)
                            .collect(Collectors.toList());

                    boy = str.get(1);
                    girl = str.get(3);

                    found = true;
                }
            }

            scan.close();
        } catch (FileNotFoundException e) {
            System.err.println(e);
        }

        return found;
    }

    // loads the secondary.fxml scene and sends information to the other controller
    void loadScene(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("secondary.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);

        // access the controller and call a method
        SecondaryController controller = loader.getController();

        // sends the data to the other controller
        controller.initData(getName(), getGender(), getYear(), str.get(0));

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
}
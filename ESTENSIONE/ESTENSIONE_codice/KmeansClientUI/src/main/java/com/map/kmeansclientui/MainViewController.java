package com.map.kmeansclientui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * Controller dell'interfaccia grafica che si occupa di mostrare il menu principale
 */
public class MainViewController {

    /**
     * Riferimento al bottone che mostra la scena per effettuare il calcolo dei cluster
     */
    @FXML
    private Button computeClusterbtn;

    /**
     * Riferimento al bottone che mostra la scena per caricare i cluster da file
     */
    @FXML
    private Button loadFilebtn;

    /**
     * AnchorPane al quale saranno aggiunte le diverse scene a seconda del pulsante premuto
     */
    @FXML
    private AnchorPane mainViewScene;

    /**
     * Costruttore vuoto consente a JavaFX di creare un'istanza della classe
     * Controller quando viene caricato il file FXML
     */
    public MainViewController(){}

    /**
     * Evento che fa apparire nella schermata di destra l'interfaccia per calcolare un cluster
     *
     * @param event evento scatenante
     */
    @FXML
    void showContentComputeCluster(ActionEvent event) {
        try {
            // Carica il nuovo layout FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("computeCluster-view.fxml"));
            AnchorPane content1 = loader.load();

            // Aggiungi il nuovo layout alla parte destra
            mainViewScene.getChildren().clear();
            mainViewScene.getChildren().add(content1);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Evento che fa apparire nella schermata di destra l'interfaccia per caricare cluster da file
     *
     * @param event evento scatenante
     */
    @FXML
    void showContentLoadFile(ActionEvent event) {
        try {
            // Carica il nuovo layout FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("loadFile-view.fxml"));
            AnchorPane content1 = loader.load();

            // Aggiungi il nuovo layout alla parte destra
            mainViewScene.getChildren().clear();
            mainViewScene.getChildren().add(content1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
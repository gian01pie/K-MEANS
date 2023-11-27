package com.map.kmeansclientui;

import com.map.kmeansclientui.model.ClientSingleton;
import com.map.kmeansclientui.model.exception.ServerException;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.SocketException;

/**
 * Controller dell'interfaccia che si occupa di caricare i cluster da file
 */
public class LoadFileViewController {

    /**
     * Casella di testo in cui l'utente inserisce il numero di iterazioni
     */
    @FXML
    private TextField k;

    /**
     * Casella di testo in cui l'utente inserisce il nome della tabella
     */
    @FXML
    private TextField tabName;

    /**
     * Riferimento al bottone che avvia il caricamento dei cluster da file
     */
    @FXML
    private Button loadBtn;

    /**
     * Riferimento al pannello su cui verrano stampati i cluster caricati
     */
    @FXML
    private StackPane printPane;

    /**
     * Testo in cui sarà inserita la risposta del Server
     */
    private Text clusterText;

    /**
     * Costruttore vuoto consente a JavaFX di creare un'istanza della classe
     * Controller quando viene caricato il file FXML
     */
    public LoadFileViewController() {
    }


    /**
     * Evento che avvia la richiesta al Server di caricare i cluster da un file
     *
     * @param event evento scatenante
     */
    @FXML
    void learningFromFile(ActionEvent event) {
        if (tabName.getText().isEmpty() || k.getText().isEmpty()) {
            AlertWindow.showErrorAlert("Entrambi i campi devono essere compilati.");
        } else {
            Task<Void> networkTask = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    try {
                        String clusters = ClientSingleton.getInstance().learningFromFile(tabName.getText(), Integer.parseInt(k.getText()));
                        javafx.application.Platform.runLater(() -> {
                            printClusterToScreen(clusters);
                        });
                    } catch (SocketException e) {
                        Platform.runLater(() -> {
                            AlertWindow.showErrorAlert(e.getMessage());
                        });
                    } catch (FileNotFoundException e) {
                        Platform.runLater(() -> {
                            AlertWindow.showErrorAlert(e.getMessage());
                        });
                    } catch (IOException e) {
                        Platform.runLater(() -> {
                            AlertWindow.showErrorAlert(e.getMessage());
                        });
                    } catch (ClassNotFoundException e) {
                        Platform.runLater(() -> {
                            AlertWindow.showErrorAlert(e.getMessage());
                        });
                    } catch (ServerException e) {
                        Platform.runLater(() -> {
                            AlertWindow.showErrorAlert(e.getMessage());
                        });
                    } catch (NumberFormatException e) {
                        System.err.println(e.getMessage());
                        AlertWindow.showErrorAlert("Le ITERAZIONI devono essere un numero\n");
                    }
                    return null;
                }
            };
            // gestore errori del thread separato
            networkTask.setOnFailed(e -> {
                Throwable exception = networkTask.getException();
                if (exception != null) {
                    exception.printStackTrace();
                }
            });

            // Avvia il task su un thread separato
            Thread networkThread = new Thread(networkTask);
            networkThread.setDaemon(true); // Imposta come thread daemon
            networkThread.start();
        }
    }

    /**
     * Stampa i cluster a schermo
     *
     * @param clusters scluster da stampare
     */
    private void printClusterToScreen(String clusters) {
        if (clusterText == null || !clusters.equals(clusterText.getText())) {
            clusterText = new Text(clusters);
            clusterText.setFont(Font.font("Roboto", 16));
            clusterText.setFill(Color.valueOf("#BBE1FA"));
            printPane.getChildren().clear();
            printPane.getChildren().add(clusterText);
            StackPane.setAlignment(clusterText, Pos.CENTER);
        }
    }
}
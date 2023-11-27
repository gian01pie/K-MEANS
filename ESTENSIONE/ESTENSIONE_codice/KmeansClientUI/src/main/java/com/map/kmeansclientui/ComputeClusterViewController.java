package com.map.kmeansclientui;

import com.map.kmeansclientui.model.ClientSingleton;
import com.map.kmeansclientui.model.exception.ServerException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.SocketException;

/**
 * Controller dell'interfaccia che si occupa di calcolare i cluster
 */
public class ComputeClusterViewController {

    /**
     * Riferimento al bottone che avvia il calcolo dei cluster
     */
    @FXML
    private Button computeBtn;

    /**
     * Riferimento al bottone che avvia il salvataggio
     */
    @FXML
    private Button saveBtn;

    /**
     * Riferimento al box che contiene il vottone di salvataggio
     */
    @FXML
    private HBox saveBox;

    /**
     * Riferimento al pannello su cui si stamperà il risultato
     */
    @FXML
    private StackPane printPane;

    /**
     * Campo di testo in cui l'utente inserisce il nome della tabella
     */
    @FXML
    private TextField tabName;

    /**
     * Campo di testo in cui l'utente inserisce il numero di cluster
     */
    @FXML
    private TextField clustersNumber;

    /**
     * Testo in cui sarà inserita la risposta del Server
     */
    private Text clusterText;

    /**
     * Costruttore vuoto consente a JavaFX di creare un'istanza della classe
     * Controller quando viene caricato il file FXML
     */
    public ComputeClusterViewController() {
    }

    /**
     * Evento che avvia la richiesta al server di caricare i dati della tabella da DB
     *
     * @param event evento scatenante
     */
    @FXML
    void learningFromDbTable(ActionEvent event) {
        if (tabName.getText().isEmpty() || clustersNumber.getText().isEmpty()) {
            AlertWindow.showErrorAlert("Entrambi i campi devono essere compilati.");
        } else {
            try {
                int k = Integer.parseInt(clustersNumber.getText());
                Task<Void> networkTask = new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        try {
                            ClientSingleton.getInstance().storeTableFromDb(tabName.getText());
                            String clusters = ClientSingleton.getInstance().learningFromDbTable(k);
                            System.out.println(clusters);
                            // Aggiorna l'interfaccia utente sul thread principale
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

            } catch (NumberFormatException e) {//inserite parole per numero cluster
                System.err.println(e.getMessage());
                AlertWindow.showErrorAlert("I CLUSTER devono essere un numero\n");
            }
        }
    }

    /**
     * Funzione che stampa a schermo i cluster dati in input
     *
     * @param clusters cluster trovati
     */
    private void printClusterToScreen(String clusters) {
        clusterText = new Text(clusters);
        clusterText.setFont(Font.font("Roboto", 16));
        clusterText.setFill(Color.valueOf("#BBE1FA"));

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent; ");
        scrollPane.setContent(clusterText);

        printPane.getChildren().clear();
        printPane.getChildren().add(scrollPane);
        StackPane.setAlignment(scrollPane, Pos.CENTER);
    }

    /**
     * Evento che avvia la richiesta al server di salvare i cluster individuati su file
     *
     * @param event evento scatenante
     */
    @FXML
    void storeClusterInFile(ActionEvent event) {
        if (!(clusterText == null)) {
            Task<Void> networkTask = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    try {
                        ClientSingleton.getInstance().storeClusterInFile();
                        javafx.application.Platform.runLater(() -> {
                            successfulSaveText();
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
        } else {
            AlertWindow.showErrorAlert("Nessun File da salvare.");
        }
    }

    /**
     * Funzione che stampa a schermo l'avvenuto salvataggio
     */
    private void successfulSaveText() {
        Label saveLabel = new Label("Salvataggio avvenuto con successo");
        saveLabel.setTextFill(Color.valueOf("#BBE1FA"));
        saveBox.getChildren().add(saveLabel);

        // Crea una timeline per nascondere e rimuovere la albel
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(3), event1 -> {
                    // Rimuove la label dalla scena
                    saveBox.getChildren().remove(saveLabel);
                })
        );
        timeline.play();
    }
}



package com.map.kmeansclientui;

import com.map.kmeansclientui.model.ClientSingleton;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller dell'interfaccia che si occupa di effettuare la connessione con il Server
 */
public class ConnectionController {
    /**
     * Riferimento al bottone che avvia la connesione
     */
    @FXML
    private Button connectionBtn;

    /**
     * Casella di testo in cui l'utente inserisce l'indirizzo ip al quale vuole connettersi
     */
    @FXML
    private TextField ip;

    /**
     * Casella di testo in cui l'utente inserisce la porta alla quale connettersi
     */
    @FXML
    private TextField port;

    /**
     * Costruttore vuoto consente a JavaFX di creare un'istanza della classe
     * ConnectionController quando viene caricato il file FXML
     */
    public ConnectionController() {
    }

    /**
     * Evento che avvia la richiesta di connessione al Server
     *
     * @param event evento scatenante
     */
    @FXML
    void connectToServer(ActionEvent event) {
        if (validateInput()) {
            Task<Void> networkTask = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    try {
                        ClientSingleton.getInstance().connectToServer(ip.getText(), Integer.parseInt(port.getText()));
                        // Aggiorna l'interfaccia utente sul thread principale
                        Platform.runLater(() -> {
                            changeScene(event, "main-view.fxml");
                        });
                    } catch (IOException e) {
                        System.err.println(e.getMessage());
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
        }
    }

    /**
     * Funzione che verifica se l'indirzzo ip e la porta inseriti dall'utente sono validi
     *
     * @return restituisce true se gli inserimenti sono validi
     */
    private boolean validateInput() {
        if (ip.getText().isEmpty() || port.getText().isEmpty()) {
            AlertWindow.showErrorAlert("Entrambi i campi devono essere compilati.");
            return false;
        }
        try {
            if (Integer.parseInt(port.getText()) < 0 || Integer.parseInt(port.getText()) > 65535) {
                AlertWindow.showErrorAlert("Il numero di porta deve essere un numero compreso tra 1 e 65535");
                return false;
            }
        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
            AlertWindow.showErrorAlert("La PORTA deve essere un numero\n");
            return false;
        }
        return true;
    }

    /**
     * Evento che Effettua un cambio di scena
     *
     * @param event    evento scatenante
     * @param fxmlFile file della scena a cui si cambia
     */
    private void changeScene(ActionEvent event, String fxmlFile) {
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        try {
            root = loader.load();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}

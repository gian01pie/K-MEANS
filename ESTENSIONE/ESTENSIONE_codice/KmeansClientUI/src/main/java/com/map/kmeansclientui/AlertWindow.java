package com.map.kmeansclientui;

import javafx.scene.control.Alert;

import java.util.Objects;

/**
 * Classe che estende Alert, viene utilizzata per aprire delle finistre a schermo
 * che effettuano delle segnalazioni di qualche tipo.
 */
public class AlertWindow extends Alert {

    /**
     * Costruisce una finsetra di Alert
     *
     * @param alertType   tipologia di alert e.g. (errore, informativa...)
     * @param title       titolo della finestra
     * @param headerText  intestazione dell'alert
     * @param contentText testo informativo dell'alert
     */
    public AlertWindow(AlertType alertType, String title, String headerText, String contentText) {
        super(alertType);
        setTitle(title);
        setHeaderText(headerText);
        setContentText(contentText);
        // Carica il file CSS personalizzato
        getDialogPane().getStylesheets().add(Objects.requireNonNull(getClass().getResource("alert-style.css")).toExternalForm());
        // mostra lo stage è aspetta che sia nascosto/chiuso per proseguire
        showAndWait();
    }

    /**
     * Mostra una finestra di Alert che notifica un errore
     *
     * @param contentText messaggio di errore
     */
    public static void showErrorAlert(String contentText) {
        new AlertWindow(AlertType.ERROR, "ERRORE", "ERRORE", contentText);
    }
}

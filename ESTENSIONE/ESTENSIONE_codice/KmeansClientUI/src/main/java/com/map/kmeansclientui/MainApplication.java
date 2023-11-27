package com.map.kmeansclientui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * Classe MainApplication da cui ha origine l'interfaccia grafica
 */
public class MainApplication extends Application {
    /**
     * Punto di partenza dell'interfaccia grafica
     *
     * @param stage stage principale di questa applicazione,
     * sul quale viene impostata la scene.
     * @throws IOException in caso di errori di I/O
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("connection-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
        stage.setTitle("K-MEANS");
        stage.setScene(scene);
        stage.show();

//        // fa eseguire del codice quando si chiude il programma
//        stage.setOnCloseRequest(e->{
//            e.consume();    //fa consumare del tutto l'evento
//            AlertWindow.display("Chiusura...");
//        });
    }

    /**
     * Avvia l'esecuzione
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }
}
package com.map.kmeansclientui.model.exception;

/**
 * Classe exception.ServerException modella un'eccezione che viene sollevata dal server e trasmessa al client
 */
public class ServerException extends Exception {

    /**
     * Costruisce una nuova eccezione usando il costruttore della superclasse
     */
    public ServerException() {
        super("[!] Errore del Server: ");
    }

    /**
     * Costruisce una nuova eccezione usando il costruttore della superclasse e come messaggio prende la stringa in input
     *
     * @param e messaggio che si vuole far restituire all'eccezione
     */
    public ServerException(String e) {
        super(e);
    }
}

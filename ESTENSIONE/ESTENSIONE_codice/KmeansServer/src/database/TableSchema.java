package database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Classe TableSChema che modella lo schema di una tabella in un DB Relazionale
 */
public class TableSchema {
    /**
     * Riferimento al DB a cui si è connesi
     */
    private final DbAccess db;

    /**
     * Classe Column che modella le colonne della tabella.
     * Di ogni colonne so il nome ed il tipo (number/string)
     */
    public class Column {
        /**
         * Nome della colonna
         */
        private String name;
        /**
         * Tipo di elementi contenuti in colonna (stringa/numerico)
         */
        private String type;

        /**
         * Costruttore di classe inizializza il nome e il tipo di colonna
         *
         * @param name nome della colonna
         * @param type tipo di colonna
         */
        Column(String name, String type) {
            this.name = name;
            this.type = type;
        }

        /**
         * Restituisce il nome della colonna
         *
         * @return Restituisce il nome della colonna
         */
        public String getColumnName() {
            return name;
        }

        /**
         * Restituisce true se il tipo di colonna è numerico
         *
         * @return Restituisce true se il tipo di colonna è numerico
         */
        public boolean isNumber() {
            return type.equals("number");
        }

        /**
         * Rappresentazione sottoforma di stringa di una colonna
         *
         * @return Rappresentazione sottoforma di stringa di una colonna
         */
        public String toString() {
            return name + ":" + type;
        }
    }

    /**
     * Lista delle colonne nella tabella.
     */
    private final List<Column> tableSchema = new ArrayList<Column>();

    /**
     * Costruttore di classe.
     * Mappa i tipi disponibili in JDBC nei soli tipi number/string.
     * Inserisce in tableSchema le colonne della tabella nel DB
     *
     * @param db        DB a cui si è connessi
     * @param tableName nome della tabella nel DB
     * @throws SQLException in caso di errore nell'accesso al DB
     */
    public TableSchema(DbAccess db, String tableName) throws SQLException {
        this.db = db;
        HashMap<String, String> mapSQL_JAVATypes = new HashMap<String, String>();
        // Mappa tutti i valori possibili della JDBC in valori stringa o numerici
        //http://java.sun.com/j2se/1.3/docs/guide/jdbc/getstart/mapping.html
        mapSQL_JAVATypes.put("CHAR", "string");
        mapSQL_JAVATypes.put("VARCHAR", "string");
        mapSQL_JAVATypes.put("LONGVARCHAR", "string");
        mapSQL_JAVATypes.put("BIT", "string");
        mapSQL_JAVATypes.put("SHORT", "number");
        mapSQL_JAVATypes.put("INT", "number");
        mapSQL_JAVATypes.put("LONG", "number");
        mapSQL_JAVATypes.put("FLOAT", "number");
        mapSQL_JAVATypes.put("DOUBLE", "number");


        Connection con = db.getConnection();
        DatabaseMetaData meta = con.getMetaData();
        ResultSet res = meta.getColumns(null, null, tableName, null);

        while (res.next()) {

            if (mapSQL_JAVATypes.containsKey(res.getString("TYPE_NAME")))
                tableSchema.add(new Column(
                        res.getString("COLUMN_NAME"),
                        mapSQL_JAVATypes.get(res.getString("TYPE_NAME")))
                );


        }
        res.close();


    }


    /**
     * Restituisce la dimensione di tableSchema, quindi il numero di colonne della tabella
     *
     * @return la dimensione di tableSChema
     */
    public int getNumberOfAttributes() {
        return tableSchema.size();
    }

    /**
     * Restituisce la colonna in posizione index nel tableSchema
     *
     * @param index indice di posizione dentro tableSchema
     * @return la colonna in posizione index
     */
    public Column getColumn(int index) {
        return tableSchema.get(index);
    }

}

		     



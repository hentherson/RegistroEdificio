package com.uac.registroedificio;

public class DefDB {

    public static final String nameDB = "Edificio";

    public static final String tabla_vis = "Visitantes";
    public static final String col_nombre = "nombre";
    public static final String col_codigo = "codigo";
    public static final String col_apartamento = "apartamento";
    public static final String col_tipo_visita = "tipovisita";
    public static final String col_hora_fecha= "horafecha";
    public static final String create_tabla_vis = "CREATE TABLE IF NOT EXISTS " + tabla_vis + " ( " +
            DefDB.col_nombre + " text," +
            DefDB.col_codigo + " text primary key," +
            DefDB.col_apartamento + " text," +
            DefDB.col_tipo_visita + " text," +
            DefDB.col_hora_fecha + " text" +
            ");";
}
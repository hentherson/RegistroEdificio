package com.uac.registroedificio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

public class VisitanteController {

    private BaseDatos bd;
    private Context c;

    public VisitanteController(Context c) {
        this.bd = new BaseDatos(c);
        this.c = c;
    }

    public long agregarVisitante(Visitante v) {
        try {
            SQLiteDatabase sql = bd.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(DefDB.col_nombre, v.getNombre());
            cv.put(DefDB.col_codigo, v.getId());
            cv.put(DefDB.col_apartamento, v.getApartamento());
            cv.put(DefDB.col_tipo_visita, v.getTipoVisita());
            cv.put(DefDB.col_hora_fecha, v.getFechaHora());
            long id = sql.insert(DefDB.tabla_vis, null, cv);
            return id;
        }
        catch (Exception ex) {
            return 0;
        }
    }

    public void eliminarVisitante(Visitante v) {
        try {
            SQLiteDatabase sql = bd.getWritableDatabase();
            String[] args = {v.getId()};
            sql.delete(DefDB.tabla_vis, "codigo=?", args);
            Log.i("ELIMINAR DE BD", "VISITANTE ELIMINADO: "+v.getNombre());

        } catch (Exception ex) {
            Toast.makeText(c,"Error Eliminación", Toast.LENGTH_LONG).show();
        }
    }

    public void editarVisitante (Visitante v) {
        try {
            SQLiteDatabase sql = bd.getWritableDatabase();
            ContentValues cvalues = new ContentValues();

            cvalues.put(DefDB.col_nombre, v.getNombre());
            cvalues.put(DefDB.col_codigo, v.getId());
            cvalues.put(DefDB.col_apartamento, v.getApartamento());
            cvalues.put(DefDB.col_tipo_visita, v.getTipoVisita());
            cvalues.put(DefDB.col_hora_fecha, v.getFechaHora());
            Log.i("VISITANTE ANTES DE",  v.getNombre() + v.getId() + v.getApartamento() + v.getTipoVisita() + v.getFechaHora());
            sql.update(DefDB.tabla_vis, cvalues, "codigo = ?", new String[]{v.getId()});
            sql.close();
        } catch(Exception ex) {
            Toast.makeText(c,"Error Edición", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean buscarVisitante(String vCodigo) {
        SQLiteDatabase sql = bd.getWritableDatabase();
        String[] args = {vCodigo};

        Cursor cur = sql.query(DefDB.tabla_vis, null, "codigo=?", args, null, null, null);

        if(cur.getCount() > 0) {
            sql.close();
            return true;
        } else {
            sql.close();
            return false;
        }
    }

    public Cursor allVisitantes() {
        try {
            SQLiteDatabase data = bd.getReadableDatabase();
            Cursor cur = data.query(DefDB.tabla_vis, null, null, null, null, null, null);
            return cur;
        }
        catch(Exception ex) {
            Toast.makeText(c,"Error Consulta", Toast.LENGTH_LONG).show();
            return null;
        }
    }
}
package com.uac.registroedificio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListadoVisitantes extends AppCompatActivity implements VisitanteListener {
    List<Visitante> listadoVisitantes = new ArrayList<>();
    private VisitanteController vc;
    private RecyclerView vLista;
    private VisitanteAdapter va;
    private Visitante visitanteSeleccionado = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_visitantes);

        vLista = findViewById(R.id.listaView);
        vLista.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        vc = new VisitanteController(getApplicationContext());
        listadoVisitantes.clear();
        Cursor cur = vc.allVisitantes();

        while (cur.moveToNext()) {
            String nombre = cur.getString(cur.getColumnIndexOrThrow("nombre"));
            String id = cur.getString(cur.getColumnIndexOrThrow("codigo"));
            String apt = cur.getString(cur.getColumnIndexOrThrow("apartamento"));
            String tipovisita = cur.getString(cur.getColumnIndexOrThrow("tipovisita"));
            String horafecha = cur.getString(cur.getColumnIndexOrThrow("horafecha"));
            Visitante v = new Visitante(nombre, id, apt, tipovisita, horafecha);
            listadoVisitantes.add(v);
        }

        va = new VisitanteAdapter(listadoVisitantes, this);
        vLista.setAdapter(va);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.barraherramientas, menu);
        MenuItem mi = menu.findItem(R.id.iconoBusqueda);
        SearchView sv = (SearchView) mi.getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
               ArrayList<Visitante> nuevaLista = new ArrayList<>();
               for (Visitante v : listadoVisitantes) {
                   String apt = v.getApartamento();
                   if (apt.contains(newText)) {
                       nuevaLista.add(v);
                   }
               }

               va.actualizarLista(nuevaLista);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case 1:
                Intent intent = new Intent(this, EditarVisitante.class);
                intent.putExtra("key", visitanteSeleccionado);

                startActivity(intent);

                return true;
            case 2:

                vc.eliminarVisitante(visitanteSeleccionado);
                va.eliminaVisitante(visitanteSeleccionado);
                Toast.makeText(this, "Visitante "+ visitanteSeleccionado.getNombre() +" Eliminado", Toast.LENGTH_SHORT).show();

                return  true;
        }
        return true;
    }

    @Override
    public void itemSeleccionado(Visitante v) {
        this.visitanteSeleccionado = v;
    }
}
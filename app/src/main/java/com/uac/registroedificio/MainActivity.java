package com.uac.registroedificio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText nombre, documento, apartamento;
    private RadioGroup tipoVisitantRG;
    private RadioButton tipoVisitantRB;
    private Button registrar, listado;
    private SimpleDateFormat sdf;
    private String fechaHoraActual;

    Visitante v;
    VisitanteController vc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre = findViewById(R.id.nombreTxt);
        documento = findViewById(R.id.idTxt);
        apartamento = findViewById(R.id.aptoTxt);
        tipoVisitantRG = findViewById(R.id.tipoVisitanteRG);
        registrar = findViewById(R.id.registrarBtn);
        listado = findViewById(R.id.listadoBtn);
        registrar.setOnClickListener(this);
        listado.setOnClickListener(this);
        sdf = new SimpleDateFormat("yyyy.MM.dd '|' HH:mm:ss");
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.registrarBtn:
                int idSeleccionado = tipoVisitantRG.getCheckedRadioButtonId();
                tipoVisitantRB = findViewById(idSeleccionado);
                fechaHoraActual = sdf.format(new Date());
                vc = new VisitanteController(this);
                v = new Visitante(nombre.getText().toString(), documento.getText().toString(), apartamento.getText().toString(), tipoVisitantRB.getText().toString(), fechaHoraActual);
                vc.agregarVisitante(v);
                Toast.makeText(this, "Visitante Agregado", Toast.LENGTH_LONG).show();
                nombre.setText("");
                documento.setText("");
                apartamento.setText("");
                fechaHoraActual="";
                tipoVisitantRG.clearCheck();
                nombre.clearFocus();
                documento.clearFocus();
                apartamento.clearFocus();

                break;
            case R.id.listadoBtn:
                vc = new VisitanteController(this);
                Cursor c = vc.allVisitantes();
                if (c != null) {
                    Intent i = new Intent(MainActivity.this, ListadoVisitantes.class);
                    startActivity(i);
                } else {
                    Toast.makeText(this, "No hay datos", Toast.LENGTH_LONG).show();
                }
                break;
        }


    }
}
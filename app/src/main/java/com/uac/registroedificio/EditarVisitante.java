package com.uac.registroedificio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class EditarVisitante extends AppCompatActivity implements View.OnClickListener{

    private EditText nombre, documento, apartamento;
    private RadioGroup tipoVisitantRG;
    private RadioButton tipoVisitantRB, familiaRB, amigoRB, domicilioRB;
    private Button confirmarBtn;

    private Visitante v;
    private VisitanteController vc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_visitante);

        vc = new VisitanteController(this);

        nombre = findViewById(R.id.editarNombreTxt);
        documento = findViewById(R.id.editarIdTxt);
        apartamento = findViewById(R.id.editarAptTxt);
        tipoVisitantRG = findViewById(R.id.editarTipoVisitanteRG);
        confirmarBtn = findViewById(R.id.confirmarBtn);
        confirmarBtn.setOnClickListener(this);
        familiaRB = findViewById(R.id.editarFamiliaRB);
        amigoRB = findViewById(R.id.editarAmigoRB);
        domicilioRB = findViewById(R.id.editarDomicilioRB);

        v = (Visitante) getIntent().getExtras().getSerializable("key");

        nombre.setText(v.getNombre());
        documento.setText(v.getId());
        apartamento.setText(v.getApartamento());
        String text = v.getTipoVisita();
        Log.i("TIPO VISITA STORED", text);
        switch(text) {
            case "Familia":
                familiaRB.setChecked(true);
                break;

            case "Amigo":
                amigoRB.setChecked(true);
                break;

            case "Domiciliario":
                domicilioRB.setChecked(true);
                break;
        }
    }

    @Override
    public void onClick(View view) {

       if(view.getId() == R.id.confirmarBtn){
            int idSeleccionado = tipoVisitantRG.getCheckedRadioButtonId();
            tipoVisitantRB = findViewById(idSeleccionado);

            Visitante vEditado = new Visitante(
                    nombre.getText().toString(),
                    documento.getText().toString(),
                    apartamento.getText().toString(),
                    tipoVisitantRB.getText().toString(),
                    v.getFechaHora()
            );
            vc.editarVisitante(vEditado);

            finish();
       }
    }
}
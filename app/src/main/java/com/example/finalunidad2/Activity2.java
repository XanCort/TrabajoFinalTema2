package com.example.finalunidad2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;


public class Activity2 extends AppCompatActivity {

    ArrayList<ArrayList<String>> historial = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    @Override
    protected void onResume() {

        super.onResume();
        Intent recibir = getIntent();
        historial= ((ArrayList<ArrayList<String>>) recibir.getSerializableExtra("historial"));
        for(int i=0;i<historial.size();i++){
            TextView nuevoTexto = new TextView(this);
            nuevoTexto.setId(View.generateViewId());
            ((LinearLayout)findViewById(R.id.scroll)).addView(nuevoTexto);
            nuevoTexto.setText(historial.get(i).get(1).toString());
            nuevoTexto.setVisibility(View.VISIBLE);
            nuevoTexto.setMaxWidth(10);

            if(historial.get(i).get(0).equals("1")){
                nuevoTexto.setBackgroundColor(Color.RED);
                nuevoTexto.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            }else if(historial.get(i).get(0).equals("2")){
                nuevoTexto.setBackgroundColor(Color.GREEN);
                nuevoTexto.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
            }else{
                nuevoTexto.setBackgroundColor(Color.YELLOW);
            }
            nuevoTexto.setPadding(10,10,10,10);
        }
        (findViewById(R.id.scrolView)).post(new Runnable() {
            public void run() {
                ((ScrollView)findViewById(R.id.scrolView)).fullScroll(View.FOCUS_DOWN);
            }
        });

    }

    public void enviarMensaje(View view) {
        Intent enviar = new Intent(this, MainActivity.class);
        ArrayList<String> nuevaEntrada = new ArrayList<>();
        nuevaEntrada.add("2");
        nuevaEntrada.add(((EditText)findViewById(R.id.editTextText)).getText().toString());
        historial.add(nuevaEntrada);
        enviar.putExtra("historial",historial);

        startActivity(enviar);
    }
}
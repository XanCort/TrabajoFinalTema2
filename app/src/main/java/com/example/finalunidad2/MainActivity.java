package com.example.finalunidad2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    HashMap<Integer, String> historial = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        historial.put(0,"");
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        TextView nuevoTexto = new TextView(this);
        ((LinearLayout)findViewById(R.id.scroll)).addView(nuevoTexto);
        nuevoTexto.setText(R.string.app_name);
        nuevoTexto.setVisibility(View.VISIBLE);
        nuevoTexto.setBackgroundColor(Color.RED);
        nuevoTexto.setHeight(12);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent recibir = new Intent(this, Activity2.class);
        if(recibir.getSerializableExtra("historial")!=null){
            historial = (HashMap<Integer, String>) recibir.getSerializableExtra("historial");
            for(int i=0;i<historial.size();i++){
                TextView nuevoTexto = new TextView(this);
                ((LinearLayout)findViewById(R.id.scroll)).addView(nuevoTexto);
                nuevoTexto.setText(historial.get(i));
                nuevoTexto.setVisibility(View.VISIBLE);
                nuevoTexto.setBackgroundColor(Color.RED);
                nuevoTexto.setHeight(12);
            }
        }



    }

    public void enviarMensaje(View view) {
        Intent enviar = new Intent(this, Activity2.class);
        historial.put(1,((TextView)findViewById(R.id.editTextText)).getText().toString());
        enviar.putExtra("historial",historial);
        startActivity(enviar);
    }
}
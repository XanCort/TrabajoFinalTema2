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

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

   ArrayList<String> historial = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        Intent recibir = getIntent();
        if(recibir.getSerializableExtra("historial")!=null){
            historial =recibir.getStringArrayListExtra("historial");
            for(int i=0;i<historial.size();i++){
                TextView nuevoTexto = new TextView(this);
                ((LinearLayout)findViewById(R.id.scroll)).addView(nuevoTexto);
                nuevoTexto.setVisibility(View.VISIBLE);
                nuevoTexto.setBackgroundColor(Color.RED);
            }
        }



    }

    public void enviarMensaje(View view) {



        Intent enviar = new Intent(this, Activity2.class);
        historial.add("Si");
        historial.add("No");
        Log.i("ARRAY",historial.get(0));
        enviar.putExtra("Prueba","2");
        enviar.putStringArrayListExtra("historial",historial);
        startActivity(enviar);
    }
}
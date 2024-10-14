package com.example.finalunidad2;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ArrayList<ArrayList<String>> historial = new ArrayList<>();


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

    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent recibir = getIntent();
        if(recibir.getSerializableExtra(("historial"))!=null){
            historial= ((ArrayList<ArrayList<String>>) recibir.getSerializableExtra("historial"));
            for(int i=0;i<historial.size();i++){

                TextView nuevoTexto = new TextView(this);
                nuevoTexto.setId(View.generateViewId());
                ((LinearLayout)findViewById(R.id.scroll)).addView(nuevoTexto);

                nuevoTexto.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_corner));
                if(historial.get(i).get(0).equals("1")){
                    nuevoTexto.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ffe0f9")));
                    nuevoTexto.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
                }else if(historial.get(i).get(0).equals("2")){
                    nuevoTexto.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#f5e9d3")));
                    nuevoTexto.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
                }else{
                    nuevoTexto.setBackgroundColor(Color.YELLOW);
                }
                nuevoTexto.setPadding(10,10,70,10);
                nuevoTexto.setText(historial.get(i).get(1).toString());


                nuevoTexto.setVisibility(View.VISIBLE);


            }
            (findViewById(R.id.scrolView)).post(new Runnable() {
                public void run() {
                    ((ScrollView)findViewById(R.id.scrolView)).fullScroll(View.FOCUS_DOWN);
                }
            });

        }




    }

    public void enviarMensaje(View view) {
        Intent enviar = new Intent(this, Activity2.class);
        ArrayList<String> nuevaEntrada = new ArrayList<>();
        nuevaEntrada.add("1");
        nuevaEntrada.add(((EditText)findViewById(R.id.editTextText)).getText().toString());
        historial.add(nuevaEntrada);
        enviar.putExtra("historial",historial);
        startActivity(enviar);
    }
}
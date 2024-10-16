package com.example.finalunidad2;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
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

import org.w3c.dom.Text;

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

        //Elimina el historial anterior para que al cargar el nuevo no salgan repetidos los mensajes
        LinearLayout vista = findViewById(R.id.scroll);
        vista.removeAllViews();

        Intent recibir = getIntent();
        if(recibir.getSerializableExtra(("historial"))!=null){
            historial= ((ArrayList<ArrayList<String>>) recibir.getSerializableExtra("historial"));
            cargarHistorial(historial);
        }



    }

    /*
    Método que se ejecuta al pulsar el botón enviar, crea una nueva entrada en el historial con la id
    de la actividad actual y lanza la siguiente actividad
     */
    public void enviarMensaje(View view) {
        //Comprobamos que haya texto y no el campo vacio o solo espacios
        if(!((EditText)findViewById(R.id.editTextText)).getText().toString().trim().equals("")){
            ArrayList<String> nuevaEntrada = new ArrayList<>();
            nuevaEntrada.add("1");
            nuevaEntrada.add(((EditText)findViewById(R.id.editTextText)).getText().toString());
            historial.add(nuevaEntrada);
            cambiarUsuario(view);
        }
    }

    /*
        Método que carga el historial de mensajes
        Comprueba cada mensaje compuesto por una id y el texto del mensaje
        Asigna un estilo al mensaje en función del a id
        @param historial es un arrayList de arrayList de strings para poder guardar los dos valores
        He intentado hacerlo con un hashmap pero el hashmap no es una lista ordenada y también con likedhashmap
        pero no se puede recuperar un linkedhashmap de un intent se convierte en hashmap, así que esta es la forma que se me ha ocurrido
     */
    public void cargarHistorial(ArrayList<ArrayList<String>> historial){
        LinearLayout.LayoutParams params;


        for(int i=0;i<historial.size();i++){
            params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            TextView nuevoTexto = new TextView(this);
            nuevoTexto.setId(View.generateViewId());
            ((LinearLayout)findViewById(R.id.scroll)).addView(nuevoTexto);
            nuevoTexto.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_corner));
            if(historial.get(i).get(0).equals("1")){
                nuevoTexto.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#A5D6A7")));
                nuevoTexto.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
                params.gravity=Gravity.START;
            }else if(historial.get(i).get(0).equals("2")){
                nuevoTexto.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFF59D")));
                nuevoTexto.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
                params.gravity=Gravity.END;
            }
            nuevoTexto.setTextSize(18);
            nuevoTexto.setLayoutParams(params);
            nuevoTexto.setPadding(20,20,20,20);
            nuevoTexto.setText(historial.get(i).get(1).toString());
            nuevoTexto.setVisibility(View.VISIBLE);
        }
        (findViewById(R.id.scrolView)).post(new Runnable() {
            public void run() {
                ((ScrollView)findViewById(R.id.scrolView)).fullScroll(View.FOCUS_DOWN);
            }
        });

    }


    /*
    Método para ir a la otra actividad
     */
    public void cambiarUsuario(View view) {
        Intent enviar = new Intent(this, Activity2.class);
        enviar.putExtra("historial",historial);
        startActivity(enviar);
    }
}
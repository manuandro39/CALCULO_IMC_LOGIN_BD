package com.example.mespada.logueo_calculo_imc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    //Inicialización de variables
    private String peso="", altura="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);Button btnVolver = (Button) findViewById(R.id.btn_volver);

        //Asignación de variables que representan los objetos componentes de la MainActivity
        TextView textView = (TextView) findViewById(R.id.textView8);
        TextView textView2 = (TextView) findViewById(R.id.textView9);

        //Recogemos en un objeto Bundle la información enviado por el Intent desde el MainActivity
        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {//En el caso de haber introducido la información, se almacenan los datos
            // en las variables para ser utilizados para calcular el IMC posteriormente
            peso = bundle.getString("peso");
            altura = bundle.getString("altura");
        }

        //Llamada a la función que calcula el IMC en base al peso y altura introducidos
        double miIMC = CalcularIMC(peso, altura);
        String estado_masa = "";

        //Creamos un objeto de la clase Literales para llamar al método MostrarEstado para obtener el estado
        //según en el idioma configurado para visualizar la aplicación
        Literales lt = new Literales(this);
        estado_masa = lt.MostrarEstado(miIMC);

        //Transformación del valor de IMC en cadena para inclusión en el TextView para visualizar por pantalla
        //tanto el IMC como el estado
        String IMC = String.valueOf(miIMC);
        textView.setText(IMC);
        textView2.setText(estado_masa);

        //Llamada al Listener al pulsar el botón que nos devuelve a la pantalla de inicio de la aplicación
        btnVolver.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    //Método que calcula el IMC y lo devuelve, pasándole como parámetros el peso y la altura
    private double CalcularIMC(String p, String a) {
        int peso = Integer.parseInt(p);
        double altura = Double.parseDouble(a);
        double imc;

        altura /= 100;

        //Log.d(getClass().getCanonicalName(), "Peso: " + peso);
        //Log.d(getClass().getCanonicalName(), "Altura: " + altura);

        //Cálculo del IMC con dos decimales
        imc = Math.rint(peso/(Math.pow(altura,2))*100)/100;

        return imc;
    }
}

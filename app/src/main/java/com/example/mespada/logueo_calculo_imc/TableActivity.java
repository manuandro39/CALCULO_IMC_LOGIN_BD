package com.example.mespada.logueo_calculo_imc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

public class TableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Preparación de componentes de la TableActivity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        //Asignación de variables que representan los objetos componentes de la TableActivity
        Button btnVolverTable = (Button) findViewById(R.id.btnVolverTable);

        //Asignación de variables correspondientes a literales internacionalizados
        String EL_IMC = getResources().getString(R.string.el_imc);
        String Y = getResources().getString(R.string.y);

        //Llamada al método de la clase Literales que devuelve un array con todos los estados nutricionales según IMC
        Literales lit = new Literales(this);
        String [] cadenaEstados = lit.ArrayEstados();

        //Asignación de las variables array con el imc y los estados que se mostrarán en la tabla de valores
        String [] valor_imc = new String[] {
                "" + EL_IMC + " < 16",
                "" + EL_IMC + " >= 16 " + Y + " < 18,5",
                "" + EL_IMC + " >= 18,5 " + Y + " < 25",
                "" + EL_IMC + " >= 25 " + Y + " < 31",
                "" + EL_IMC + " >= 31"
        };

        String[] estado = new String[5];
        for(int i = 0; i < cadenaEstados.length;i++) {
            estado[i] = cadenaEstados[i];
        }

        Integer [] arrayImagenes = new Integer[] {
                R.drawable.desnutrida,
                R.drawable.bajopeso,
                R.drawable.normal,
                R.drawable.sobrepeso,
                R.drawable.obeso
        };

        //Llamada a la clase ListaAdapter con la que podamos inflar la vista que muestre la tabla de valores
        //valiéndonos de las filas de datos del layout "fila_listado"
        ListAdapter adapter = new ListaAdapter (this, valor_imc, estado, arrayImagenes, R.layout.fila_listado);

        //Asociar el Adapter a la vista

        ListView listview = (ListView) findViewById(R.id.tabla_valores);
        listview.setAdapter(adapter);

        //Llamada al Listener al pulsar el botón que nos devuelve a la pantalla de inicio de la aplicación
        btnVolverTable.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                //Se puede implementar en una clase Listener aparte o aquí
                Intent intent = new Intent(TableActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}

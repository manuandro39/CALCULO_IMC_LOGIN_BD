package com.example.mespada.logueo_calculo_imc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Preparación de componentes de la MainActivity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        boolean usuarioLogin;
        //Recojo con SharedPreferences el valor booleano devuelto que indica
        //si el usuario se ha logueado o no correctamente.
        //Si lo ha hecho, no necesitará loguearse de nuevo si no que accederá a la pantalla principal directamente
        SharedPreferences spf = getSharedPreferences("usuarioLogin", MODE_PRIVATE);

        usuarioLogin = spf.getBoolean("usuarioLogin", false);

        //En el caso no loguearse bien, permanece en la pantalla de logueo.
        if(!usuarioLogin) {
            Intent intento = new Intent(this, LoginActivity.class);
            this.startActivity(intento);
        }

        //Asignación de variables que representan los objetos componentes de la MainActivity
        final EditText editText = (EditText) findViewById(R.id.txtPeso);
        final EditText editText2 = (EditText) findViewById(R.id.txtAltura);
        Button btnCalculo = (Button) findViewById(R.id.btn_calculo);
        Button btnTablaValores = (Button) findViewById(R.id.btn_tablavalores);

        //Llamada al Listener al pulsar el botón para calcular el IMC
        btnCalculo.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                //Código que se encarga de enviar los datos introducidos a la ResultActivity.
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                intent.putExtra("peso", editText.getText().toString() + "");
                intent.putExtra("altura", editText2.getText().toString() + "");
                startActivity(intent);
            }
        });

        //Llamada al Listener al pulsar el botón para mostrar los rangos del IMC con su estado nutricional correspondiente
        btnTablaValores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Código que se encarga de enviar los datos introducidos a la Activity que muestra una tabla con los valores de IMC
                //y el estado nutricional
                Intent intent = new Intent(MainActivity.this, TableActivity.class);
                startActivity(intent);

            }
        });
    }

    //Creamos el menú para poder desconectarse el usuario de la sesión.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuItem cerrarSesion = menu.add(Menu.NONE, Menu.FIRST, Menu.NONE, "Desconectar");
        MenuItem salirApp = menu.add(Menu.NONE, Menu.FIRST+1, Menu.NONE, "Salir de App");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch(item.getItemId()) {
            case Menu.FIRST:
                Intent intent = new Intent(this, LoginActivity.class);
                this.startActivity(intent);
                return true;
            case Menu.FIRST+1:
                Intent intentAway = new Intent(Intent.ACTION_MAIN);
                intentAway.addCategory(Intent.CATEGORY_HOME);
                intentAway.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intentAway);
                return true;
            default:
                return true;
        }
    }
}

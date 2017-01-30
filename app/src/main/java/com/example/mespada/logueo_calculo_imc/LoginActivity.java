package com.example.mespada.logueo_calculo_imc;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.DialogPreference;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private int contador = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        boolean usuarioLogin = true;

        final EditText edtxUsuario = (EditText)findViewById(R.id.edtUsuario);
        final EditText edtxPassword = (EditText)findViewById(R.id.edtPassword);
        Button btLogin = (Button)findViewById(R.id.btnLogin);
        Button btRegistrar = (Button)findViewById(R.id.btnRegistro);


        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BaseDatosUser bd = new BaseDatosUser(LoginActivity.this, "Mibd", null, 1);
                String textUsuario = edtxUsuario.getText().toString();
                String textPassword = edtxPassword.getText().toString();

                if(textUsuario.equals("") || textPassword.equals("")) {
                    AlertDialog ad2 = new AlertDialog.Builder(LoginActivity.this).create();
                    ad2.setTitle("Datos vacíos");
                    ad2.setMessage("Te has dejado Datos sin rellenar");
                    ad2.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    ad2.show();
                } else if (bd.login(textUsuario, textPassword)) {
                        //Registro en SharedPreferences que el usuario se ha logueado correctamente y además aseguro que no se tenga
                        //que loguear más, es decir, que la segunda vez que se conecte entrará directamente a la actividad principal
                        //de la aplicación de cálculo del IMC
                        SharedPreferences shpr = getSharedPreferences("usuarioLogin", LoginActivity.this.MODE_PRIVATE);
                        SharedPreferences.Editor editor = shpr.edit();
                        editor.putBoolean("usuarioLogin", true);
                        editor.commit();

                        //Paso a la actividad principal de la aplicación porque el usuario ya se ha logueado correctamente
                        Intent intentAlMain = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intentAlMain);

                    } else {
                        //Error al loguearse y aviso a modo de diálogo
                        Log.d(getClass().getCanonicalName(), "Login Incorrecto");
                        contador--;
                        if(contador!=0) {
                            AlertDialog ad = new AlertDialog.Builder(LoginActivity.this).create();
                            ad.setTitle("Login error");
                            ad.setMessage("Error al loguearse. Dispones de 3 intentos, te quedan " + contador);
                            ad.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                            ad.show();

                        } else {
                            //Si tras los 3 intentos no hay login correcto, el usuario sale de la app.
                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    }
                }
            });

        btRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentoReg = new Intent(LoginActivity.this, RegistrarActivity.class);
                startActivity(intentoReg);
            }
        });


    }

    //Creamos el menú para poder desconectarse el usuario de la sesión.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuItem salirApp = menu.add(Menu.NONE, Menu.FIRST, Menu.NONE, "Salir de App");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch(item.getItemId()) {
            case Menu.FIRST:
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

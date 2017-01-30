package com.example.mespada.logueo_calculo_imc;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);


    }

    public void Registro (View view) {
        BaseDatosUser bdu = new BaseDatosUser(this, "Mibd", null, 1);

        String usuario;
        String password;
        boolean vacio = false, isExist;

        EditText user = (EditText)findViewById(R.id.edtRegUsuario);
        EditText pass = (EditText)findViewById(R.id.edtRegPassword);

        usuario = user.getText().toString();
        password = pass.getText().toString();

        //Se controla si hay campos vacíos
        if(usuario.equals("") || password.equals("")) {
            vacio = true;
            AlertDialog ad = new AlertDialog.Builder(this).create();
            ad.setTitle("Datos incompletos");
            ad.setMessage("Debe rellenar ambas cajas de texto (Usuario y Contraseña)");
            ad.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            ad.show();
        }

        //Se controla si el nombre de usuario con el que se va a registrar ya existe
        isExist = bdu.comprobarExistenciaUsuario(usuario);

        if(isExist) {
            AlertDialog ad2 = new AlertDialog.Builder(this).create();
            ad2.setTitle("Info");
            ad2.setMessage("El usuario existe");
            ad2.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            ad2.show();
        }

        if(!vacio && !isExist) {
            User nuevoUsuario = new User(usuario, password);
            bdu.nuevoUsuario(nuevoUsuario);

            //Se ha creado el usuario correctamente
            AlertDialog ad3 = new AlertDialog.Builder(this).create();
            ad3.setTitle("Usuario creado");
            ad3.setMessage("Usuario creado correctamente");
            ad3.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            ad3.show();

            Intent intento = new Intent(this, LoginActivity.class);
            this.startActivity(intento);
        }
    }
}

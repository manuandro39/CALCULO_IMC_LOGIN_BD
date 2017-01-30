package com.example.mespada.logueo_calculo_imc;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by maquillalo on 23/01/2017.
 */

public class BaseDatosUser extends SQLiteOpenHelper {

    // String que contiene el código SQL para crear la tabla de Usuarios. Se utilizará en el método OnCreate.
    private static final String sqlCreacionTablaUsuario = "CREATE TABLE USUARIOS (id INTEGER PRIMARY KEY AUTOINCREMENT, user TEXT, password TEXT)";

    // Constructor de la clase, con los parámetros por defecto
    public BaseDatosUser (Context context, String nombre, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, nombre, factory, version);
    }

    // Método obligatorio que pide SQLiteOpenHelper, sirve para actualizar la BD.
    @Override
    public void onUpgrade (SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    // Método obligatorio que crea la BBDD, es llamado cuando se utiliza getReadableDatabase y getWritableDatabas
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreacionTablaUsuario);
    }

    //Este método se invocará a la hora de cerarr la base de datos
    public void cerrarBaseDatos (SQLiteDatabase db) { db.close(); }

    //Con esta función damos de alta al usuario
    public void nuevoUsuario (User usuario) {
        SQLiteDatabase dataBase = this.getWritableDatabase();
        Log.d("prueba", usuario.getUser());
        dataBase.execSQL("INSERT INTO USUARIOS (user, password) VALUES ('"+usuario.getUser()+"', '"+usuario.getPassword()+"')");
        this.cerrarBaseDatos(dataBase);
    }

    public boolean login(String usuario, String password) {
        boolean coincide = false;
        String user = null;
        String pass = null;
        String consulta = "SELECT user, password FROM USUARIOS WHERE user = '" +usuario+"'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(consulta, null);

        int n_reg = cursor.getCount();

        if(cursor != null && cursor.moveToFirst()) {

            user = cursor.getString(0);
            pass = cursor.getString(1);

            if(user.equals(usuario) && pass.equals(password)){
                coincide = true;
            } else {
                coincide = false;
            }
        } else {
            coincide = false;
        }

        cursor.close();

        this.cerrarBaseDatos(db);

        return coincide;
    }

    public boolean comprobarExistenciaUsuario(String user) {
        boolean usuarioCreado = false;
        int n_registros = 0;
        String usuario = "";
        String consulta = "SELECT * FROM USUARIOS";

        SQLiteDatabase bd = this.getReadableDatabase();
        Cursor cur = bd.rawQuery(consulta, null);

        n_registros = cur.getCount();

        if(cur != null && n_registros > 0) {

            //la consulta ha recuperado datos
            cur.moveToFirst();//Cursor se mueve al primer registro

            do {
                usuario = cur.getString(1);
                if(usuario.equals(user)){
                    usuarioCreado = true;
                }
            } while (cur.moveToNext());

            cur.close();
        }

        this.cerrarBaseDatos(bd);

        return usuarioCreado;
    }
}

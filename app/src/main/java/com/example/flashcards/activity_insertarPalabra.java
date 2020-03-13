package com.example.flashcards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class activity_insertarPalabra extends AppCompatActivity {
    EditText etpalabra;
    Button binsertar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_palabra);
        etpalabra = (EditText) findViewById(R.id.etpalabra);
        binsertar= (Button) findViewById(R.id.binsertar);
        //Quitamos barra de notificaciones
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        binsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Insertar();
            }
        });



    }

    private void Insertar() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"Palabras",null,1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String palabra= etpalabra.getText().toString();

        if (!palabra.isEmpty()){
            ContentValues registro= new ContentValues();
            registro.put("palabra",palabra);

            BaseDeDatos.insert("palabras",null,registro);

            BaseDeDatos.close();
            etpalabra.setText(" ");

            Toast.makeText(this,"Se agrego la palabra",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Debe llenar todos los campos",Toast.LENGTH_SHORT).show();

        }
    }



}

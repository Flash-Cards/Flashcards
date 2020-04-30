package com.example.flashcards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class activity_seleccionarempezar extends AppCompatActivity {
    Button bcomenzar;
    ListView lv1;




   public int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionarempezar);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        bcomenzar = (Button) findViewById(R.id.bcomenzar);
        lv1 = (ListView) findViewById(R.id.lv1);

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Palabras", null, 1);
        SQLiteDatabase BaseDeDatabase = admin.getWritableDatabase();

        Cursor fila = BaseDeDatabase.rawQuery("select palabra from palabras", null);
        ArrayList<String> listapalabras = new ArrayList<>();
        if (fila.moveToFirst()) {

            do {
                listapalabras.add(fila.getString(0));

            } while (fila.moveToNext());


            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item_palabras, listapalabras);
            lv1.setAdapter(adapter);


            BaseDeDatabase.close();
        } else {
            Toast.makeText(this, "No se encuentran palabras", Toast.LENGTH_SHORT).show();

        }
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ModificarSeleccion(parent.getItemAtPosition(position).toString());




            }

        });

        bcomenzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_seleccionarempezar.this, activity_flashcard.class);

                startActivity(intent);
            }
        });






    }

    public void ModificarSeleccion(String Pselect){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Palabras", null, 1);
        SQLiteDatabase BaseDeDatabase = admin.getWritableDatabase();

        ContentValues registro = new ContentValues();
        registro.put("seleccion",1);

        int cantidad = BaseDeDatabase.update("palabras",registro,"palabra='"+Pselect+"'",null);
        BaseDeDatabase.close();

        if (cantidad==1){
            Toast.makeText(this, "Se ha selecciono la palabra", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Error al seleccionars", Toast.LENGTH_SHORT).show();
        }
    }
}


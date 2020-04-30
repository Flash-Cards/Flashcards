package com.example.flashcards;

import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.RunnableScheduledFuture;

public class activity_flashcard extends AppCompatActivity {

    private Handler mhandler = new Handler();
    TextView tflash;
    Button bpreguntas;
    ArrayList<String> listapalabras = new ArrayList<>();
    int carreglo;

    Boolean repetir=true;
    //int carreglo=palabras.length;

    public int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        bpreguntas=(Button) findViewById(R.id.bpreguntas);

        tflash = (TextView) findViewById(R.id.tflash);
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Palabras", null, 1);
        SQLiteDatabase BaseDeDatabase = admin.getWritableDatabase();
         //consulta
        Cursor fila = BaseDeDatabase.rawQuery("select palabra from palabras where seleccion = 1", null);

        if (fila.moveToFirst()) {

            do {
                listapalabras.add(fila.getString(0));

            } while (fila.moveToNext());

            BaseDeDatabase.close();
            int npalabras=listapalabras.size();
            carreglo=npalabras;
        } else {
            Toast.makeText(this, "No se encuentran palabras", Toast.LENGTH_SHORT).show();

        }
       //fin consulta
        bpreguntas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

       mhandler.postDelayed(mcicloflashcards,5000);

       /* for(i=0;i<carreglo;i++){
            tflash.setText(palabras[i]);
            tflash.setText(" ");

        }
         for (String i:palabras)
            {

                tflash.setText(i);
                mhandler.postDelayed(this,4000);
            }*/
      /*if(repetir==true){
            if(i<=carreglo){
                tflash.setText(palabras[i]);
                i++;
            }else{
                i=0;
            }
        }*/
    }

    private Runnable cambiar=new Runnable() {
        @Override
        public void run() {
            if (i<carreglo){
                i++;
                mhandler.postDelayed(mcicloflashcards,1000);
            }else{
                i=0;
                mhandler.postDelayed(mcicloflashcards,1000);
            }

        }


    };

    private Runnable mcicloflashcards=new Runnable(){

        @Override
        public void run() {
                tflash.setText(listapalabras.get(i).toString());

                mhandler.postDelayed(cambiar,2000);




        }
    };

}

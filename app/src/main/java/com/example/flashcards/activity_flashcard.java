package com.example.flashcards;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class activity_flashcard extends AppCompatActivity {
    TextView tflash;
    Button bpreguntas;
    String palabras[] = {"Clase","Perro","Gato","Casa","Celular"};
    Boolean repetir=true;

    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard);
        bpreguntas=(Button) findViewById(R.id.bpreguntas);
        int carreglo=palabras.length;
        tflash = (TextView) findViewById(R.id.tflash);


       /* for(i=0;i<=carreglo;i++){
            tflash.setText(palabras[i]);
            tflash.setText(" ");

        }*/










        for (String i:palabras)
        {
            esperar(5);
            tflash.setText(i);

        }



      /*if(repetir==true){
            if(i<=carreglo){
                tflash.setText(palabras[i]);
                i++;
            }else{
                i=0;
            }
        }*/







    }

    public static void esperar(int segundos){
        try {
            Thread.sleep(segundos * 1000);
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}

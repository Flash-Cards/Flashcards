package com.example.flashcards;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

public class activity_flashcard extends AppCompatActivity {

    private Handler mhandler = new Handler();
    TextView tflash;
    Button bpreguntas;
    String palabras[] = {"Clase","Perro","Gato","Casa","Celular","ola","Carro"};
    Boolean repetir=true;
    int carreglo=palabras.length;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard);
        bpreguntas=(Button) findViewById(R.id.bpreguntas);

        tflash = (TextView) findViewById(R.id.tflash);


        mcicloflashcards.run();


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

    /*public static void esperar(int segundos){
        try {
            Thread.sleep(segundos * 1000);
        } catch (Exception e) {
            System.out.println(e);
        }
    }*/

    private Runnable mcicloflashcards=new Runnable(){

        @Override
        public void run() {

            for(int i=0;i<carreglo;i++){
                tflash.setText(palabras[i]);
                tflash.setText(" ");
                mhandler.postDelayed(this,4000);
            }
        }
    };

}

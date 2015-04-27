package com.example.lukaszpp.saper;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;


public class showFiles extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_files);

        //  TextView textViewObject = (TextView) findViewById(R.id.textView);
        //  textViewObject.setText(message);

        TextView textViewObject = (TextView) findViewById(R.id.listaPlikow);
       // String listaPlikow = getFilesDir().getAbsolutePath();
        String sListaPlikow = "";
        File dirFiles = getFilesDir();
        for (String strFile : dirFiles.list())
        {
            // strFile is the file name
            sListaPlikow += strFile + "\n";
        }


        textViewObject.setText(sListaPlikow);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_files, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void otworzPlik(View view){

        //wez nazwe pliku
        EditText editTextObject = (EditText) findViewById(R.id.nazwaPliku);
        String filename = editTextObject.getText().toString();


        try {
            //pokaz tresc pliku
            FileInputStream fis = openFileInput(filename);
            InputStreamReader inputStreamReader = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder sb = new StringBuilder();
            String line;

            while((line = bufferedReader.readLine()) != null){
                sb.append(line);

            }
            editTextObject = (EditText) findViewById(R.id.editText);
           // editTextObject.setText(fis.toString());

           // String readString = new String();
            //fis.read(readString.getBytes());

            //readString+="nnn";

            editTextObject.setText(sb.toString());

            inputStreamReader.close();
            fis.close();

        }catch (Exception e) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("Blad odczytu do pliku " + filename);
            builder1.setCancelable(true);
            builder1.setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });


            AlertDialog alert11 = builder1.create();
            alert11.show();
        }


    }

    public void zapiszNotatke(View view){

        //tresc pliku
        EditText editTextObject = (EditText) findViewById(R.id.editText);
        String filecontent = editTextObject.getText().toString();

        //nazwa pliku
        EditText editTextObject2 = (EditText) findViewById(R.id.nazwaPliku);
        String filename = editTextObject2.getText().toString();

        try {
            FileOutputStream fos = openFileOutput(filename,2);
            fos.write(filecontent.getBytes());
            fos.close();

            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("Zapisano w pliku: " + filename);
            builder1.setCancelable(true);
            builder1.setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });


            AlertDialog alert11 = builder1.create();
            alert11.show();

        }catch(Exception e){

            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("Blad zapisu do pliku " + filename);
            builder1.setCancelable(true);
            builder1.setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });


            AlertDialog alert11 = builder1.create();
            alert11.show();
        }

    }
}

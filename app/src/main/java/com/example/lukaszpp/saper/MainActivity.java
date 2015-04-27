package com.example.lukaszpp.saper;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileOutputStream;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    //funkcja otwierająca notatki
    public void otworzNotatki(View view){
        Intent myIntent = new Intent(this, showFiles.class);
        startActivity(myIntent);
    }

    //funkcja zapisująca notatkę
    public void zapiszNotke(View view){
       // Intent intent = new Intent(this, DisplayMessageActivity.class);

        //get text
        EditText textFilenameObject = (EditText) findViewById(R.id.textFilename);
        String filename = textFilenameObject.getText().toString();

        EditText textFilecontentObject = (EditText) findViewById(R.id.textFilecontent);
        String filecontent = textFilecontentObject.getText().toString();

        //show text
      // / TextView textViewObject = (TextView) findViewById(R.id.textView);
      //  textViewObject.setText(message);


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

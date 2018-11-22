package com.example.a17437557_7.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText nombre,apellido;
    Button ingresar,mostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre=(EditText)findViewById(R.id.innombre);
        apellido=(EditText)findViewById(R.id.inapellido);

        ingresar=(Button) findViewById(R.id.ingresar);
        mostrar=(Button)findViewById(R.id.mostrar);

        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardar(nombre.getText().toString(),apellido.getText().toString());
            }
        });

        mostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Listado.class));
            }
        });
    }

    private void guardar (String Nombre, String Apellido){
        ConexionDBHelper helper =new ConexionDBHelper(this,"APPSQLITE",null,1);
        SQLiteDatabase db=helper.getWritableDatabase();
        try{
            ContentValues c=new ContentValues();
            c.put("Nombre",Nombre);
            c.put("Apellido",Apellido);
            db.insert("USUARIO",null,c);
            db.close();
            Toast.makeText(MainActivity.this,"Dato Ingresado",Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(MainActivity.this,"Error"+e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
}

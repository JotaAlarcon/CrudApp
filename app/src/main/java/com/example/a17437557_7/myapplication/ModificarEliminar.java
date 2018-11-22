package com.example.a17437557_7.myapplication;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ModificarEliminar extends AppCompatActivity {

    EditText nombre,apellido;
    Button modificar,eliminar;
    int iduser;
    String nomuser,apuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_eliminar);

        Bundle b = getIntent().getExtras();
        if (b!=null)
        {
            iduser=b.getInt("id");
            nomuser=b.getString("Nombre");
            apuser=b.getString("Apellido");
        }

        nombre=(EditText)findViewById(R.id.innombre_moel);
        apellido=(EditText)findViewById(R.id.inapellido_moel);

        nombre.setText(nomuser);
        apellido.setText(apuser);

        modificar=(Button) findViewById(R.id.modificar);
        eliminar=(Button)findViewById(R.id.eliminar);

        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modificar(iduser,nombre.getText().toString(),apellido.getText().toString());
                onBackPressed();
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminar(iduser);
                onBackPressed();
            }
        });

        if (getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private  void modificar(int id,String Nombre, String Apellido){
        ConexionDBHelper helper = new ConexionDBHelper(this,"APPSQLITE",null,1);
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql="UPDATE USUARIO SET NOMBRE='"+Nombre+"',APELLIDO='"+Apellido+"' WHERE ID="+id;
        db.execSQL(sql);
        db.close();
    }

    private  void eliminar(int id){
        ConexionDBHelper helper = new ConexionDBHelper(this,"APPSQLITE",null,1);
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql="DELETE FROM USUARIO WHERE ID="+id;
        db.execSQL(sql);
        db.close();
    }
}

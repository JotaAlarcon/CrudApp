package com.example.a17437557_7.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Listado extends AppCompatActivity {

    ListView lista;
    ArrayList<String> listado;

    @Override
    protected void onPostResume() {
        super.onPostResume();
        cargarLista();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        lista=(ListView)findViewById(R.id.lista);

        cargarLista();

        if (getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Listado.this,listado.get(position),Toast.LENGTH_SHORT).show();
                int clave=Integer.parseInt(listado.get(position).split(" ")[0]);
                String nombre = listado.get(position).split(" ")[1];
                String apellido = listado.get(position).split(" ")[2];
                Intent intent = new Intent(Listado.this,ModificarEliminar.class);
                intent.putExtra("id",clave);
                intent.putExtra("Nombre",nombre);
                intent.putExtra("Apellido",apellido);
                startActivity(intent);

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void cargarLista(){
        listado=listausuarios();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listado);
        lista.setAdapter(adapter);
    }

    private ArrayList<String> listausuarios(){
        ArrayList<String> datos = new ArrayList<String>();
        ConexionDBHelper helper =new ConexionDBHelper(this,"APPSQLITE",null,1);
        SQLiteDatabase db=helper.getWritableDatabase();
        String sql="SELECT * FROM USUARIO";
        Cursor c=db.rawQuery(sql,null);
        if (c.moveToFirst())
        {
            do {
                String linea= c.getInt(0)+" "+c.getString(1)+" "+c.getString(2);
                datos.add(linea);
            }while (c.moveToNext());
        }
        db.close();
        return datos;
    }
}

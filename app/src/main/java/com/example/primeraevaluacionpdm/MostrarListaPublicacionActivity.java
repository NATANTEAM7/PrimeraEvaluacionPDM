package com.example.primeraevaluacionpdm;

import static com.example.primeraevaluacionpdm.MainActivity.lstPublicaciones;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.primeraevaluacionpdm.clases.Publicacion;

public class MostrarListaPublicacionActivity extends AppCompatActivity {

    ListView lsvPublicaciones;
    Context context = this;
    private int selectedPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_lista_publicacion);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        lsvPublicaciones =  findViewById(R.id.lsvPublicacion);

        ArrayAdapter<Publicacion> arrayAdapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, lstPublicaciones);

        lsvPublicaciones.setAdapter(arrayAdapter);

        lsvPublicaciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedPosition = position; // Almacena la posición seleccionada
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Opciones");
                builder.setItems(new CharSequence[]{"Eliminar"}, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                eliminarPublicacion(selectedPosition);
                                arrayAdapter.notifyDataSetChanged();
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });

    }

    // Implementa la lógica para eliminar el elemento seleccionado
    private void eliminarPublicacion(int selectedPosition) {
        if (selectedPosition >= 0 && selectedPosition < lstPublicaciones.size()) {
            lstPublicaciones.remove(selectedPosition);
            Toast.makeText(MostrarListaPublicacionActivity.this, "Publicacion eliminada en la posición " + selectedPosition, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
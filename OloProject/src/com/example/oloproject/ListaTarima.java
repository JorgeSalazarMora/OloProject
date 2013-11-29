package com.example.oloproject;

import java.util.List;
import com.data.DatabaseDataSource;
import com.domain.Tarima;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.EditText;
import android.widget.Toast;


public class ListaTarima extends Activity {
	private DatabaseDataSource datasource;
	private int positiont = 0; 
	private boolean selected;
	ListView lvTarima;
	@Override 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_tarima);
		lvTarima =(ListView) findViewById(R.id.lvTarima);
		datasource = new DatabaseDataSource(this);
	    datasource.open();
		//llenarLista();
	    List<Tarima> values = datasource.getAllTarimas();
		ArrayAdapter<Tarima> adapter = new ArrayAdapter<Tarima>(this,
				android.R.layout.simple_list_item_1, values );
		lvTarima.setAdapter(adapter);
		lvTarima.getAdapter();
		lvTarima.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View view,
	                int position, long id) {
	        	positiont= position;
	        	selected = true;
	        }
	    });
	}
	
	public void onClick(View view) {
		lvTarima =(ListView) findViewById(R.id.lvTarima);
		@SuppressWarnings("unchecked")
		ArrayAdapter<Tarima> adapter = (ArrayAdapter<Tarima>) lvTarima.getAdapter();
		Tarima tam = null;
		switch (view.getId()) {
		case R.id.btnEditarTarima:
			if (lvTarima.getAdapter().getCount() > 0) {
				try{if(selected){
					Intent intent = new Intent(ListaTarima.this,
							EditarTarima.class);
					tam = (Tarima) lvTarima.getAdapter().getItem(positiont);
					intent.putExtra("ID", tam.getNumeroTarima());
					startActivity(intent);
				}else{
					Toast.makeText(getApplicationContext(),
							"Por favor seleccione la tarima que desea editar",
							Toast.LENGTH_LONG).show();
				}}catch(Exception e){}
			}
			break;
		case R.id.btnEliminarTarima:
			if (lvTarima.getAdapter().getCount() > 0) {
					if(selected){
						tam = (Tarima) lvTarima.getAdapter().getItem(positiont);
						datasource.deleteTarima(tam);
						adapter.remove(tam);
					
						//adapter.remove(tam);
				}else{
					Toast.makeText(getApplicationContext(),
							"Por favor seleccione la tarima que desea borrar",
							Toast.LENGTH_LONG).show();
				}
			}
			break;
		}
		adapter.notifyDataSetChanged();
	}

	public void llenarLista() {
		datasource = new DatabaseDataSource(this);
		datasource.open();
		List<Tarima> tam = datasource.getAllTarimas();
		ArrayAdapter<String> adapters = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1);
		for (int i = 0; i < tam.size(); i++) {
			lvTarima.getAdapter().getItem(i);
			int id = 0;
			String origen = null;
			id = ((Tarima) lvTarima.getAdapter().getItem(i)).getNumeroTarima();
			origen = ((Tarima) lvTarima.getAdapter().getItem(i)).getTarimaOrigen();
				adapters.add("Tarima: " +id+ "\n" + "  Origen: " +origen );
		}
	    lvTarima.setAdapter(adapters);
	}

	@Override
	  protected void onResume() {
	    datasource.open();
	    super.onResume();
	  }

	  @Override
	  protected void onPause() {
	    datasource.close();
	    super.onPause();
	  }
}

package com.example.oloproject;

import java.util.List;
import com.data.DatabaseDataSource;
import com.domain.Tarima;
import com.domain.Referencia;
import com.example.oloproject.R;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


import android.widget.Toast;

public class NuevaTarima extends Activity {

	private DatabaseDataSource datasource;
	Button btnGuardarTam;
	EditText txtOrigenTam;
	EditText txtIdRef;
	EditText txtBultos;
	EditText txtEmpaque;
	EditText txtPeso;
	EditText txtExpediente;
	TextView txtUnidades;
	TextView txtPesoTotal;
	ListView lvReferencias;
	EditText txtTarimaId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nueva_tarima);
		btnGuardarTam = (Button) findViewById(R.id.btnGuardarTam);
		txtTarimaId = (EditText) findViewById(R.id.txtIdTam);
		txtOrigenTam = (EditText) findViewById(R.id.txtTarimaOrigen);
		txtExpediente = (EditText) findViewById(R.id.txtTExpediente);
		lvReferencias = (ListView) findViewById(R.id.lvReferencias);

		btnGuardarTam.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				capturarTam();
			}
		});

		lvReferencias.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View view,
	                int position, long id) {
	        	//puede ser
	        }
	    });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.nueva_tarima, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.menu_agregarRef:
			if(txtTarimaId.getText().toString().isEmpty())
				Toast.makeText(getApplicationContext(), "Ingrese el numero de tarima antes de continuar",Toast.LENGTH_LONG).show();
				else
					createDialog();
			return true;
		//posible un boton de editar
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	void createDialog() {

		final Dialog dialog = new Dialog(NuevaTarima.this);
		dialog.setContentView(R.layout.xml_referencia);
		dialog.setTitle("Agregar Referencia");

		Button btnDesachar = (Button) dialog
				.findViewById(R.id.btnEDesecharRef);
		Button btnAgregar = (Button) dialog
				.findViewById(R.id.btnERAgregarReferencia);
		txtIdRef = (EditText) dialog.findViewById(R.id.txtReferencia);
		txtBultos = (EditText) dialog.findViewById(R.id.txtBultos);
		txtEmpaque = (EditText) dialog.findViewById(R.id.txtEmpaque);
		txtPeso = (EditText) dialog.findViewById(R.id.txtPeso);
		txtUnidades = (TextView) dialog.findViewById(R.id.txtUnidadesTotales);
		txtPesoTotal = (TextView) dialog.findViewById(R.id.txtPesoTotal);
		Button btnPrueba = (Button) dialog.findViewById(R.id.btnPrueba2);
		txtPeso.setOnEditorActionListener(new EditText.OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView v, int actionId,
					KeyEvent event) {
				if (actionId == EditorInfo.IME_ACTION_DONE) {
					multiplicar();
					return true;
				}
				return false;
			}
		});
		txtEmpaque.setOnEditorActionListener(new EditText.OnEditorActionListener() {
					@Override
					public boolean onEditorAction(TextView v, int actionId,
							KeyEvent event) {
						if (actionId == EditorInfo.IME_ACTION_DONE) {
							multiplicar();
							return true;
						}
						return false;
					}
				});
		txtBultos.setOnEditorActionListener(new EditText.OnEditorActionListener() {
					@Override
					public boolean onEditorAction(TextView v, int actionId,
							KeyEvent event) {
						if (actionId == EditorInfo.IME_ACTION_DONE) {
							multiplicar();
							return true;
						}
						return false;
					}
				});

		txtEmpaque.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {

				if (!hasFocus) {
					multiplicar();
				}
			}
		});
		txtBultos.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {

				if (!hasFocus) {
					multiplicar();
				}
			}
		});
		txtPeso.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {

				if (!hasFocus) {
					multiplicar();
				}
			}
		});

		btnDesachar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				fillList();
				dialog.dismiss();

			}
		});
		btnPrueba.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				mostrar2();

			}
		});
		btnAgregar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (txtPesoTotal.getText().toString().equals("0"))
					multiplicar();
				capturarRef();
				limpiar();
			}
		});

		dialog.show();
	}



	public void capturarRef()
	{
		datasource = new DatabaseDataSource(this);
		datasource.open();
		if (txtIdRef.getText().toString().isEmpty()
				|| txtBultos.getText().toString().isEmpty()
				|| txtEmpaque.getText().toString().isEmpty()
				|| txtPeso.getText().toString().isEmpty()
				|| txtPeso.getText().toString().equals("0")) {
			Toast.makeText(getApplicationContext(), "Hay espacios en blanco",
					Toast.LENGTH_LONG).show();
		}else{
			Referencia ref = null;
			ref = datasource.createReferencia(txtIdRef.getText().toString(),
					txtBultos.getText().toString(), 
					txtEmpaque.getText().toString(), 
					txtPeso.getText().toString(), 
					txtUnidades.getText().toString(), 
					txtPesoTotal.getText().toString(), 
					txtTarimaId.getText().toString());
			Toast.makeText(getApplicationContext(), "Referencia guardada satisfactoriamente!",
					Toast.LENGTH_LONG).show();
		}
		
	}




	public void mostrar2() {
		datasource = new DatabaseDataSource(this);
		datasource.open();
		List<Referencia> ref = datasource.getAllReferencias();

		for (int i = 0; i < ref.size(); i++) {
			String id = null;
			int bultos = 0;
			int empaque = 0;
			double peso = 0;
			int unidades = 0;
			double pesototal = 0;
			int tarima = 0;
			id = ref.get(i).getRef();
			bultos = ref.get(i).getBultos();
			empaque = ref.get(i).getEmpaque();
			peso = ref.get(i).getPeso();
			unidades = ref.get(i).getUnidades();
			pesototal = ref.get(i).getPesototal();
			tarima = ref.get(i).getNumeroTarimas();
			Toast.makeText(
					getApplicationContext(),
					"ID: " + id + " BULTOS: " + bultos + " empaque: " + empaque
							+ " peso: " + peso + " unidades: " + unidades
							+ " pesototal: " + pesototal
							+ " Tarima: " + tarima, Toast.LENGTH_LONG)
					.show();
		}
	}

	public void multiplicar() {
		try {
			int unidades = Integer.parseInt(txtBultos.getText().toString())
					* Integer.parseInt(txtEmpaque.getText().toString());
			txtUnidades.setText("" + unidades);
			float pesoTotal = Float.parseFloat(txtBultos.getText().toString())
					* Float.parseFloat(txtPeso.getText().toString());
			txtPesoTotal.setText("" + pesoTotal);
		} catch (Exception e) {
		}

	}

	public void fillList() {

		datasource = new DatabaseDataSource(this);
		datasource.open();
		List<Referencia> ref = datasource.getAllReferencias();
		ArrayAdapter<String> adapters = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1);
		for (int i = 0; i < ref.size(); i++) {
			String id = null;
			int tarima = 0;
			id = ref.get(i).getRef();
			tarima = ref.get(i).getNumeroTarimas();
			if(txtTarimaId.getText().toString().equals(tarima+""))
			adapters.add("Referencia: " + "\n" + " " + id);
		}
		lvReferencias.setAdapter(adapters);
	}

	public void limpiar() {
		txtBultos.setText("");
		txtEmpaque.setText("");
		txtPeso.setText("");
		txtUnidades.setText("0");
		txtPesoTotal.setText("0");
		txtIdRef.setText("");
	}

	public void limpiarTam() {
		txtTarimaId.setText("");
		txtOrigenTam.setText("");
		txtExpediente.setText("");
		ArrayAdapter<String> adapters = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1);
		lvReferencias.setAdapter(adapters);
	}

	public int contarLista() {
		int count = 0;
		for (int i = 0; i <= lvReferencias.getLastVisiblePosition(); i++) {
			if (lvReferencias.getChildAt(i) != null)
				count++;
		}
		return count;
	}

	public void capturarTam() {
		datasource = new DatabaseDataSource(this);
		datasource.open();
		
		if (txtOrigenTam.getText().toString().isEmpty()
				|| txtExpediente.getText().toString().isEmpty()) {
			Toast.makeText(getApplicationContext(), "Hay espacios en blanco",
					Toast.LENGTH_LONG).show();
		} else {
			

			// TAMBIEN LA FECHA DE I Y F DE DESPACHO
			if (contarLista() == 0)
				Toast.makeText(getApplicationContext(),
						"Agregar una referencia antes de guardar",
						Toast.LENGTH_LONG).show();
			else {
				AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
				dialogo1.setTitle("Importante");
				dialogo1.setMessage("¿ Esta seguro de guardar la tarima, al precionar 'SI' se limpiaran los campos ?");
				dialogo1.setCancelable(false);
				dialogo1.setPositiveButton("Confirmar",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialogo1, int id) {
								Tarima tarima = null;
								tarima = datasource.createTarima(txtTarimaId
										.getText().toString(), txtOrigenTam
										.getText().toString(), txtExpediente
										.getText().toString(), contarLista()
										+ "");
								Toast.makeText(getApplicationContext(),
										"Tarima guardada satisfactoriamente!",
										Toast.LENGTH_LONG).show();
								limpiarTam();
							}
						});
				dialogo1.setNegativeButton("Cancelar",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialogo1, int id) {
								finish();
							}
						});
				dialogo1.show();

			}
		}
		}
	}




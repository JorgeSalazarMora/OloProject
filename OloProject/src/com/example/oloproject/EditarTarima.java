package com.example.oloproject;

import java.util.List;
import com.data.DatabaseDataSource;
import com.domain.Referencia;
import com.domain.Tarima;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


public class EditarTarima extends Activity {
	
	

	private DatabaseDataSource datasource;
	EditText txtEOrigenTam;
	EditText txtEExpediente;
	EditText txtETarimaId;
	TextView txtROrigen;
	TextView txtRTarimaId;
	ListView lvEReferencias;
	Button btnGuardar;
	Button btnCancelar;
	EditText txtEIdRef;
	EditText txtEBultos;
	EditText txtEEmpaque;
	EditText txtEPeso;
	TextView txtEUnidades;
	TextView txtEPesoTotal;
	String refId;


	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editar_tarima);
		txtETarimaId = (EditText) findViewById(R.id.txtETarimaId);
		txtEOrigenTam = (EditText) findViewById(R.id.txtETarimaOrigen);
		txtEExpediente = (EditText) findViewById(R.id.txtETExpediente);
		lvEReferencias = (ListView) findViewById(R.id.lvEReferencias);
		btnGuardar = (Button) findViewById(R.id.btnEGuardarTam);
		btnCancelar = (Button) findViewById(R.id.btnCancelar);

		llenarCampos();
		fillList();
		btnGuardar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				actualizarTam();
			}
		});
		btnCancelar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		lvEReferencias.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View view,
	                int position, long id) {
	        	String ref = lvEReferencias.getAdapter().getItem(position).toString();
	        	refId = (String) ref.subSequence(11, ref.length());
	        	editarRef();
	    				
	        }
	    });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.editar_tarima, menu);
		return true;
	}

	public void llenarCampos()
	{
		 datasource = new DatabaseDataSource(this);
		 datasource.open();
			Bundle extra = this.getIntent().getExtras();
		extra.getInt("ID");
		List<Tarima> tam = datasource.getAllTarimas();

		for (int i = 0; i < tam.size(); i++) {
			String origen= null;
			int id =0;
			String expediente = null;
			expediente = tam.get(i).getExpediente();
			id = tam.get(i).getNumeroTarima();
			origen = tam.get(i).getTarimaOrigen();
			if(extra.getInt("ID") == id){
				txtEExpediente.setText(expediente);
				txtEOrigenTam.setText(origen);
				txtETarimaId.setText(id+"");
			}
		}
	}
	public void llenarRef(String referencia)
	{
		 datasource = new DatabaseDataSource(this);
		 datasource.open();

		List<Referencia> ref = datasource.getAllReferencias();

		for (int i = 0; i < ref.size(); i++) {
			String id= null;
			double peso = 0;
			double pesoT = 0;
			int bultos= 0;
			int empaque = 0;
			int unidades = 0;
			peso = ref.get(i).getPeso();
			pesoT = ref.get(i).getPesototal();
			empaque = ref.get(i).getEmpaque();
			bultos = ref.get(i).getBultos();
			unidades = ref.get(i).getUnidades();
			id = ref.get(i).getRef();
			if(id.equals(referencia)){
				txtEIdRef.setText(id);
				txtEBultos.setText(""+bultos);
				txtEEmpaque.setText(""+empaque);
				txtEPeso.setText(""+peso);
				txtEUnidades.setText(""+unidades) ;
				txtEPesoTotal.setText(""+pesoT) ;
				txtROrigen.setText(txtEOrigenTam.getText().toString());
				txtRTarimaId.setText(txtETarimaId.getText().toString());
			}
		}
	}
	public void fillList() {
		Bundle extra = this.getIntent().getExtras();
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
			if(extra.getInt("ID") == tarima)
			adapters.add("Referencia: " + "\n" + " " + id);
		}
		lvEReferencias.setAdapter(adapters);
	}
	public void actualizarTam()
	{
		datasource = new DatabaseDataSource(this);
		datasource.open();
		datasource.updateTarima(txtETarimaId.getText().toString(),
				txtEOrigenTam.getText().toString(),txtEExpediente.getText().toString());
		Toast.makeText(getApplicationContext(), "Tarima editada satisfactoriamente!",
				Toast.LENGTH_LONG).show();
	}
	public void actualizarRef()
	{
		datasource = new DatabaseDataSource(this);
		datasource.open();
		datasource.updateReferencia(txtEIdRef.getText().toString(),
				txtEBultos.getText().toString(),
				txtEEmpaque.getText().toString(),
				txtEPeso.getText().toString(),
				txtEUnidades.getText().toString(),
				txtEPesoTotal.getText().toString());
		Toast.makeText(getApplicationContext(), "Referencia editada satisfactoriamente!",
				Toast.LENGTH_LONG).show();
	}
	public void editarRef()
	{
		AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
		dialogo1.setTitle("Importante");
		dialogo1.setMessage("¿ Desea editar esta Referencia ?");
		dialogo1.setCancelable(false);
		dialogo1.setPositiveButton("Confirmar",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialogo1, int id) {
						createDialog();
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
	
	void createDialog() {

		final Dialog dialog = new Dialog(EditarTarima.this);
		dialog.setContentView(R.layout.xml_editareferencia);
		dialog.setTitle("Editar Referencia");

		Button btnDesachar = (Button) dialog
				.findViewById(R.id.btnCancelarRef);
		Button btnAgregar = (Button) dialog
				.findViewById(R.id.btnEGuardar);
		txtEIdRef = (EditText) dialog.findViewById(R.id.txtEReferencia);
		txtEBultos = (EditText) dialog.findViewById(R.id.txtEBultos);
		txtEEmpaque = (EditText) dialog.findViewById(R.id.txtEEmpaque);
		txtEPeso = (EditText) dialog.findViewById(R.id.txtEPeso);
		txtEUnidades = (TextView) dialog.findViewById(R.id.txtEUnidadesTotales);
		txtEPesoTotal = (TextView) dialog.findViewById(R.id.txtEPesoTotal);
		txtROrigen=(TextView)dialog.findViewById(R.id.txtROrigen);
		txtRTarimaId=(TextView)dialog.findViewById(R.id.txtRTam);
		llenarRef(refId.trim());
		txtEPeso.setOnEditorActionListener(new EditText.OnEditorActionListener() {

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
		txtEEmpaque.setOnEditorActionListener(new EditText.OnEditorActionListener() {
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
		txtEBultos.setOnEditorActionListener(new EditText.OnEditorActionListener() {
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

		txtEEmpaque.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {

				if (!hasFocus) {
					multiplicar();
				}
			}
		});
		txtEBultos.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {

				if (!hasFocus) {
					multiplicar();
				}
			}
		});
		txtEPeso.setOnFocusChangeListener(new OnFocusChangeListener() {
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
				dialog.dismiss();

			}
		});

		btnAgregar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (txtEPesoTotal.getText().toString().equals("0"))
					multiplicar();
				actualizarRef();

			}
		});

		dialog.show();
	}
	
	public void multiplicar() {
		try {
			int unidades = Integer.parseInt(txtEBultos.getText().toString())
					* Integer.parseInt(txtEEmpaque.getText().toString());
			txtEUnidades.setText("" + unidades);
			float pesoTotal = Float.parseFloat(txtEBultos.getText().toString())
					* Float.parseFloat(txtEPeso.getText().toString());
			txtEPesoTotal.setText("" + pesoTotal);
		} catch (Exception e) {
		}

	}
	
}

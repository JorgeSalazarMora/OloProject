package com.example.oloproject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import com.domain.*;
import com.data.DatabaseDataSource;
import com.example.oloproject.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class HojaDespachos extends Activity implements  OnClickListener{

	Button btnNuevaTarima;
	EditText txtNombre;
	EditText txtFicha;
	Spinner spnDestino;
	//fecha
	//hinicio
	//hfinal
	EditText txtFecha;
	EditText txtDespacho;
	Button btnListaTarima;
	Button btnGuardar;
	Button btnRegresar;
	private DatabaseDataSource datasource;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hoja_despacho);
		btnNuevaTarima = (Button)findViewById(R.id.btnNuevaTarima);
		txtNombre = (EditText)findViewById(R.id.txtNombre);
		txtFicha = (EditText)findViewById(R.id.txtFicha);
		txtFecha = (EditText)findViewById(R.id.txtDia);
		 txtFecha.setText(getDatePhone());
		spnDestino = (Spinner)findViewById(R.id.spnDestino);
		txtDespacho = (EditText)findViewById(R.id.txtDespacho);
		btnListaTarima =  (Button)findViewById(R.id.btnListaTarima);
		btnGuardar = (Button)findViewById(R.id.btnGuardar);
		btnRegresar = (Button)findViewById(R.id.btnRegresar);
		btnRegresar.setOnClickListener(this);
		datasource = new DatabaseDataSource(this);
		datasource.open();
		fillPaisSpinner();
		
		btnNuevaTarima.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(HojaDespachos.this,
						NuevaTarima.class);
				startActivity(intent);
				}
			
		});
		btnGuardar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (txtNombre.getText().toString().isEmpty()
						|| txtFicha.getText().toString().isEmpty()
						|| txtDespacho.getText().toString().isEmpty()) {
					Toast.makeText(getApplicationContext(), "Hay espacios en blanco",
							Toast.LENGTH_LONG).show();
				}else{
					HojaDespacho despacho = null;
					despacho = datasource.createDespacho("2",
							txtFicha.getText().toString(), spnDestino.getSelectedItem().toString(), 
							"12/25/13", "123", 
							"321", txtNombre.getText().toString(), txtDespacho.getText().toString());
					Toast.makeText(getApplicationContext(), "Hoja de Despacho guardada satisfactoriamente!",
							Toast.LENGTH_LONG).show();
				}
			}
		});
		btnListaTarima.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(HojaDespachos.this,
						ListaTarima.class);
				startActivity(intent);
				}
			
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hoja_despacho, menu);
		return true;
	}
	
	public void fillPaisSpinner() {

		List<String> countrySpinnerArray = new ArrayList<String>();
		countrySpinnerArray.add("Costa Rica");
		countrySpinnerArray.add("El Salvador");
		countrySpinnerArray.add("Venezuela");

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, countrySpinnerArray);

		spnDestino = (Spinner)findViewById(R.id.spnDestino);
		
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnDestino.setAdapter(adapter);
	}
	public void mostrar()
	{
		
	    datasource = new DatabaseDataSource(this);
	    datasource.open();
	    List<HojaDespacho> despachos = datasource.getAllDespacho();
	    
		for(int i =0;i < despachos.size(); i++){
	    	String name = null;
	    	String ficha = null;
	    	String pais = null;
	    	String fecha =null;
	    	String fi = null;
	    	String ff =null;
	    	String departamento = null;
	    	name = despachos.get(i).getValidadoPor();
	    	int ntarima = 0;
	    	ntarima = despachos.get(i).getNtarimas();
	    	ficha = despachos.get(i).getFichaEmpleado();
	    	pais = despachos.get(i).getPais();
	    	fecha = despachos.get(i).getFecha();
	    	fi = despachos.get(i).getHoraInicio();
	    	ff = despachos.get(i).getHoraFinal();
	    	departamento = despachos.get(i).getDespacho();
	    	Toast.makeText(getApplicationContext(), "name: "+ name +" ficha: "+ficha+" pais: "
	    			+pais+" fecha: "+fecha+" fi: "+fi+" ff:"+ff+" departamento: "+departamento+" NTARIMAS: "+ntarima,
					Toast.LENGTH_LONG).show();
	    }
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		 case R.id.btnRegresar:
			 HojaDespacho hoja = new HojaDespacho();
			 hoja.setFichaEmpleado(txtFicha.getText().toString());
			 hoja.setValidadoPor(txtNombre.getText().toString());
			 hoja.setPais(spnDestino.getSelectedItem().toString());
			// TODO Auto-generated method stub
				OloExcel myExcel = new OloExcel(hoja);
				myExcel.saveExcelFile(this,"MyExcel.xls");
		        break;
		
		}
		
		
		
		
	}
	
	private String getDatePhone()

	{

	    Calendar cal = new GregorianCalendar();

	    Date date = cal.getTime();

	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	    String formatteDate = df.format(date);

	    return formatteDate;

	}

}

package com.data;

import java.util.ArrayList;
import java.util.List;
import com.domain.*;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseDataSource {

	private SQLiteDatabase database;
	private DatabaseHelper dbHelper;
	
	private String[] allDespachoColumns = { DatabaseHelper.COLUMN_ID,
			DatabaseHelper.DESPACHO_NTARIMA , DatabaseHelper.DESPACHO_VALIDADO,
			DatabaseHelper.DESPACHO_FICHAE, DatabaseHelper.DESPACHO_PAIS,
			DatabaseHelper.DESPACHO_FECHA , DatabaseHelper.DESPACHO_HINICIO,
			DatabaseHelper.DESPACHO_HFINAL, DatabaseHelper.DESPACHO_DESPACHO};
	
	private String[] allTarimaColumns = { DatabaseHelper.COLUMN_ID,
			DatabaseHelper.TARIMA_NTARIMA , 
			DatabaseHelper.TARIMA_TORIGEN,DatabaseHelper.TARIMA_EXPEDIENTE, 
			DatabaseHelper.TARIMA_NREFERENCIA};
	
	private String[] allReferenciaColumns = { DatabaseHelper.COLUMN_ID,
			DatabaseHelper.REFERENCIA_REF, DatabaseHelper.REFERENCIA_BULTOS , 
			DatabaseHelper.REFERENCIA_EMPAQUE, DatabaseHelper.REFERENCIA_PESO, 
			DatabaseHelper.REFERENCIA_UNIDADES, DatabaseHelper.REFERENCIA_PESOTOTAL , 
			DatabaseHelper.REFERENCIA_TARIMA};
	
	public DatabaseDataSource(Context context) {
		dbHelper = new DatabaseHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}
	
	//TARIMA
	public Tarima createTarima(String tarima,String origen,String expediente, String referencia) {
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.TARIMA_NTARIMA, tarima);
		values.put(DatabaseHelper.TARIMA_TORIGEN, origen);
		values.put(DatabaseHelper.TARIMA_EXPEDIENTE, expediente);
		values.put(DatabaseHelper.TARIMA_NREFERENCIA, referencia);
		long insertId = database.insert(DatabaseHelper.TABLE_TARIMA,
				null, values);
		Cursor cursor = database.query(DatabaseHelper.TABLE_TARIMA,
				allTarimaColumns, DatabaseHelper.TARIMA_NTARIMA + " = " + insertId,
				null, null, null, null);
		cursor.moveToFirst();
		Tarima newTarima = cursorToTarima(cursor);
		cursor.close();
		return newTarima;
	}
	public long updateTarima(String tarima,String origen,String expediente) {
	     ContentValues con = new ContentValues();

	     con.put("tarima", tarima);
	     con.put("origen", origen);
	     con.put("expediente", expediente);
	     return database.update(DatabaseHelper.TABLE_TARIMA, con, DatabaseHelper.TARIMA_NTARIMA + " = " + tarima, null);
	    

	}
	public void deleteTarima(Tarima tam) {
	
		long id = tam.getId();
		System.out.println("Tarima deleted with id: " + id);
		database.delete(DatabaseHelper.TABLE_TARIMA , DatabaseHelper.COLUMN_ID
		        + " = " + id, null);
		
	}

	public List<Tarima> getAllTarimas() {
		List<Tarima> tarimas = new ArrayList<Tarima>();
		Cursor cursor = database.query(DatabaseHelper.TABLE_TARIMA,
				allTarimaColumns, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Tarima tarima = cursorToTarima(cursor);
			tarimas.add(tarima);
			cursor.moveToNext();
		}

		cursor.close();
		return tarimas;
	}

	private Tarima cursorToTarima(Cursor cursor) {
		Tarima tarima = new Tarima();
		tarima.setId(cursor.getLong(0));
		tarima.setNumeroTarima(cursor.getInt(1));
		tarima.setTarimaOrigen(cursor.getString(2));
		tarima.setExpediente(cursor.getString(3));
		tarima.setnRefetencias(cursor.getInt(4));
		return tarima;
	}
	//REFERENCIA
    public Referencia createReferencia(String ref,String bultos,String empaque,String peso,
    		String unidades,String pesoTotal,String tarimas)
	  {
		  ContentValues values = new ContentValues(); 
		  values.put(DatabaseHelper.REFERENCIA_REF, ref);
		  values.put(DatabaseHelper.REFERENCIA_BULTOS, bultos);
		  values.put(DatabaseHelper.REFERENCIA_EMPAQUE, empaque);
		  values.put(DatabaseHelper.REFERENCIA_PESO, peso);
		  values.put(DatabaseHelper.REFERENCIA_UNIDADES, unidades);
		  values.put(DatabaseHelper.REFERENCIA_PESOTOTAL, pesoTotal);
		  values.put(DatabaseHelper.REFERENCIA_TARIMA, tarimas);
		  long insertId = database.insert(DatabaseHelper.TABLE_REFERENCIA , null, values);
		  Cursor cursor = database.query(DatabaseHelper.TABLE_REFERENCIA ,
				  allReferenciaColumns,DatabaseHelper.COLUMN_ID + " = " + insertId, null,
				  null,null,null);
		  cursor.moveToFirst();
		  Referencia newReferencia = cursorToReferencia(cursor);
		  cursor.close();
		  return newReferencia;
		  
	  }
	  public void deleteReferencia(int id)
	  {
			System.out.println("Tarima deleted with id: " + id);
			database.delete(DatabaseHelper.TABLE_TARIMA,
					DatabaseHelper.TARIMA_NTARIMA + " = " + id, null);
	  }
	public long updateReferencia(String ref,String bultos,String empaque, String peso,String unidades,String pesoT) {
		     ContentValues con = new ContentValues();
		     con.put("ref", ref);
		     con.put("bultos", bultos);
		     con.put("empaque", empaque);
		     con.put("peso", peso);
		     con.put("unidadesTotales", unidades);
		     con.put("pesoTotal", pesoT);
		     return database.update(DatabaseHelper.TABLE_REFERENCIA, con, DatabaseHelper.REFERENCIA_REF + " = " + "'"+ref+"'", null);
		    

		}
	  public List<Referencia> getAllReferencias() 
	  {
		  List<Referencia> refs = new ArrayList<Referencia>(); 
		  Cursor cursor = database.query(DatabaseHelper.TABLE_REFERENCIA ,
			        allReferenciaColumns, null, null, null, null,null);
		  cursor.moveToFirst();
		  while (!cursor.isAfterLast()) {
		      Referencia ref = cursorToReferencia(cursor);
		      refs.add(ref);
		      cursor.moveToNext();
		    }

		    cursor.close();
		    return refs;
	  }
	  private Referencia cursorToReferencia(Cursor cursor) {
		    Referencia ref = new Referencia();
		    ref.setId(cursor.getLong(0));
		    ref.setRef(cursor.getString(1));
		    ref.setBultos(cursor.getInt(2));
		    ref.setEmpaque(cursor.getInt(3));
		    ref.setPeso(cursor.getInt(4));
		    ref.setUnidades(cursor.getInt(5));
		    ref.setPesototal(cursor.getInt(6));
		    ref.setNumeroTarimas(cursor.getInt(7));
		    return ref;
		  }
	
	  //DESPACHO
	
		
	    public HojaDespacho createDespacho(String nTarima,String ficha,String pais,
	    		String fecha, String hInicio, String hFinal, String validado, String despacho)
		  {
			  ContentValues values = new ContentValues(); 
			  values.put(DatabaseHelper.DESPACHO_NTARIMA, nTarima);
			  values.put(DatabaseHelper.DESPACHO_VALIDADO, validado);
			  values.put(DatabaseHelper.DESPACHO_FICHAE, ficha);
			  values.put(DatabaseHelper.DESPACHO_PAIS, pais);
			  values.put(DatabaseHelper.DESPACHO_FECHA, fecha);
			  values.put(DatabaseHelper.DESPACHO_HINICIO, hInicio);
			  values.put(DatabaseHelper.DESPACHO_HFINAL, hFinal);
			  values.put(DatabaseHelper.DESPACHO_DESPACHO, despacho);
			  long insertId = database.insert(DatabaseHelper.TABLE_DESPACHO , null, values);
			  Cursor cursor = database.query(DatabaseHelper.TABLE_DESPACHO ,
					  allDespachoColumns,DatabaseHelper.COLUMN_ID + " = " + insertId, null,
					  null,null,null);
			  cursor.moveToFirst();
			  HojaDespacho newDespacho = cursorToHojaDespacho(cursor);
			  cursor.close();
			  return newDespacho;
			  
		  }
		  public void deleteHojaDespacho(HojaDespacho despacho)
		  {
			long id = despacho.getId();
			System.out.println("Despacho deleted with id: " + id);
			database.delete(DatabaseHelper.TABLE_DESPACHO , DatabaseHelper.COLUMN_ID
			        + " = " + id, null);
		  }
		  public List<HojaDespacho> getAllDespacho() 
		  {
			  List<HojaDespacho> despachos = new ArrayList<HojaDespacho>(); 
			  Cursor cursor = database.query(DatabaseHelper.TABLE_DESPACHO ,
				        allDespachoColumns, null, null, null, null,null);
			  cursor.moveToFirst();
			  while (!cursor.isAfterLast()) {
				  HojaDespacho despacho = cursorToHojaDespacho(cursor);
				  despachos.add(despacho);
			      cursor.moveToNext();
			    }
			    // make sure to close the cursor
			    cursor.close();
			    return despachos;
		  }
		  private HojaDespacho cursorToHojaDespacho(Cursor cursor) {
			  	HojaDespacho des = new HojaDespacho();
			    des.setId(cursor.getLong(0));
			    des.setNtarimas(cursor.getInt(1));
			    des.setValidadoPor(cursor.getString(2));
			    des.setFichaEmpleado(cursor.getString(3)); 
			    des.setPais(cursor.getString(4));
			    des.setFecha(cursor.getString(5));
			    des.setHoraInicio(cursor.getString(6));
			    des.setHoraFinal(cursor.getString(7));
			    des.setDespacho(cursor.getString(8));
			    return des;
			  }
	
}

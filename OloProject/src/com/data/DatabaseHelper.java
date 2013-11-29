package com.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
	// Logcat tag


	private static final String DATABASE_NAME = "oloDB";
	private static final int DATABASE_VERSION = 1;

	// Table Names
	public static final String TABLE_DESPACHO = "despacho";
	public static final String TABLE_TARIMA = "tarima";
	public static final String TABLE_REFERENCIA = "referencia";
	// Common column names ARTIST AND GENRE
	public static final String COLUMN_ID = "_id";
	// DESPACHO column names
	public static final String DESPACHO_NTARIMA = "tarimas";
	public static final String DESPACHO_VALIDADO = "validado";
	public static final String DESPACHO_FICHAE = "ficha";
	public static final String DESPACHO_PAIS = "pais";
	public static final String DESPACHO_FECHA = "fecha";
	public static final String DESPACHO_HINICIO = "inicio";
	public static final String DESPACHO_HFINAL = "final";
	public static final String DESPACHO_DESPACHO = "despacho";
	// TARIMA column names
	public static final String TARIMA_NTARIMA = "tarima";
	public static final String TARIMA_TORIGEN = "origen";
	public static final String TARIMA_EXPEDIENTE = "expediente";
	public static final String TARIMA_NREFERENCIA = "referencias";
	// REFERENCIA column names
	public static final String REFERENCIA_REF = "ref";
	public static final String REFERENCIA_BULTOS = "bultos";
	public static final String REFERENCIA_EMPAQUE = "empaque";
	public static final String REFERENCIA_PESO = "peso";
	public static final String REFERENCIA_UNIDADES = "unidadesTotales";
	public static final String REFERENCIA_PESOTOTAL = "pesoTotal";
	public static final String REFERENCIA_TARIMA = "numTarima";


	private static final String CREATE_TABLE_TARIMA = "create table "
			+ TABLE_TARIMA+ "(" + COLUMN_ID
			+ " integer primary key autoincrement, " + TARIMA_NTARIMA + " interger, "  
			+ TARIMA_TORIGEN + " interger, " + TARIMA_EXPEDIENTE + " text, " +
			TARIMA_NREFERENCIA + " interger);";
			
	private static final String CREATE_TABLE_REFERENCIA = "create table "
			+ TABLE_REFERENCIA+ "(" + COLUMN_ID 
			+ " integer primary key autoincrement, "+ REFERENCIA_REF + " text, "
		    + REFERENCIA_BULTOS + " text, "+ REFERENCIA_EMPAQUE 
		    + " text, "  + REFERENCIA_PESO + " text, "+ REFERENCIA_UNIDADES 
		    + " text, "  + REFERENCIA_PESOTOTAL + " text, "
			+ REFERENCIA_TARIMA + " text);";


	private static final String CREATE_TABLE_DESPACHO = "create table "
			+ TABLE_DESPACHO + "(" + COLUMN_ID
			+ " integer primary key autoincrement, " + DESPACHO_NTARIMA
			+ " interger, " + DESPACHO_VALIDADO + " text not null, " 
			+ DESPACHO_FICHAE + " interger, "  + DESPACHO_PAIS + " text not null, "
			+ DESPACHO_FECHA + " text not null, "  + DESPACHO_HINICIO + " text not null, "
			+ DESPACHO_HFINAL + " text not null, " + DESPACHO_DESPACHO + " text not null);";

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		// TODO Auto-generated method stub
		database.execSQL(CREATE_TABLE_TARIMA);
		database.execSQL(CREATE_TABLE_REFERENCIA);
		database.execSQL(CREATE_TABLE_DESPACHO);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		Log.w(DatabaseHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_DESPACHO);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_REFERENCIA);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_TARIMA);
		onCreate(db);
	}

}

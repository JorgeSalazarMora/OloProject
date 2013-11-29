package com.example.oloproject;

import com.example.oloproject.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	Button btnHojaDespacho;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnHojaDespacho = (Button) findViewById(R.id.btnHojaDespacho);
		
	btnHojaDespacho.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(MainActivity.this,
							HojaDespachos.class);
					startActivity(intent);
					}
				
			});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	   @Override
	    public void finish() {
	        super.finish();
	        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
	    }

}

package com.example.oloproject;

import com.example.oloproject.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MotionEvent;
import android.widget.ImageButton;
import android.view.GestureDetector.SimpleOnGestureListener;

public class HomeActivity extends Activity {
	//private SimpleGestureFilter detector; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		
		//detector = new SimpleGestureFilter(this,this);
		
	}

	  


	public boolean onTouchEvent(MotionEvent event) {
	    int eventaction = event.getAction();

	    switch (eventaction) {
	    case MotionEvent.ACTION_DOWN: 
	    	Intent intent = new Intent(HomeActivity.this,
					MainActivity.class);
			   Bundle translateBundle =
                        ActivityOptions.makeCustomAnimation(HomeActivity.this,
                        R.anim.slide_in_left, R.anim.slide_out_left).toBundle();
                startActivity(intent , translateBundle);
            break;

        case MotionEvent.ACTION_MOVE:
        	
            break;

        case MotionEvent.ACTION_UP:   
            // finger leaves the screen
            break;
	    
	        

	   
	    }

	    // tell the system that we handled the event and no further processing is required
	    return true; 
	}

	

	}
	

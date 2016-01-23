package com.technotronics.priceconverter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ShippingCalc extends AppCompatActivity{

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shipping);
		
		Button ShipRes = (Button) findViewById(R.id.shipCalc);
		ShipRes.setOnClickListener(Calculater);
		
	}
	
	private OnClickListener Calculater = new OnClickListener()
	{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			InputMethodManager inputManager = (InputMethodManager)
            getSystemService(Context.INPUT_METHOD_SERVICE); 
			inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
			
			EditText Weight = (EditText) findViewById(R.id.Weight);
			 String Wei = Weight.getText().toString();
			   if(Wei.matches(""))
			     {
				   Wei = "0";
			     }
			  float W = Float.parseFloat(Wei); 
			  	W *= 1000;
			  	W /= 500;
			
			Integer Parts = (int) W;
			 if(W > Parts)
			 {
				 Parts++;
			 }
			 
			TextView Result = (TextView) findViewById(R.id.shipping);
			 Result.setText(Parts.toString());
			 
			 
		}
		
	};
}

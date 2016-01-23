package com.technotronics.priceconverter;

import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

	//Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
	
   @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TextView Shipping = (TextView) findViewById(R.id.shipping_text);
		Shipping.setOnClickListener(ShipCalc);
		
		Button Convert = (Button) findViewById(R.id.convert);
		Convert.setOnClickListener(Converter);
		
		ImageView rA = (ImageView) findViewById(R.id.rAView);
		rA.setOnClickListener(ImageClick);
		
		Button List = (Button) findViewById(R.id.list);
		List.setOnClickListener(PDF);
	}
	private OnClickListener ShipCalc = new OnClickListener()
	{
		public void onClick(View v)
		{
			Intent i = new Intent(getApplicationContext(), ShippingCalc.class);
			startActivity(i);
		}
	};
	private OnClickListener Converter = new OnClickListener()
	{
		public void onClick(View v)
		{
			try{
		 EditText Base = (EditText) findViewById(R.id.base);
		  String B = Base.getText().toString();
		   if(B.matches(""))
		     {
			   B = "0";
		     } 
		 float numBase = Float.parseFloat(B);
		   
		  
		 EditText vAT = (EditText) findViewById(R.id.vat);
		  String V = vAT.getText().toString();
		   if(V.matches(""))
		     {
			   V = "0";
		     }
		  float VAT = Float.parseFloat(V);
		  VAT = VAT/100;
		  
		  
		 EditText margin = (EditText) findViewById(R.id.margin);
		  String M = margin.getText().toString();
		   if(M.matches(""))
		     {
			   M = "0";
		     }
		  float Margin = Float.parseFloat(M);
		  
		  
		 EditText shipping = (EditText) findViewById(R.id.shipping);
		  String S = shipping.getText().toString();
		   if(S.matches(""))
		     {
			   S = "0";
		}
		float Shipping = Float.parseFloat(S);

		TextView Result = (TextView) findViewById(R.id.result);

		float converted;

		converted = numBase*VAT;
		converted += numBase;
		converted += Shipping;
		converted += Margin;

		Result.setText(Float.toString(converted));
	}

	catch(Exception e)
		{
			Toast.makeText(getApplicationContext(), (CharSequence) e, Toast.LENGTH_SHORT);
			
		}
		}
	};
	
	private OnClickListener PDF = new OnClickListener()
	{
		public void onClick(View v)
		{
			Intent i = new Intent(getApplicationContext(), PDF.class);
			startActivity(i);
		}
		
	};
	
	private OnClickListener ImageClick = new OnClickListener()
	{
		public void onClick(View v)
		{
			//vibe.vibrate(100);
			Toast T = Toast.makeText(getApplicationContext(), "-Powered by Technotronic", Toast.LENGTH_SHORT);
			T.setGravity(Gravity.BOTTOM|Gravity.RIGHT, 0, 0);
			T.show();
		}
	};
	
	/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
   */
}

package com.technotronics.priceconverter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;


public class ShippingCalc extends AppCompatActivity{

	Float ShipCost = (float) 0;
	int BaseRate, AddRate;
	RadioButton Delhi, Zone, Metro, ROI;
	CheckBox Rural;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shipping);

		Button ShipRes = (Button) findViewById(R.id.shipCalc);
		ShipRes.setOnClickListener(Calculater);

		Delhi = (RadioButton) findViewById(R.id.city);
		Zone = (RadioButton) findViewById(R.id.zone);
		Metro = (RadioButton) findViewById(R.id.metro);
		ROI = (RadioButton) findViewById(R.id.roi);
		Rural = (CheckBox) findViewById(R.id.ruralCheck);


	}

	private OnClickListener Calculater = new OnClickListener()
	{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub

			//----KEYBOARD DOWN-------------//
			InputMethodManager inputManager = (InputMethodManager)
            getSystemService(Context.INPUT_METHOD_SERVICE); 
			//inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
			//----KEYBOARD DOWN-------------//


			//----PARTS CALCULATION-------------//
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
			//----PARTS CALCULATION-------------//

			//----REGION SELECTION-------------//
			RadioGroup Region = (RadioGroup) findViewById(R.id.regionSelect);
			Region.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(RadioGroup group, int checkedId) {

					if (checkedId == R.id.city) {
						BaseRate = 32;
						AddRate = 27;
					} else if (checkedId == R.id.zone) {
						BaseRate = 37;
						AddRate = 32;
					} else if (checkedId == R.id.metro) {
						BaseRate = 47;
						AddRate = 42;
					} else if(checkedId == R.id.roi){
						BaseRate = 52;
						AddRate = 47;
					} else if (checkedId != R.id.city && checkedId != R.id.zone && checkedId != R.id.metro && checkedId != R.id.roi){
						Toast T = Toast.makeText(getApplicationContext(), "Please Select a Region", Toast.LENGTH_SHORT);
						T.setGravity(Gravity.CENTER | Gravity.RIGHT, 0, 0);
						T.show();
					}
				}
			});
			//----REGION SELECTION-------------//

			Float Sum = (float) BaseRate;
			for(int i = 0 ; i < Parts-1 ; i++)
			{
				Sum += AddRate;
			}

			ShipCost = Sum;

			TextView Result = (TextView) findViewById(R.id.shipping);

			if(Rural.isChecked())
				ShipCost += 50;

			if(Wei == "0")
			{
				Toast T = Toast.makeText(getApplicationContext(), "Please Input Weight", Toast.LENGTH_SHORT);
				T.setGravity(Gravity.CENTER | Gravity.RIGHT, 0, 0);
				T.show();
				Result.setText("");
			}
			else {
				ShipCost += 10; //Fuel Charges
				Result.setText(ShipCost.toString());
			}
		}


	};

	@Override
	public void onBackPressed() {
		Intent intent = new Intent(getApplicationContext(), MainActivity.class);
		intent.putExtra("Shipping_Charge",Float.toString(ShipCost));
		setResult(RESULT_OK, intent);
		startActivity(intent);
	}
}

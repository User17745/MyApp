package com.technotronics.priceconverter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

	Float ShipCost;
	int BaseRate, AddRate;
	RadioButton Delhi = (RadioButton) findViewById(R.id.city);
	RadioButton Zone = (RadioButton) findViewById(R.id.zone);
	RadioButton Metro = (RadioButton) findViewById(R.id.metro);
	RadioButton ROI = (RadioButton) findViewById(R.id.roi);
	CheckBox Rural = (CheckBox) findViewById(R.id.ruralCheck);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
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
					} else {
						BaseRate = 52;
						AddRate = 47;
					}

				}
			});
			//----REGION SELECTION-------------//

			Float Sum = (float) BaseRate;
			for(int i = 0 ; i < Parts ; i++)
			{
				Sum += AddRate;
			}
			Sum += 10; //Fuel Charges

			ShipCost = Sum;

			TextView Result = (TextView) findViewById(R.id.shipping);

			if(Rural.isChecked())
				ShipCost += 50;

			Result.setText(ShipCost.toString());

		}


	};
}

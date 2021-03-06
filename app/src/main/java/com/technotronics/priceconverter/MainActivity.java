package com.technotronics.priceconverter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

	Vibrator Vibe;
	CheckBox EnableShip, ShipInclude;

	//All the basic calculation variables//
	EditText Base;
	EditText vAT;
	EditText margin;
	EditText shipping;
	TextView Result;
	//All the basic calculation variables//

   @Override
	protected void onCreate(Bundle savedInstanceState) {
	   super.onCreate(savedInstanceState);
	   setContentView(R.layout.activity_main);

	   Vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

	   //==============All the basic calculation variables===============//
	   Base = (EditText) findViewById(R.id.base);
	   vAT = (EditText) findViewById(R.id.vat);
	   margin = (EditText) findViewById(R.id.margin);
	   shipping = (EditText) findViewById(R.id.shipping);
	   Result = (TextView) findViewById(R.id.result);
	   //==============All the basic calculation variables===============//

	   /*Result.setOnClickListener(Mar);*/

	   //=================Other on screen elements like button, images, etc.======================//
	   TextView Shipping = (TextView) findViewById(R.id.shipping_text);
	   		/*Shipping.setOnClickListener(ShipCalc);*/
	   Button Convert = (Button) findViewById(R.id.convert);
	   		Convert.setOnClickListener(Converter);
	   ImageView rA = (ImageView) findViewById(R.id.rAView);
	   		rA.setOnClickListener(ImageClick);
	   		rA.setOnLongClickListener(ImageClickLong);
	   Button List = (Button) findViewById(R.id.list);
	   		List.setOnClickListener(PDF);
	   Button List2 = (Button) findViewById(R.id.list2);
	   		List2.setOnClickListener(PDF2);
	   EnableShip = (CheckBox) findViewById(R.id.enableShip);
	   		EnableShip.setOnCheckedChangeListener(ship);
	   ShipInclude = (CheckBox) findViewById(R.id.ship_include);
	   //=================Other on screen elements like button, images, etc.======================//

   }

	//=============================Shipping Toggle================================//
	private CompoundButton.OnCheckedChangeListener ship = new CompoundButton.OnCheckedChangeListener() {
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

			if(EnableShip.isChecked())
				shipping.setEnabled(true);
			else
				shipping.setEnabled(false);
		}
	};
	//=============================Shipping Toggle================================//

	//==========Shipping Cost passing from ShippingCalc Activity (Non yet working)===========//
	public void onActivityResult(int requestCode, int resultCode, Intent data) {

		EditText shipping = (EditText) findViewById(R.id.shipping);

		if (requestCode == 1) {
			if(resultCode == RESULT_OK) {
				String PassResult = data.getStringExtra("Shipping_Charge");
				shipping.setText(PassResult);
			}
		}
	}
	//==========Shipping Cost passing from ShippingCalc Activity (Non yet working)===========//

	//====================EditText to Float converter=====================//
	private float numInput(EditText intext)
	{
		String S = intext.getText().toString();
		 if(S.matches(""))
			 S = "0";
		return Float.parseFloat(S);
	}
	//====================EditText to Float converter=====================//

	private OnClickListener Converter = new OnClickListener()
	{
		public void onClick(View v)
		{

			float numBase = numInput(Base);
			float Margin = numInput(margin);
			float Shipping = numInput(shipping);
			float VAT = numInput(vAT);
				VAT = VAT/100;
			float converted = 0;

			try{
				if(ShipInclude.isChecked())
				{
					converted += numBase;
					converted += Margin;
					if (shipping.isEnabled())
						converted += Shipping;
					converted += converted * VAT;
				}
				else
				{
					converted += numBase;
					converted += Margin;
					converted += converted * VAT;
					if (shipping.isEnabled())
						converted += Shipping;
				}
				Result.setText(Float.toString(converted));
	}

	catch(Exception e)
		{
			Toast.makeText(getApplicationContext(), (CharSequence) e, Toast.LENGTH_SHORT).show();
		}
		}
	};

	/*private OnClickListener ShipCalc = new OnClickListener()
	{
		public void onClick(View v)
		{
			Intent i = new Intent(getApplicationContext(), ShippingCalc.class);
			startActivityForResult(i,1);
		}
	};*/

	/*private OnClickListener Mar = new OnClickListener() {
		@Override
		public void onClick(View v) {
			try {
				Intent i = new Intent(getApplicationContext(), MarginCalc.class);
				startActivity(i);
			}
			catch (Exception e)
			{
				Toast.makeText(getApplicationContext(), (CharSequence) e, Toast.LENGTH_SHORT).show();
			}
		}
	};*/

	private OnClickListener PDF = new OnClickListener()
	{
		public void onClick(View v)
		{
			Intent i = new Intent(getApplicationContext(), PDF.class);
			startActivity(i);
		}
		
	};

	private OnClickListener PDF2 = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent i = new Intent(getApplicationContext(), PDFSeller.class);
			startActivity(i);
		}
	};
	
	private OnClickListener ImageClick = new OnClickListener()
	{
		public void onClick(View v)
		{
			Toast T = Toast.makeText(getApplicationContext(), "-Powered by Technotronic", Toast.LENGTH_SHORT);
			T.setGravity(Gravity.BOTTOM | Gravity.END, 0, 0);
			T.show();
		}
	};

	private View.OnLongClickListener ImageClickLong = new View.OnLongClickListener() {
		@Override
		public boolean onLongClick(View v) {
			launchApp(getApplicationContext(), "com.sauravarya.toc");
			Vibe.vibrate(100);
			return false;
		}
	};

	public void launchApp(Context context, String appPackageName) {
		Intent i = context.getPackageManager().getLaunchIntentForPackage(appPackageName);
		if (i == null) {
			// Bring user to the market or let them choose an app?
			i = new Intent(Intent.ACTION_VIEW);
			i.setData(Uri.parse("market://details?id=" + appPackageName));
		}
		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(i);
	}

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
		if (id == R.id.action_reverseBilling) {
			Intent i = new Intent(getApplicationContext(), ReverseBilling.class);
			startActivity(i);
			}

		if (id == R.id.action_report) {
			Intent i = new Intent(getApplicationContext(), WeeklyReport.class);
			Calendar calendar = Calendar.getInstance();

			int Day = calendar.get(Calendar.DAY_OF_WEEK); // If current day is Sunday, day=1. Saturday, day=7.

			switch (Day) {
				case 1:
					startActivity(i);
					break;
				case 7:
					startActivity(i);
					break;
				default:
					Toast T = Toast.makeText(getApplicationContext(),"Weekly Reports can only be sent on Saturdays",Toast.LENGTH_SHORT);
					T.setGravity(Gravity.TOP, 0, 160);
					T.show();
			}
		}
		return super.onOptionsItemSelected(item);
	}

}

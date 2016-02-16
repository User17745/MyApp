package com.technotronics.priceconverter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LogIn extends AppCompatActivity{

	@Override
	protected void onCreate( Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		Button LogIn = (Button) findViewById(R.id.login);
		
		LogIn.setOnClickListener(Verify);
	}

	private OnClickListener Verify = new OnClickListener()
	{
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			EditText Password = (EditText) findViewById(R.id.pass);
			String pass = Password.getText().toString();
			Intent i = new Intent(getApplicationContext(), MainActivity.class);
			
			if(pass.equals("Techemp"))
			{
				startActivity(i);
				finish();
			}
			else
			{
				Toast.makeText(getApplicationContext(), "Wrong Password", Toast.LENGTH_SHORT).show();
			}
		}
	};
}

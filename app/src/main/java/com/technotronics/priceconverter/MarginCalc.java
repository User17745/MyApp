package com.technotronics.priceconverter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by HP Lap on 21-Jan-16.
 */
public class MarginCalc extends AppCompatActivity {

   float CP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {

            {
                setContentView(R.layout.activity_main);
                TextView Result = (TextView) findViewById(R.id.result);
                String CoP = Result.getText().toString();
                if (CoP.matches(""))
                    CoP = "0";
                CP = Float.parseFloat(CoP);
            }

            setContentView(R.layout.marginwindow);

            EditText SellingPrice = (EditText) findViewById(R.id.selling_price);
            String SeP = SellingPrice.getText().toString();
            if (SeP.matches(""))
                SeP = "0";
            float SP = Float.parseFloat(SeP);

            TextView Margin = (TextView) findViewById(R.id.margin);
            Margin.setText(Float.toString(SP - CP));


        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), (CharSequence) e, Toast.LENGTH_SHORT).show();
        }
    }

}

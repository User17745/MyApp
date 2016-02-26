package com.technotronics.priceconverter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by HP Lap on 24-Feb-16.
 */
public class ReverseBilling extends AppCompatActivity{

    EditText FinalAmount, Shipping, VAT;
    TextView ProductValue, VatValue, TotalValue;
    CheckBox ShipEnable, VatInclude;
    Button Generate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reverse_billing);

        FinalAmount = (EditText) findViewById(R.id.selling_price);
        Shipping = (EditText) findViewById(R.id.ship_edit);
        VAT = (EditText) findViewById(R.id.vat_edit);
        ProductValue = (TextView) findViewById(R.id.prod_value);
        VatValue = (TextView) findViewById(R.id.vat_value);
        TotalValue = (TextView) findViewById(R.id.total_value);
        ShipEnable = (CheckBox) findViewById(R.id.ship_check);
            ShipEnable.setOnCheckedChangeListener(ShipCheck);
        VatInclude = (CheckBox) findViewById(R.id.vat_include);
            VatInclude.setOnCheckedChangeListener(VatCheck);
        Generate = (Button) findViewById(R.id.generate);
            Generate.setOnClickListener(Gen);

    }

    private CompoundButton.OnCheckedChangeListener ShipCheck = new CompoundButton.OnCheckedChangeListener()
    {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(!ShipEnable.isChecked())
            {
                Shipping.setEnabled(false);
            }
            else
                Shipping.setEnabled(true);
        }
    };


    private CompoundButton.OnCheckedChangeListener VatCheck = new CompoundButton.OnCheckedChangeListener()
   {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(!VatInclude.isChecked())
            {
                VAT.setEnabled(false);
            }
            else
                VAT.setEnabled(true);
        }
    };

    private View.OnClickListener Gen = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            //----KEYBOARD DOWN-------------//
            InputMethodManager inputManager = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
            //----KEYBOARD DOWN-------------//

            float SellingPrice = numInput(FinalAmount);
            float ShippingCost = numInput(Shipping);
            float Vat = numInput(VAT);
                Vat /= 100;
            if(ShipEnable.isChecked())
            {
                SellingPrice -= ShippingCost;
            }
            else
                ShippingCost = 0;

            float PValue = SellingPrice/(Vat+1);
            ProductValue.setText(Float.toString(PValue));
            float VATValue = PValue*Vat;
            VatValue.setText(Float.toString(VATValue));
            TotalValue.setText( Float.toString(PValue + ShippingCost + VATValue));

        }
    };

    //====================EditText to Float converter=====================//
    private float numInput(EditText intext)
    {
        String S = intext.getText().toString();
        if(S.matches(""))
            S = "0";
        return Float.parseFloat(S);
    }
    //====================EditText to Float converter=====================//


}


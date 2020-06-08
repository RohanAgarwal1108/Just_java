package com.example.just_java;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
        /**
         * This method is called when the order button is clicked.
         */
        int quantity=0;
        boolean checking=false;
        boolean chococheck=false;
        String name="";

        public void submitOrder(View view)
        {
            int price=calculatePrice(quantity);
            String priceMessage=createOrderSummary(price);
            displayMessage(priceMessage);
        }

        public int calculatePrice(int n)
        {
            return n*5;
        }

        public void increment(View view)
        {
            if(quantity<100){
            quantity++;}
            else
            {
                Toast.makeText(this,"You cannot have more than 100 coffees",Toast.LENGTH_SHORT).show();
            }
            display(quantity);
        }

        public void decrement(View view)
        {
            if(quantity>0) {
                quantity--;
            }
            else
            {
                Toast.makeText(this,"You cannot have less than 0 coffees",Toast.LENGTH_SHORT).show();
            }
            display(quantity);
        }

        public void boo(View view)
        {
            CheckBox checkbox= (CheckBox) findViewById(R.id.checkbox_view);
            checking=checkbox.isChecked();
            CheckBox choco= findViewById(R.id.chocolate);
            chococheck=choco.isChecked();
        }

        public String createOrderSummary(int price)
        {
            EditText edit=findViewById(R.id.edittext);
            name=edit.getText().toString();
            if(checking==true)
            {
                price+=quantity;
            }
            if(chococheck==true)
            {
                price+=2*quantity;
            }
            String value="Name: "+name+"\nAdd whipped cream? "+checking+"\nAdd chocolate? "+chococheck+"\nQuantity: "+quantity+"\nTotal: $"+ price +"\nThank you!";
            return value;
        }

        /**
         * This method displays the given quantity value on the screen.
         */
        private void display(int number) {
            TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
            quantityTextView.setText("" + number);
        }

    private void displayMessage(String message)
    {
        Intent intent=new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT,"Just Java order for "+name);
        intent.putExtra(Intent.EXTRA_TEXT,message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        /*TextView ordersummaryTextView=(TextView) findViewById(R.id.order_summary_text_view);
        ordersummaryTextView.setText(message);*/
    }
    }
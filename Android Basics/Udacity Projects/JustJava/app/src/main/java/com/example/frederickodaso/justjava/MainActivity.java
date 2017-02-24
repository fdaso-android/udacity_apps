package com.example.frederickodaso.justjava;

/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 */

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText addNameField = (EditText) findViewById(R.id.add_name_field);
        String nameField = addNameField.getText().toString();
        Log.i("MainActivity.java", nameField);

        CheckBox itemWhippedCream = (CheckBox) findViewById(R.id.item_whipped_cream);
        boolean itemWhippedCreamChecked = itemWhippedCream.isChecked();

        CheckBox itemChocolate = (CheckBox) findViewById(R.id.item_chocolate);
        boolean itemChocolateChecked = itemChocolate.isChecked();

        int price = calculatePrice(quantity, itemWhippedCreamChecked, itemChocolateChecked);
        String priceMessage = createOrderSummary(nameField, price, itemWhippedCreamChecked, itemChocolateChecked);
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "JustJava order for " + nameField);
        if (emailIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(emailIntent);
        }
    }

    public String createOrderSummary(String name, int number, boolean hasWhippedCream, boolean hasChocolate) {
        String priceMessage = "Name: " + name;
        priceMessage += "\nAdd whipped cream? " + hasWhippedCream;
        priceMessage += "\nAdd chocolate? " + hasChocolate;
        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\nTotal: $" + number;
        priceMessage += "\nThank you!";
        return priceMessage;
    }

    /**
     * Calculates the price of the order.
     *
     *
     */
    private int calculatePrice(int number, boolean hasWhippedCream, boolean hasChocolate) {
        int extraToppingsCost = 0;
        if (hasWhippedCream) {
            extraToppingsCost += 1;
        }
        if (hasChocolate) {
            extraToppingsCost += 2;
        }
        return number * 5 + extraToppingsCost;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int amount) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + amount);
    }


    public void increment(View view) {
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    public void decrement(View view) {
        quantity = quantity - 1;
        displayQuantity(quantity);
    }
}

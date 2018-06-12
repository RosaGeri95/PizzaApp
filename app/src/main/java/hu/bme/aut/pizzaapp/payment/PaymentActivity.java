package hu.bme.aut.pizzaapp.payment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import hu.bme.aut.pizzaapp.R;

public class PaymentActivity extends AppCompatActivity {

    private TextView etCustomerName;
    private TextView etCustomerAddress;
    private TextView etPhone;
    private TextView etEmail;
    private TextView tvTotalCost;
    private Button btnPay;

    public static final String KEY_TOTAL_COST = "TOTAL_COST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        etCustomerName = (TextView) findViewById(R.id.etCustomerName);
        etCustomerAddress = (TextView) findViewById(R.id.etCustomerAddress);
        etPhone = (TextView) findViewById(R.id.etPhone);
        etEmail = (TextView) findViewById(R.id.etEmail);
        tvTotalCost = (TextView) findViewById(R.id.tvTotalCost);
        btnPay = (Button) findViewById(R.id.btnPay);

        SharedPreferences myPrefs = PreferenceManager.getDefaultSharedPreferences(
                getApplicationContext());

        final String customerName = myPrefs.getString("name", "");
        final String customerAddress = myPrefs.getString("address", "");
        final String phone = myPrefs.getString("phone", "");
        final String email = myPrefs.getString("email", "");

        etCustomerName.setText(customerName);
        etCustomerAddress.setText(customerAddress);
        etPhone.setText(phone);
        etEmail.setText(email);

        Intent intent = getIntent();
        final String totalCost = intent.getStringExtra(KEY_TOTAL_COST);
        tvTotalCost.setText(totalCost);

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String finalCustomerName = etCustomerName.getText().toString();
                String finalCustomerAddress = etCustomerAddress.getText().toString();
                String finalPhone = etPhone.getText().toString();
                String finalEmail = etEmail.getText().toString();

                if (finalCustomerName.trim().equals("")) {
                    Toast.makeText(getApplication(), "Name field cannot be empty!", Toast.LENGTH_LONG).show();
                    return;
                } else if (finalCustomerAddress.trim().equals("")) {
                    Toast.makeText(getApplication(), "Address field cannot be empty!", Toast.LENGTH_LONG).show();
                    return;
                } else if (finalPhone.trim().equals("") && finalEmail.trim().equals("")) {
                    Toast.makeText(getApplication(), "At least one way of contact is required!", Toast.LENGTH_LONG).show();
                    return;
                }

                Intent i = new Intent();
                setResult(RESULT_OK, i);
                finish();
            }
        });
    }
}

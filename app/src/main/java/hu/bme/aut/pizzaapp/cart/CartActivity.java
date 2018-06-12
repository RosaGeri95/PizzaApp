package hu.bme.aut.pizzaapp.cart;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import hu.bme.aut.pizzaapp.favourites.FavouritesActivity;
import hu.bme.aut.pizzaapp.payment.PaymentActivity;
import hu.bme.aut.pizzaapp.R;
import hu.bme.aut.pizzaapp.interfaces.INewPizzaItemListener;
import hu.bme.aut.pizzaapp.interfaces.IPriceListener;
import hu.bme.aut.pizzaapp.model.Pizza;
import hu.bme.aut.pizzaapp.settings.SettingsActivity;

import static hu.bme.aut.pizzaapp.payment.PaymentActivity.KEY_TOTAL_COST;

public class CartActivity extends AppCompatActivity implements INewPizzaItemListener, IPriceListener {

    public static final int OPEN_FAVOURITES = 1;
    public static final int OPEN_PAYMENT = 2;

    private RecyclerView recyclerView;
    private CartAdapter adapter;

    private TextView tvCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        adapter = new CartAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        tvCost = (TextView) findViewById(R.id.tvCost);
        tvCost.setText("0");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new NewPizzaDialogFragment().show(getSupportFragmentManager(), "NewPizzaDialog");
            }
        });

        Button btn = (Button) findViewById(R.id.btnCheckOut);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CartActivity.this, PaymentActivity.class);
                i.putExtra(KEY_TOTAL_COST, tvCost.getText().toString());
                startActivityForResult(i, OPEN_PAYMENT);
            }
        });

        if (savedInstanceState != null) {
            ArrayList<Pizza> restored = (ArrayList<Pizza>) savedInstanceState.getSerializable("cart_list");
            for (Pizza p : restored) {
                adapter.addItem(p);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cart, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent i = new Intent(CartActivity.this, SettingsActivity.class);
            startActivity(i);
            return true;
        } else if (id == R.id.action_favourites) {
            Intent i = new Intent(CartActivity.this, FavouritesActivity.class);
            startActivityForResult(i, OPEN_FAVOURITES);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPizzaCreated(Pizza newItem) {
        adapter.addItem(newItem);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == OPEN_FAVOURITES) {
            if (resultCode == RESULT_OK) {
                Bundle bundle = data.getExtras();
                ArrayList<Pizza> items = (ArrayList<Pizza>) bundle.getSerializable("pizza");

                if (items != null) {
                    for (Pizza p : items) {
                        adapter.addItem(p);
                    }
                }
            }
        } else if (requestCode == OPEN_PAYMENT) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, tvCost.getText() + " Forints have been paid!", Toast.LENGTH_LONG).show();
                adapter.clear();
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle state) {
        state.putSerializable("cart_list", adapter.getPizzas());
        super.onSaveInstanceState(state);
    }

    @Override
    public void onPriceChanged() {
        tvCost.setText(String.valueOf(adapter.getTotalCost()));
    }
}
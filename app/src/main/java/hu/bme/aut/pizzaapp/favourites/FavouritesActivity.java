package hu.bme.aut.pizzaapp.favourites;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import hu.bme.aut.pizzaapp.R;
import hu.bme.aut.pizzaapp.model.Pizza;

public class FavouritesActivity extends AppCompatActivity {
    RecyclerView favouritesRV;
    FavouritesAdapter favouritesAdapter;

    Button btnCancel;
    Button btnSave;

    public FavouritesActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        favouritesRV = (RecyclerView) findViewById(R.id.favourites_recycler);
        favouritesAdapter = new FavouritesAdapter(this);
        favouritesRV.setLayoutManager(new LinearLayoutManager(this));
        favouritesRV.setAdapter(favouritesAdapter);

        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnSave = (Button) findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Pizza> result = favouritesAdapter.getSelected();
                Bundle bundle = new Bundle();
                bundle.putSerializable("pizza", result);

                Intent intent = new Intent();
                intent.putExtras(bundle);

                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(RESULT_CANCELED, intent);
                finish();
            }
        });

        loadItemsInBackground();
    }


    private void loadItemsInBackground() {
        new AsyncTask<Void, Void, List<Pizza>>() {

            @Override
            protected List<Pizza> doInBackground(Void... voids) {
                return Pizza.listAll(Pizza.class);
            }

            @Override
            protected void onPostExecute(List<Pizza> pizzaItems) {
                super.onPostExecute(pizzaItems);

                for (Pizza p : pizzaItems) {
                    favouritesAdapter.addItem(p);
                }

            }
        }.execute();
    }
}

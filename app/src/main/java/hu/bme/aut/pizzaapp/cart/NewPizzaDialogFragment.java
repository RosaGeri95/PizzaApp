package hu.bme.aut.pizzaapp.cart;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import hu.bme.aut.pizzaapp.R;
import hu.bme.aut.pizzaapp.interfaces.INewPizzaItemListener;
import hu.bme.aut.pizzaapp.model.Pizza;


public class NewPizzaDialogFragment extends AppCompatDialogFragment {
    private EditText etName;
    private Spinner spinner;
    private CheckBox cbHam;
    private CheckBox cbSausage;
    private CheckBox cbMushroom;
    private CheckBox cbCorn;
    private CheckBox cbPineapple;
    private CheckBox cbOnion;

    private CheckBox cbSave;

    private INewPizzaItemListener listener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentActivity activity = getActivity();
        if (activity instanceof INewPizzaItemListener) {
            listener = (INewPizzaItemListener) activity;
        } else {
            throw new RuntimeException("Activity must implement the INewPizzaItemListener interface!");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getContext())
                .setTitle("Order new pizza")
                .setView(getContentView())
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Pizza pizza = getPizzaFromDialog();
                        listener.onPizzaCreated(pizza);

                        if (cbSave.isChecked()) {
                            pizza.save();
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, null)
                .create();
    }

    private Pizza getPizzaFromDialog() {
        Pizza pizza = new Pizza();
        String size = spinner.getSelectedItem().toString();
        int price = 0;

        pizza.setName(etName.getText().toString());
        pizza.setSize(Pizza.Size.valueOf(size));

        switch (pizza.getSize()) {
            case small:
                price += 800;
                break;
            case medium:
                price += 1000;
                break;
            case large:
                price += 1200;
                break;
            default:
                price += 1000;
        }

        if (cbHam.isChecked()) {
            pizza.setHam(true);
            price += 300;
        } else {
            pizza.setHam(false);
        }

        if (cbSausage.isChecked()) {
            pizza.setSausage(true);
            price += 300;
        } else {
            pizza.setSausage(false);
        }

        if (cbMushroom.isChecked()) {
            pizza.setMushroom(true);
            price += 150;
        } else {
            pizza.setMushroom(false);
        }

        if (cbCorn.isChecked()) {
            pizza.setCorn(true);
            price += 150;
        } else {
            pizza.setCorn(false);
        }

        if (cbPineapple.isChecked()) {
            pizza.setPineapple(true);
            price += 100;
        } else {
            pizza.setPineapple(false);
        }

        if (cbOnion.isChecked()) {
            pizza.setOnion(true);
            price += 100;
        } else {
            pizza.setOnion(false);
        }

        pizza.setPrice(price);

        return pizza;
    }

    private View getContentView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_new_pizza_dialog, null);

        etName = view.findViewById(R.id.etName);
        cbHam = view.findViewById(R.id.cbHam);
        cbSausage = view.findViewById(R.id.cbSausage);
        cbMushroom = view.findViewById(R.id.cbMushroom);
        cbCorn = view.findViewById(R.id.cbCorn);
        cbPineapple = view.findViewById(R.id.cbPineapple);
        cbOnion = view.findViewById(R.id.cbOnion);

        spinner = view.findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.sizes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        cbSave = view.findViewById(R.id.cbSave);

        return view;
    }

}

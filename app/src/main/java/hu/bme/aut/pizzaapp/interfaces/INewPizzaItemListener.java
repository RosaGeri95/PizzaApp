package hu.bme.aut.pizzaapp.interfaces;


import hu.bme.aut.pizzaapp.model.Pizza;

public interface INewPizzaItemListener {
    void onPizzaCreated(Pizza newItem);
}

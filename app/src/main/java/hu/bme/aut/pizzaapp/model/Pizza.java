package hu.bme.aut.pizzaapp.model;

import com.orm.SugarRecord;

import java.io.Serializable;

public class Pizza extends SugarRecord implements Serializable{

    public enum Size { small, medium, large }

    private String name;
    private Size size;
    private int price;
    private boolean ham;
    private boolean sausage;
    private boolean mushroom;
    private boolean corn;
    private boolean pineapple;
    private boolean onion;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean hasHam() {
        return ham;
    }

    public void setHam(boolean ham) {
        this.ham = ham;
    }

    public boolean hasSausage() {
        return sausage;
    }

    public void setSausage(boolean sausage) {
        this.sausage = sausage;
    }

    public boolean hasMushroom() {
        return mushroom;
    }

    public void setMushroom(boolean mushroom) {
        this.mushroom = mushroom;
    }

    public boolean hasCorn() {
        return corn;
    }

    public void setCorn(boolean corn) {
        this.corn = corn;
    }

    public boolean hasPineapple() {
        return pineapple;
    }

    public void setPineapple(boolean pineapple) {
        this.pineapple = pineapple;
    }

    public boolean hasOnion() {
        return onion;
    }

    public void setOnion(boolean onion) {
        this.onion = onion;
    }
}

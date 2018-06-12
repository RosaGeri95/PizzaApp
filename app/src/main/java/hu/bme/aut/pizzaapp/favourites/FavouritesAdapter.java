package hu.bme.aut.pizzaapp.favourites;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import hu.bme.aut.pizzaapp.R;
import hu.bme.aut.pizzaapp.model.Pizza;


public class FavouritesAdapter extends RecyclerView.Adapter<FavouritesAdapter.FavouritesViewHolder> {
    private List<Pizza> favourites;
    private ArrayList<Pizza> selected;

    private Context context;

    public FavouritesAdapter(Context context) {
        favourites = new ArrayList<>();
        selected = new ArrayList<>();

        this.context = context;

        initialize();
    }


    @Override
    public FavouritesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.favourites_list_item, parent, false);
        FavouritesViewHolder viewHolder = new FavouritesViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final FavouritesViewHolder holder, int position) {
        final Pizza pizza = favourites.get(position);

        holder.tvFavouriteName.setText(pizza.getName());
        holder.tvFavouritePrice.setText(String.valueOf(pizza.getPrice()));


        if(!holder.ibDelete.isEnabled()){
            holder.ibDelete.setEnabled(true);
        }
        /* need to disable delete button, so it wont try to delete
        the same item if the button is clicked twice too fast */
        holder.ibDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.ibDelete.setEnabled(false);
                deleteItem(pizza);
            }
        });

        holder.ibSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected.add(pizza);
                if (selected.size() == 1) {
                    Toast.makeText(context, String.valueOf(selected.size()) + " item selected", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, String.valueOf(selected.size()) + " items selected", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return favourites.size();
    }

    public void addItem(Pizza item) {
        favourites.add(item);
        notifyItemInserted(favourites.size() - 1);
    }

    public void deleteItem(Pizza item) {
        int position = favourites.indexOf(item);
        favourites.remove(position);
        item.delete();
        notifyItemRemoved(position);
    }


    public class FavouritesViewHolder extends RecyclerView.ViewHolder {

        TextView tvFavouriteName;
        TextView tvFavouritePrice;
        ImageButton ibDelete;
        ImageButton ibSelect;

        public FavouritesViewHolder(View itemView) {
            super(itemView);

            tvFavouriteName = itemView.findViewById(R.id.tvFavouriteName);
            tvFavouritePrice = itemView.findViewById(R.id.tvFavouritePrice);
            ibDelete = itemView.findViewById(R.id.ibDelete);
            ibSelect = itemView.findViewById(R.id.ibSelect);
        }
    }


    public ArrayList<Pizza> getSelected() {
        return selected;
    }


    private void initialize() {
        boolean foundHawaiiPizza = false;
        boolean foundMushroomPizza = false;
        boolean foundSausagePizza = false;
        boolean foundVegetarianPizza = false;

        for (Pizza p : favourites) {
            if (p.getName().equals("Hawaii")) {
                foundHawaiiPizza = true;
            } else if (p.getName().equals("Mushroom")) {
                foundMushroomPizza = true;
            } else if (p.getName().equals("Sausage")) {
                foundSausagePizza = true;
            } else if (p.getName().equals("Vegetarian")) {
                foundVegetarianPizza = true;
            }
        }

        if (!foundVegetarianPizza) {
            Pizza pizza = new Pizza();
            pizza.setName("Vegetarian");
            pizza.setPrice(1400);
            pizza.setSize(Pizza.Size.medium);
            pizza.setHam(false);
            pizza.setSausage(false);
            pizza.setMushroom(true);
            pizza.setCorn(true);
            pizza.setPineapple(false);
            pizza.setOnion(true);
            addItem(pizza);
        }

        if (!foundSausagePizza) {
            Pizza pizza = new Pizza();
            pizza.setName("Sausage");
            pizza.setPrice(1550);
            pizza.setSize(Pizza.Size.medium);
            pizza.setHam(false);
            pizza.setSausage(true);
            pizza.setMushroom(false);
            pizza.setCorn(true);
            pizza.setPineapple(false);
            pizza.setOnion(true);
            addItem(pizza);
        }

        if (!foundMushroomPizza) {
            Pizza pizza = new Pizza();
            pizza.setName("Mushroom");
            pizza.setPrice(1250);
            pizza.setSize(Pizza.Size.medium);
            pizza.setHam(false);
            pizza.setSausage(false);
            pizza.setMushroom(true);
            pizza.setCorn(false);
            pizza.setPineapple(false);
            pizza.setOnion(true);
            addItem(pizza);
        }

        if (!foundHawaiiPizza) {
            Pizza pizza = new Pizza();
            pizza.setName("Hawaii");
            pizza.setPrice(1350);
            pizza.setSize(Pizza.Size.medium);
            pizza.setHam(true);
            pizza.setSausage(false);
            pizza.setMushroom(false);
            pizza.setCorn(true);
            pizza.setPineapple(true);
            pizza.setOnion(false);
            addItem(pizza);
        }
    }
}

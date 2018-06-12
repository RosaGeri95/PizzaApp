package hu.bme.aut.pizzaapp.cart;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import hu.bme.aut.pizzaapp.R;
import hu.bme.aut.pizzaapp.interfaces.IPriceListener;
import hu.bme.aut.pizzaapp.model.Pizza;


public class CartAdapter extends RecyclerView.Adapter<CartAdapter.PizzaItemViewHolder> {

    private ArrayList<Pizza> pizzas;
    private int totalCost;

    private IPriceListener listener;

    public CartAdapter(IPriceListener listener) {
        pizzas = new ArrayList<>();
        totalCost = 0;

        this.listener = listener;
    }

    @Override
    public PizzaItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pizza_list_item, parent, false);
        return new PizzaItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final PizzaItemViewHolder holder, int position) {
        final Pizza pizza = pizzas.get(position);

        holder.tvName.setText(pizza.getName());
        holder.tvSize.setText(pizza.getSize().toString());
        holder.tvPrice.setText(String.valueOf(pizza.getPrice()));


        if (pizza.hasHam()) {
            holder.ivHam.setVisibility(View.VISIBLE);
        } else {
            holder.ivHam.setVisibility(View.INVISIBLE);
        }


        if (pizza.hasSausage()) {
            holder.ivSausage.setVisibility(View.VISIBLE);
        } else {
            holder.ivSausage.setVisibility(View.INVISIBLE);
        }


        if (pizza.hasMushroom()) {
            holder.ivMushroom.setVisibility(View.VISIBLE);
        } else {
            holder.ivMushroom.setVisibility(View.INVISIBLE);
        }


        if (pizza.hasCorn()) {
            holder.ivCorn.setVisibility(View.VISIBLE);
        } else {
            holder.ivCorn.setVisibility(View.INVISIBLE);
        }


        if (pizza.hasPineapple()) {
            holder.ivPineapple.setVisibility(View.VISIBLE);
        } else {
            holder.ivPineapple.setVisibility(View.INVISIBLE);
        }


        if (pizza.hasOnion()) {
            holder.ivOnion.setVisibility(View.VISIBLE);
        } else {
            holder.ivOnion.setVisibility(View.INVISIBLE);
        }


        if(!holder.ibRemove.isEnabled()){
            holder.ibRemove.setEnabled(true);
        }

        /* need to disable remove button, so it wont try to delete
        the same item if the button is clicked twice too fast */
        holder.ibRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.ibRemove.setEnabled(false);
                deleteItem(pizza);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pizzas.size();
    }

    public void addItem(Pizza item) {
        totalCost += item.getPrice();
        pizzas.add(0, item);

        notifyItemInserted(0);

        listener.onPriceChanged();
    }

    public void deleteItem(Pizza item) {
        totalCost -= item.getPrice();

        int idx = pizzas.indexOf(item);

        pizzas.remove(idx);
        notifyItemRemoved(idx);

        listener.onPriceChanged();
    }

    public void clear() {
        pizzas.clear();
        totalCost = 0;

        notifyDataSetChanged();

        listener.onPriceChanged();
    }

    public class PizzaItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvSize;
        TextView tvPrice;
        ImageView ivHam;
        ImageView ivSausage;
        ImageView ivMushroom;
        ImageView ivCorn;
        ImageView ivPineapple;
        ImageView ivOnion;
        ImageButton ibRemove;

        public PizzaItemViewHolder(View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvSize = itemView.findViewById(R.id.tvSize);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            ivHam = itemView.findViewById(R.id.ivHam);
            ivSausage = itemView.findViewById(R.id.ivSausage);
            ivMushroom = itemView.findViewById(R.id.ivMushroom);
            ivCorn = itemView.findViewById(R.id.ivCorn);
            ivPineapple = itemView.findViewById(R.id.ivPineapple);
            ivOnion = itemView.findViewById(R.id.ivOnion);
            ibRemove = itemView.findViewById(R.id.ibRemove);
        }
    }

    public int getTotalCost() {
        return totalCost;
    }

    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }
}

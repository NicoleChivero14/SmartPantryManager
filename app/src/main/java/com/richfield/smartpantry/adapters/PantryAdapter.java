package com.richfield.smartpantry.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.richfield.smartpantry.R;
import com.richfield.smartpantry.models.PantryItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Custom RecyclerView adapter that binds pantry items to list rows
 * and forwards edit/delete taps to the fragment.
 */
public class PantryAdapter extends RecyclerView.Adapter<PantryAdapter.PantryViewHolder> {

    public interface PantryListener {
        void onEdit(PantryItem item);

        void onDelete(PantryItem item);
    }

    private final List<PantryItem> items = new ArrayList<>();
    private final PantryListener listener;

    public PantryAdapter(PantryListener listener) {
        this.listener = listener;
    }

    /** Replace the current list and refresh the RecyclerView. */
    public void setItems(List<PantryItem> newItems) {
        items.clear();
        items.addAll(newItems);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PantryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pantry, parent, false);
        return new PantryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PantryViewHolder holder, int position) {
        PantryItem item = items.get(position);
        holder.nameText.setText(item.getName());
        holder.quantityText.setText(String.format("%s %s", item.getQuantity(), item.getUnit()));

        if (item.getExpiryDate() != null && !item.getExpiryDate().isEmpty()) {
            holder.expiryText.setVisibility(View.VISIBLE);
            holder.expiryText.setText(holder.itemView.getContext()
                    .getString(R.string.expires_on, item.getExpiryDate()));
        } else {
            holder.expiryText.setVisibility(View.GONE);
        }

        holder.editButton.setOnClickListener(v -> listener.onEdit(item));
        holder.deleteButton.setOnClickListener(v -> listener.onDelete(item));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class PantryViewHolder extends RecyclerView.ViewHolder {
        final TextView nameText;
        final TextView quantityText;
        final TextView expiryText;
        final ImageButton editButton;
        final ImageButton deleteButton;

        PantryViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.textPantryName);
            quantityText = itemView.findViewById(R.id.textPantryQuantity);
            expiryText = itemView.findViewById(R.id.textPantryExpiry);
            editButton = itemView.findViewById(R.id.buttonEdit);
            deleteButton = itemView.findViewById(R.id.buttonDelete);
        }
    }
}

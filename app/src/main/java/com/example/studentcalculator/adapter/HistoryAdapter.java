package com.example.studentcalculator.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.example.studentcalculator.database.HistoryEntity;
import com.example.studentcalculator.databinding.ItemHistoryBinding;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class HistoryAdapter extends ListAdapter<HistoryEntity, HistoryAdapter.HistoryViewHolder> {

    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(HistoryEntity history);
        void onDeleteClick(HistoryEntity history);
    }

    public HistoryAdapter(OnItemClickListener listener) {
        super(new DiffUtil.ItemCallback<HistoryEntity>() {
            @Override
            public boolean areItemsTheSame(@NonNull HistoryEntity oldItem, @NonNull HistoryEntity newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull HistoryEntity oldItem, @NonNull HistoryEntity newItem) {
                return oldItem.getExpression().equals(newItem.getExpression()) &&
                        oldItem.getResult().equals(newItem.getResult()) &&
                        oldItem.getType().equals(newItem.getType());
            }
        });
        this.listener = listener;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHistoryBinding binding = ItemHistoryBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new HistoryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        holder.bind(getItem(position), listener);
    }

    static class HistoryViewHolder extends RecyclerView.ViewHolder {
        private final ItemHistoryBinding binding;
        private final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm - dd/MM/yyyy", Locale.getDefault());

        public HistoryViewHolder(ItemHistoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(HistoryEntity history, OnItemClickListener listener) {
            binding.tvItemExpression.setText(history.getExpression());
            
            // Nếu là máy tính thông thường thì thêm dấu "=", các công cụ khác (PT, Số học) thì không
            if ("Calculator".equals(history.getType())) {
                binding.tvItemResult.setText("= " + history.getResult());
            } else {
                binding.tvItemResult.setText(history.getResult());
            }

            binding.tvItemTime.setText(dateFormat.format(new Date(history.getCreatedAt())));

            binding.getRoot().setOnClickListener(v -> listener.onItemClick(history));
            binding.getRoot().setOnLongClickListener(v -> {
                listener.onDeleteClick(history);
                return true;
            });
        }
    }
}
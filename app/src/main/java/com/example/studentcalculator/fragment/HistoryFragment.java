package com.example.studentcalculator.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.studentcalculator.adapter.HistoryAdapter;
import com.example.studentcalculator.database.HistoryEntity;
import com.example.studentcalculator.databinding.FragmentHistoryBinding;
import com.example.studentcalculator.viewmodel.HistoryViewModel;

public class HistoryFragment extends Fragment implements HistoryAdapter.OnItemClickListener {

    private FragmentHistoryBinding binding;
    private HistoryViewModel viewModel;
    private HistoryAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHistoryBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(HistoryViewModel.class);
        
        setupRecyclerView();
        setupObservers();
        setupClickListeners();
    }

    private void setupRecyclerView() {
        adapter = new HistoryAdapter(this);
        binding.rvHistory.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvHistory.setAdapter(adapter);
    }

    private void setupObservers() {
        viewModel.getAllHistory().observe(getViewLifecycleOwner(), historyEntities -> {
            adapter.submitList(historyEntities);
            if (historyEntities == null || historyEntities.isEmpty()) {
                binding.tvEmptyHistory.setVisibility(View.VISIBLE);
                binding.rvHistory.setVisibility(View.GONE);
            } else {
                binding.tvEmptyHistory.setVisibility(View.GONE);
                binding.rvHistory.setVisibility(View.VISIBLE);
            }
        });
    }

    private void setupClickListeners() {
        binding.btnClearHistory.setOnClickListener(v -> {
            new AlertDialog.Builder(requireContext())
                    .setTitle("Xóa lịch sử")
                    .setMessage("Bạn có chắc chắn muốn xóa tất cả lịch sử không?")
                    .setPositiveButton("Xóa hết", (dialog, which) -> viewModel.clearAll())
                    .setNegativeButton("Hủy", null)
                    .show();
        });
    }

    @Override
    public void onItemClick(HistoryEntity history) {
        // Có thể bổ sung logic khi click vào item (VD: copy kết quả)
        Toast.makeText(getContext(), "Đã chọn: " + history.getResult(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteClick(HistoryEntity history) {
        new AlertDialog.Builder(requireContext())
                .setTitle("Xóa bản ghi")
                .setMessage("Xóa phép tính này khỏi lịch sử?")
                .setPositiveButton("Xóa", (dialog, which) -> viewModel.delete(history))
                .setNegativeButton("Hủy", null)
                .show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
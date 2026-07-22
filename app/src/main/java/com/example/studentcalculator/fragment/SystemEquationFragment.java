package com.example.studentcalculator.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.studentcalculator.databinding.FragmentSystemEquationBinding;
import com.example.studentcalculator.viewmodel.EquationViewModel;

public class SystemEquationFragment extends Fragment {

    private FragmentSystemEquationBinding binding;
    private EquationViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSystemEquationBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(EquationViewModel.class);
        binding.btnSolve.setOnClickListener(v -> solve());
    }

    private void solve() {
        try {
            double a1 = Double.parseDouble(binding.etA1.getText().toString());
            double b1 = Double.parseDouble(binding.etB1.getText().toString());
            double c1 = Double.parseDouble(binding.etC1.getText().toString());
            double a2 = Double.parseDouble(binding.etA2.getText().toString());
            double b2 = Double.parseDouble(binding.etB2.getText().toString());
            double c2 = Double.parseDouble(binding.etC2.getText().toString());

            // Sử dụng ViewModel để giải và lưu lịch sử
            String result = viewModel.solveSystem(a1, b1, c1, a2, b2, c2);
            binding.tvResult.setText(result);
        } catch (NumberFormatException e) {
            Toast.makeText(getContext(), "Vui lòng nhập đầy đủ và đúng định dạng hệ số", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
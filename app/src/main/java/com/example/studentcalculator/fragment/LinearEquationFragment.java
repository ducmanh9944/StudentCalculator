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
import com.example.studentcalculator.databinding.FragmentLinearEquationBinding;
import com.example.studentcalculator.viewmodel.EquationViewModel;

public class LinearEquationFragment extends Fragment {

    private FragmentLinearEquationBinding binding;
    private EquationViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLinearEquationBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(EquationViewModel.class);
        binding.btnSolve.setOnClickListener(v -> solve());
    }

    private void solve() {
        String strA = binding.etA.getText().toString();
        String strB = binding.etB.getText().toString();

        if (strA.isEmpty() || strB.isEmpty()) {
            Toast.makeText(getContext(), "Vui lòng nhập đầy đủ hệ số", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double a = Double.parseDouble(strA);
            double b = Double.parseDouble(strB);

            String result = viewModel.solveLinear(a, b);
            binding.tvResult.setText(result);
        } catch (NumberFormatException e) {
            Toast.makeText(getContext(), "Hệ số không hợp lệ", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
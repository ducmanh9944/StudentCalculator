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
import com.example.studentcalculator.databinding.FragmentCubicEquationBinding;
import com.example.studentcalculator.viewmodel.EquationViewModel;

public class CubicEquationFragment extends Fragment {

    private FragmentCubicEquationBinding binding;
    private EquationViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCubicEquationBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(EquationViewModel.class);
        binding.btnSolve.setOnClickListener(v -> solve());
    }

    private void solve() {
        String sA = binding.etA.getText().toString();
        String sB = binding.etB.getText().toString();
        String sC = binding.etC.getText().toString();
        String sD = binding.etD.getText().toString();

        if (sA.isEmpty() || sB.isEmpty() || sC.isEmpty() || sD.isEmpty()) {
            Toast.makeText(getContext(), "Vui lòng nhập đủ hệ số", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double a = Double.parseDouble(sA);
            double b = Double.parseDouble(sB);
            double c = Double.parseDouble(sC);
            double d = Double.parseDouble(sD);

            String result = viewModel.solveCubic(a, b, c, d);
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
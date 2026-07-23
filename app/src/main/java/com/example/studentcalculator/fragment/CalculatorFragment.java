package com.example.studentcalculator.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.studentcalculator.databinding.FragmentCalculatorBinding;
import com.example.studentcalculator.viewmodel.CalculatorViewModel;

public class CalculatorFragment extends Fragment {

    private FragmentCalculatorBinding binding;
    private CalculatorViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCalculatorBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(CalculatorViewModel.class);

        setupObservers();
        setupClickListeners();
    }

    private void setupObservers() {
        viewModel.getExpression().observe(getViewLifecycleOwner(), s -> binding.tvExpression.setText(s));
        viewModel.getResult().observe(getViewLifecycleOwner(), s -> binding.tvResult.setText(s));
    }

    private void setupClickListeners() {
        binding.btn0.setOnClickListener(v -> viewModel.append("0"));
        binding.btn1.setOnClickListener(v -> viewModel.append("1"));
        binding.btn2.setOnClickListener(v -> viewModel.append("2"));
        binding.btn3.setOnClickListener(v -> viewModel.append("3"));
        binding.btn4.setOnClickListener(v -> viewModel.append("4"));
        binding.btn5.setOnClickListener(v -> viewModel.append("5"));
        binding.btn6.setOnClickListener(v -> viewModel.append("6"));
        binding.btn7.setOnClickListener(v -> viewModel.append("7"));
        binding.btn8.setOnClickListener(v -> viewModel.append("8"));
        binding.btn9.setOnClickListener(v -> viewModel.append("9"));

        binding.btnPlus.setOnClickListener(v -> viewModel.append("+"));
        binding.btnMinus.setOnClickListener(v -> viewModel.append("-"));
        binding.btnMultiply.setOnClickListener(v -> viewModel.append("×"));
        binding.btnDivide.setOnClickListener(v -> viewModel.append("÷"));
        binding.btnDot.setOnClickListener(v -> viewModel.append("."));
        binding.btnPercent.setOnClickListener(v -> viewModel.append("%"));

        binding.btnSqr.setOnClickListener(v -> viewModel.appendSquare());
        binding.btnPow.setOnClickListener(v -> viewModel.appendPower());
        binding.btnSqrt.setOnClickListener(v -> viewModel.appendSqrt());

        if (binding.btnSin != null) binding.btnSin.setOnClickListener(v -> viewModel.appendSin());
        if (binding.btnCos != null) binding.btnCos.setOnClickListener(v -> viewModel.appendCos());
        if (binding.btnTan != null) binding.btnTan.setOnClickListener(v -> viewModel.appendTan());
        if (binding.btnPi != null) binding.btnPi.setOnClickListener(v -> viewModel.appendPi());

        binding.btnAc.setOnClickListener(v -> viewModel.clear());
        binding.btnDel.setOnClickListener(v -> viewModel.delete());
        binding.btnEquals.setOnClickListener(v -> viewModel.onEquals());
        binding.btnSign.setOnClickListener(v -> viewModel.toggleSign());

        binding.btnParentheses.setOnClickListener(v -> {
            String current = viewModel.getExpression().getValue();
            if (current != null && !current.isEmpty()) {
                int openCount = 0;
                int closeCount = 0;
                for (char c : current.toCharArray()) {
                    if (c == '(') openCount++;
                    if (c == ')') closeCount++;
                }
                char lastChar = current.charAt(current.length() - 1);
                if (openCount > closeCount && !isOperator(lastChar) && lastChar != '(') {
                    viewModel.append(")");
                } else {
                    viewModel.append("(");
                }
            } else {
                viewModel.append("(");
            }
        });
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '×' || c == '÷' || c == '^';
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

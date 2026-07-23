package com.example.studentcalculator.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.studentcalculator.databinding.FragmentConverterBinding;
import com.example.studentcalculator.utils.UnitConverter;
import com.example.studentcalculator.viewmodel.ConverterViewModel;

public class ConverterFragment extends Fragment {

    private FragmentConverterBinding binding;
    private ConverterViewModel viewModel;

    private final String[] categories = {
            UnitConverter.CATEGORY_LENGTH,
            UnitConverter.CATEGORY_WEIGHT,
            UnitConverter.CATEGORY_TEMPERATURE
    };

    private final String[] lengthUnits = {"mm", "cm", "dm", "m", "km"};
    private final String[] weightUnits = {"mg", "g", "kg", "tấn"};
    private final String[] tempUnits = {"Celsius (°C)", "Fahrenheit (°F)", "Kelvin (K)"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentConverterBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ConverterViewModel.class);

        setupSpinners();
        setupObservers();
        setupInputListeners();
    }

    private void setupSpinners() {
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_dropdown_item_1line, categories);
        binding.spinnerCategory.setAdapter(categoryAdapter);

        binding.spinnerCategory.setText(categories[0], false);
        updateUnitSpinners(categories[0]);

        binding.spinnerCategory.setOnItemClickListener((parent, view, position, id) -> {
            String selectedCategory = categories[position];
            updateUnitSpinners(selectedCategory);
            convert();
        });

        binding.spinnerFrom.setOnItemClickListener((parent, view, position, id) -> convert());
        binding.spinnerTo.setOnItemClickListener((parent, view, position, id) -> convert());
    }

    private void updateUnitSpinners(String category) {
        String[] units;
        switch (category) {
            case UnitConverter.CATEGORY_WEIGHT:
                units = weightUnits;
                break;
            case UnitConverter.CATEGORY_TEMPERATURE:
                units = tempUnits;
                break;
            default:
                units = lengthUnits;
                break;
        }

        ArrayAdapter<String> unitAdapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_dropdown_item_1line, units);
        binding.spinnerFrom.setAdapter(unitAdapter);
        binding.spinnerTo.setAdapter(unitAdapter);

        binding.spinnerFrom.setText(units[0], false);
        binding.spinnerTo.setText(units[units.length > 1 ? 1 : 0], false);
    }

    private void setupObservers() {
        viewModel.getResult().observe(getViewLifecycleOwner(), result -> {
            binding.tvResult.setText(result);
        });
    }

    private void setupInputListeners() {
        binding.etInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                convert();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void convert() {
        String category = binding.spinnerCategory.getText().toString();
        String from = binding.spinnerFrom.getText().toString();
        String to = binding.spinnerTo.getText().toString();
        String input = binding.etInput.getText().toString();

        viewModel.performConversion(category, from, to, input);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
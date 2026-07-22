package com.example.studentcalculator.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import com.example.studentcalculator.R;
import com.example.studentcalculator.databinding.FragmentToolsBinding;

public class ToolsFragment extends Fragment {

    private FragmentToolsBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentToolsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupClickListeners();
    }

    private void setupClickListeners() {
        binding.cardLinearEq.setOnClickListener(v -> 
            Navigation.findNavController(v).navigate(R.id.nav_linear_equation));

        binding.cardQuadraticEq.setOnClickListener(v -> 
            Navigation.findNavController(v).navigate(R.id.nav_quadratic_equation));

        binding.cardCubicEq.setOnClickListener(v -> 
            Navigation.findNavController(v).navigate(R.id.nav_cubic_equation));

        binding.cardSystemEq.setOnClickListener(v -> 
            Navigation.findNavController(v).navigate(R.id.nav_system_equation));

        binding.cardGcdLcm.setOnClickListener(v -> 
            Navigation.findNavController(v).navigate(R.id.nav_gcd_lcm));

        binding.cardConverter.setOnClickListener(v -> 
            Navigation.findNavController(v).navigate(R.id.nav_converter));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
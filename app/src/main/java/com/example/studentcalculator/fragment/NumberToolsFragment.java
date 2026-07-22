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
import com.example.studentcalculator.databinding.FragmentNumberToolsBinding;
import com.example.studentcalculator.utils.MathUtils;
import com.example.studentcalculator.viewmodel.NumberToolsViewModel;

public class NumberToolsFragment extends Fragment {

    private FragmentNumberToolsBinding binding;
    private NumberToolsViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentNumberToolsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(NumberToolsViewModel.class);
        binding.btnCalculate.setOnClickListener(v -> calculate());
    }

    private void calculate() {
        String s1 = binding.etN1.getText().toString();
        String s2 = binding.etN2.getText().toString();

        if (s1.isEmpty() || s2.isEmpty()) {
            Toast.makeText(getContext(), "Vui lòng nhập đủ 2 số", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            long n1 = Long.parseLong(s1);
            long n2 = Long.parseLong(s2);

            // Gọi ViewModel để thực hiện tính toán và lưu lịch sử cho ƯCLN & BCNN
            viewModel.calculateGcdLcm(n1, n2);

            // Tính toán để hiển thị lên UI (Vẫn dùng MathUtils để tách biệt các giá trị trên TextView)
            long gcdValue = MathUtils.gcd(n1, n2);
            long lcmValue = MathUtils.lcm(n1, n2);

            // Phân tích thừa số nguyên tố (Không lưu lịch sử theo yêu cầu)
            String prime1 = MathUtils.primeFactorization(n1);
            String prime2 = MathUtils.primeFactorization(n2);

            // Hiển thị kết quả
            binding.tvGcd.setText("ƯCLN: " + gcdValue);
            binding.tvLcm.setText("BCNN: " + lcmValue);
            binding.tvPrime1.setText("n1 = " + prime1);
            binding.tvPrime2.setText("n2 = " + prime2);

            binding.cardResult.setVisibility(View.VISIBLE);
        } catch (NumberFormatException e) {
            Toast.makeText(getContext(), "Vui lòng nhập số nguyên hợp lệ", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

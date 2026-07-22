package com.example.studentcalculator.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.studentcalculator.utils.NumberFormatter;
import com.example.studentcalculator.utils.UnitConverter;

public class ConverterViewModel extends ViewModel {

    private final MutableLiveData<String> result = new MutableLiveData<>("0.00");
    
    public LiveData<String> getResult() {
        return result;
    }

    public void performConversion(String category, String from, String to, String valueStr) {
        if (valueStr == null || valueStr.isEmpty()) {
            result.setValue("0.00");
            return;
        }

        try {
            double value = Double.parseDouble(valueStr);
            double convertedValue = UnitConverter.convert(category, from, to, value);
            result.setValue(NumberFormatter.format(convertedValue));
        } catch (NumberFormatException e) {
            result.setValue("Lỗi");
        }
    }
}
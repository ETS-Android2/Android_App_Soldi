package com.project.soldiapp.data_handling;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.NavController;

/**
 * Class to share data of the Activity among the fragments and also using LiveData
 */
public class SharedViewModel extends ViewModel {
    private MutableLiveData<Double> expense = new MutableLiveData<>();
    private MutableLiveData<String> expenseType = new MutableLiveData<>();
    private MutableLiveData<Boolean> paymentMethod = new MutableLiveData<>();

    public void setExpense(Double input) {
        expense.setValue(input);
    }

    public LiveData<Double> getExpense() { //LiveData in order not to expose the set method
        return expense;
    }

    public void setExpenseType(String input) {
        expenseType.setValue(input);
    }

    public LiveData<String> getExpenseType() {
        return expenseType;
    }

    public void setPaymentMethod(Boolean input) {
        paymentMethod.setValue(input);
    }

    public LiveData<Boolean> getPaymentMethod() {
        return paymentMethod;
    }
}

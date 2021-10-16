package com.example.sa_is_very_hard.ui.pie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PieViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PieViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("市盈率各段比例分布图");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
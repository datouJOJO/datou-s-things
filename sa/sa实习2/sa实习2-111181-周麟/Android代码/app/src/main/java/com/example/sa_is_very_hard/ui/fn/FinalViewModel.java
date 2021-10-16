package com.example.sa_is_very_hard.ui.fn;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FinalViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public FinalViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("2016年收盘价比例分布图");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
package com.example.sa_is_very_hard.ui.ave;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AveViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AveViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("均价比例分布图");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
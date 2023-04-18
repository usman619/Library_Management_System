package com.example.librarymanagementsystem.ui.mybooks;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyBooksViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public MyBooksViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is My Books fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
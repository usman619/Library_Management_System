package com.example.librarymanagementsystem.ui.add_books;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddBooksViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AddBooksViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Add Books fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
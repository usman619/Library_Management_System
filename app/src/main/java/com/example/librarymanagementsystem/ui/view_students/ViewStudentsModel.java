package com.example.librarymanagementsystem.ui.view_students;

import android.widget.ListView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewStudentsModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ViewStudentsModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is View Students fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

}
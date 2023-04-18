package com.example.librarymanagementsystem.ui.add_books;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.librarymanagementsystem.databinding.FragmentAddBooksBinding;

public class AddBooksFragment extends Fragment {

    private FragmentAddBooksBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AddBooksViewModel dashboardViewModel =
                new ViewModelProvider(this).get(AddBooksViewModel.class);

        binding = FragmentAddBooksBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textAddBooks;
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
package com.example.librarymanagementsystem.ui.mybooks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.librarymanagementsystem.databinding.FragmentMybooksBinding;

public class MyBooksFragment extends Fragment {

private FragmentMybooksBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        MyBooksViewModel notificationsViewModel =
                new ViewModelProvider(this).get(MyBooksViewModel.class);

    binding = FragmentMybooksBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

        final TextView textView = binding.textMyBooks;
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
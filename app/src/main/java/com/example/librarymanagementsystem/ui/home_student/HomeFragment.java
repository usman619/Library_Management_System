package com.example.librarymanagementsystem.ui.home_student;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.librarymanagementsystem.databinding.FragmentHomeStudentsBinding;

public class HomeFragment extends Fragment {

private FragmentHomeStudentsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

    binding = FragmentHomeStudentsBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

//        final TextView textView = binding.textHomeStudents;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
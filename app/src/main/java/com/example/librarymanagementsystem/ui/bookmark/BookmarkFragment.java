package com.example.librarymanagementsystem.ui.bookmark;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.librarymanagementsystem.databinding.FragmentBookmarkBinding;

public class BookmarkFragment extends Fragment {

private FragmentBookmarkBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        BookmarkViewModel dashboardViewModel =
                new ViewModelProvider(this).get(BookmarkViewModel.class);

    binding = FragmentBookmarkBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

        final TextView textView = binding.textBookmark;
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
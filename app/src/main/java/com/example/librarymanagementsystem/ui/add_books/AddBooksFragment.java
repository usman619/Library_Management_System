package com.example.librarymanagementsystem.ui.add_books;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.librarymanagementsystem.BooksDB;
import com.example.librarymanagementsystem.Login;
import com.example.librarymanagementsystem.R;
import com.example.librarymanagementsystem.databinding.FragmentAddBooksBinding;

public class AddBooksFragment extends Fragment {

    public FragmentAddBooksBinding binding;

    BooksDB DB;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AddBooksViewModel dashboardViewModel =
                new ViewModelProvider(this).get(AddBooksViewModel.class);

        binding = FragmentAddBooksBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        View view = inflater.inflate(R.layout.fragment_add_books, container, false);

        DB = new BooksDB(getContext());
        EditText bookID = view.findViewById(R.id.bookId);
        EditText title = view.findViewById(R.id.title);
        EditText author = view.findViewById(R.id.author);
        EditText genre = view.findViewById(R.id.genre);
        EditText quantity = view.findViewById(R.id.quantity);


        Button addBtn = view.findViewById(R.id.addBook);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bookID_txt = bookID.getText().toString();
                String title_txt = title.getText().toString();
                String author_txt = author.getText().toString();
                String genre_txt = genre.getText().toString();
                int quantity_int  = Integer.parseInt(quantity.getText().toString());

                boolean checkBookInsert = DB.insertBook(bookID_txt, title_txt, author_txt, genre_txt, quantity_int, false);
                if(checkBookInsert==true)
                {
                    Toast.makeText(getContext(), "New Entry Inserted", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getContext(), Login.class);
                    startActivity(i);
                }
                else
                    Toast.makeText(getContext(), "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
            }
        });

        return root;


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
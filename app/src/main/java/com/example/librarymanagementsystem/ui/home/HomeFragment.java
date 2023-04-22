package com.example.librarymanagementsystem.ui.home;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.librarymanagementsystem.BooksDB;
import com.example.librarymanagementsystem.R;
import com.example.librarymanagementsystem.databinding.FragmentHomeBinding;
import com.example.librarymanagementsystem.ui.view_students.ViewStudentsFragment;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

//    BooksDB DB;
//
//    ArrayList<String> title;
//
//    ArrayList<String> author;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final ListView listView = binding.ViewHomeListView;
//        DB = new BooksDB(getContext());
//        Cursor cursor = DB.getAllBooks();
//
//        title = new ArrayList<String>();
//        author = new ArrayList<String>();
//
//        while (cursor.moveToNext()){
//            title.add(cursor.getString(1));
//            author.add(cursor.getString(2));
//        }
//
//        Log.d("title", title.toString());
//        Log.d("author", author.toString());
//
//        CustomBaseAdapter ca = new CustomBaseAdapter();
//        listView.setAdapter(ca);
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getContext(), title.get(position), Toast.LENGTH_SHORT).show();
//            }
//        });

        return root;
    }
//
//    class CustomBaseAdapter extends BaseAdapter{
//
//        @Override
//        public int getCount() {
//            return title.size();
//        }
//
//        @Override
//        public Object getItem(int i) {
//            return null;
//        }
//
//        @Override
//        public long getItemId(int i) {
//            return 0;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            if (convertView == null) {
//                convertView = LayoutInflater.from(parent.getContext())
//                        .inflate(R.layout.activity_view_book_list_view, parent, false);
//            }
//            TextView TitleView = convertView.findViewById(R.id.book_title);
//            TextView AuthorView = convertView.findViewById(R.id.book_author);
//
//
//            TitleView.setText(title.get(position));
//            AuthorView.setText(author.get(position));
//
//            return convertView;
//        }
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
package com.example.librarymanagementsystem;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import com.example.librarymanagementsystem.ui.view_students.ViewStudentsFragment;

import java.util.ArrayList;
public class ReturnBookListView extends AppCompatActivity {

    IssueBookDB issueBookDB;
    ArrayList<String> bookID;
    //ArrayList<String> rollno;
    //ArrayList<String> date;

    String rollNo;
    private CustomBaseAdapter_ customBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_return_book_admin);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);

        // Getting rollno from the view student from Admin
        Intent intent = getIntent();
        rollNo = intent.getStringExtra("RollNo");

        Log.d("ROLL NO (Return book ListView):", rollNo);

        bookID = new ArrayList<String>();
//        rollno = new ArrayList<String>();
//        date = new ArrayList<String>();

        issueBookDB = new IssueBookDB(getApplicationContext());

        Cursor cursor = issueBookDB.getAllIssuedBook(rollNo);
        if (cursor == null) {
            bookID.add("No Books");
        } else {
            while (cursor.moveToNext()) {
                bookID.add(cursor.getString(0));
            }
        }

        customBaseAdapter = new CustomBaseAdapter_();
        ListView listView = findViewById(R.id.return_list_view);
        listView.setAdapter(customBaseAdapter);
    }

    class CustomBaseAdapter_ extends BaseAdapter {

        @Override
        public int getCount() {
            return bookID.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.activity_return_book_list_view, parent, false);
            }
            TextView bookIDView = convertView.findViewById(R.id.Return_BookID_List_View);
            Button removeBtn = convertView.findViewById(R.id.deleteBtn_returnBook);

            removeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //rollNo.get(position)

                    String bookToRemove = bookID.get(position);

                    boolean checkbookdelete = issueBookDB.deleteIssueBook(rollNo, bookToRemove);
                    Log.d("Remove the issue book", String.valueOf(checkbookdelete));

                    if (checkbookdelete == true) {
                        bookID.remove(position);
                        notifyDataSetChanged();
                        StudentDB studentDB = new StudentDB(getApplicationContext());
                        BooksDB booksDB = new BooksDB(getApplicationContext());

                        booksDB.incrementBookQuantity(bookToRemove);
                        studentDB.decrementIssuedBooks(rollNo);

                        Toast.makeText(getApplicationContext(), "Book Returned!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Book NOT Returned!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            bookIDView.setText(bookID.get(position));

            return convertView;
        }
    }

//    @Override
//    public void onBackPressed() {
//        Intent intent = new Intent(this, ViewStudentsFragment.class);
//        startActivity(intent);
//        finish();
//    }
}



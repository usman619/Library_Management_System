package com.example.librarymanagementsystem;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class IssueBookAdmin extends AppCompatActivity {

    EditText et_bookID, et_student_rollno, et_due_date;
    Button issueBtn;
    int student_book_issued = 0;
    int book_limit = 0;
    StudentDB db;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_issue_book_admin);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);


        Intent intent = getIntent();
        String bookID = intent.getStringExtra("BookID");

        //Getting Book Limit from SharedPreferences
        SharedPreferences sp = getSharedPreferences("user_info", Activity.MODE_PRIVATE);
        book_limit = Integer.parseInt(sp.getString("Book Limit", "0"));

        Log.d("CHECK BOOK LIMIT: ",String.valueOf(book_limit));


        et_bookID = (EditText) findViewById(R.id.issue_bookId);
        //Auto Filling the Book ID
        et_bookID.setText(bookID);

        et_student_rollno = (EditText) findViewById(R.id.issue_rollno);
        et_due_date = (EditText) findViewById(R.id.issue_date);


        et_due_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dataPickerDialog = new DatePickerDialog(IssueBookAdmin.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                c.set(year,monthOfYear, dayOfMonth);
                                Date selectedDate = c.getTime();
                                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                                et_due_date.setText(sdf.format(selectedDate));

                                selectedDate = c.getTime();
                            }
                        },year,month,day);
                dataPickerDialog.show();
            }
        });





        issueBtn = (Button)findViewById(R.id.issueBookBtn);

        issueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bookID = et_bookID.getText().toString();
                String student_rollno_txt = et_student_rollno.getText().toString();
                Date due_date = (Date) et_due_date.getText();


                db = new StudentDB(getApplicationContext());
                Cursor cursor = db.getStudentIssueBook(student_rollno_txt);

                if (cursor != null && cursor.moveToFirst()){
                    student_book_issued = cursor.getColumnIndexOrThrow("bookIssued");
                }

                Log.d("CHECK STUDENT: ",String.valueOf(student_book_issued));

                if (cursor != null) {
                    cursor.close();
                }


                if (book_limit >= student_book_issued){
                    java.sql.Date date = new java.sql.Date(due_date.getTime());
                    IssueBookDB isssue_db = new IssueBookDB(getApplicationContext());
                    boolean check = isssue_db.insertIssueBook(bookID, student_rollno_txt, date);

                    Log.d("CHECK ISSUE BOOK: ",String.valueOf(check));

                    if (check == true){
                        // Increment Student Issue Book and Decrement Book quantity
                        BooksDB booksDB = new BooksDB(getApplicationContext());
                        StudentDB studentDB = new StudentDB(getApplicationContext());

                        booksDB.decrementBookQuantity(bookID);
                        studentDB.incrementIssuedBooks(student_rollno_txt);


                        Toast.makeText(IssueBookAdmin.this, "Book Issued Successfully!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(IssueBookAdmin.this, "Book can Not be Issued", Toast.LENGTH_SHORT).show();

                    }
                }



            }
        });


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

package com.example.librarymanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText rollNo, password;
    Button registerBtn, loginBtn;
    StudentDB DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Library Management System");

        rollNo = findViewById(R.id.RollNo);
        password = findViewById(R.id.password);

        registerBtn = findViewById(R.id.Register);
        loginBtn = findViewById(R.id.Login);


        DB = new StudentDB(this);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String rollNo_txt = rollNo.getText().toString();
                String password_txt = password.getText().toString();


                if(rollNo_txt.equals("admin") && password_txt.equals("admin")){
                    Intent  i = new Intent(getApplicationContext(), Admin_Dashboard.class);
                    Toast.makeText(Login.this, "Successfully Logged In!", Toast.LENGTH_SHORT).show();
                    startActivity(i);
                }
                else
                {
                    Cursor res = DB.getpassword(rollNo_txt);
                    if(res.getCount() == 0){
                        Toast.makeText(Login.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        //password exist!
                        //comparing passwords
                        while(res.moveToNext()){
                            int index;
                            index = res.getColumnIndexOrThrow("password");
                            String DB_password = res.getString(index);
                            if(password_txt.equals(DB_password))
                            {
                                Toast.makeText(Login.this, "Successfully Login!", Toast.LENGTH_SHORT).show();
                                // Go to student dashboard
                            Intent i = new Intent(getApplicationContext(), Student_Dashboard.class);
                            startActivity(i);
                            }
                            else
                            {
                                Toast.makeText(Login.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                            }
                        }


                    }
                }

            }
        });


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(getApplicationContext(), Register.class);
                startActivity(i);
            }
        });
    }


}
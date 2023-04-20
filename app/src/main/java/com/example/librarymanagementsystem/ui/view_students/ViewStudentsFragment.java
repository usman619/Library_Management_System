package com.example.librarymanagementsystem.ui.view_students;

import android.content.Intent;
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

import com.example.librarymanagementsystem.R;
import com.example.librarymanagementsystem.StudentDB;
import com.example.librarymanagementsystem.databinding.FragmentViewStudentsBinding;

import java.util.ArrayList;

public class ViewStudentsFragment extends Fragment {

    private FragmentViewStudentsBinding binding;

    StudentDB DB;
    ArrayList<String> Names;
    ArrayList<String> RollNo;

    ArrayList<String> Dept;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentViewStudentsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final ListView listView = binding.ViewStudentsListView;

        DB = new StudentDB(getContext());
        Cursor cursor = DB.getAllstudents();

        Names = new ArrayList<String>();
        RollNo = new ArrayList<String>();
        Dept = new ArrayList<String>();

        while (cursor.moveToNext()) {
            RollNo.add(cursor.getString(0));
            Names.add(cursor.getString(1));
            Dept.add(cursor.getString(3));
        }


        CustomBaseAdapter ca = new CustomBaseAdapter();
        listView.setAdapter(ca);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), RollNo.get(position), Toast.LENGTH_SHORT).show();
            }
        });
        return root;
    }

    class CustomBaseAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return Names.size();
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
                        .inflate(R.layout.activity_view_student_list_view, parent, false);
            }
            TextView NameView = convertView.findViewById(R.id.Name_ListView);
            TextView RollNoView = convertView.findViewById(R.id.RollNo_ListView);
            TextView DeptView = convertView.findViewById(R.id.Dept_ListView);

            NameView.setText(Names.get(position));
            RollNoView.setText(RollNo.get(position));
            DeptView.setText(Dept.get(position));

            return convertView;
        }
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
package com.prm391.sample.khoibm_he141771_practicetest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int SECOND_ACTIVITY_REQUEST_CODE = 0;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Employee> employees;
    private DBHelper dbHelper;
    private final String SELECT_TABLE_EMPLOYEE = "SELECT id, name, gender,phone,salary,deptId FROM Employee";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        employees = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_view_employee);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        dbHelper = new DBHelper(this, "hr.db", 1);

        getEmployees();
        customView();

    }

    public void createNew(View view) {
        Intent intent = new Intent(this, NewActivity.class);
        intent.putExtra("typeAction", "insert");
        startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SECOND_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                getEmployees();
                customView();
            }
        }
    }

    private void getEmployees() {
        employees = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(SELECT_TABLE_EMPLOYEE, null);
        while (cursor.moveToNext()) {
            Employee e = new Employee();
            e.setId(cursor.getInt(cursor.getColumnIndex("id")));
            e.setName(cursor.getString(cursor.getColumnIndex("name")));
            e.setGender(cursor.getString(cursor.getColumnIndex("gender")));
            e.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
            e.setSalary(cursor.getString(cursor.getColumnIndex("salary")));
            e.setDeptId(cursor.getInt(cursor.getColumnIndex("deptId")));
            employees.add(e);
        }
        for (Employee e:employees) {
            System.out.println(e.toString());
        }
    }

    private void customView() {
        adapter = new MyAdapter(employees, this);
        recyclerView.setAdapter(adapter);
    }
}
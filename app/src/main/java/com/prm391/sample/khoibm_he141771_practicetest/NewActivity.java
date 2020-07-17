package com.prm391.sample.khoibm_he141771_practicetest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class NewActivity extends AppCompatActivity {
    EditText name,gender,phone,salary;
    Spinner spinnerDept;
    List<Department> departments;
    DBHelper dbHelper;
    ArrayAdapter<Department> adapter;
    String typeAction;
    Employee employee;
    private final String INSERT_TABLE_EMPLOYEE="INSERT INTO Employee(name, gender,phone,salary,deptId) VALUES(?,?,?,?,?)";
    private final String UPDATE_TABLE_EMPLOYE = "UPDATE Employee SET name=?,gender=?,phone=?,salary=?,deptId=? WHERE id= ?";
    int deptIdSelected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        name=findViewById(R.id.name);
        gender=findViewById(R.id.gender);
        phone=findViewById(R.id.phone);
        salary=findViewById(R.id.salary);
        spinnerDept=findViewById(R.id.spinnerDept);
        createSpinner();
        Intent intent = getIntent();
        typeAction = intent.getStringExtra("typeAction");
        Log.i("typeAction", "type: " + typeAction);
        employee = intent.getParcelableExtra("employee");
        if (employee != null) {
            name.setText(employee.getName());
            gender.setText(employee.getGender());
            phone.setText(employee.getPhone());
            salary.setText(employee.getSalary());
            for (Department d : departments) {
                if (d.getId() == employee.getDeptId()) {
                    int spinnerPosition = adapter.getPosition(d);
                    spinnerDept.setSelection(spinnerPosition);
                }
            }
        }
    }

    private void createSpinner() {
        dbHelper = new DBHelper(this, "hr.db", 1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT id,name FROM Department", null);

        departments = new ArrayList<>();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));

            Department d = new Department();
            d.setId(id);
            d.setName(name);

            departments.add(d);
        }
        adapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, departments);

        spinnerDept.setAdapter(adapter);
        spinnerDept.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int i = (int) parent.getItemIdAtPosition(position);
                deptIdSelected = departments.get(i).getId();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void save(View view) {
        String _name = name.getText().toString();
        String _gender =gender.getText().toString();
        String _phone =phone.getText().toString();
        String _salary =salary.getText().toString();
        if (typeAction.equals("insert")) {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            db.execSQL(INSERT_TABLE_EMPLOYEE, new String[]{_name,_gender,_phone,_salary,String.valueOf(deptIdSelected)});

        } else if (typeAction.equals("update")) {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            db.execSQL(UPDATE_TABLE_EMPLOYE, new Object[]{_name,_gender,_phone,_salary,String.valueOf(deptIdSelected),employee.getId()});

        }
        Snackbar.Callback callback = null;
        callback=snackbarCallBackOnDismissed();
        Snackbar.make(view, "Save successful", Snackbar.LENGTH_SHORT)
                .addCallback(callback).show();
    }
    public  Snackbar.Callback snackbarCallBackOnDismissed() {
        return new Snackbar.Callback() {
            @Override
            public void onDismissed(Snackbar snackbar, int event) {
                super.onDismissed(snackbar, event);
                Intent intent = new Intent();
                setResult(Activity.RESULT_OK, intent);
                finish();
            }

        };
    }
}
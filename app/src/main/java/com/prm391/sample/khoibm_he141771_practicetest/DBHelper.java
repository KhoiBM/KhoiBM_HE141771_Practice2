package com.prm391.sample.khoibm_he141771_practicetest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private final String CREATE_TABLE_EMPLOYEE=
            "CREATE TABLE Employee("+
                    "id integer primary key autoincrement," +
                    "name text," +
                    "gender text," +
                    "phone text," +
                    "salary text," +
                    "deptId integer," +
                    "FOREIGN KEY(deptId) REFERENCES Department(id)"+
                    ")";
    private final String CREATE_TABLE_DEPARTMENT=
            "CREATE TABLE Department("+
                    "id integer primary key autoincrement," +
                    "name text" +
                    ")";
    List<Employee> employees ;
    private final String DROP_TABLE_EMPLOYEE = "DROP TABLE Employee";
    private final String DROP_TABLE_DEPARTMENT = "DROP TABLE Department";
    private final String INSERT_TABLE_DEPARTMENT="INSERT INTO Department(name) VALUES(?)";
    private final String INSERT_TABLE_EMPLOYEE="INSERT INTO Employee(name, gender,phone,salary,deptId) VALUES(?,?,?,?,?)";




    public DBHelper(Context context, String dbName, int version) {
        super(context, dbName, null, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_DEPARTMENT);
        db.execSQL(CREATE_TABLE_EMPLOYEE);
        employees= new ArrayList<>();
        employees.add(new Employee("Khoi","male","0387741552","1000",1));
        employees.add(new Employee("Thanh","female","0387741553","1000",2));
        db.execSQL(INSERT_TABLE_DEPARTMENT, new String[]{"A"});
        db.execSQL(INSERT_TABLE_DEPARTMENT, new String[]{"B"});
        for (Employee e:employees) {
            db.execSQL(INSERT_TABLE_EMPLOYEE, new String[]{e.getName(),e.getGender(),e.getPhone(),e.getSalary(),String.valueOf(e.getDeptId())});
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            db.execSQL(DROP_TABLE_DEPARTMENT);
            db.execSQL(DROP_TABLE_EMPLOYEE);
            onCreate(db);
        }
    }
}

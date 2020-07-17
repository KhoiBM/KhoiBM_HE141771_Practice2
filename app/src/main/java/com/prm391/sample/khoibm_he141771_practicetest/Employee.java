package com.prm391.sample.khoibm_he141771_practicetest;

import android.os.Parcel;
import android.os.Parcelable;

public class Employee  implements Parcelable {
    int id;
    String name;
    String gender;
    String phone;
    String salary;
    int deptId;

    public Employee()  {
    }

    public Employee(int id, String name, String gender, String phone, String salary, int deptId) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.salary = salary;
        this.deptId = deptId;
    }
    public Employee(String name, String gender, String phone, String salary, int deptId) {
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.salary = salary;
        this.deptId = deptId;
    }

    protected Employee(Parcel in) {
        id = in.readInt();
        name = in.readString();
        gender = in.readString();
        phone = in.readString();
        salary = in.readString();
        deptId = in.readInt();
    }

    public static final Creator<Employee> CREATOR = new Creator<Employee>() {
        @Override
        public Employee createFromParcel(Parcel in) {
            return new Employee(in);
        }

        @Override
        public Employee[] newArray(int size) {
            return new Employee[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                ", salary='" + salary + '\'' +
                ", deptId=" + deptId +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(gender);
        parcel.writeString(phone);
        parcel.writeString(salary);
        parcel.writeInt(deptId);
    }
}
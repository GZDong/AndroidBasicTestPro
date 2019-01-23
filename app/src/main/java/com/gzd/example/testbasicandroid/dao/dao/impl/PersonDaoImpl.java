package com.gzd.example.testbasicandroid.dao.dao.impl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gzd.example.testbasicandroid.dao.PersonDao;
import com.gzd.example.testbasicandroid.pojo.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gzd on 2019/1/24 0024
 */
public class PersonDaoImpl implements PersonDao {
    private SQLiteDatabase sqLiteDatabase;

    public PersonDaoImpl(SQLiteDatabase sqLiteDatabase) {
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @Override
    public void addPerson(Person person) {
        sqLiteDatabase.execSQL("insert into Person values(?,?)",new String[] {person.getName(),Integer.toString( person.getAge())});
    }

    @Override
    public void deletePerson(Person person) {
        sqLiteDatabase.execSQL("delete from Person where name = ?",new String[]{person.getName()});
    }

    @Override
    public List<Person> findAllPersons() {
        List<Person> list = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from Person",null);
        if (cursor.moveToFirst()){
            do{
                String name = cursor.getString(cursor.getColumnIndex("name"));
                Integer age = cursor.getInt(cursor.getColumnIndex("age"));
                Person person = new Person();
                person.setName(name);
                person.setAge(age);
                list.add(person);
            }while (cursor.moveToNext());
        }
        return list;
    }

    @Override
    public int updatePerson(Person person) {
        sqLiteDatabase.execSQL("update Person set age = ? where name = ?",new String[] {Integer.toString(person.getAge()) ,person.getName()});
        return 1;
    }
}

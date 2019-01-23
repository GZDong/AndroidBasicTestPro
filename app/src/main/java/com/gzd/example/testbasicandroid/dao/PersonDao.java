package com.gzd.example.testbasicandroid.dao;

import com.gzd.example.testbasicandroid.pojo.Person;

import java.util.List;

/**
 * Created by gzd on 2019/1/24 0024
 */
public interface PersonDao {
    public void addPerson(Person person);
    public void deletePerson(Person person);
    public List<Person> findAllPersons();
    public int updatePerson(Person person);
}

package com.example.demo.model;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.List;

@NodeEntity
public class Person {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int Dob;

    @Relationship(type = "ACTED_IN")
    private List<Movie> movie = new ArrayList<>();

    public Person() {
    }

    public Person(String name, int dob) {

        this.name = name;
        Dob = dob;

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getDob() {
        return Dob;
    }

    public List<Movie> getMovie() {
        return movie;
    }
}

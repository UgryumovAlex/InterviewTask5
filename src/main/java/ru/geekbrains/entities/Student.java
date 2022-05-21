package ru.geekbrains.entities;

import javax.persistence.*;

@Entity
@Table(name="students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="student_id")
    private Long id;

    @Column(name="student_name")
    private String name;

    @Column(name="mark")
    private int mark;

    public Student() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMark() {
        return mark;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mark=" + mark +
                '}';
    }
}

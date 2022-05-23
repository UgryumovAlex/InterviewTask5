package ru.geekbrains;

import ru.geekbrains.dao.StudentRepository;
import ru.geekbrains.entities.Student;

import java.util.List;
import java.util.Random;


public class Task5 {

    public static void main(String[] args) {

        fillDbByStudents(); //Добавим 1000 записей в БД, получим все записи и одну запись по ID
        crudOperations(); //Добавим одного студента, изменим значения полей и удалим.
    }

    private static int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    private static Student[] createStudentsArr() {
        Student[] students = new Student[1000];

        for (int i = 0; i < 1000; i++) {
             students[i] = new Student("Student " + i, getRandomNumber(1, 5));
        }

        return students;
    }

    private static void fillDbByStudents() {
        try {
            StudentRepository studentRepository = new StudentRepository(HibernateFactory.getFactory());
            Student[] students = createStudentsArr();

            for (int i = 0; i < 1000; i++) {
                studentRepository.addStudent(students[i]);
            }

            List<Student> studentsDB = studentRepository.getStudents();
            System.out.println("Выбрано записей студентов : " + studentsDB.size());

            Student studentById = studentRepository.getStudentById(100L);
            System.out.println("Студент с ID=100 : " + studentById);

        } finally {
            HibernateFactory.CloseFactory();
        }
    }

    private static void crudOperations() {

        try {
            StudentRepository studentRepository = new StudentRepository(HibernateFactory.getFactory());

            //CREATE
            Student student = new Student("Угрюмов Алексей", 2);
            studentRepository.addStudent(student);
            System.out.println("Добавили нового студента " + student);

            //UPDATE
            student.setName("Угрюмов Алексей Николаевич");
            student.setMark(5);
            studentRepository.updateStudent(student);
            System.out.println("Изменили данные студента " + student);

            //DELETE
            studentRepository.deleteStudent(student);
            System.out.println("Удалили студента " + student);

        } finally {
            HibernateFactory.CloseFactory();
        }

    }

}

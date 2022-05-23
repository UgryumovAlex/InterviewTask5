package ru.geekbrains.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.geekbrains.HibernateFactory;
import ru.geekbrains.entities.Student;

import java.util.List;

public class StudentRepository {

    private SessionFactory factory;

    public StudentRepository(SessionFactory factory) {
        this.factory = HibernateFactory.getFactory();
    }

    public void addStudent(Student student) {
        Session session = this.factory.getCurrentSession();
        session.beginTransaction();
		session.save(student);
		session.getTransaction().commit();
    }

    public void deleteStudent(Student student) {
        Session session = this.factory.getCurrentSession();
		session.beginTransaction();
        Student studentDelete = session.get(Student.class, student.getId());
		session.delete(studentDelete);
		session.getTransaction().commit();
    }

    public void updateStudent(Student student) {
        Session session = this.factory.getCurrentSession();
		session.beginTransaction();
		Student studentUpdate = session.get(Student.class, student.getId());
        studentUpdate.setName(student.getName());
        studentUpdate.setMark(student.getMark());
		session.getTransaction().commit();
    }

    public Student getStudentById(Long student_id) {
        Session session = this.factory.getCurrentSession();
		session.beginTransaction();
		Student student = session.get(Student.class, student_id);
		session.getTransaction().commit();

        return student;
    }

    public List<Student> getStudentByName(String name) {

        Session session = factory.getCurrentSession();
        session.beginTransaction();
        List<Student> students = session
					.createQuery("from Student c where c.name = :t")
					.setParameter("t", name)
					.getResultList();
        session.getTransaction().commit();

        return students;
    }

    public List<Student> getStudents() {

		Session session = factory.getCurrentSession();
		session.beginTransaction();
		List<Student> students = session.createQuery("from Student").getResultList();
		session.getTransaction().commit();

        return students;
    }
}

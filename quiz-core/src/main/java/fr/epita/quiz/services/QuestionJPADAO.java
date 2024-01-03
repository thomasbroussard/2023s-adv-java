package fr.epita.quiz.services;

import fr.epita.quiz.datamodel.Question;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.inject.Inject;

public class QuestionJPADAO {

    @Inject
    SessionFactory sessionFactory;

    public void create(Question question){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(question);
        transaction.commit();
    }


}

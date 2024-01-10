package fr.epita.quiz.services;

import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.services.api.IQuestionDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class QuestionJPADAO implements IQuestionDAO {

    SessionFactory sessionFactory;

    public QuestionJPADAO(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public void create(Question question){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(question);
        transaction.commit();
    }
    public void update(Question question){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(question);
        transaction.commit();
    }

    public void delete(Question question){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(question);
        transaction.commit();
    }

    @Override
    public List<Question> search(Question question) {
        return null;
    }

    @Override
    public Question getById(Object id) {
        return null;
    }


}

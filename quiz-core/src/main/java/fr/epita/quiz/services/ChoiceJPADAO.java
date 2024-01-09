package fr.epita.quiz.services;

import fr.epita.quiz.datamodel.Choice;
import fr.epita.quiz.datamodel.Question;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ChoiceJPADAO {

    SessionFactory sessionFactory;

    public ChoiceJPADAO(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }


    public List<Choice> search(Choice criteria){
        Query query = sessionFactory.openSession().createQuery("from Choice c where c.question.id = :qid");
        query.setParameter("qid", criteria.question.getId());
        List list = query.list();
        return list;
    }


    public void create(Choice question){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(question);
        transaction.commit();
    }



}

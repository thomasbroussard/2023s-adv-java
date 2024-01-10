package fr.epita.quiz.services;

import fr.epita.quiz.datamodel.Choice;
import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.services.api.IChoiceDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ChoiceJPADAO implements IChoiceDAO {

    SessionFactory sessionFactory;

    public ChoiceJPADAO(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }


    public List<Choice> search(Choice qbe){
        Query<Choice> query = sessionFactory.openSession().createQuery("from Choice c where c.question.id = :qid", Choice.class);
        query.setParameter("qid", qbe.getQuestion().getId());
        return query.list();
    }

    @Override
    public Choice getById(Object id) {
        return null;
    }


    public void create(Choice choice){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(choice);
        transaction.commit();
    }

    @Override
    public void update(Choice entity) {

    }

    @Override
    public void delete(Choice entity) {

    }


}

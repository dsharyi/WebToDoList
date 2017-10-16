package com.objectivelist.homework5.dao;

import com.objectivelist.homework5.entity.ObjectiveEntity;
import com.objectivelist.homework5.session_factory.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;


public class DaoObjectiveImpl implements DaoObjective {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();


    public void create(ObjectiveEntity objectiveEntity) {
        Session currentSession = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = currentSession.getTransaction();

            transaction.begin();

            currentSession.save(objectiveEntity);

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            currentSession.close();
        }

    }

    public ObjectiveEntity read(int id) {
        Session currentSession = sessionFactory.openSession();
        Transaction transaction = null;
        ObjectiveEntity objectiveEntity = null;

        try {
            transaction = currentSession.getTransaction();

            transaction.begin();

            objectiveEntity = currentSession.get(ObjectiveEntity.class, id);

            transaction.commit();


        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            currentSession.close();
        }
        return objectiveEntity;
    }

    public void update(ObjectiveEntity objectiveEntity) {
        Session currentSession = sessionFactory.openSession();
        Transaction transaction = null;


        try {
            transaction = currentSession.getTransaction();

            transaction.begin();

            currentSession.update(objectiveEntity);

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            currentSession.close();
        }
    }

    public void delete(ObjectiveEntity objectiveEntity) {

        Session currentSession = sessionFactory.openSession();
        Transaction transaction = null;


        try {
            transaction = currentSession.getTransaction();

            transaction.begin();

            currentSession.remove(objectiveEntity);

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            currentSession.close();
        }

    }

    public List<ObjectiveEntity> getAllObjectives() {
        Session currentSession = sessionFactory.openSession();
        Transaction transaction = null;
        List<ObjectiveEntity> objectiveEntityList = null;

        try {
            transaction = currentSession.getTransaction();

            transaction.begin();

            Query<ObjectiveEntity> namedQuery = currentSession.createNamedQuery("allObjectiveEntities", ObjectiveEntity.class);

            objectiveEntityList = namedQuery.getResultList();

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            currentSession.close();
        }
        return objectiveEntityList;
    }
}


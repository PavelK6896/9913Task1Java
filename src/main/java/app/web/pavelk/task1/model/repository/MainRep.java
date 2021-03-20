package app.web.pavelk.task1.model.repository;


import app.web.pavelk.task1.model.init.DataBase;

import java.io.Serializable;
import java.util.List;

public abstract class MainRep {

    public <T> List<?> get(DataBase dataBase, Class<T> tClass) {
        dataBase.getSessionFactory().getCurrentSession().beginTransaction();
        List<?> list = dataBase.getSessionFactory().getCurrentSession()
                .createQuery("from " + tClass.getName() + " as s order by s.id ").list();
        dataBase.getSessionFactory().getCurrentSession().getTransaction().commit();
        return list;
    }

    public <T> T create(DataBase dataBase, Object object, Class<T> tClass) {
        dataBase.getSessionFactory().getCurrentSession().beginTransaction();
        Serializable save = dataBase.getSessionFactory().getCurrentSession()
                .save(object);
        dataBase.getSessionFactory().getCurrentSession().getTransaction().commit();
        return (T) save;
    }

    public <T> T update(DataBase dataBase, Object object, Class<T> tClass) {
        dataBase.getSessionFactory().getCurrentSession().beginTransaction();
        dataBase.getSessionFactory().getCurrentSession()
                .update(object);
        dataBase.getSessionFactory().getCurrentSession().getTransaction().commit();
        return (T) object;
    }

    public void delete(DataBase dataBase, Object object) {
        dataBase.getSessionFactory().getCurrentSession().beginTransaction();
        dataBase.getSessionFactory().getCurrentSession()
                .delete(object);
        dataBase.getSessionFactory().getCurrentSession().getTransaction().commit();
    }

}

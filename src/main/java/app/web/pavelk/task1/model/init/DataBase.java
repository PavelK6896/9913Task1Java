package app.web.pavelk.task1.model.init;

import app.web.pavelk.task1.model.Project;
import app.web.pavelk.task1.model.Task;
import app.web.pavelk.task1.model.User;
import org.hibernate.SessionFactory;

public interface DataBase {

    void create();

    void close();

    SessionFactory getSessionFactory();

    default void loadInit(DataBase dataBase, LoadJson loadJson) {
        dataBase.getSessionFactory().getCurrentSession().beginTransaction();
        for (Project p : loadJson.getProjects()) {
            dataBase.getSessionFactory().getCurrentSession().save(p);
        }
        for (User p : loadJson.getUsers()) {
            dataBase.getSessionFactory().getCurrentSession().save(p);
        }
        for (Task p : loadJson.getTasks()) {
            dataBase.getSessionFactory().getCurrentSession().save(p);
        }
        dataBase.getSessionFactory().getCurrentSession().getTransaction().commit();
    }
}

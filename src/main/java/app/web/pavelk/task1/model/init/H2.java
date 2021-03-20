package app.web.pavelk.task1.model.init;

import app.web.pavelk.task1.model.Project;
import app.web.pavelk.task1.model.Task;
import app.web.pavelk.task1.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public class H2 implements DataBase {

    public static final String CREATE_SQL = "create.sql";
    private SessionFactory sessionFactory;

    public static SessionFactory connect() {
        Map<String, String> settings = new HashMap<>();
        settings.put("connection.driver_class", "org.h2.Driver");
        settings.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        settings.put("hibernate.connection.url", "jdbc:h2:mem:mydatabase;MODE=PostgreSQL");
        settings.put("hibernate.connection.username", "sa");
        settings.put("hibernate.connection.password", "");
        settings.put("hibernate.current_session_context_class", "thread");
        settings.put("hibernate.show_sql", "true");
        settings.put("hibernate.connection.release_mode", "auto");
        settings.put("hibernate.format_sql", "true");
        settings.put("connection.pool_size", "10");
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(settings).build();
        return createMetadata(serviceRegistry).getSessionFactoryBuilder().build();
    }

    private static Metadata createMetadata(ServiceRegistry serviceRegistry) {
        MetadataSources metadataSources = new MetadataSources(serviceRegistry);
        metadataSources.addAnnotatedClass(Project.class);
        metadataSources.addAnnotatedClass(Task.class);
        metadataSources.addAnnotatedClass(User.class);
        Metadata metadata = metadataSources.buildMetadata();
        SchemaExport schemaExport = new SchemaExport();
        schemaExport.setFormat(true);
        delete();
        schemaExport.setOutputFile(CREATE_SQL);
        EnumSet<TargetType> targetTypes = EnumSet.of(TargetType.DATABASE, TargetType.SCRIPT, TargetType.STDOUT);
        schemaExport.execute(targetTypes, SchemaExport.Action.CREATE, metadata);
        return metadata;
    }

    private static void delete() {
        try {
            Files.delete(Path.of(CREATE_SQL));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create() {
        sessionFactory = connect();
    }

    @Override
    public void close() {
        sessionFactory.close();
    }

    @Override
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}

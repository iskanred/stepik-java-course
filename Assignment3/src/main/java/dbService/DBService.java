package dbService;

import accountService.UserAccount;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class DBService {
    public static final String url = "jdbc:h2:./h2db";
    public static final String username = "test";
    public static final String password = "test";

    private static final String showSql = "true";
    private static final String hbm2ddlAuto = "validate";
    private static final String dialect = "org.hibernate.dialect.H2Dialect";
    private static final String driver = "org.h2.Driver";

    private final SessionFactory sessionFactory;


    public DBService() {
        sessionFactory = createSessionFactory(getH2Configuration());
    }

    public UserAccount getUserAccountByLogin(String login) {
        Session session = sessionFactory.openSession();
        UserAccountDAO dao = new UserAccountDAO(session);
        UserAccount result = dao.getByLogin(login);
        session.close();
        return result;
    }

    public void insertUserAccount(String login, String password) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        UserAccountDAO dao = new UserAccountDAO(session);
        dao.insertUser(login, password);
        transaction.commit();
        session.close();
    }

    private Configuration getH2Configuration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(UserAccount.class);

        configuration.setProperty("hibernate.dialect", dialect);
        configuration.setProperty("hibernate.connection.driver_class", driver);
        configuration.setProperty("hibernate.connection.url", url);
        configuration.setProperty("hibernate.connection.username", username);
        configuration.setProperty("hibernate.connection.password", password);
        configuration.setProperty("hibernate.show_sql", showSql);
        configuration.setProperty("hibernate.hbm2ddl.auto", hbm2ddlAuto);
        return configuration;
    }

    private SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
}

package dbService;

import accountService.UserAccount;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class UserAccountDAO {
    private final Session session;

    public UserAccountDAO(Session session) {
        this.session = session;
    }

    public UserAccount getByLogin(String login) throws HibernateException {
        Criteria criteria = session.createCriteria(UserAccount.class);
        return (UserAccount) criteria.add(Restrictions.eq("login", login)).uniqueResult();
    }

    public void insertUser(String login, String password) throws HibernateException {
        session.save(new UserAccount(login, password));
    }
}

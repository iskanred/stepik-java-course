package accountService;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class UserAccount implements Serializable {
    private static final long serialVersionUID = -8706689714326132798L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login", unique = true, nullable = false, updatable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;


    //Important to Hibernate!
    @SuppressWarnings("UnusedDeclaration")
    public UserAccount() { }

    @SuppressWarnings("UnusedDeclaration")
    public UserAccount(long id, String login, String password) {
        this.setId(id);
        this.setLogin(login);
        this.setPassword(password);
    }

    public UserAccount(String login, String password) {
        this.setId(-1L);
        this.setLogin(login);
        this.setPassword(password);
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

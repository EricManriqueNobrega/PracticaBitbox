import com.sun.istack.internal.NotNull;

import javax.persistence.*;

@Entity
@Table(name="USERCLIENT")
public class UserClient {
    @Id
    @NotNull
    @Column(name = "NAMEUSER", unique=true, nullable=false)
    private String nameUser;

    @Column(name = "PASSWORDUSER",nullable = false)
    private String passwordUser;

    public UserClient(){}
    public UserClient(String nameuser){
        this.nameUser = nameuser;
    }
    public UserClient(String nameuser, String passworduser){
        this.nameUser = nameuser;
        this.passwordUser = passworduser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public String getPasswordUser() {
        return passwordUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public void setPasswordUser(String passwordUser) {
        this.passwordUser = passwordUser;
    }
}

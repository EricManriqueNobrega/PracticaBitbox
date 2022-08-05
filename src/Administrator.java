import com.sun.istack.internal.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ADMINISTRATOR")
public class Administrator {

    @Id
    @NotNull
    @Column(name = "NAMEUSER", unique = true)
    private String nameAdmin;
    @NotNull
    @Column(name = "PASSWORDUSER")
    private String passwordAdmin;

    public Administrator(){

    }

    public Administrator(String nameadmin, String passwordadmin) {
        this.nameAdmin = nameadmin;
        this.passwordAdmin = passwordadmin;
    }


    public String getNameAdmin() {
        return nameAdmin;
    }

    public void setNameAdmin(String nameadmin) {
        this.nameAdmin = nameadmin;
    }

    public String getPasswordAdmin() {
        return passwordAdmin;
    }

    public void setPasswordAdmin(String passwordadmin) {
        this.passwordAdmin = passwordadmin;
    }
}

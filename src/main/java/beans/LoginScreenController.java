package beans;

import dao.UserDAO;
import model.User;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;

/**
 *
 * @author arasshchektaiev
 */
@ManagedBean(name = "loginScreenController")
@SessionScoped
public class LoginScreenController implements Serializable {

    private String username;
    private String password;

    private UserDAO userDAO = new UserDAO();

    //<editor-fold desc="GETTERS AND SETTERS">
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    //</editor-fold>

    public String send() {

        User user = userDAO.getUser(username, password);

        if (user == null) {
            FacesContext fc = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Login denied", "User " + username + " not found.");
            fc.addMessage(null, message);

            return null;
        } else {
            return "pages/main";
        }
    }
}

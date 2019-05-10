package tn.esprit.macdoloan.managedbeans;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import tn.esprit.macdoloan.entity.User;
import tn.esprit.macdoloan.service.impl.UserService;


@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {
	private String login;
	private String password;
	private User user;
	private Boolean loggedIn;
	@EJB
	UserService userservice;

	public String doLogin() {
		String navigateTo = "null";
		user = userservice.login(login, password);
		if (user != null ) //user.getRole() == "Client"
			{
			navigateTo = "/pages/client/welcome?faces-redirect=true";
			loggedIn = true;
		} else {
			FacesContext.getCurrentInstance().addMessage("form:btn", new FacesMessage("Bad Credentials"));
		}
		return navigateTo;

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

	public Boolean getLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(Boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserService getUserservice() {
		return userservice;
	}

	public void setUserservice(UserService userservice) {
		this.userservice = userservice;
	}

	public String doLogout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/login?faces-redirect=true";
	}
}
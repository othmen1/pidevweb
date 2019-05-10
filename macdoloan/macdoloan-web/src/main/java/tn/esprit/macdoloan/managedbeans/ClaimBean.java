package tn.esprit.macdoloan.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import tn.esprit.macdoloan.entity.Answer;
import tn.esprit.macdoloan.entity.Claim;
import tn.esprit.macdoloan.entity.Client;
import tn.esprit.macdoloan.entity.User;
import tn.esprit.macdoloan.service.impl.ClaimServiceImpl;
import tn.esprit.macdoloan.service.impl.UserService;


@ManagedBean
@SessionScoped
public class ClaimBean implements Serializable {
	
	private Client client;
	private Answer answer;
	private Claim cc;
	public List<Claim> returnClaims(){
		return claimserviceimpl.findUserClaims(LoginBean.getInstance().getUser().getId());
	}
	public boolean returnAbility(){
		List<Claim> l= claimserviceimpl.findUserClaims(LoginBean.getInstance().getUser().getId());
		List<Claim> last = new ArrayList<Claim>();
		Date d = new Date();
		for (Claim e : l) {
			if ((e.getDateSend().getDay()==d.getDay())){
				if(e.getDateSend().getHours()==d.getHours())
				{
					last.add(e);
				}
				else if(e.getDateSend().getHours()+1==d.getHours()&&e.getDateSend().getMinutes()>d.getMinutes()){
					last.add(e);
				}
			}
		}
		return last.size()<3;
	}
	
	public String returnTime(){
		List<Claim> l= claimserviceimpl.findUserClaims(LoginBean.getInstance().getUser().getId());
		List<Claim> last = new ArrayList<Claim>();
		Date d = new Date();
		for (Claim e : l) {
			System.out.println((e.getDateSend().getDay()==d.getDay())&&(e.getDateSend().getHours()==d.getHours()));
			if ((e.getDateSend().getDay()==d.getDay())){
				if(e.getDateSend().getHours()==d.getHours())
				{
					last.add(e);
				}
				else if(e.getDateSend().getHours()+1==d.getHours()&&e.getDateSend().getMinutes()>d.getMinutes()){
					last.add(e);
				}
			}
		}
		String text = (last.get(0).getDateSend().getHours()+1)+":"+last.get(0).getDateSend().getMinutes();
		return text;
	}
	
	
	private int Id;
	private String Object;
	private String Description;
	private Date dateSend;
	private String state;
	@EJB
	ClaimServiceImpl claimserviceimpl;
	@EJB
	UserService us;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getObject() {
		return Object;
	}
	public void setObject(String object) {
		Object = object;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public Date getDateSend() {
		return dateSend;
	}
	public void setDateSend(Date dateSend) {
		this.dateSend = dateSend;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public ClaimServiceImpl getClaimserviceimpl() {
		return claimserviceimpl;
	}
	public void setClaimserviceimpl(ClaimServiceImpl claimserviceimpl) {
		this.claimserviceimpl = claimserviceimpl;
	}
    public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Answer getAnswer() {
		return answer;
	}
	public void setAnswer(Answer answer) {
		this.answer = answer;
	}
	public UserService getUs() {
		return us;
	}
	public void setUs(UserService us) {
		this.us = us;
	}
	
	public Claim getCc() {
		return cc;
	}
	public void setCc(Claim cc) {
		this.cc = cc;
	}
	public String addClaim(User u){
		Client c = (Client)LoginBean.getInstance().getUser();
		Claim cl = new Claim(Object,Description,c);
		claimserviceimpl.addClaim(cl);
		String navigateTo = "/pages/client/myClaims?faces-redirect=true";
		return navigateTo;
	}
	public String showAnswer(Claim c){
		cc=c;
		String navigateTo = "/pages/client/claimDetails?faces-redirect=true";
		return navigateTo;
	}
	public String editClaim(Claim c){
		cc=c;
		this.dateSend=cc.getDateSend();
		this.Object=cc.getObject();
		this.Description=cc.getDescription();
		this.Id=cc.getId();
		String navigateTo = "/pages/client/editClaim?faces-redirect=true";
		return navigateTo;
	}
	public String updateClaim(){
		Client c = (Client)LoginBean.getInstance().getUser();
		Claim cl = new Claim(Id,Object,Description,c);
		claimserviceimpl.updateClaim(cl);
		String navigateTo = "/pages/client/myClaims?faces-redirect=true";
		return navigateTo;
	}


}

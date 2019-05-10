
package tn.esprit.macdoloan.managedbeans;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import tn.esprit.macdoloan.entity.Client;
import tn.esprit.macdoloan.entity.Installment;
import tn.esprit.macdoloan.entity.Loan;
import tn.esprit.macdoloan.entity.Product;
import tn.esprit.macdoloan.entity.State;
import tn.esprit.macdoloan.service.impl.LoanServiceImpl;
import tn.esprit.macdoloan.service.impl.UserService;

@ManagedBean
@SessionScoped
public class LoanBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String startdate;
	private String enddate;
	private float amount;
	private String name;
	private Date refunddate;
	private State state;
	private Client client;
	private Product product;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getRefunddate() {
		return refunddate;
	}

	public void setRefunddate(Date refunddate) {
		this.refunddate = refunddate;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@EJB
	LoanServiceImpl loanservice;
	@EJB
	UserService userservice;

	public String addLoanRequest() throws ParseException {

		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = sdf1.parse(startdate);
		SimpleDateFormat sdf11 = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date1 = sdf11.parse(enddate);
		java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
		java.sql.Date sqlStartDate1 = new java.sql.Date(date1.getTime());
		client = (Client) LoginBean.getInstance().getUser();
		Product l = new Product();
		l.setId(1);
		product = l;
		loanservice.addLoan(new Loan(client, product, sqlStartDate, sqlStartDate1, amount, name, State.INPRGRESS));
		loanservice.addLoan(new Loan(amount, name, sqlStartDate, sqlStartDate1, State.INPRGRESS));
		String navigateTo = "/pages/client/date?faces-redirect=true";
		return navigateTo;
	}

	private List<Loan> loans;

	public LoanServiceImpl getLoanservice() {
		return loanservice;
	}

	public void setLoanservice(LoanServiceImpl loanservice) {
		this.loanservice = loanservice;
	}

	public List<Loan> getLoans() {
		loans = loanservice.findUserLoan(LoginBean.getInstance().getUser().getId());
		return loans;
	}

	public void setListloan(List<Loan> loans) {
		this.loans = loans;
	}

	private List<Installment> installment;

	public List<Installment> getInstallment() {
		installment = loanservice.findAllinstallementInProcessByloanId(1);
		return installment;
	}

}

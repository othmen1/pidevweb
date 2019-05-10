package tn.esprit.macdoloan.managedbeans;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import tn.esprit.macdoloan.entity.Installment;
import tn.esprit.macdoloan.entity.Loan;
import tn.esprit.macdoloan.service.impl.LoanServiceImpl;
import tn.esprit.macdoloan.service.impl.UserService;

@ManagedBean
@SessionScoped
public class InstallmentBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private int Id;	
	private float prix;
	private Date refunddatenddate;
	private Boolean verif;
	private Loan loan;
	@EJB
	LoanServiceImpl loanservice;
	@EJB
	UserService userservice;
	private List<Installment> installments;

	
	public int getId() {
		return Id;
	}


	public void setId(int id) {
		Id = id;
	}


	public float getPrix() {
		return prix;
	}


	public void setPrix(float prix) {
		this.prix = prix;
	}


	public Date getRefunddatenddate() {
		return refunddatenddate;
	}


	public void setRefunddatenddate(Date refunddatenddate) {
		this.refunddatenddate = refunddatenddate;
	}


	public Boolean getVerif() {
		return verif;
	}


	public void setVerif(Boolean verif) {
		this.verif = verif;
	}


	public Loan getLoan() {
		return loan;
	}


	public void setLoan(Loan loan) {
		this.loan = loan;
	}


	public LoanServiceImpl getLoanservice() {
		return loanservice;
	}


	public void setLoanservice(LoanServiceImpl loanservice) {
		this.loanservice = loanservice;
	}


	public UserService getUserservice() {
		return userservice;
	}


	public void setUserservice(UserService userservice) {
		this.userservice = userservice;
	}


	public List<Installment> getInstallments() {
		return installments;
	}


	public void setInstallments(List<Installment> installments) {
		this.installments = installments;
	}


	public String putInstallment(int i) {
		installments = loanservice.findAllinstallementInProcessByloanId(i);
		String navigateTo = "/pages/client/installment?faces-redirect=true";
		return navigateTo;
	}
	
	
	
	
}

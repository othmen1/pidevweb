package tn.esprit.macdoloan.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Enumerated;

@Entity
@Table(name = "Loan")
public class Loan implements Serializable {
	@ManyToOne
	Client client;
	@ManyToOne
	Product product;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "loan")
	private Set<Installment> accountInstallments;

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	int id;
	@Column(name = "StartDate")
	Date startdate;
	@Column(name = "EndtDate")
	Date enddate;
	@Column(name = "Amount")
	float amount;
	@Column(name = "Reason")
	String name;
	@Column(name = "RefundDate")
	Date refunddate;
	@Column(name = "Verif")
	Boolean verif=false;
	@Enumerated(EnumType.STRING)
	private State state;

	public Loan(Client client, Product product, int id, Date startdate, Date enddate, float amount, String name,
			Date refunddate, State state, Boolean verif) {
		super();
		this.client = client;
		this.product = product;
		this.id = id;
		this.startdate = startdate;
		this.enddate = enddate;
		this.amount = amount;
		this.name = name;
		this.refunddate = refunddate;
		this.state = state;
		this.verif=verif;
	}

	public Loan() {
		super();
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

	public Set<Installment> getAccountInstallments() {
		return accountInstallments;
	}

	public void setAccountInstallments(Set<Installment> accountInstallments) {
		this.accountInstallments = accountInstallments;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

	public Boolean getVerif() {
		return verif;
	}

	public void setVerif(Boolean verif) {
		this.verif = verif;
	}

	@Override
	public String toString() {
		return "Loan [client=" + client.firstName + ", product=" + product + ", id=" + id + ", startdate=" + startdate
				+ ", enddate=" + enddate + ", amount=" + amount + ", name=" + name + ", refunddate=" + refunddate
				+ ", verif=" + verif + ", state=" + state + "]";
	}

	

}

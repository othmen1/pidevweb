package tn.esprit.macdoloan.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Installment
 *
 */
@Entity
@Table(name = "Installment")
public class Installment implements Serializable {
	@ManyToOne
	private Loan loan ;
	@OneToMany(cascade = CascadeType.ALL, mappedBy="installment")
	private Set<Penalty> installmentPenalties;
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private int Id;
	@Column(name = "RefundDate")
	Date refunddatenddate;
	@Column(name = "Prix")
	float prix;
	public Installment() {
		super();
	}
	public Loan getLoan() {
		return loan;
	}
	public void setLoan(Loan loan) {
		this.loan = loan;
	}
	public Set<Penalty> getInstallmentPenalties() {
		return installmentPenalties;
	}
	public void setInstallmentPenalties(Set<Penalty> installmentPenalties) {
		this.installmentPenalties = installmentPenalties;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public Date getRefunddatenddate() {
		return refunddatenddate;
	}
	public void setRefunddatenddate(Date refunddatenddate) {
		this.refunddatenddate = refunddatenddate;
	}
	public float getPrix() {
		return prix;
	}
	public void setPrix(float prix) {
		this.prix = prix;
	}
	
	@Override
	public String toString() {
		return "Installment [loan=, Id=" + Id
				+ ", refunddatenddate=" + refunddatenddate + ", prix=" + prix + "]";
	}
   
}

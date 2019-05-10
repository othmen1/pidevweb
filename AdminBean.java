package tn.esprit.macdoloan.managedbeans;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import tn.esprit.macdoloan.entity.Installment;
import tn.esprit.macdoloan.entity.Loan;
import tn.esprit.macdoloan.entity.Penalty;
import tn.esprit.macdoloan.service.impl.LoanServiceImpl;
import tn.esprit.macdoloan.service.impl.UserService;

@ManagedBean
@SessionScoped
public class AdminBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	LoanServiceImpl loanservice;
	@EJB
	UserService userservice;
	
	List<Penalty> currentPenalties;
	
	public LoanServiceImpl getLoanservice() {
		return loanservice;
	}
	
	public String doAccept(Loan l){
		
		try {
			Date h = new SimpleDateFormat("yyyy-MM-dd").parse("2019-5-1");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Installment i = new Installment();
		
		i.setLoan(l);
		int nbmois = ((l.getEnddate().getYear() - l.getStartdate().getYear()) * 12)
				+ (l.getEnddate().getMonth() - l.getStartdate().getMonth());
		System.out.println(nbmois);
		i.setPrix(loanservice.getprix(l.getStartdate(), l.getEnddate(), l.getAmount(), 0.12f, 12));
		// i.setRefunddatenddate(convertToDateViaSqlDate(LocalDate.now()));
		Calendar c = Calendar.getInstance();
		c.setTime(convertToDateViaSqlDate((LocalDate.now())));
		//
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

		// DateFormat k = new SimpleDateFormat("yyyy-MM-dd");

		Date k = new Date();
		k = c.getTime();
		String strDate = dateFormat.format(k);
		i.setRefunddatenddate(k);
		Date newDate = (c.getTime());
		System.out.println("Date Incremented by One: " + newDate);
		for (int j = 0; j < nbmois; j++) {
			Installment ni = new Installment();
			ni.setLoan(i.getLoan());
			ni.setPrix(i.getPrix());
			ni.setRefunddatenddate(i.getRefunddatenddate());
			ni.setVerif(i.getVerif());
			loanservice.addInstalement(ni);
			c.add(Calendar.MONTH, 1);
			i.setRefunddatenddate(c.getTime());
		}
		loanservice.AcceptCredit(l);
	/*	JOptionPane.showMessageDialog(null, "Hey the credit is accepted , now you can see the installment  ",
				"Display Message", JOptionPane.INFORMATION_MESSAGE);*/
		
		return "ListeLoan.jsf";
	}
	
	public Date convertToDateViaSqlDate(LocalDate dateToConvert) {
		return java.sql.Date.valueOf(dateToConvert.now());
	}

    public List<Loan> getLoansProcessing() {
    	return loanservice.findAllLoan();
	}
	
	public List<Loan> getAcceptedLoans() {
		return loanservice.LoanInProcess();
	}


	private List<Installment> installment;
	
	

	public List<Installment> getInstallment() {
		return installment;
	}

	public void setInstallment(List<Installment> installment) {
		this.installment = installment;
	}

	public String doGoInstallements(int idLoan) {
		installment = loanservice.findAllinstallementInProcessByloanId(idLoan);
		return "Installements.jsf?faces-recirect=true";
	}
	
	public String goPenality(int idInstallement) {
		currentPenalties = loanservice.findAllPenalityByInstallmentId(idInstallement);
		return "penality.jsf?faces-recirect=true";
	}

	public List<Penalty> getCurrentPenalties() {
		return currentPenalties;
	}

	public void setCurrentPenalties(List<Penalty> currentPenalties) {
		this.currentPenalties = currentPenalties;
	}
	
	public List<Penalty> getPenalityByInstallement(int idi) {
		return  loanservice.findAllPenalityByInstallmentId(idi);
	}
	
	public String checkPenalities() {
		for(Installment inst : installment) {
			if(inst.getRefunddatenddate().before(new Date())) {
				Penalty p = new Penalty();
				float amount = loanservice.getamount(inst.getRefunddatenddate(), inst.getPrix());
				p.setInstallment(inst);
				p.setAmount(amount);
				p.setRefunddatenddate(inst.getRefunddatenddate());
				loanservice.addPenality(p);
			}
		}
		return "Installements.jsf?faces-recirect=true";

	}
	
	public int getTotalInstallement() {
		
		return loanservice.findAllinstallement().size();
	}
	
public int getTotalPenalalities() {
		
		return loanservice.findAllPenalities(2).size();
	}

}

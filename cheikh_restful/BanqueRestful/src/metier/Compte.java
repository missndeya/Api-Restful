package metier;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Compte")
public class Compte {
	public long idcompte;
	public double  solde;
	public Date   dcreation;

	public Compte() {
		
	}
	
	public Compte(long num,double sld) {
		this(num,sld,new Date());
	}
	public Compte(long num,double sld,Date dj) {
		
		idcompte=num;
		solde=sld;
		dcreation=dj;
		
	}

	public long getNumro() {
		// TODO Auto-generated method stub
		return this.idcompte;
	}

	public void setNumero(long parseLong) {
		// TODO Auto-generated method stub
		this.idcompte=parseLong;
	}

	public void setSolde(double parseDouble) {
		// TODO Auto-generated method stub
		this.solde=parseDouble;
	}

	public void setDatecreation(long parseLong) {
		// TODO Auto-generated method stub
		this.dcreation=new Date(parseLong);
	}
	

}

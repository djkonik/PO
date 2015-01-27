package uaiContacts.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;

@Entity
public class Rozwiazanie {

    @Id
    @GeneratedValue
    private int id;
    
	@Column
	@Type(type="timestamp")
	private Date czasPrzeslania;
	
	@Column
	private boolean czySprawdzone;
	
	@Column
	private boolean czyZatwierdzone;
	
	@Column
	private String jezyk;
	
	@Column
	@Type(type="text")
	private String kod;
	
	@Column
	private String przyczyna;
	
	@Column
	private int nrZadania;
	
	@JsonBackReference 
	@ManyToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "autor", insertable=false, updatable=false)
    private User autor;
	
	public Rozwiazanie(Date czasPrzeslania, boolean czySprawdzone,
			boolean czyZatwierdzone, String jezyk, String kod,
			String przyczyna, int nrZadania) {
		this.czasPrzeslania = czasPrzeslania;
		this.czySprawdzone = czySprawdzone;
		this.czyZatwierdzone = czyZatwierdzone;
		this.jezyk = jezyk;
		this.kod = kod;
		this.przyczyna = przyczyna;
		this.nrZadania = nrZadania;
	}

	public Date getCzasPrzeslania() {
		return czasPrzeslania;
	}


	public void setCzasPrzeslania(Date czasPrzeslania) {
		this.czasPrzeslania = czasPrzeslania;
	}


	public boolean isCzySprawdzone() {
		return czySprawdzone;
	}


	public void setCzySprawdzone(boolean czySprawdzone) {
		this.czySprawdzone = czySprawdzone;
	}


	public boolean isCzyZatwierdzone() {
		return czyZatwierdzone;
	}


	public void setCzyZatwierdzone(boolean czyZatwierdzone) {
		this.czyZatwierdzone = czyZatwierdzone;
	}


	public String getJezyk() {
		return jezyk;
	}


	public void setJezyk(String jezyk) {
		this.jezyk = jezyk;
	}


	public String getKod() {
		return kod;
	}


	public void setKod(String kod) {
		this.kod = kod;
	}


	public String getPrzyczyna() {
		return przyczyna;
	}


	public void setPrzyczyna(String przyczyna) {
		this.przyczyna = przyczyna;
	}


	public int getNrZadania() {
		return nrZadania;
	}


	public void setNrZadania(int nrZadania) {
		this.nrZadania = nrZadania;
	}


	public User getAutor() {
		return autor;
	}


	public void setAutor(User autor) {
		this.autor = autor;
	}
	
	public Rozwiazanie(){
		
	}

 
    @Override
    public boolean equals(Object object) {
        if (object instanceof Rozwiazanie){
            Rozwiazanie contact = (Rozwiazanie) object;
            return contact.id == id;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return id;
    }
}

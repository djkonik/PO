package uaiContacts.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Ograniczenie {

    @Id
    @GeneratedValue
    private int id;
    
	@Column
	private String nazwa;
	
	@Column
	private String jezyk;
	
	@JsonBackReference 
	@ManyToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "zadanie", insertable=false, updatable=false)
	@JsonIgnore
    private Zadanie zadanie;

    public Ograniczenie() {
		super();
	}

	public Ograniczenie(int id, String nazwa, String jezyk) {
		super();
		this.id = id;
		this.nazwa = nazwa;
		this.jezyk = jezyk;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public String getJezyk() {
		return jezyk;
	}

	public void setJezyk(String jezyk) {
		this.jezyk = jezyk;
	}

	@JsonIgnore
	public Zadanie getZadanie() {
		return zadanie;
	}

	@JsonIgnore
	public void setZadanie(Zadanie zadanie) {
		this.zadanie = zadanie;
	}

	@Override
    public boolean equals(Object object) {
        if (object instanceof Ograniczenie){
            Ograniczenie ograniczenie = (Ograniczenie) object;
            return ograniczenie.id == id;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return id;
    }
}

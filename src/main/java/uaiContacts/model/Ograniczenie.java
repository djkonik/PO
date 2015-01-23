package uaiContacts.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Ograniczenie {

    @Id
    @GeneratedValue
    private int id;
    
	@Column
	private String nazwa;
	
	@Column
	private String jezyk;
	
	@Column
	private int zadanie;

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

	public int getZadanie() {
		return zadanie;
	}

	public void setZadanie(int zadanie) {
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

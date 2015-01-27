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
public class SlowoKluczowe {

    @Id
    @GeneratedValue
    private int id;
    
	@Column
	private String slowo;

	@JsonBackReference 
	@ManyToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "ograniczenie")
	@JsonIgnore
    private Ograniczenie ograniczenie;
	
    public SlowoKluczowe() {
		super();
	}

	public SlowoKluczowe(int id, String slowo) {
		super();
		this.id = id;
		this.slowo = slowo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSlowo() {
		return slowo;
	}

	public void setSlowo(String slowo) {
		this.slowo = slowo;
	}
	
	@JsonIgnore
	public Ograniczenie getOgraniczenie() {
		return ograniczenie;
	}
	
	@JsonIgnore
	public void setOgraniczenie(Ograniczenie ograniczenie) {
		this.ograniczenie = ograniczenie;
	}

	@Override
    public boolean equals(Object object) {
        if (object instanceof SlowoKluczowe){
            SlowoKluczowe slowoKluczowe = (SlowoKluczowe) object;
            return slowoKluczowe.id == id;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return id;
    }
}

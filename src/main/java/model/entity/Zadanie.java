package model.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;

@Entity
public class Zadanie {

    @Id
    @GeneratedValue
    private int id;
    
	@Column
	private int numer;
	
	@Column
	@Type(type="text")
	private String tresc;
	
	@Column
	private boolean isCaseSensitive;
	
	@Column
	private long maxCzasWykonania;
	
	@Column
	private String tytul;
	
	@JsonBackReference 
	@ManyToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "autor", insertable=false, updatable=false)
    private User autor;
	
	@JsonBackReference 
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "zadanie")
	@Fetch(FetchMode.JOIN)
    private List<Ograniczenie> ograniczenia;
	
    public Zadanie() {
	}

	public Zadanie(int id, int numer, String tresc, boolean isCaseSensitive,
			long maxCzasWykonania, String tytul) {
		super();
		this.id = id;
		this.numer = numer;
		this.tresc = tresc;
		this.isCaseSensitive = isCaseSensitive;
		this.maxCzasWykonania = maxCzasWykonania;
		this.tytul = tytul;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumer() {
		return numer;
	}

	public void setNumer(int numer) {
		this.numer = numer;
	}

	public String getTresc() {
		return tresc;
	}

	public void setTresc(String tresc) {
		this.tresc = tresc;
	}

	public boolean isCaseSensitive() {
		return isCaseSensitive;
	}

	public void setCaseSensitive(boolean isCaseSensitive) {
		this.isCaseSensitive = isCaseSensitive;
	}

	public long getMaxCzasWykonania() {
		return maxCzasWykonania;
	}

	public void setMaxCzasWykonania(long maxCzasWykonania) {
		this.maxCzasWykonania = maxCzasWykonania;
	}

	public String getTytul() {
		return tytul;
	}

	public void setTytul(String tytul) {
		this.tytul = tytul;
	}

	public User getAutor() {
		return autor;
	}

	public void setAutor(User autor) {
		this.autor = autor;
	}
	

	public List<Ograniczenie> getOgraniczenia() {
		return ograniczenia;
	}

	public void setOgraniczenia(List<Ograniczenie> ograniczenia) {
		this.ograniczenia = ograniczenia;
	}

	@Override
    public boolean equals(Object object) {
        if (object instanceof Zadanie){
            Zadanie zadanie = (Zadanie) object;
            return zadanie.id == id;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return id;
    }
}

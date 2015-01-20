package uaiContacts.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
	
	@Column
	private int autor;
 
	
    public Zadanie() {
	}

	public Zadanie(int id, int numer, String tresc, boolean isCaseSensitive,
			long maxCzasWykonania, String tytul, int autor) {
		super();
		this.id = id;
		this.numer = numer;
		this.tresc = tresc;
		this.isCaseSensitive = isCaseSensitive;
		this.maxCzasWykonania = maxCzasWykonania;
		this.tytul = tytul;
		this.autor = autor;
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

	public int getAutor() {
		return autor;
	}

	public void setAutor(int autor) {
		this.autor = autor;
	}

	@Override
    public boolean equals(Object object) {
        if (object instanceof Zadanie){
            Zadanie contact = (Zadanie) object;
            return contact.id == id;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return id;
    }
}

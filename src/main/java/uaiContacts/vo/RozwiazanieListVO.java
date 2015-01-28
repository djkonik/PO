package uaiContacts.vo;

import java.util.List;

import model.entity.Rozwiazanie;

public class RozwiazanieListVO {
    private int pagesCount;
    private long totalContacts;

    private List<Rozwiazanie> rozwiazania;

    public RozwiazanieListVO() {
    }

    public RozwiazanieListVO(int pages, long totalContacts, List<Rozwiazanie> rozwiazania) {
        this.pagesCount = pages;
        this.rozwiazania = rozwiazania;
        this.totalContacts = totalContacts;
    }

    public int getPagesCount() {
        return pagesCount;
    }

    public void setPagesCount(int pagesCount) {
        this.pagesCount = pagesCount;
    }

    public List<Rozwiazanie> getRozwiazania() {
        return rozwiazania;
    }

    public void setRozwiazania(List<Rozwiazanie> rozwiazania) {
        this.rozwiazania = rozwiazania;
    }

    public long getTotalContacts() {
        return totalContacts;
    }

    public void setTotalContacts(long totalContacts) {
        this.totalContacts = totalContacts;
    }
}
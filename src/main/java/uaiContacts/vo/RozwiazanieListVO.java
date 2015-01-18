package uaiContacts.vo;

import java.util.List;

import uaiContacts.model.Rozwiazanie;

public class RozwiazanieListVO {
    private int pagesCount;
    private long totalContacts;

    private String actionMessage;
    private String searchMessage;

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

    public String getActionMessage() {
        return actionMessage;
    }

    public void setActionMessage(String actionMessage) {
        this.actionMessage = actionMessage;
    }

    public String getSearchMessage() {
        return searchMessage;
    }

    public void setSearchMessage(String searchMessage) {
        this.searchMessage = searchMessage;
    }
}
package uaiContacts.vo;

import java.util.List;

import uaiContacts.model.Zadanie;

public class ZadanieListVO {
    private int pagesCount;
    private long totalContacts;

    private String actionMessage;
    private String searchMessage;

    private List<Zadanie> zadania;

    public ZadanieListVO() {
    }

    public ZadanieListVO(int pages, long totalContacts, List<Zadanie> zadania) {
        this.pagesCount = pages;
        this.zadania = zadania;
        this.totalContacts = totalContacts;
    }

    public int getPagesCount() {
        return pagesCount;
    }

    public void setPagesCount(int pagesCount) {
        this.pagesCount = pagesCount;
    }

    public List<Zadanie> getZadania() {
        return zadania;
    }

    public void setRozwiazania(List<Zadanie> zadania) {
        this.zadania = zadania;
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
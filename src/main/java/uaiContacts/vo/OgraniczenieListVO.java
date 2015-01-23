package uaiContacts.vo;

import java.util.List;

import uaiContacts.model.Ograniczenie;

public class OgraniczenieListVO {
    private int pagesCount;
    private long totalContacts;

    private String actionMessage;
    private String searchMessage;

    private List<Ograniczenie> ograniczenia;

    public OgraniczenieListVO() {
    }

    public OgraniczenieListVO(int pages, long totalContacts, List<Ograniczenie> ograniczenia) {
        this.pagesCount = pages;
        this.ograniczenia = ograniczenia;
        this.totalContacts = totalContacts;
    }

    public int getPagesCount() {
        return pagesCount;
    }

    public void setPagesCount(int pagesCount) {
        this.pagesCount = pagesCount;
    }

    public List<Ograniczenie> getOgraniczenia() {
        return ograniczenia;
    }

    public void setOgraniczenia(List<Ograniczenie> ograniczenia) {
        this.ograniczenia = ograniczenia;
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
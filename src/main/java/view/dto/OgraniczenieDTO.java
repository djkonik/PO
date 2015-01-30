package view.dto;

import java.util.List;

import model.entity.Ograniczenie;

public class OgraniczenieDTO {
    private int pagesCount;
    private long totalCount;

    private String actionMessage;
    private String searchMessage;

    private List<Ograniczenie> ograniczenia;

    public OgraniczenieDTO() {
    }

    public OgraniczenieDTO(int pages, long totalCount, List<Ograniczenie> ograniczenia) {
        this.pagesCount = pages;
        this.ograniczenia = ograniczenia;
        this.totalCount = totalCount;
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

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
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
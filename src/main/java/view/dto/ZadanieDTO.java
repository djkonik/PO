package view.dto;

import java.util.List;

import model.entity.Zadanie;

public class ZadanieDTO {
    private int pagesCount;
    private long totalCount;

    private List<Zadanie> zadania;

    public ZadanieDTO() {
    }

    public ZadanieDTO(int pages, long totalCount, List<Zadanie> zadania) {
        this.pagesCount = pages;
        this.zadania = zadania;
        this.totalCount = totalCount;
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

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

}
package view.dto;

import java.util.List;

import model.entity.Rozwiazanie;

public class RozwiazanieDTO {
    private int pagesCount;
    private long totalCount;

    private List<Rozwiazanie> rozwiazania;

    public RozwiazanieDTO() {
    }

    public RozwiazanieDTO(int pages, long totalCount, List<Rozwiazanie> rozwiazania) {
        this.pagesCount = pages;
        this.rozwiazania = rozwiazania;
        this.totalCount = totalCount;
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

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }
}
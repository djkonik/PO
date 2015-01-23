package uaiContacts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uaiContacts.model.Zadanie;
import uaiContacts.repository.ZadanieRepository;
import uaiContacts.vo.ZadanieListVO;

@Service
@Transactional
public class ZadanieService {

    @Autowired
    private ZadanieRepository zadanieRepository;
    

    @Transactional(readOnly = true)
    public ZadanieListVO findAll(int page, int maxResults) {
        Page<Zadanie> result = executeQueryFindAll(page, maxResults);

        if(shouldExecuteSameQueryInLastPage(page, result)){
            int lastPage = result.getTotalPages() - 1;
            result = executeQueryFindAll(lastPage, maxResults);
        }

        return buildResult(result);
    }
    
    @Transactional(readOnly = true)
    public ZadanieListVO findByAutorLike(int page, int maxResults, int autor) {
        Page<Zadanie> result = executeQueryFindByAutor(page, maxResults, autor);

        if(shouldExecuteSameQueryInLastPage(page, result)){
            int lastPage = result.getTotalPages() - 1;
            result = executeQueryFindByAutor(lastPage, maxResults, autor);
        }

        return buildResult(result);
    }

    private boolean shouldExecuteSameQueryInLastPage(int page, Page<Zadanie> result) {
        return isUserAfterOrOnLastPage(page, result) && hasDataInDataBase(result);
    }

    private Page<Zadanie> executeQueryFindAll(int page, int maxResults) {
        final PageRequest pageRequest = new PageRequest(page, maxResults, sortByNumerASC());

        return zadanieRepository.findAll(pageRequest);
    }
    
    private Page<Zadanie> executeQueryFindByAutor(int page, int maxResults, int autor) {
        final PageRequest pageRequest = new PageRequest(page, maxResults, sortByNumerASC());

        return zadanieRepository.findByAutorLike(pageRequest, autor);
    }

    private Sort sortByNumerASC() {
        return new Sort(Sort.Direction.ASC, "numer");
    }

    private ZadanieListVO buildResult(Page<Zadanie> result) {
        return new ZadanieListVO(result.getTotalPages(), result.getTotalElements(), result.getContent());
    }
/*
    private Page<Rozwiazanie> executeQueryFindByName(int page, int maxResults, String jezyk) {
        final PageRequest pageRequest = new PageRequest(page, maxResults, sortByJezykASC());

        return rozwiazanieRepository.findByJezykLike(pageRequest, "%" + jezyk + "%");
    }*/
    



    private boolean isUserAfterOrOnLastPage(int page, Page<Zadanie> result) {
        return page >= result.getTotalPages() - 1;
    }

    private boolean hasDataInDataBase(Page<Zadanie> result) {
        return result.getTotalElements() > 0;
    }
}
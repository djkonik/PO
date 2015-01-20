package uaiContacts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uaiContacts.model.Rozwiazanie;
import uaiContacts.repository.RozwiazanieRepository;
import uaiContacts.vo.RozwiazanieListVO;

@Service
@Transactional
public class RozwiazanieService {

    @Autowired
    private RozwiazanieRepository rozwiazanieRepository;
    

    @Transactional(readOnly = true)
    public RozwiazanieListVO findAll(int page, int maxResults) {
        Page<Rozwiazanie> result = executeQueryFindAll(page, maxResults);

        if(shouldExecuteSameQueryInLastPage(page, result)){
            int lastPage = result.getTotalPages() - 1;
            result = executeQueryFindAll(lastPage, maxResults);
        }

        return buildResult(result);
    }
/*
    @Transactional(readOnly = true)
    public RozwiazanieListVO findByJezykLike(int page, int maxResults, String jezyk) {
        Page<Rozwiazanie> result = executeQueryFindByName(page, maxResults, jezyk);

        if(shouldExecuteSameQueryInLastPage(page, result)){
            int lastPage = result.getTotalPages() - 1;
            result = executeQueryFindByName(lastPage, maxResults, jezyk);
        }

        return buildResult(result);
    }*/
    
    @Transactional(readOnly = true)
    public RozwiazanieListVO findByAutorLike(int page, int maxResults, int autor) {
        Page<Rozwiazanie> result = executeQueryFindByAutor(page, maxResults, autor);

        if(shouldExecuteSameQueryInLastPage(page, result)){
            int lastPage = result.getTotalPages() - 1;
            result = executeQueryFindByAutor(lastPage, maxResults, autor);
        }

        return buildResult(result);
    }

    private boolean shouldExecuteSameQueryInLastPage(int page, Page<Rozwiazanie> result) {
        return isUserAfterOrOnLastPage(page, result) && hasDataInDataBase(result);
    }

    private Page<Rozwiazanie> executeQueryFindAll(int page, int maxResults) {
        final PageRequest pageRequest = new PageRequest(page, maxResults, sortByCzasPrzeslaniaDESC());

        return rozwiazanieRepository.findAll(pageRequest);
    }

    private Sort sortByCzasPrzeslaniaDESC() {
        return new Sort(Sort.Direction.DESC, "czasPrzeslania");
    }

    private RozwiazanieListVO buildResult(Page<Rozwiazanie> result) {
        return new RozwiazanieListVO(result.getTotalPages(), result.getTotalElements(), result.getContent());
    }
/*
    private Page<Rozwiazanie> executeQueryFindByName(int page, int maxResults, String jezyk) {
        final PageRequest pageRequest = new PageRequest(page, maxResults, sortByJezykASC());

        return rozwiazanieRepository.findByJezykLike(pageRequest, "%" + jezyk + "%");
    }*/
    
    //TODO - 
    private Page<Rozwiazanie> executeQueryFindByAutor(int page, int maxResults, int autor) {
        final PageRequest pageRequest = new PageRequest(page, maxResults, sortByCzasPrzeslaniaDESC());

        return rozwiazanieRepository.findByAutorLike(pageRequest, autor);
    }

    private boolean isUserAfterOrOnLastPage(int page, Page<Rozwiazanie> result) {
        return page >= result.getTotalPages() - 1;
    }

    private boolean hasDataInDataBase(Page<Rozwiazanie> result) {
        return result.getTotalElements() > 0;
    }
}
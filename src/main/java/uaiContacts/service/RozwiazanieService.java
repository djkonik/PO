package uaiContacts.service;

import model.entity.Rozwiazanie;
import model.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    
    @Transactional(readOnly = true)
    public RozwiazanieListVO findByAutorLike(int page, int maxResults, User autor) {
        Page<Rozwiazanie> result = executeQueryFindByAutor(page, maxResults, autor);

        if(shouldExecuteSameQueryInLastPage(page, result)){
            int lastPage = result.getTotalPages() - 1;
            result = executeQueryFindByAutor(lastPage, maxResults, autor);
        }

        return buildResult(result);
    }

    private boolean shouldExecuteSameQueryInLastPage(int page, Page<Rozwiazanie> result) {
        return page > result.getTotalPages() && result.getTotalElements() > 0;
    }

    private Page<Rozwiazanie> executeQueryFindAll(int page, int maxResults) {
        final PageRequest pageRequest = new PageRequest(page, maxResults, sortByCzasPrzeslaniaDESC());

        return rozwiazanieRepository.findAll(pageRequest);
    }
    
    private Page<Rozwiazanie> executeQueryFindByAutor(int page, int maxResults, User autor) {
        final PageRequest pageRequest = new PageRequest(page, maxResults, sortByCzasPrzeslaniaDESC());

        return rozwiazanieRepository.findByAutorLike(pageRequest, autor);
    }

    private Sort sortByCzasPrzeslaniaDESC() {
        return new Sort(Sort.Direction.DESC, "czasPrzeslania");
    }

    private RozwiazanieListVO buildResult(Page<Rozwiazanie> result) {
        return new RozwiazanieListVO(result.getTotalPages(), result.getTotalElements(), result.getContent());
    }

}
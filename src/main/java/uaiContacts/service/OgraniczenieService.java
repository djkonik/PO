package uaiContacts.service;

import model.entity.Ograniczenie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uaiContacts.repository.OgraniczenieRepository;
import uaiContacts.vo.OgraniczenieListVO;

@Service
@Transactional
public class OgraniczenieService {

    @Autowired
    private OgraniczenieRepository ograniczenieRepository;
    

    @Transactional(readOnly = true)
    public OgraniczenieListVO findAll(int page, int maxResults) {
        Page<Ograniczenie> result = executeQueryFindAll(page, maxResults);

        if(shouldExecuteSameQueryInLastPage(page, result)){
            int lastPage = result.getTotalPages() - 1;
            result = executeQueryFindAll(lastPage, maxResults);
        }

        return buildResult(result);
    }
    
    @Transactional(readOnly = true)
    public OgraniczenieListVO findByZadanieLike(int page, int maxResults, int zadanie) {
        Page<Ograniczenie> result = executeQueryFindByZadanie(page, maxResults, zadanie);

        if(shouldExecuteSameQueryInLastPage(page, result)){
            int lastPage = result.getTotalPages() - 1;
            result = executeQueryFindByZadanie(lastPage, maxResults, zadanie);
        }

        return buildResult(result);
    }

    private boolean shouldExecuteSameQueryInLastPage(int page, Page<Ograniczenie> result) {
        return isUserAfterOrOnLastPage(page, result) && hasDataInDataBase(result);
    }

    private Page<Ograniczenie> executeQueryFindAll(int page, int maxResults) {
        final PageRequest pageRequest = new PageRequest(page, maxResults, sortByNazwaASC());

        return ograniczenieRepository.findAll(pageRequest);
    }

    private Sort sortByNazwaASC() {
        return new Sort(Sort.Direction.ASC, "nazwa");
    }

    private OgraniczenieListVO buildResult(Page<Ograniczenie> result) {
        return new OgraniczenieListVO(result.getTotalPages(), result.getTotalElements(), result.getContent());
    }

    private Page<Ograniczenie> executeQueryFindByZadanie(int page, int maxResults, int zadanie) {
        final PageRequest pageRequest = new PageRequest(page, maxResults, sortByNazwaASC());

        return ograniczenieRepository.findByZadanieLike(pageRequest, zadanie);
    }

    private boolean isUserAfterOrOnLastPage(int page, Page<Ograniczenie> result) {
        return page >= result.getTotalPages() - 1;
    }

    private boolean hasDataInDataBase(Page<Ograniczenie> result) {
        return result.getTotalElements() > 0;
    }
}
package uaiContacts.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import uaiContacts.model.Rozwiazanie;

public interface RozwiazanieRepository extends PagingAndSortingRepository<Rozwiazanie, Integer> {
    /*Page<Rozwiazanie> findByJezykLike(Pageable pageable, String jezyk);*/
	Page<Rozwiazanie> findByAutorLike(Pageable pageable, int autor);
}
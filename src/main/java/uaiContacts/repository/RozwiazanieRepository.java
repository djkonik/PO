package uaiContacts.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import uaiContacts.model.Rozwiazanie;
import uaiContacts.model.User;

public interface RozwiazanieRepository extends PagingAndSortingRepository<Rozwiazanie, Integer> {
	Page<Rozwiazanie> findByAutorLike(Pageable pageable, User autor);
}
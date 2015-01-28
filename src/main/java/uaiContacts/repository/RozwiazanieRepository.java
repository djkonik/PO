package uaiContacts.repository;

import model.entity.Rozwiazanie;
import model.entity.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RozwiazanieRepository extends PagingAndSortingRepository<Rozwiazanie, Integer> {
	Page<Rozwiazanie> findByAutorLike(Pageable pageable, User autor);
}
package uaiContacts.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import uaiContacts.model.User;
import uaiContacts.model.Zadanie;

public interface ZadanieRepository extends PagingAndSortingRepository<Zadanie, Integer> {
	Page<Zadanie> findByAutorLike(Pageable pageable, User autor);
}
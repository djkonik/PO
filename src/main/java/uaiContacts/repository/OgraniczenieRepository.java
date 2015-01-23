package uaiContacts.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import uaiContacts.model.Ograniczenie;

public interface OgraniczenieRepository extends PagingAndSortingRepository<Ograniczenie, Integer> {
	Page<Ograniczenie> findByZadanieLike(Pageable pageable, int zadanie);
}
package uaiContacts.repository;

import model.entity.Ograniczenie;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OgraniczenieRepository extends PagingAndSortingRepository<Ograniczenie, Integer> {
	Page<Ograniczenie> findByZadanieLike(Pageable pageable, int zadanie);
}
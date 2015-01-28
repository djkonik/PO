package uaiContacts.repository;

import model.entity.User;
import model.entity.Zadanie;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ZadanieRepository extends PagingAndSortingRepository<Zadanie, Integer> {
	Zadanie findByIdLike(int id);
	Page<Zadanie> findByAutorLike(Pageable pageable, User autor);
}
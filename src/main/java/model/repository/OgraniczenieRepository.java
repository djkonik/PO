package model.repository;

import model.entity.Ograniczenie;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface OgraniczenieRepository extends PagingAndSortingRepository<Ograniczenie, Integer> {
}
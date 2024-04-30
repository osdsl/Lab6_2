package com.example.lab5.Repositories;

import com.example.lab5.Repositories.Entities.TvEntity;

import org.springframework.data.repository.CrudRepository;



public interface TvRepository extends CrudRepository<TvEntity, Integer> {
}

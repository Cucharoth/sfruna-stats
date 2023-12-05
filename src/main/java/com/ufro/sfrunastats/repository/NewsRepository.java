package com.ufro.sfrunastats.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ufro.sfrunastats.model.News;

@Repository
public interface NewsRepository extends CrudRepository<News, Integer> {

}

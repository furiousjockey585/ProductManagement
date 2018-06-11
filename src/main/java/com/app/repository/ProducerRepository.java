package com.app.repository;

import com.app.model.Producer;

import java.util.List;
import java.util.Optional;

public interface ProducerRepository {
    void addProducer(Producer producer);
    void updateProducer(Producer producer);
    void deleteProducer(Integer id);
    Optional<Producer> findOneById(Integer id);
    Optional<Producer> findOneByName(String name);
    List<Producer> findAll();
}


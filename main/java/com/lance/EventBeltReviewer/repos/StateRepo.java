package com.lance.EventBeltReviewer.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.lance.EventBeltReviewer.models.State;

public interface StateRepo extends CrudRepository<State, Long> {

	List <State> findAll();
}

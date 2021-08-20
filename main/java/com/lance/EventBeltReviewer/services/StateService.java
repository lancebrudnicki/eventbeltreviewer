package com.lance.EventBeltReviewer.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lance.EventBeltReviewer.models.State;
import com.lance.EventBeltReviewer.repos.StateRepo;

@Service
public class StateService {
	private final StateRepo stateRepo;
	
	public StateService(StateRepo stateRepo) {
		this.stateRepo = stateRepo;
	}
	
	public List<State> getAllStates(){
		return stateRepo.findAll();
	}
	
}

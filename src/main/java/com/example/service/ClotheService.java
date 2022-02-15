package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Clothe;
import com.example.repository.ClotheRepository;

@Service
@Transactional
public class ClotheService {
	@Autowired
	private ClotheRepository repository;
	
	public List<Clothe> searchSelect(Integer gender,String color){
		List<Clothe> clotheList=repository.searchSelect(gender,color);
		return clotheList;
		
	}
}

package com.pozatticaique.projeto24.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pozatticaique.projeto24.DTOs.UserDTO;
import com.pozatticaique.projeto24.entities.User;
import com.pozatticaique.projeto24.repositories.UserRepository;
import com.pozatticaique.projeto24.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional (readOnly = true) //Import spring, não jakart
	public List<User> findAll(){
		List<User> result = userRepository.findAll();
		return result;
	}
	
	@Transactional (readOnly = true) //Import spring, não jakart
	public User findById(String id){
		try {
			User result = userRepository.findById(id).get();
			return result;	
		}catch(NoSuchElementException e) {
			throw new ObjectNotFoundException(id);
		}
	}	
	
	@Transactional
	public User insert(User obj) {
		return userRepository.insert(obj);
	}
	
	@Transactional
	public void delete(String id) {
		findById(id);
		userRepository.deleteById(id);
	}
	
	public User update(User obj) {
		User newUser = userRepository.findById(obj.getId()).get();
		UpadateData(newUser, obj);
		return userRepository.save(newUser);		
	}
	

	private void UpadateData(User newUser, User obj) {
		newUser.setName(obj.getName());
		newUser.setEmail(obj.getEmail());
	}

	@Transactional
	public User fromDTO(UserDTO obj) {
		return new User(obj.getId(), obj.getName(), obj.getEmail());
	}
}

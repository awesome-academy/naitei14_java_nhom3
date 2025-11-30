package org.example.framgiabookingtours.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.framgiabookingtours.entity.User;
import org.example.framgiabookingtours.repository.UserRepository;
import org.example.framgiabookingtours.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Override
	public Page<User> getAllUsers(Pageable pageable) {
		return userRepository.findAll(pageable);
	}
}
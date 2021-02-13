package com.stackroute.newz.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.newz.model.UserProfile;
import com.stackroute.newz.repository.UserProfileRepository;
import com.stackroute.newz.util.exception.UserProfileAlreadyExistsException;
import com.stackroute.newz.util.exception.UserProfileNotExistsException;

/*
 * This class is implementing the UserProfileRepository interface. This class has to be annotated with 
 * @Service annotation.
 * @Service - is an annotation that annotates classes at the service layer, thus 
 * clarifying it's role.
 * 
 * */
@Service
@Transactional
public class UserProfileServiceImpl implements UserProfileService {

	/*
	 * Autowiring should be implemented for the UserProfileRepository.
	 */
	@Autowired
	UserProfileRepository userRepo;

	/*
	 * Add a new user. Throw UserProfileAlreadyExistsException if the userProfile
	 * with specified userId already exists.
	 */
	public UserProfile registerUser(UserProfile user) throws UserProfileAlreadyExistsException {
		UserProfile added = null;
		Optional<UserProfile> check = userRepo.findById(user.getUserId());
		if (check.isPresent()) {
			throw new UserProfileAlreadyExistsException();
		} else {
			added = userRepo.save(user);
		}
		return added;
	}

	/*
	 * Update an existing userProfile by it's userId. Throw
	 * UserProfileNotExistsException if the userProfile with specified userId does
	 * not exist.
	 */
	public UserProfile updateUserProfile(UserProfile user, String userId) throws UserProfileNotExistsException {
		UserProfile search = userRepo.getOne(userId);
		if (search == null) {
			throw new UserProfileNotExistsException();
		} else {
			search.setFirstName(user.getFirstName());
			search.setLastName(user.getLastName());
			search.setContact(user.getContact());
			search.setCreateAt(LocalDateTime.now());
			userRepo.save(search);
		} 
		return search;
	}

	/*
	 * Delete an existing userProfile by it's userId. Throw
	 * UserProfileNotExistsException if the userProfile with specified userId does
	 * not exist.
	 */
	public void deleteUserProfile(String userId) throws UserProfileNotExistsException {
		UserProfile search = userRepo.getOne(userId);
		if (search == null) {
			throw new UserProfileNotExistsException();
		} else {
			userRepo.deleteById(userId);
		}
	}

	/*
	 * Retrieve an existing userProfile by it's userId. Throw
	 * UserProfileNotExistsException if the userProfile with specified userId does
	 * not exist.
	 */
	public UserProfile getUserProfile(String userId) throws UserProfileNotExistsException {
		UserProfile search = null;
		Optional<UserProfile> check = userRepo.findById(userId);
		if (check.isPresent()) {
			search = check.get();
		} else {
			throw new UserProfileNotExistsException();
		}
		return search;
	}

	/*
	 * Retrieve all existing userProfiles
	 */
	public List<UserProfile> getAllUserProfiles() {
		List<UserProfile> userList = userRepo.findAll();
		return userList;
	}

}

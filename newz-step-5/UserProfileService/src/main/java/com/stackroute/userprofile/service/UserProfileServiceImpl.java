package com.stackroute.userprofile.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.userprofile.model.UserProfile;
import com.stackroute.userprofile.repository.UserProfileRepository;
import com.stackroute.userprofile.util.exception.UserProfileAlreadyExistsException;
import com.stackroute.userprofile.util.exception.UserProfileNotFoundException;

/*
* Service classes are used here to implement additional business logic/validation 
* This class has to be annotated with @Service annotation.
* @Service - It is a specialization of the component annotation. It doesn't currently 
* provide any additional behavior over the @Component annotation, but it's a good idea 
* to use @Service over @Component in service-layer classes because it specifies intent 
* better. Additionally, tool support and additional behavior might rely on it in the 
* future.
* */
@Service
public class UserProfileServiceImpl implements UserProfileService {

	/*
	 * Autowiring should be implemented for the UserProfileRepository. (Use
	 * Constructor-based autowiring) Please note that we should not create any
	 * object using the new keyword.
	 */

	@Autowired
	UserProfileRepository userProfileRepository;

	public UserProfileServiceImpl(UserProfileRepository userProfileRepository) {
		this.userProfileRepository = userProfileRepository;
	}
	/*
	 * This method should be used to save a new userprofile.Call the corresponding
	 * method of Respository interface.
	 */

	public UserProfile registerUser(UserProfile user) throws UserProfileAlreadyExistsException {
		user.setCreatedAt();
		UserProfile added = userProfileRepository.insert(user);
		if (added != null) {
			return added;
		} else {
			throw new UserProfileAlreadyExistsException("User Profile Already Exists");
		}
	}

	/*
	 * This method should be used to update a existing userprofile.Call the
	 * corresponding method of Respository interface.
	 */

	@Override
	public UserProfile updateUser(String userId, UserProfile user) throws UserProfileNotFoundException {
		try {
			UserProfile target = getUserById(userId);
			target.setFirstName(user.getFirstName());
			target.setLastName(user.getLastName());
			target.setContact(user.getContact());
			target.setEmail(user.getEmail());
			target.setCreatedAt();
			userProfileRepository.save(target);
			return target;
		} catch (Exception e) {
			throw new UserProfileNotFoundException("User Profile Not Found");
		}
	}

	/*
	 * This method should be used to delete an existing user. Call the corresponding
	 * method of Respository interface.
	 */

	@Override
	public boolean deleteUser(String userId) throws UserProfileNotFoundException {
		UserProfile search = getUserById(userId);
		if (search != null) {
			userProfileRepository.deleteById(userId);
			return true;
		}
		return false;
	}

	/*
	 * This method should be used to get userprofile by userId.Call the
	 * corresponding method of Respository interface.
	 */

	@Override
	public UserProfile getUserById(String userId) throws UserProfileNotFoundException {
		UserProfile search = null;
		try {
			Optional<UserProfile> target = userProfileRepository.findById(userId);
			if (target.isPresent()) {
				search = target.get();
			}
		} catch (Exception e) {
			throw new UserProfileNotFoundException("User Profile Not Found");
		}
		return search;
	}
}

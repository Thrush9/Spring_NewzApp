package com.stackroute.userprofile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.userprofile.model.UserProfile;
import com.stackroute.userprofile.service.UserProfileService;
import com.stackroute.userprofile.util.exception.UserProfileAlreadyExistsException;
import com.stackroute.userprofile.util.exception.UserProfileNotFoundException;

/*
 * As in this assignment, we are working on creating RESTful web service, hence annotate
 * the class with @RestController annotation. A class annotated with the @Controller annotation
 * has handler methods which return a view. However, if we use @ResponseBody annotation along
 * with @Controller annotation, it will return the data directly in a serialized 
 * format. Starting from Spring 4 and above, we can use @RestController annotation which 
 * is equivalent to using @Controller and @ResposeBody annotation
 */

@RestController
public class UserProfileController {

	/*
	 * Autowiring should be implemented for the UserService. (Use Constructor-based
	 * autowiring) Please note that we should not create an object using the new
	 * keyword
	 */
	@Autowired
	UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
       this.userProfileService = userProfileService;
    }

	/*
	 * Define a handler method which will create a specific userprofile by reading the
	 * Serialized object from request body and save the user details in the
	 * database. This handler method should return any one of the status messages
	 * basis on different situations:
	 * 1. 201(CREATED) - If the userprofile created successfully. 
	 * 2. 409(CONFLICT) - If the userId conflicts with any existing user, return the 
	 * UserProfileAlreadyExistsException along with the status
	 * 
	 * This handler method should map to the URL "/api/v1/user" using HTTP POST method
	 */
   
    @PostMapping("/api/v1/user")
	public ResponseEntity<?> addUser(@RequestBody() UserProfile user) {
		try {
			UserProfile addedUser = userProfileService.registerUser(user);
			return new ResponseEntity<String>("UserProfile Created", HttpStatus.CREATED);
		} catch (UserProfileAlreadyExistsException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}

	/*
	 * Define a handler method which will update a specific userprofile by reading the
	 * Serialized object from request body and save the updated user details in a
	 * database. This handler method should return any one of the status messages
	 * basis on different situations: 
	 * 1. 200(OK) - If the userprofile is updated successfully.
	 * 2. 404(NOT FOUND) - If the userprofile with specified userId is not found,return the 
	 * UserProfileNotFoundException along with the status
	 * 
	 * This handler method should map to the URL "/api/v1/userprofile/{userid}" using HTTP PUT method.
	 */

    @PutMapping("/api/v1/userprofile/{userid}")
	public ResponseEntity<?> updateUserProfile(@RequestBody() UserProfile user,@PathVariable("userid") String userId) {
		try {
			UserProfile updatedUser = userProfileService.updateUser(userId, user);
			return new ResponseEntity<String>("UserProfile Updated", HttpStatus.OK);
		} catch (UserProfileNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
    
    
	/*
	 * Define a handler method which will delete an userprofile from a database.
	 * This handler method should return any one of the status messages basis on
	 * different situations: 
	 * 1. 200(OK) - If the userprofile is deleted successfully from database. 
	 * 2. 404(NOT FOUND) - If the user with specified userId is not found, return 
	 * the UserProfileNotFoundException along with the status.
	 *
	 * This handler method should map to the URL "/api/v1/userprofile/{userId}" using 
	 * HTTP Delete method where "userId" should be replaced by a valid userId
	 * 
	 */

    @DeleteMapping("/api/v1/userprofile/{userid}")
   	public ResponseEntity<?> deleteUserProfile(@PathVariable("userid") String userId) {
   		try {
   			boolean deleted = userProfileService.deleteUser(userId);
   			if (deleted) {
   				return new ResponseEntity<String>("UserProfile Deleted", HttpStatus.OK);
   			} else {
   				return new ResponseEntity<String>("UserProfile Not Deleted", HttpStatus.NOT_FOUND);	
   			}
   		} catch (UserProfileNotFoundException e) {
   			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
   		}
   	}
    
	/*
	 * Define a handler method which will show details of a specific user. This
	 * handler method should return any one of the status messages basis on
	 * different situations: 
	 * 1. 200(OK) - If the userprofile found successfully. 
	 * 2. 404(NOT FOUND) - If the userprofile with specified userId is not found, return 
	 * UserProfileNotFoundException message along with the status.
	 * This handler method should map to the URL "/api/v1/userprofile/{userId}" using 
	 * HTTP GET method where "id" should be replaced by a valid userId without {}.
	 */


    @GetMapping("/api/v1/userprofile/{userid}")
   	public ResponseEntity<?> findUserProfile(@PathVariable("userid") String userId) {
   		try {
   			UserProfile search = userProfileService.getUserById(userId);
   				return new ResponseEntity<String>("UserProfile Found", HttpStatus.OK);
   		} catch (UserProfileNotFoundException e) {
   			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
   		}
   	}


}

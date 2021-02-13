package com.stackroute.newz.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.newz.model.Reminder;
import com.stackroute.newz.repository.ReminderRepository;
import com.stackroute.newz.service.ReminderService;
import com.stackroute.newz.util.exception.ReminderNotExistsException;

/*
 * This class is implementing the ReminderService interface. This class has to be annotated with 
 * @Service annotation.
 * @Service - is an annotation that annotates classes at the service layer, thus 
 * clarifying it's role.
 * 
 * */
@Service
public class ReminderServiceImpl implements ReminderService {

	/*
	 * Autowiring should be implemented for the ReminderRepository.
	 */
	@Autowired
	ReminderRepository reminderRepo;

	/*
	 * Add a new reminder.
	 */
	public Reminder addReminder(Reminder reminder) {
        Reminder added = reminderRepo.save(reminder);
		return added;
	}

	/*
	 * Update an existing reminder by it's reminderId. Throw ReminderNotExistsException 
	 * if the reminder with specified reminderId does not exist.
	 */
	public Reminder updateReminder(Reminder reminder) throws ReminderNotExistsException {
		Reminder search = reminderRepo.getOne(reminder.getReminderId());
        if(search == null) {
       	 throw new ReminderNotExistsException();
        } else {
       	 reminderRepo.saveAndFlush(reminder);
        }
		return search;

	}

	/*
	 * Delete an existing reminder by it's reminderId. Throw ReminderNotExistsException if 
	 * the reminder with specified reminderId does not exist.
	 */
	public void deleteReminder(int reminderId) throws ReminderNotExistsException {
         Reminder search = reminderRepo.getOne(reminderId);
         if(search == null) {
        	 throw new ReminderNotExistsException();
         } else {
        	 reminderRepo.deleteById(reminderId);
         }
	
	}

	/*
	 * Retrieve an existing reminder by it's reminderId. Throw ReminderNotExistsException 
	 * if the reminder with specified reminderId does not exist.
	 */
	public Reminder getReminder(int reminderId) throws ReminderNotExistsException {
        Reminder search = null;
        Optional<Reminder> check = reminderRepo.findById(reminderId);
        if (check.isPresent()) {
			search = check.get();
		} else {
			throw new ReminderNotExistsException();
		}
		return search;
	}

	/*
	 * Retrieve all existing reminders
	 */
	public List<Reminder> getAllReminders() {
		List<Reminder>  reminderList = reminderRepo.findAll();
		return reminderList;
	}

}

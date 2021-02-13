package com.stackroute.newz.service;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.newz.model.NewsSource;
import com.stackroute.newz.repository.NewsSourceRepository;
import com.stackroute.newz.util.exception.NewsSourceNotFoundException;

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
public class NewsSourceServiceImpl implements NewsSourceService {

	/*
	 * Autowiring should be implemented for the NewsDao and MongoOperation.
	 * (Use Constructor-based autowiring) Please note that we should not create any
	 * object using the new keyword.
	 */

	@Autowired
	NewsSourceRepository newsSourceRepository;
	
	public NewsSourceServiceImpl(NewsSourceRepository newsSourceRepository) {
		this.newsSourceRepository = newsSourceRepository;
	}

	/*
	 * This method should be used to save a newsSource.
	 */

	@Override
	public boolean addNewsSource(NewsSource newsSource) {
		newsSource.setNewsSourceCreationDate();
		NewsSource added = newsSourceRepository.insert(newsSource);
		if (added != null) {
			return true;
		} else {
			return false;
		}
	}

	/* This method should be used to delete an existing newsSource. */

	@Override
	public boolean deleteNewsSource(int newsSourceId) {
		try {
			Optional<NewsSource> search = newsSourceRepository.findById(newsSourceId);
			if (search.isPresent()) {
				newsSourceRepository.deleteById(newsSourceId);
				return true;
			}
		} catch (NullPointerException e) {
			return false;
		}
		return false;
	}

	/* This method should be used to update an existing newsSource. */
	
	@Override
	public NewsSource updateNewsSource(NewsSource newsSource, int newsSourceId) throws NewsSourceNotFoundException {
		try {
			Optional<NewsSource> oldSource = newsSourceRepository.findById(newsSourceId);
			if (oldSource.isPresent()) {
				NewsSource updated = oldSource.get();
				updated.setNewsSourceCreationDate();
				updated.setNewsSourceCreatedBy(newsSource.getNewsSourceCreatedBy());
				updated.setNewsSourceDesc(newsSource.getNewsSourceDesc());
				updated.setNewsSourceName(newsSource.getNewsSourceName());
				newsSourceRepository.save(updated);
				return updated;
			}
		} catch (NullPointerException e) {
			throw new NewsSourceNotFoundException("News Source Not Found");
		}
		return newsSource;
	}

	/* This method should be used to get a specific newsSource for an user. */

	@Override
	public NewsSource getNewsSourceById(String userId, int newsSourceId) throws NewsSourceNotFoundException {
		NewsSource specificNewsSource = null;
		try {
			List<NewsSource> newsSourceList = newsSourceRepository.findAllNewsSourceByNewsSourceCreatedBy(userId);
			Iterator<NewsSource> iterator = newsSourceList.iterator();
			while (iterator.hasNext()) {
				NewsSource source = iterator.next();
				if (source.getNewsSourceId() == newsSourceId) {
					specificNewsSource = source;
				}
			}
			if (specificNewsSource == null) {
				throw new NewsSourceNotFoundException("News Source Not Found");
			} else {
				return specificNewsSource;
			}
		} catch (NoSuchElementException e) {
			// throw new NoSuchElementException("News Source Not Found");
		}
		return specificNewsSource;
	}

	
	 /* This method should be used to get all newsSource for a specific userId.*/

	@Override
	public List<NewsSource> getAllNewsSourceByUserId(String createdBy) {
		List<NewsSource> newsSourceList = newsSourceRepository.findAllNewsSourceByNewsSourceCreatedBy(createdBy);
		return newsSourceList;
	}

}

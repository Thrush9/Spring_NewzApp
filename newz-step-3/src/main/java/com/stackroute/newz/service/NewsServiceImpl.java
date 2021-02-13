package com.stackroute.newz.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.newz.model.News;
import com.stackroute.newz.repository.NewsRepository;
import com.stackroute.newz.service.NewsService;
import com.stackroute.newz.util.exception.NewsAlreadyExistsException;
import com.stackroute.newz.util.exception.NewsNotExistsException;


/*
 * This class is implementing the NewsService interface. This class has to be annotated with 
 * @Service annotation.
 * @Service - is an annotation that annotates classes at the service layer, thus 
 * clarifying it's role.
 * 
 * */

@Service
public class NewsServiceImpl implements NewsService {

	/*
	 * Autowiring should be implemented for the NewsRepository.
	 */
	@Autowired
	NewsRepository newsRepo;

	/*
	 * Add a new news. Throw NewsAlreadyExistsException if the news with specified
	 * newsId already exists.
	 */
	public News addNews(News news) throws NewsAlreadyExistsException {
		News added = null;
		News check = newsRepo.getOne(news.getNewsId());
		if(check != null ) {
			throw new NewsAlreadyExistsException();
		} else {
			added = newsRepo.save(news);
		}
		return added;
	}

	/*
	 * Retrieve an existing news by it's newsId. Throw NewsNotExistsException if the 
	 * news with specified newsId does not exist.
	 */
	public News getNews(int newsId) throws NewsNotExistsException {
		News search = null;
		Optional<News> check = newsRepo.findById(newsId);
		if (check.isPresent()) {
			search = check.get();
		} else {
			throw new NewsNotExistsException();
		}
		return search;
	}

	/*
	 * Retrieve all existing news
	 */
	public List<News> getAllNews() {
		List<News> newsList = newsRepo.findAll();
		return newsList;
	}

	
	/*
	 * Update an existing news by it's newsId. Throw NewsNotExistsException if the 
	 * news with specified newsId does not exist.
	 */
	public News updateNews(News news) throws NewsNotExistsException {
		 News search = newsRepo.getOne(news.getNewsId());
	       if(search == null) {
	    	   throw new NewsNotExistsException();
	       } else {
	    	   newsRepo.saveAndFlush(news);
	       }
		return search;
	}

	/*
	 * Delete an existing news by it's newsId. Throw NewsNotExistsException if the 
	 * news with specified newsId does not exist.
	 */
	public void deleteNews(int newsId) throws NewsNotExistsException {
       News search = newsRepo.getOne(newsId);
       if(search == null) {
    	   throw new NewsNotExistsException();
       } else {
    	   newsRepo.deleteById(newsId);;
       }

	}

}

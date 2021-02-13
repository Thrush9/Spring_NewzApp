package com.stackroute.newz.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.newz.model.News;
import com.stackroute.newz.model.NewsSource;
import com.stackroute.newz.model.UserNews;
import com.stackroute.newz.repository.NewsRepository;
import com.stackroute.newz.util.exception.NewsNotFoundException;

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
public class NewsServiceImpl implements NewsService {

	/*
	 * Autowiring should be implemented for the NewsDao and MongoOperation.
	 * (Use Constructor-based autowiring) Please note that we should not create any
	 * object using the new keyword.
	 */
	
	@Autowired
	NewsRepository newsRepo;
	
	public NewsServiceImpl(NewsRepository newsRepository) {
		this.newsRepo = newsRepository;
	}


	/*
	 * This method should be used to save a new news.
	 */
	@Override
	public boolean addNews(News news) {
		List<News> newsList = new ArrayList<>();
		newsList.add(news);
		NewsSource ns = new NewsSource();
		ns.setNewsSourceId(news.getNewsSource().getNewsSourceId());
		ns.setNewsSourceName(news.getNewsSource().getNewsSourceName());
		ns.setNewsSourceDesc(news.getNewsSource().getNewsSourceDesc());
		ns.setNewsSourceCreatedBy(news.getNewsSource().getNewsSourceCreatedBy());
		ns.setNewsSourceCreationDate();
		news.setNewsSource(ns);
		UserNews user = new UserNews();
		//user.setUserId(news.getAuthor());
		user.setUserId(news.getNewsSource().getNewsSourceCreatedBy());
		user.setNewslist(newsList);
		UserNews added = newsRepo.insert(user);
		if (added != null) {
			return true;
		} else {
			return false;
		}
	}

	/* This method should be used to delete an existing news. */
	
	public boolean deleteNews(String userId, int newsId) {
		try {
			Optional<UserNews> search = newsRepo.findById(userId);
			if (search.isPresent()) {
				UserNews target = search.get();
				List<News> newsList = target.getNewslist();
				Iterator<News> iterator = newsList.iterator();
				while(iterator.hasNext()) {
					News tagetNews = iterator.next();
					if(tagetNews.getNewsId() == newsId) {
						iterator.remove();
					}
				}
				target.setNewslist(newsList);
				newsRepo.save(target);
				return true;
			}
		} catch (NullPointerException e) {
           throw new NullPointerException("NewId not found");
		}
		return false;
	}

	/* This method should be used to delete all news for a  specific userId. */
	
	public boolean deleteAllNews(String userId) throws NewsNotFoundException {
		try {
			Optional<UserNews> search = newsRepo.findById(userId);
			if (search.isPresent()) {
				UserNews target = search.get();
				//List<News> newsList = new  ArrayList<>();
				//target.setNewslist(newsList);
				//newsRepository.save(target);
				newsRepo.delete(target);				
                return true;         
			}
		} catch (NoSuchElementException e) {
			throw new NewsNotFoundException("News Not Found");
		}
		return false;
	}

	/*
	 * This method should be used to update a existing news.
	 */

	public News updateNews(News news, int newsId, String userId) throws NewsNotFoundException {
		try {
			Optional<UserNews> search = newsRepo.findById(userId);
			if (search.isPresent()) {
				UserNews target = search.get();
				List<News> newsList = target.getNewslist();
				News oldNews = null;
				Iterator<News> iterator = newsList.iterator();
				while(iterator.hasNext()) {
					News targetNews = iterator.next();
					if(targetNews.getNewsId() == newsId) {
						oldNews = targetNews;
					}
				}
				oldNews.setTitle(news.getTitle());
				oldNews.setAuthor(news.getAuthor());
				oldNews.setContent(news.getContent());
				oldNews.setDescription(news.getDescription());
				oldNews.setPublishedAt();
				oldNews.setUrl(news.getUrl());
				oldNews.setUrlToImage(news.getUrlToImage());
				oldNews.setReminder(news.getReminder());
				oldNews.setNewsSource(news.getNewsSource());
				target.setNewslist(newsList);
				newsRepo.save(target);
			}
		} catch (NoSuchElementException e) {
           throw new NewsNotFoundException("NewId not found");
		}
		return news;
	}

	/*
	 * This method should be used to get a news by newsId created by specific user
	 */

	public News getNewsByNewsId(String userId, int newsId) throws NewsNotFoundException {
		News result = null;
		try {
			Optional<UserNews> search = newsRepo.findById(userId);
			if (search.isPresent()) {
				UserNews target = search.get();
				List<News> newsList = target.getNewslist();
				News check = null;
				Iterator<News> iterator = newsList.iterator();
				while(iterator.hasNext()) {
					News targetNews = iterator.next();
					if(targetNews.getNewsId() == newsId) {
						check = targetNews;
					}
				}
				return check;
			}
		} catch (NoSuchElementException e) {
           throw new NewsNotFoundException("NewId not found");
		}
		return result;
	}

	/*
	 * This method should be used to get all news for a specific userId.
	 */

	public List<News> getAllNewsByUserId(String userId) {
		List<News> newsListByUser = new ArrayList<>();
		try {
			Optional<UserNews> search = newsRepo.findById(userId);
			if (search.isPresent()) {
				UserNews target = search.get();
				newsListByUser = target.getNewslist();
			}
		} catch (Exception e) {

		}
		return newsListByUser;
	}

}

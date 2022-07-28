package com.KMS.java.BAM.service;

import java.util.List;

import com.KMS.java.BAM.container.Container;
import com.KMS.java.BAM.dao.ArticleDao;
import com.KMS.java.BAM.dto.Article;

public class ArticleService {
	
	private ArticleDao articleDao;
	public ArticleService() {
		this.articleDao = Container.articleDao;
	}
	
	public List<Article>getForPrintAritcles(String searchKeyword){
		List<Article> articles = Container.articleDao.getArticles(searchKeyword);
		return articles;
	}
	
	public int setNewId() {
		int id = articleDao.setNewId();
		return id;
	}
	public void add(Article article) {
		articleDao.add(article);
	}
	public Article getArticleById(int id) {
		return articleDao.getArticleById(id);
	}
	
	public void remove(Article foundArticle) {
		articleDao.remove(foundArticle);
	}
	public int getArticleIndexById(int id) {
		return articleDao.getArticleIndexById(id);
	}
	public boolean matchId(int id) {
		return articleDao.matchId(id);
	}

}

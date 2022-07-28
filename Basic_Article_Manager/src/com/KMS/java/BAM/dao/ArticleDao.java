package com.KMS.java.BAM.dao;

import java.util.ArrayList;
import java.util.List;

import com.KMS.java.BAM.dto.Article;

public class ArticleDao extends Dao{
	private static List<Article> articles;
	
	public ArticleDao(){
		articles =new ArrayList<>();
	}

	public List<Article> getArticles(String searchKeyword) {
		if(searchKeyword != null && searchKeyword.length() != 0) {
			List<Article> forPrintArticles = new ArrayList<>();
			if(searchKeyword.length() >0) {
				System.out.printf("검색어 : %s\n",searchKeyword);
				for(Article article : articles) {
					if(article.title.contains(searchKeyword)) {
						forPrintArticles.add(article);
					}
				}
			}
			return forPrintArticles;
		}
		return articles;
	}
	public static int getArticleIndexById(int id) {
		int i = 0;
		for (Article article : articles) {

			if (article.id == id) {
				return i;
			}
			i++;
		}
		return -1;
	}

	public static Article getArticleById(int id) {
		int index = getArticleIndexById(id);

		if (index != -1) {
			return articles.get(index);
		}

		return null;
	}
	public void remove(Article foundArticle) {
		articles.remove(foundArticle);
	}

	public void add(Article article) {
		articles.add(article);
		lastId++;
	}
	
	public boolean matchId(int id) {
		Article foundArticle = ArticleDao.getArticleById(id);
		if(foundArticle.writeid == id) {
			return true;
		}
		return false;
	}

}

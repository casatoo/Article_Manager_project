package com.KMS.java.BAM.container;

import com.KMS.java.BAM.dao.ArticleDao;
import com.KMS.java.BAM.dao.MemberDao;

public class Container {
	public static ArticleDao articleDao;
	public static MemberDao	memberDao;
	
	static {
		articleDao = new ArticleDao();
		memberDao = new MemberDao();
	}

}

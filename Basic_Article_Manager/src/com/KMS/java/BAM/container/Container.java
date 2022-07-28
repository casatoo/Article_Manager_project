package com.KMS.java.BAM.container;

import java.util.List;

import com.KMS.java.BAM.dao.ArticleDao;
import com.KMS.java.BAM.dao.MemberDao;
import com.KMS.java.BAM.dto.Member;
import com.KMS.java.BAM.service.ArticleService;
import com.KMS.java.BAM.service.MemberService;

public class Container {
	public static ArticleDao articleDao;
	public static MemberDao	memberDao;
	public static ArticleService articleService;
	public static MemberService memberService;
	
	static {
		articleDao = new ArticleDao();
		memberDao = new MemberDao();
		articleService = new ArticleService();
		memberService = new MemberService();
	}

}

package com.KMS.java.BAM.controller;

import java.util.ArrayList;
import java.util.List;

import com.KMS.java.BAM.dto.Article;
import com.KMS.java.BAM.dto.Member;

public abstract class Controller {
	
	public static Member loginedMember = null;
	
	public static boolean logincheck() {
		if(loginedMember != null) {
		return true;
		}
		return false;
	}
	public abstract void makeTestData();
	
	public abstract void doAction(String cmd, String actionMethodName);
	
}

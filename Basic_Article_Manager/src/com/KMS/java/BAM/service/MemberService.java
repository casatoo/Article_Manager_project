package com.KMS.java.BAM.service;

import com.KMS.java.BAM.container.Container;
import com.KMS.java.BAM.dao.MemberDao;
import com.KMS.java.BAM.dto.Member;

public class MemberService {
	
	private MemberDao memberDao;
	
	public MemberService() {
		this.memberDao = Container.memberDao;
	}
	
	public void add(Member member) {
		memberDao.add(member);
	}

	public int setNewId() {
		
		return memberDao.setNewId();
	}

	public boolean isJoinableLoginId(String loginId) {
		return memberDao.isJoinableLoginId(loginId);
	}

	public Member getMemberByLoginId(String loginId) {

		return memberDao.getMemberByLoginId(loginId);
	}

	public int getMemberIndexByLoginId(String loginId) {
		return memberDao.getMemberIndexByLoginId(loginId);
	}

	public void remove(int id){
		memberDao.remove(id);
	}
	public String getMemberNameById(int writeid) {

		return memberDao.getMemberNameById(writeid);
	}
	
	
}

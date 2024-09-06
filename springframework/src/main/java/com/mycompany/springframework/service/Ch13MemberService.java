package com.mycompany.springframework.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mycompany.springframework.dao.mybatis.Ch13MemberDao;
import com.mycompany.springframework.dto.Ch13Member;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Ch13MemberService {
	
	public enum JoinResult{		//  회원가입 할 때 발생할 수 있는 상황 2가지
		SUCCESS,
		FAIL_DUPLICATED_MID		
	}
	
	public enum LoginResult {
		SUCCESS,
		FAIL_MID,
		FAIL_MPASSWORD,
		FAIL_ENABLED
	}
	
	@Resource
	private Ch13MemberDao memberDao;
	
	// 회원가입
	public JoinResult join(Ch13Member member) {
		boolean exist = isMid(member.getMid());
		if(exist) {				// 이미 DB에 존재하는 id면 enum타입의 fail 반환 
			return JoinResult.FAIL_DUPLICATED_MID;
		}
		memberDao.insert(member);
		return JoinResult.SUCCESS;	
	}
	// 아이디 중복 체크
	public boolean isMid(String mid) {
		Ch13Member member = memberDao.selectByMid(mid);	// DB에서 mid를 가져오기
		if(member == null) {	// id가 DB에 없으면 false
			return false;
		} else {
			return true;
		}
	}
	// 로그인
	public LoginResult login(Ch13Member member) {
		Ch13Member dbMember = memberDao.selectByMid(member.getMid());	// DB에서 mid를 가져오기
		if(dbMember == null) {	
			return LoginResult.FAIL_MID;	
		}
		if (!dbMember.isMenabled()) {
			return LoginResult.FAIL_ENABLED;
		}
		if (!dbMember.getMpassword().equals(member.getMpassword())) {
			return LoginResult.FAIL_MPASSWORD;
		}
		return LoginResult.SUCCESS;
	}
	// 로그아웃
	public void logout(String mid) {
		
	}
}

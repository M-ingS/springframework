package com.mycompany.springframework.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mycompany.springframework.dao.mybatis.Ch13MemberDao;
import com.mycompany.springframework.dto.Ch13Member;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Ch17UserDetailService implements UserDetailsService{
	
	@Autowired
	private Ch13MemberDao memberDao;
	
	@Override					// Username: 사용자 id
	public UserDetails loadUserByUsername(String username) // 사용자의 id로 유저 정보를 가져옴
			throws UsernameNotFoundException {
	
		Ch13Member member = memberDao.selectByMid(username);
		if(member == null) {
			throw new UsernameNotFoundException("Bad username");
		}
		
		// user가 가지는 권한을 객체화하여 가져옴
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(member.getMrole()));
		
		UserDetails userDetails = new Ch17UserDetails(member, authorities);
		
		return userDetails;
		// 스프링시큐리티에서 사용자 정보를 가져오는 클래스는 UserDetailsService를 상속해야함
		// UserDetails를 리턴해야 사용자 정보를 가져옴
	}
	
}

package net.daum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.daum.dao.MemberDAO;
import net.daum.vo.MemberVO;
import net.daum.vo.ZipcodeVO;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDAO memberDao;

	@Override
	public MemberVO idCheck(String id) {
		
		return this.memberDao.idCheck(id);
	}

	@Override
	public List<ZipcodeVO> zipFind(String dong) {
		
		return this.memberDao.zipFind(dong);
	}

	@Override
	public void insertMember(MemberVO m) {
		
		this.memberDao.insertMember(m);
	}

	@Override
	public MemberVO pwdMember(MemberVO m) {
		
		return this.memberDao.pwdMember(m);
	}

	@Override
	public void updatePwd(MemberVO m) {
		this.memberDao.updatePwd(m);
		
	}

	@Override
	public MemberVO loginCheck(String login_id) {
		
		return this.memberDao.loginCheck(login_id);
	}

	@Override
	public MemberVO getMember(String id) {
		
		return this.memberDao.getMember(id);
	}

	@Override
	public void updateMember(MemberVO m) {
		this.memberDao.updateMember(m);
		
	}
	
	
}

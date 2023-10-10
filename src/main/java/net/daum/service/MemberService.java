package net.daum.service;

import java.util.List;

import net.daum.vo.MemberVO;
import net.daum.vo.ZipcodeVO;

public interface MemberService {

	MemberVO idCheck(String id);

	List<ZipcodeVO> zipFind(String dong);

	void insertMember(MemberVO m);

}

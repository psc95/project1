package net.daum.dao;

import java.util.List;

import net.daum.vo.MemberVO;
import net.daum.vo.ZipcodeVO;

public interface MemberDAO {

	MemberVO idCheck(String id);

	List<ZipcodeVO> zipFind(String dong);

	void insertMember(MemberVO m);

}

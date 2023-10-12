package net.daum.dao;

import java.util.List;

import net.daum.vo.MemberVO;
import net.daum.vo.ZipcodeVO;

public interface MemberDAO {

	MemberVO idCheck(String id);

	List<ZipcodeVO> zipFind(String dong);

	void insertMember(MemberVO m);

	MemberVO pwdMember(MemberVO m);

	void updatePwd(MemberVO m);

	MemberVO loginCheck(String login_id);

	MemberVO getMember(String id);

	void updateMember(MemberVO m);

	void delMem(MemberVO dm);

}

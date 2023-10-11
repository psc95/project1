package net.daum.dao;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.daum.vo.MemberVO;
import net.daum.vo.ZipcodeVO;

@Repository
public class MemberDAOImpl implements MemberDAO {

	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private ZipcodeRepository zipcodeRepo;

	@Override
	public MemberVO idCheck(String id) {
		
		//return this.sqlSession.selectOne("m_idcheck", id);
		
		System.out.println(" \n ===================>아이디 중복 검색(JPA)");
		Optional<MemberVO> rm = this.memberRepo.findById(id);
		MemberVO member;
		if(rm.isPresent()) {//아이디에 해당하는 회원정보가 있다면 참
			member = rm.get();//MemberVO 엔티티 타입 객체를 구함
		}else {//회원정보가 없는 경우
			member = null;
		}
		return member;
	}//아이디 중복검색

	@Override
	public List<ZipcodeVO> zipFind(String dong) {
		
		//return this.sqlSession.selectList("m_zip", dong);
		
		System.out.println(" \n ===========>우편주소 검색(JPA)");
		List<ZipcodeVO> zlist = this.zipcodeRepo.findByDong(dong);
		return zlist;
	}//우편주소 검색

	@Override
	public void insertMember(MemberVO m) {
		//this.sqlSession.insert("m_in", m);
		
		System.out.println(" \n =============> 회원저장(JPA)");
		m.setMem_state(1);//가입 회원일때 1 저장
		this.memberRepo.save(m);
	}//회원 저장

	@Override
	public MemberVO pwdMember(MemberVO m) {
		
		//return this.sqlSession.selectOne("p_find", m);
		
		System.out.println(" \n ===============> 비번검색(JPA)");
		MemberVO pm = this.memberRepo.pwdFind(m.getMem_id(), m.getMem_name());
		return pm;
	}//비번 찾기 => 아이디와 회원이름을 기준으로 회원정보 검색
	
	@Transactional //javax.persistence.TransactionRequiredException: Executing an
	// update/delete query 에러가 발생하기 때문에 @Transactional을 해줘야 한다.
	@Override
	public void updatePwd(MemberVO m) {
		
		//this.sqlSession.update("p_edit", m);
		
		System.out.println(" \n ================> 암호화 된 임시비번으로 수정(JPA)");
		this.memberRepo.updatePwd(m.getMem_pwd(), m.getMem_id());
	}//암호화 된 임시비번으로 수정

	@Override
	public MemberVO loginCheck(String login_id) {
		
		//return this.sqlSession.selectOne("m_loginCheck", login_id);
		
		System.out.println(" \n==========================> 아이디와 mem_state=1 인 경우만 로그인 인증처리(JPA) ");
		MemberVO m = this.memberRepo.loginCheck(login_id);
		return m;
	}//로그인 인증처리

	@Override
	public MemberVO getMember(String id) {
		
		//return this.sqlSession.selectOne("member_Info", id);
		
		System.out.println(" \n =====================> 아이디에 대한 회원정보 보기(회원수정폼에 활용)"
				+": 이 경우는 반드시 로그인 된 상태에서 하기 때문에 null이 반환될 일이 없다. 내장메서드"
				+" getReferenceById()를 사용");
		
		MemberVO em=this.memberRepo.getReferenceById(id);
		return em;
	}//회원정보 보기=>정보수정폼
	
	@Transactional
	@Override
	public void updateMember(MemberVO m) {
		
		//this.sqlSession.update("medit_ok",m);
		
		System.out.println(" \n =================> 회원정보 수정완료(JPA)");
		this.memberRepo.updateMember(m.getMem_pwd(), m.getMem_name(), m.getMem_zip(),
				m.getMem_zip2(), m.getMem_addr(), m.getMem_addr2(), m.getMem_phone01(),
				m.getMem_phone02(), m.getMem_phone03(), m.getMail_id(), m.getMail_domain(),
				m.getMem_id());
	}//회원정보 수정완료
	
}

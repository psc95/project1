package net.daum.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import net.daum.vo.MemberVO;

public interface MemberRepository extends JpaRepository<MemberVO, String> {
	
	@Query("select m from MemberVO m where m.mem_id=?1 and m.mem_name=?2")
	MemberVO pwdFind(String mem_id, String mem_name); //아이디와 회원이름을 기준으로 회원정보 검색
	
	@Modifying //@Query 애노테이션은 select문 가능하지만 @Modifying을 사용해서 DML(insert,update,
	// delete)문 작업 처리가 가능하다.
	@Query("update MemberVO m set m.mem_pwd=?1 where m.mem_id=?2") //?1은 첫번째로 전달되어지는
	//인자값 ?2는 두번째로 전달되어지는 인자값, JPQL문을 사용함. JPQL문은 실제 테이블명 대신 엔티티빈
	//클래스명을 사용하고, 실제 테이블 컬럼명 대신 빈클래스 속성명인 변수명을 사용한다.
	void updatePwd(String mem_pwd, String mem_id);//아이디를 기준으로 암호화 된 임시비번을 수정
	
	@Query("select m from MemberVO m where m.mem_id=?1 and m.mem_state=1")
	MemberVO loginCheck(String login_id);//아이디와 가입회원 1인 경우만 로그인 인증 처리
	
	@Modifying
	@Query("update MemberVO m set m.mem_pwd=?1, m.mem_name=?2, m.mem_zip=?3,"
			+"m.mem_zip2=?4, m.mem_addr=?5, m.mem_addr2=?6, m.mem_phone01=?7,"
			+"mem_phone02=?8, mem_phone03=?9, mail_id=?10, mail_domain=?11 " 
			+" where m.mem_id=?12")
	void updateMember(String mem_pwd, String mem_name, String mem_zip, String mem_zip2, String mem_addr,
			String mem_addr2, String mem_phone01, String mem_phone02, String mem_phone03, String mail_id,
			String mail_domain, String mem_id);//회원정보 수정

}

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

}

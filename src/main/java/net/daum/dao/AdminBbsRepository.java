package net.daum.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import net.daum.vo.BbsVO;

public interface AdminBbsRepository extends JpaRepository<BbsVO, Integer> {

	@Modifying //@Query 애노테이션은 select 검색쿼리문만 가능하지만 @Modifying 애노테이션을 이용해서
	//DML(insert,update,delete) 문 작업처리도 가능하다.
	@Query("update BbsVO b set b.bbs_name=?1, b.bbs_title=?2, b.bbs_cont=?3"
			+", b.bbs_file=?4 where b.bbs_no=?5")
	//?1은 첫번째로 전달되어지는 인자값, ?2는 두번째로 전달되어지는 인자값
	//JPQL(Java Persistence Query Language의 약어로서 JPA를 사용하는 쿼리문이다.)
	//JPQL문에서는 실제 테이블명 대신 엔티티빈 클래스명을 사용하고, 실제 컬럼명 대신 엔티티빈 클래스의 속성명인 변수
	//를 사용한다.
	public void adminEditBbs(String name,String title, String cont, String fileName,
			int bno);
}

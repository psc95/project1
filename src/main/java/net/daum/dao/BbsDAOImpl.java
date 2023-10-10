package net.daum.dao;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.daum.vo.BbsVO;
import net.daum.vo.PageVO;

@Repository
public class BbsDAOImpl implements BbsDAO {//spring MVC에서 모델 dao로 인식함->자료실

	@Autowired
	private SqlSession sqlSession;//mybatis 쿼리문 수행 sqlSession 자동 의존성(DI)주입
	
	@Autowired
	private BbsRepository bbsRepo;//사용자 자료실 JPA를 실행하기 위한 자동의존성 주입

	@Override
	public void insertBbs(BbsVO b) {
		//this.sqlSession.insert("bbs_in",b);//mybatis에서 insert()는 레코드를 저장시킨다.
		//bbs_in은 bbs.xml에서 설정할 유일한 아이디명이다.
		
		int bbs_no = this.sqlSession.selectOne("bbsNoSeq_Find");//시퀀스 번호값을 구함
		//mybatis에서 selectOne()메서드는 단 한개의 레코드값만 반환, bbsNoSeq_Find는 bbs.xml에서
		//설정할 유일한 아이디명
		
		System.out.println(" \n ====================> JPA로 자료실 저장");
		b.setBbs_no(bbs_no);//번호 저장
		b.setBbs_ref(bbs_no);//글 그룹번호를 저장
		
		this.bbsRepo.save(b);//JPA로 하이버네이트를 통해서 자료실 저장	
	}//자료실 저장

	@Override
	public int getRowCount(PageVO p) {
		return this.sqlSession.selectOne("bbs_count",p);
	}//검색 전후 총 레코드 개수

	@Override
	public List<BbsVO> getBbsList(PageVO p) {
		return this.sqlSession.selectList("bbs_list",p);//mybatis에서 selectList()메서드는
		//하나이상의 레코드를 검색해서 컬렉션 List로 변환
	}//검색전후 목록

	@Override
	public void updateHit(int bbs_no) {
		//this.sqlSession.update("bbs_hi",bbs_no);//mybatis에서 update메서드로 레코드를 수정,
		//bbs_hi는 bbs.xml에서 설정할 유일한 아이디명
		
		System.out.println(" \n =================> 조회수 증가");
		Optional<BbsVO> bbs_hit = this.bbsRepo.findById(bbs_no);//JPA로 번호를 기준으로
		//레코드를 검색
		
		bbs_hit.ifPresent(bbs_hit2 ->{//자료가 있다면
			bbs_hit2.setBbs_hit(bbs_hit2.getBbs_hit()+1);//조회수+1 =>증가된 조회수
			this.bbsRepo.save(bbs_hit2);//jpa로 번호를 기준으로 조회수 증가
		});
	}//조회수 증가

	@Override
	public BbsVO getBbsCont(int bbs_no) {
		//return this.sqlSession.selectOne("bbs_co",bbs_no);
		
		System.out.println(" \n =================> 내용보기 JPA");
		BbsVO bc = this.bbsRepo.getReferenceById(bbs_no);//JPA로 번호에 해당하는 자료를 검색해서
		//엔티티빈 타입으로 반환
		/* getReferenceById() JPA 내장메서드 특징)
		 * 반환값이 null이 발생하는 경우는 예외 오류가 발생한다. null이 발생하지 않는 경우에 사용하는 것이 좋다.
		 */
		return bc;
	}//내용보기

	@Override
	public void updateLevel(BbsVO rb) {
		//this.sqlSession.update("levelUp",rb);		
		
		System.out.println(" \n =============> jpa 답변 레벨 증가");
		this.bbsRepo.updateLevel(rb.getBbs_ref(), rb.getBbs_level());
	}//답변 레벨 증가

	@Override
	public void replyBbs(BbsVO rb) {
		//this.sqlSession.insert("reply_in2",rb);
		
		System.out.println("\n =============> JPA 답변 저장");
		int bbs_no = this.sqlSession.selectOne("bbsNoSeq_Find");//시퀀스로 부터 번호값을 구함
		rb.setBbs_no(bbs_no);//자료실 번호 저장
		rb.setBbs_step(rb.getBbs_step()+1);
		rb.setBbs_level(rb.getBbs_level()+1);
		
		this.bbsRepo.save(rb);//답변 저장
	}//답변 저장

	@Override
	public void editBbs(BbsVO b) {
		//this.sqlSession.update("bbs_edit", b);
		
		System.out.println(" \n =================>JPA로 자료실 수정");
		this.bbsRepo.updateBbs(b.getBbs_name(),b.getBbs_no(),b.getBbs_title(),
				b.getBbs_cont(),b.getBbs_file());
	}//자료실 수정완료

	@Override
	public void delBbs(int bbs_no) {
		//this.sqlSession.delete("bbs_del", bbs_no);//mybatis에서 delete()메서드는 레코드를 삭
		//제한다. bbd_del은 bbs.xml에서 설정할 유일한 아이디명이다.
		
		System.out.println(" \n ===============> JPA로 자료실 삭제");
		this.bbsRepo.deleteById(bbs_no);//자료실 번호를 기준으로 삭제
	}//자료실 삭제
}





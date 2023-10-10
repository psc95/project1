package net.daum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import net.daum.dao.BbsDAO;
import net.daum.vo.BbsVO;
import net.daum.vo.PageVO;

@Service
public class BbsServiceImpl implements BbsService {

	@Autowired
	private BbsDAO bbsDao;

	@Override
	public void insertBbs(BbsVO b) {
	  this.bbsDao.insertBbs(b);			
	}

	@Override
	public int getRowCount(PageVO p) {
		return this.bbsDao.getRowCount(p);
	}

	@Override
	public List<BbsVO> getBbsList(PageVO p) {
		return bbsDao.getBbsList(p);
	}

	//aop를 통한 트랜잭션 적용
	@Transactional(isolation = Isolation.READ_COMMITTED) //트랜잭션 격리(트랜잭션이 적용되는 
	//중간에 외부간섭을 배제
	@Override
	public BbsVO getBbsCont(int bbs_no) {
		this.bbsDao.updateHit(bbs_no);//조회수 증가
		return this.bbsDao.getBbsCont(bbs_no);//내용보기
	}//내용보기와 조회수 증가	

	@Override
	public BbsVO getBbsCont2(int bbs_no) {
		return bbsDao.getBbsCont(bbs_no);
	}//수정폼,삭제폼,답변폼일때는 조회수 증가 안되고 내용보기만 이루어진다.

	//AOP를 통한 트랜잭션 적용
	@Transactional
	@Override
	public void replyBbs(BbsVO rb) {
		this.bbsDao.updateLevel(rb);//답변 레벨 증가
		this.bbsDao.replyBbs(rb);//답변 저장
	}//답변 레벨증가와 답변저장

	@Override
	public void editBbs(BbsVO b) {
		this.bbsDao.editBbs(b);		
	}

	@Override
	public void delBbs(int bbs_no) {
		this.bbsDao.delBbs(bbs_no);		
	}
}






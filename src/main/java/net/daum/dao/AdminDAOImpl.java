package net.daum.dao;

import java.util.Optional;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.daum.vo.AdminVO;

@Repository
public class AdminDAOImpl implements AdminDAO {

	@Autowired
	private SqlSession sqlSession;//mybatis  쿼리문 수행 sqlSession 의존성 주입
	
	@Autowired
	private AdminRepository adminRepo; //jpa를 수행할 의존성 주입

	@Override
	public void insertAdmin(AdminVO ab) {
		//this.sqlSession.insert("admin_in",ab);
		
		System.out.println(" \n =============> JPA로 관리자 정보 저장");
		this.adminRepo.save(ab);
	}//관리자 정보 저장	

	@Override
	public AdminVO adminLogin(String admin_id) {
		//return this.sqlSession.selectOne("admin_login", admin_id);//mybatis에서 select
		//One()메서드는 단 한개의 레코드값만 반환
		
		System.out.println(" \n ===============> JPA로 관리자 로그인 인증");
		Optional<AdminVO> adminInfo = this.adminRepo.findById(admin_id);
		AdminVO admin;
		
		if(adminInfo.isPresent()) {//관리자 아이디에 해당하는 정보가 있다면 참
			admin = adminInfo.get();//관리자 정보를 AdminVO 타입으로 반환
		}else {//관리자 정보가 없는 경우
			admin = null;
		}
		return admin;
	}//관리자 로그인 인증
}




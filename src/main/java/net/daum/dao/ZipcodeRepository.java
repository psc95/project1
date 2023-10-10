package net.daum.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.daum.vo.ZipcodeVO;

public interface ZipcodeRepository extends JpaRepository<ZipcodeVO, Integer> {
	
	@Query("select z from ZipcodeVO z where z.gil like ?1 and z.no>0 order by z.no desc")
	//쿼리메서드 정의 => JPQL문을 사용함. ?1은 첫번째로 전달되어지는 인자값
	public List<ZipcodeVO> findByDong(String dong);//우편주소 검색
}

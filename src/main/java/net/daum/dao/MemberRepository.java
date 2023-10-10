package net.daum.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import net.daum.vo.MemberVO;

public interface MemberRepository extends JpaRepository<MemberVO, String> {

}

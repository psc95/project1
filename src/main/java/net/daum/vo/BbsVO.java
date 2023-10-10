package net.daum.vo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter //setter() 메서드 자동 제공
@Getter //getter() 메서드 자동 제공
@ToString //toString() 메서드 자동 제공
@Entity //엔티티빈 JPA
@Table(name="bbs") //bbs테이블 명 지정
@EqualsAndHashCode(of="bbs_no") //equals(), hashCode(), canEqual()메서드 자동 생성
public class BbsVO {//자료실 엔티티빈 클래스

	@Id //기본키 지정 =>식별키
	private int bbs_no; //자료실 번호 => JPA를 통해서 bbs_no컬럼이 생성이 되고 primary key로 설정됨
	
	private String bbs_name;//글쓴이
	private String bbs_title;//글제목
	private String bbs_pwd;//비번
	
	@Column(length=4000) //컬럼크기를 4000으로 설정
	private String bbs_cont;//내용
	
	private String bbs_file;//첨부파일 경로와 파일명
	private int bbs_hit;//조회수
	
	//계단형 계층형 자료실을 만들기 위해서 필요한것=>관리자 답변 기능 추가컬럼
	private int bbs_ref;//글 그룹번호 => 원본글과 답변글을 묶어주는 기능을 한다.
	private int bbs_step;//원본글과 답변글을 구분하는 번호값이면서 몇번째 답변글인가를 알려준다. 원본글이면
	//0,첫번째 답변글이면 1, 두번째 답변글이면 2
	private int bbs_level;//답변글 정렬순서
	
	@CreationTimestamp //orm 하이버네이트(프레임웍)의 특별한 기능으로 등록시점의 날짜값을 기록
	//mybatis에서는 작동안됨. jpa를 통한 하이버네이트에서만 작동됨.
	private Timestamp bbs_date;//글등록날짜
}

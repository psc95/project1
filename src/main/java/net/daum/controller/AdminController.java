package net.daum.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.daum.pwdconv.PwdChange;
import net.daum.service.AdminService;
import net.daum.vo.AdminVO;

@Controller
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	//관리자 로그인 페이지
	@GetMapping("/admin_login")
	public ModelAndView admin_login() {
		return new ModelAndView("admin/admin_Login");//생성자 인자값으로 뷰페이지 경로 설정=>
		// /WEB-INF/views/admin/admin_Login.jsp
	}//admin_login()
	
	//관리자 정보저장 + 관리자 비번 암호화 + 관리자 로그인 인증
	@PostMapping("/admin_login_ok") //post로 접근하는 매핑주소 처리
	public String admin_login_ok(AdminVO ab,HttpServletResponse response,
			HttpServletRequest request, HttpSession session) throws Exception{
		response.setContentType("text/html;charset=UTF-8");//웹브라우저 출력되는 한글을 안깨지게
		//하고,자바스크립트나 HTML코드 등을 웹브라우저에 잘 적용하게 한다.
		PrintWriter out = response.getWriter();//출력스트림 객체 생성
		
		ab.setAdmin_pwd(PwdChange.getPassWordToXEMD5String(ab.getAdmin_pwd()));
		//관리자 비번 암호화
		
		/*ab.setAdmin_no(1);
		ab.setAdmin_name("관리자"); //관리자 이름 저장
		this.adminService.insertAdmin(ab);//관리자 정보 저장
		*/
		
		AdminVO admin_info = this.adminService.adminLogin(ab.getAdmin_id());
		//관리자 아이디로 로그인 인증
		
		if(admin_info == null) {
			out.println("<script>");
			out.println("alert('관리자 정보가 없습니다!');");
			out.println("history.back();");
			out.println("</script>");			
		}else {
		    if(!admin_info.getAdmin_pwd().equals(ab.getAdmin_pwd())) {
		    	out.println("<script>");
		    	out.println("alert('관리자 비번이 다릅니다!');");
		    	out.println("history.go(-1);");
		    	out.println("</script>");		    	
		    }else {
		    	session.setAttribute("admin_id",ab.getAdmin_id());//admin_id 관리자 세션
		    	//키이름에 관리자 아이디 저장
		    	session.setAttribute("admin_name",admin_info.getAdmin_name());//관리자 
		    	//이름을 admin_name 세션 키이름에 저장
		    	
		    	return "redirect:/admin_index";//관리자 메인으로 이동
		    }
		}		
		return null;
	}//admin_login_ok()
	
	//관리자 로그인 인증 후  메인 페이지로 이동
	@RequestMapping("/admin_index")
	public ModelAndView admin_index(HttpServletResponse response,HttpSession session)
	throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		String admin_id = (String)session.getAttribute("admin_id");//관리자 세션 아이디를 구함
		
		if(admin_id == null) {
			out.println("<script>");
			out.println("alert('관리자 아이디로 로그인 하세요!');");
			out.println("location='admin_login';");
			out.println("</script>");
		}else {
			ModelAndView am=new ModelAndView();
			am.setViewName("admin/admin_main");//뷰페이지 경로 설정
			return am;
		}
		return null;
	}//admin_index()
	
	//관리자 로그아웃
	@RequestMapping("/admin_logout")
	public String admin_logout(HttpServletResponse response, HttpSession session)
	throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		session.invalidate();//세션 만료 => 로그아웃
		
		out.println("<script>");
		out.println("alert('관리자 로그아웃 되었습니다!');");
		out.println("location='admin_login';");
		out.println("</script>");		
		
		return null;
	}//admin_logout()
}






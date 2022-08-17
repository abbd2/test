package com.board.icia.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.icia.bean.Member;
import com.board.icia.dao.IMemberDao;
import com.board.icia.dao.MemberDao;
import com.board.icia.exception.IdCheckException;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class MemberMM {
	@Autowired
	private IMemberDao mDao;
	//private MemberDao mDao;
	ModelAndView mav;
	
	// MyBatis Test
	/*public ModelAndView access(Member mb, HttpSession session) {
		
		ModelAndView mav=new ModelAndView();
		if(mDao.access(mb)) {
			session.setAttribute("id", mb.getM_id());
			mav.addObject("msg", "로그인 성공");
			mav.setViewName("main"); 
		}else {
			mav.addObject("msg", "로그인 실패");
			mav.setViewName("home"); 
		}
		return mav;
	}*/
	
	public ModelAndView access(Member mb, HttpSession session, RedirectAttributes attr) {
		mav = new ModelAndView();
		String view = null;
		// 스프링은 복호화 안됨
		BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder();
		String pwdEncode = mDao.getSecurityPwd(mb.getM_id());
		System.out.println("pw=" + pwdEncode); // dklsfjklsdjlfkjdsldfl
		if (pwdEncode != null) { // 비교
			if (pwdEncoder.matches(mb.getM_pwd(), pwdEncode)) {
				log.info("로그인 서비스 중..");
				// 로그인 성공 마킹
				session.setAttribute("id", mb.getM_id());
				// 로그인 후 회원정보를 화면 출력 위해
				mb = mDao.getMemberInfo(mb.getM_id());
				//session.setAttribute("mb", mb);  //세션에 저장하면 서버에 부담이 된다.
				//mav.addObject("mb", mb); // request영역에 저장
				
				//RedirectAttributes는  Redirect전 session영역에 저장한뒤 redirect후 삭제한다.
				//addAttribute : (session영역에 저장-->request객체에 저장--->session영역삭제)
				attr.addAttribute("m_id",mb.getM_id());  //문자열 저장
				attr.addAttribute("m_name",mb.getM_name());
				attr.addAttribute("m_point",mb.getM_point());
				attr.addAttribute("m_grade",mb.getM_grade());
				//addFlashAttribute : (session영역에 저장후 1번 사용후 삭제함)
				//attr.addFlashAttribute("mb",mb); //객체
				view = "redirect:/boardList"; // post,get-->get으로만
				//view = "forward:/boardList"; // post-post, get-get으로만
			} else { // 비번 오류
				view = "home";
				mav.addObject("check", 2); // 로그인 실패
			}
		} else {// 아이디가 오류
			view = "home";
			mav.addObject("check", 2); // request영역에 저장
		}
		mav.setViewName(view);
		return mav;
	}
	public ModelAndView memberJoin(Member mb) {
		mav = new ModelAndView();
		String view = null;
		// Encoder(암호화) <----> Decoder(복호화)
		// 스프링은 복호화가 불가능
		BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder();
		// 시간상 비번만 암호화 할 예정
		mb.setM_pwd(pwdEncoder.encode(mb.getM_pwd()));
		if (mDao.memberJoin(mb)) {
			view = "home"; // 회원가입 성공시 로그인
			mav.addObject("check", 1); // 성공
		} else {
			view = "joinFrm";
		}
		mav.setViewName(view);
		return mav;
	}

//	public boolean idAvailable(String m_id) {
//		Member mb = mDao.getMemberInfo(m_id);
//		System.out.println("mb:"+mb);
//		if (mb == null)
//			return true; // 사용할 수 있는 아이디
//		return false; // 사용불가 아이디
//	}
	public String idAvailable(String m_id){
		Member mb = mDao.getMemberInfo(m_id);
		if (mb != null)
			throw new IdCheckException("사용할 수 없는 아이디입니다.");
		return "사용할 수 있는 아이디입니다.";
	}
}












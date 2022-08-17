package com.board.icia.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.board.icia.bean.Reply;
import com.board.icia.bean.SampleVO;
import com.board.icia.service.BoardMM;

import lombok.extern.log4j.Log4j;


@Log4j
@RestController   //@ResponseBody 생략
@RequestMapping(value="/board")   //공통url
public class BoardRestController {
	@Autowired
	private BoardMM bm;
	ModelAndView mav;
	
		
	@PostMapping(value = "/replyInsert")
	//@ResponseBody
	public List<Reply> replyInsert(Reply r,HttpSession session) {
		System.out.println("r_bnum="+r.getR_bnum());
		System.out.println("r_contents="+r.getR_contents());
		r.setR_id(session.getAttribute("id").toString());
		//String json=bm.replyInsert(r);
		List<Reply> rList=bm.replyInsert(r);
		//메시지 컨버터: 서버(객체)에서 클라이언트(json)간 데이터를 변환해서 보내준다.
		//@RequestBody, @ResponseBody를 사용시 스프링 메세지 컨버터(외장)의 등록된 JackSon 또는 Gson을 이용하여 객체와 Json을 상호 변환한다
		//Gson보다 설정이 쉽고 스프링부트는 jackson이 기본설정이다.
		//빅데이터 처리엔 jackson, 스몰데이터 처리엔 gson권장
		//@ModelAttribute, @RequestParam 사용시 스프링 메세지 컨버터(내장)는 key(파라미터이름)=value 로 매핑한다.
		//return rList;
		return rList;  //메세지컨버터가 객체를 json으로 변환
	}
	// 서블릿에서 사용 방식을 @ResponseBody가 대신
		// res.setContentType("text/html;charset=UTF-8");
		// PrintWriter out=res.getWriter();
		// out.print(json);
	
	// jackson :빅데이터엔 속도가 빠름                 //json 한글깨짐 방지 리턴(생략가능)
		@PostMapping(value = "/replyInsert2")// ,produces="application/json;charset=utf-8")
		public Map<String, List<?>> replyInsert2(@RequestBody Reply r,HttpSession session) {
			//log.info("r="+r);
			r.setR_id(session.getAttribute("id").toString());
			Map<String, List<?>> rMap=bm.replyInsert2(r);
			return rMap;
		}
		// jackson역할: Map--->json으로 변환
		// Map={"rList",rList} ---->json={"rList":[{},{},{}]}
		// Map={"mList",mList} ---->json={"mList":[{},{},{}]}
		
		//필요시에만 사용할 것.
				//consumes(파라미터 데이터 형식 지정)
				//produces(리턴할 데이터 형식 지정)
				//"application/json;charset=utf-8"
				//"text/plain;charset=utf-8"
		@PostMapping(value = "/str" ,produces = "text/plain;charset=utf-8")
		public String str(@RequestBody Reply r,HttpSession session) {
			//log.info("r="+r);
			return "사용할수 있는 아이디 입니다.";
		}
		                                       //consumes, produces 는 생략가능
//		@PostMapping(value = "/replyInsert3", produces = "application/json;charset=utf-8")
//		public ResponseEntity<?> replyInsert3(@RequestBody Reply r, HttpSession session) {
//			r.setR_id(session.getAttribute("id").toString());
//			List<Reply> rList = bm.replyInsert3(r);
//			return ResponseEntity.ok(rList);
//		}
		
		@GetMapping(value = "/restTest", produces = {"text/plain;charset=utf-8",MediaType.APPLICATION_XML_VALUE,"application/json;charset=utf-8"})
		public String getSample() {
			System.out.println("MIME TYPE:" + MediaType.TEXT_PLAIN_VALUE);
			System.out.println("MIME TYPE:" + MediaType.APPLICATION_JSON_VALUE);
//			System.out.println("MIME TYPE:" + MediaType.APPLICATION_JSON_UTF8_VALUE);
			System.out.println("MIME TYPE:" + MediaType.APPLICATION_XML_VALUE);
			//return new SampleVO(112, "차", "지헌");
			return "안녕하세요";
		}
}

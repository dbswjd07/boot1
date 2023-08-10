package net.daum.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.daum.vo.AddrVO;
import net.daum.vo.Sample2VO;

@Controller
public class Sample2Controller {

	@RequestMapping("/view_test") //view_test 매핑주소 등록
	public ModelAndView view_test() {
		ModelAndView wm = new ModelAndView();
		wm.addObject("name","홍길동");
		wm.setViewName("test_view"); //뷰리졸브 경로 -> /WEB-INF/views/test_view.jsp
		return wm;
	}
	
	//일반 @Controller 애노테이션에서 json(키, 값  사전적인 자료구조) 데이터 만들기
	@RequestMapping(value="/json_test",produces="application/json")
	public @ResponseBody Sample2VO json_test(){
		/* @ResponseBody 애노테이션을 메서드 리턴타입 앞에 사용하면 jsp파일을 만들기 않고 브라우저에 키, 값
		 * 쌍의 JSON 데이터를 만들 수 있다. 리턴타입이 Sample2VO빈타입이면 해당 클래스의 변수명이 Json데이터의 
		 * 키이름이 된다.
		 */
		
		Sample2VO sample=new Sample2VO();
		sample.setVal01("val01이다.");
		sample.setVal02("val02이다.");
		sample.setVal03("val03이다.");
		return sample;
	}
	
	@RequestMapping(value="/addrList",produces="aplication/json")
	public List<AddrVO> addrList(){
		List<AddrVO> addrlist=new ArrayList<>();
		
		for(int i=1;i<3;i++) {
			AddrVO addrvo = new AddrVO();
			addrvo.setAddrNo(i);
			addrvo.setSido("서울시");
			addrvo.setGugun("종로구");
			addrlist.add(addrvo);
		}
		return addrlist;
	}//addrList()
	
	//키, 값 쌍의 Map타입 JSON 데이터
	@RequestMapping(value="/mapList",produces="application/json")
	public Map<Integer,AddrVO> mapList(){
		Map<Integer,AddrVO> maplist = new HashMap<>();
		
		for(int i=1;i<=5;i++) {
			AddrVO addr = new AddrVO();
			addr.setAddrNo(i);
			addr.setSido("부산시");
			addr.setGugun("해운대구");
			
			maplist.put(i, addr);  //키, 값 저장
		}
		
		return maplist;
	}
	
	//정상적인 json데이터와 404(해당 경로에 파일 없음 에러) 나쁜 상태코드가 동시 전송
	@RequestMapping(value="/sendListNot",produces="application/json")
	public ResponseEntity<List<AddrVO>> sendlistNot(){
		List<AddrVO> addrList = new ArrayList<>();
		
		for(int i=1;i<=3;i++) {
			AddrVO addr = new AddrVO();
			addr.setAddrNo(i);
			addr.setSido("부산시");
			addr.setGugun("영도구");
			
			addrList.add(addr); //컬렉션에 추가
		}
		
		return new ResponseEntity<>(addrList,HttpStatus.NOT_FOUND);
	}
}

package com.dealim.univ.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dealim.univ.domain.Board;
import com.dealim.univ.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	BoardService boardService;
	
	@GetMapping("/register")
	public String registerForm(Board board, Model model) throws Exception {
		logger.info("BoardController, registerForm.");
		
		return "board/register";
	}
	
	@PostMapping("/register")
	public String register(Board board, Model model) throws Exception {
		logger.info("BoardController, register.");
			
		boardService.register(board);
		  
		model.addAttribute("msg", "등록되었습니다");
		 		
		return "board/success";
	}
	
	@GetMapping("/list")
	public ModelAndView list(Model model, @RequestParam(defaultValue="price") String searchOption, 
						@RequestParam(defaultValue="") String keyword) throws Exception {
		logger.info("BoardController, list.");
		
		//model테스트
		String test= "model interface";		
		model.addAttribute("test", test);
		
		//DB 질의 결과값
		List<Board> resultList = boardService.list(searchOption, keyword);
		
		//레코드의 갯수
		int count = boardService.countArticle(searchOption, keyword);
		
		ModelAndView mav = new ModelAndView();
		
		model.addAttribute("list", resultList);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", resultList );
		map.put("count", count);
		map.put("searchOption", searchOption);
		map.put("keyword", keyword);
		mav.addObject("map", map);
		mav.setViewName("board/list");		

		logger.info("list={}, secondData={}", new Object[]{resultList, "thisisseconddata"});
		logger.info("mav={}, secondData={}", new Object[]{mav, "thisisseconddata"});
		logger.info(searchOption);
		logger.info(keyword);
		
		return mav;
		
//		return "board/list";
	}
	
	@GetMapping("/read")
	public String read(
			@RequestParam("boardNo") int boardNo
			, Model model
			) throws Exception {
		logger.info("BoardController, read.");
			
		Board board = boardService.read(boardNo);
		model.addAttribute("board", board);	 
		
		return "board/read";
	}
	
	@PostMapping("/remove")
	public String remove(
			@RequestParam("boardNo") int boardNo
			, Model model
			) throws Exception {
		logger.info("BoardController, remove.");
		
		boardService.remove(boardNo);
		
		model.addAttribute("msg", "삭제되었습니다.");
		
		return "board/success";
	}
	
	@GetMapping("/modify")
	public String modifyForm(int boardNo, Model model) throws Exception {
		logger.info("BoardController, modifyForm.");
		
		Board board = boardService.read(boardNo);
		model.addAttribute("board", board);		 
		
		return "board/modify";
	}
	
	@PostMapping("/modify")
	public String modify(Board board, Model model) throws Exception {
		logger.info("BoardController, modify.");
		
		boardService.modify(board);
		model.addAttribute("msg", "수정되었습니다");

		return "board/success";
	}

}

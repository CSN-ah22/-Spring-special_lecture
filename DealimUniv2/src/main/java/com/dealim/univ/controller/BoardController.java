package com.dealim.univ.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String list(Model model) throws Exception {
		logger.info("BoardController, list.");
		
		List<Board> resultList = boardService.list();
		
		model.addAttribute("list", resultList);
		
		return "board/list";
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

package net.scit.spring7.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scit.spring7.dto.BoardDTO;
import net.scit.spring7.service.BoardService;

@Controller
@Slf4j
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
	private final BoardService boardService;
	
	@GetMapping("/boardList")
	public String boardList(Model model) {
		
		List<BoardDTO> list = boardService.selectAll();
		model.addAttribute("list", list);
		
		return "/board/boardList";
	}
	
	@GetMapping("/boardWrite")
	public String boardWrite() {
		return "/board/boardWrite";
	}
	
	@PostMapping("/boardWrite")
	public String boardWrite(@ModelAttribute BoardDTO boardDTO) {
		boardService.insertBoard(boardDTO);
		return "redirect:/board/boardList";
	}
	
	@GetMapping("/boardDetail")
	public String boardDetail(@RequestParam(name="boardSeq") Long boardSeq, Model model) {
		BoardDTO boardDTO =  boardService.selectOne(boardSeq);
		boardService.incrementHitCount(boardSeq);
		
		model.addAttribute("board", boardDTO);
		return "/board/boardDetail";
	}
	
	/*
	 * 삭제를 위한 요청
	 */
	@GetMapping("/boardDelete")
	public String boardDelete(@RequestParam(name="boardSeq") Long boardSeq) {
		boardService.deleteOne(boardSeq);
		return "redirect:/board/boardList";
	}
	
	/*
	 * 수정을 위한 요청
	 */
	@GetMapping("/boardUpdate")
	public String boardUpdate(@RequestParam(name="boardSeq") Long boardSeq, Model model) {
		BoardDTO boardDTO =  boardService.selectOne(boardSeq);
		
		model.addAttribute("board", boardDTO);
		
		return "/board/boardUpdate";
	}
	
	/*
	 * 게시글 수정 처리 요청
	 */
	@PostMapping("/boardUpdate")
	public String boardUpdate(@ModelAttribute BoardDTO boardDTO) {
		log.info("==수정데이터: {}", boardDTO.toString());
		boardService.updateBoard(boardDTO);
		return "redirect:/board/boardList";
	}
}

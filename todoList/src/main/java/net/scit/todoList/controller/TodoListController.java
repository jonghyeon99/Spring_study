package net.scit.todoList.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import net.scit.todoList.dto.TodoListDTO;
import net.scit.todoList.service.TodoListService;

@Controller
@RequestMapping("/todoList")
@RequiredArgsConstructor
public class TodoListController {

	private final TodoListService todoListService;

	@PostMapping("/todoRegist")
	public String todoRegist(@ModelAttribute TodoListDTO todoListDTO, Model model) {
		// 데이터 저장
		todoListService.insert(todoListDTO);

		// 등록 후 리스트 갱신
		List<TodoListDTO> list = todoListService.selectAll();
		model.addAttribute("list", list);

		// 리스트와 함께 같은 뷰로 이동
		return "todoList/todoRegist";
	}

	@GetMapping("/todoRegist")
	public String todoRegist(Model model) {
		// 현재 리스트 가져오기
		List<TodoListDTO> list = todoListService.selectAll();
		model.addAttribute("list", list);

		return "todoList/todoRegist";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam(name = "seqno") Integer seqno) {
		// 데이터 삭제
		todoListService.delete(seqno);

		// 삭제 후 메인 페이지로 리다이렉트
		return "redirect:/todoList/todoRegist";
	}
}

package com.study.board.controller;

import com.study.board.controller.entity.Board;
import com.study.board.service.BoardService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;
    @GetMapping("/board/write") //localhost:8080/board/write
    public String boardWriteForm(){
        return "boardWrite";
    }

    @PostMapping("board/writePro")
    //public String boardWritePro(String title, String content){
    public String boardWritePro(Board board){
        boardService.write(board);
        //System.out.println(board.getTitle());
        //System.out.println("제목 : "+title);
        //System.out.println("내용 : "+content);
        return "";
    }

    @GetMapping("/board/list")
    public String boardList(Model model){
        model.addAttribute("list", boardService.boardList());
        return "boardlist";
    }

    @GetMapping("/board/view") //localhost:8080/board/view?id=1
    public String boardView(Model model,Integer id){
        model.addAttribute("board", boardService.boardView(id));
        return "boardView";
    }

    @GetMapping("/board/delete")
    public String boardDelete(Integer id){
        boardService.boardDelete(id);
        //삭제하면 리스트 페이지로 감
        return "redirect:/board/list";
    }


    @GetMapping("/board/modify/{id}")
    public String boardmodify(@PathVariable("id") Integer id){
        return "boardmodify";
    }

}

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

    @GetMapping("/board/list")  //http://localhost:8080/board/list
    public String boardList(Model model){
        model.addAttribute("list", boardService.boardList());
        return "boardlist";
    }

    @GetMapping("/board/view") //localhost:8080/board/view?id=1
    public String boardView(Model model,Integer id){
        model.addAttribute("board", boardService.boardView(id));
        return "boardView";
    }

    @GetMapping("/board/delete")  //http://localhost:8080/board/delete?id=4 (쿼리스트링)
    public String boardDelete(Integer id){
        boardService.boardDelete(id);
        //삭제하면 리스트 페이지로 감
        return "redirect:/board/list";
    }


    @GetMapping("/board/modify/{id}")  //http://localhost:8080/board/modify/5
    public String boardmodify(@PathVariable("id") Integer id, Model model){
        model.addAttribute(boardService.boardView(id));

        return "boardmodify";
    }

    @PostMapping("/board/update/{id}")
    //public String boardupdate(@PathVariable("id") Integer id, Board board){
    public String boardUpdate(@PathVariable("id") Integer id, Board board){  //update u 대문자 아니면 수정이 안된다.

        Board boardTemp = boardService.boardView(id);  //기존 내용 가지고 오기
        boardTemp.setTitle(board.getTitle()); //새로입력한값을 덮어씌우기
        boardTemp.setContent(board.getContent()); //새로입력한값을 덮어씌우기
        return "redirect:/board/list";
    }
}

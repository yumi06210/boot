package com.study.board.controller;

import com.study.board.controller.entity.Board;
import com.study.board.service.BoardService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/board/write") //localhost:8080/board/write
    public String boardWriteForm(){
        return "boardWrite";
    }

    @PostMapping("/board/writePro4")

    public String boardWritePro4(Board board, Model model) throws Exception{
    //public String boardWritePro4(Board board, Model model, MultipartFile file) throws Exception{  //이걸 넣으면 파일첨부가 되지만 수정이 안되는데?
        //boardService.write(board, file);
        boardService.write(board);

        model.addAttribute("message", "글 작성이 완료되었습니다.");
        model.addAttribute("searchUrl", "/board/list");
        return "message";
        //return "redirect:/board/list";
    }

    @GetMapping("/board/list")  //http://localhost:8080/board/list
    public String boardList(Model model, @PageableDefault(page = 0, size = 10, sort="id", direction = Sort.Direction.DESC) Pageable pageable, String searchKeyword){

        Page<Board> list = null;

        if(searchKeyword == null){ //검색어가 없을때
            list = boardService.boardList(pageable);
        }else{
            list = boardService.boardSearchList(searchKeyword,pageable);
        }

        int nowPage = list.getPageable().getPageNumber()+1; //페이지는 0에서 시작하므로 더하기 1
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        //페이지 넘겨주기
        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);


        model.addAttribute("list", boardService.boardList(pageable));
        return "boardlist";
    }

    @GetMapping("/board/view") //localhost:8080/board/view?id=1
    public String boardView(Model model,Integer id){
        model.addAttribute("board", boardService.boardView(id));
        return "boardView";
    }

    @GetMapping("/board/delete")  //http://localhost:8080/board/delete?id=4 (쿼리스트링)
    public String boardDelete(Integer id,Model model){
        boardService.boardDelete(id);
        model.addAttribute("message", "글 삭제가 완료되었습니다.");
        model.addAttribute("searchUrl", "/board/list");
        return "message";

        //삭제하면 리스트 페이지로 감
        //return "redirect:/board/list";
    }


    @GetMapping("/board/modify/{id}")  //http://localhost:8080/board/modify/5
    public String boardmodify(@PathVariable("id") Integer id, Model model){
        model.addAttribute(boardService.boardView(id));

        return "boardmodify";
    }

    @PostMapping("/board/update3/{id}")
    public String boardUpdate3(@PathVariable("id") Integer id, Board board, Model model, MultipartFile file) throws Exception {  //update u 대문자 아니면 수정이 안된다.

        Board boardTemp = boardService.boardView(id);  //기존 내용 가지고 오기
        boardTemp.setTitle(board.getTitle()); //새로입력한값을 덮어씌우기
        boardTemp.setContent(board.getContent()); //새로입력한값을 덮어씌우기

        boardService.write(boardTemp);  //이거 빠져서 update 안된거였음

        model.addAttribute("message", "글 수정이 완료되었습니다.");
        model.addAttribute("searchUrl", "/board/list");
        return "message";


        //return "redirect:/board/list";
    }
}

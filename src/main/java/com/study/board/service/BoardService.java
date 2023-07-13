package com.study.board.service;

import com.study.board.controller.entity.Board;
//import com.study.board.entity.Board;
import com.study.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository; //new 인스턴스 생성하는데 대신에 Autowired 사용
    public void write(Board board){
        boardRepository.save(board);
    }
    public List<Board> boardList(){
        return boardRepository.findAll();  //리턴받은 모든 값을 반환
    }
}

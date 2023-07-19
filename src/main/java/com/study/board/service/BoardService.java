package com.study.board.service;

import com.study.board.controller.entity.Board;
//import com.study.board.entity.Board;
import com.study.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository; //new 인스턴스 생성하는데 대신에 Autowired 사용

    //글작성

    public void write(Board board ) {
    //public void write(Board board, MultipartFile file ) throws Exception{
        /*
        String projectPath = System.getProperty("user.dir")+ "\\src\\main\\resources\\static\\files";  //저장할 경로 지정

        UUID uuid = UUID.randomUUID(); //랜덤으로 만들어줌
        
        String fileName = uuid + "_" +file.getOriginalFilename(); // file 을 생성해서 projectPath 경로에  fileName 이름으로 담는다.

        File saveFile = new File(projectPath, fileName);

        file.transferTo(saveFile);

        board.setFilename(fileName);
        board.setFilepath("/files/" + fileName);

         */
        boardRepository.save(board);
    }
    //return 이 없을때 void 사용
    
    //게시글 리스트
    public List<Board> boardList(){
        return boardRepository.findAll();  //리턴받은 모든 값을 반환
    }

    //특정게시글 불러오기
    public Board boardView(Integer id){
        return boardRepository.findById(id).get();
    }
    
    //특정게시글 삭제
    public void boardDelete(Integer id){

        boardRepository.deleteById(id);
    }
}

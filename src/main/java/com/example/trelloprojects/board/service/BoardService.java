package com.example.trelloprojects.board.service;

import com.example.trelloprojects.board.dto.BoardRequestDto;
import com.example.trelloprojects.board.dto.BoardResponseDto;
import com.example.trelloprojects.board.entity.Board;
import com.example.trelloprojects.board.entity.ColorEnum;
import com.example.trelloprojects.board.repository.BoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Component
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardResponseDto createBoard(BoardRequestDto requestDto) {
        Board board = new Board(requestDto);
        boardRepository.save(board);
        return new BoardResponseDto(board);
    }

    public BoardResponseDto getOneBoard(Long boardId) {
      Board board =   boardRepository.findById(boardId).orElseThrow(
              ()->new IllegalArgumentException()
      );
         return new BoardResponseDto(board);
    }


    public BoardResponseDto changeBoardName(Long id,BoardRequestDto requestDto) {
        Board board =   boardRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException()
        );

       board.updateName(requestDto);

        return new BoardResponseDto(board);
    }

    public BoardResponseDto changeBoardDescription(Long id, BoardRequestDto requestDto) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException()
        );
        board.updateDescription(requestDto);

        return new BoardResponseDto(board);
    }

    public void deleteBoard(Long id) {
        Board board =   boardRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException()
        );
        boardRepository.delete(board);
    }

    }


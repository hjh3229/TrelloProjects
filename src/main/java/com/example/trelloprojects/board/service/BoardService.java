package com.example.trelloprojects.board.service;

import com.example.trelloprojects.board.dto.ApiResponseDto;
import com.example.trelloprojects.board.dto.BoardRequestDto;
import com.example.trelloprojects.board.dto.BoardResponseDto;
import com.example.trelloprojects.board.entity.Board;
import com.example.trelloprojects.board.entity.ColorEnum;
import com.example.trelloprojects.board.repository.BoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class BoardService {

    private final BoardRepository boardRepository;

    public void createBoard(BoardRequestDto requestDto) {
        Board board = new Board(requestDto);
        boardRepository.save(board);
    }

    public BoardResponseDto getOneBoard(Long id) {
      Board board =   boardRepository.findById(id).orElseThrow(
              ()->new IllegalArgumentException()
      );
         return new BoardResponseDto(board);
    }


    public ResponseEntity<ApiResponseDto> changeBoardName(Long id, BoardRequestDto requestDto) {
        Board board =   boardRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException()
        );

       board.updateName(requestDto);

        ApiResponseDto apiResponseDto = new ApiResponseDto("이름이 변경되었습니다.", HttpStatus.OK.value());
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }

    public ResponseEntity<ApiResponseDto> changeBoardDescription(Long id, BoardRequestDto requestDto) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException()
        );
        board.updateDescription(requestDto);

        ApiResponseDto apiResponseDto = new ApiResponseDto("설명이 변경되었습니다.", HttpStatus.OK.value());
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }

    public ResponseEntity<ApiResponseDto> deleteBoard(Long id) {
        Board board =   boardRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException()
        );
        boardRepository.delete(board);
        ApiResponseDto apiResponseDto = new ApiResponseDto("보드가 삭제되었습니다.", HttpStatus.OK.value());
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }

    }


package com.example.trelloprojects.board.service;

import com.example.trelloprojects.board.dto.ApiResponseDto;
import com.example.trelloprojects.board.dto.BoardRequestDto;
import com.example.trelloprojects.board.dto.BoardResponseDto;
import com.example.trelloprojects.board.entity.Board;
import com.example.trelloprojects.board.repository.BoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@AllArgsConstructor
@Transactional
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardResponseDto createBoard(@RequestBody BoardRequestDto requestDto) {
        Board board = new Board(requestDto);
        boardRepository.save(board);
        return new BoardResponseDto(board);
    }

    public BoardResponseDto getOneBoard(@RequestParam Long id) {
        Board board =   boardRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException()
        );
        return new BoardResponseDto(board);
    }


    public BoardResponseDto changeBoardName(@RequestParam Long id,@RequestBody BoardRequestDto requestDto) {
        Board board =   boardRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException()
        );

        System.out.println(requestDto.getName());
        board.updateName(requestDto);
        System.out.println(board.getName());

        return new BoardResponseDto(board);
    }

    public BoardResponseDto changeBoardDescription(@RequestParam Long id,@RequestBody BoardRequestDto requestDto) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException()
        );
        board.updateDescription(requestDto);

        return new BoardResponseDto(board);
    }

    public void deleteBoard(@RequestParam Long id) {
        Board board =   boardRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException()
        );
        boardRepository.delete(board);
    }


    }


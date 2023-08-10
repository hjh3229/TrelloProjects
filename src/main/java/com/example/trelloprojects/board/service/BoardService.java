package com.example.trelloprojects.board.service;

import com.example.trelloprojects.board.dto.BoardColumnResponseDto;
import com.example.trelloprojects.board.dto.BoardRequestDto;
import com.example.trelloprojects.board.dto.UpdateBoardColor;
import com.example.trelloprojects.board.dto.UpdateBoardDescription;
import com.example.trelloprojects.board.dto.UpdateBoardName;
import com.example.trelloprojects.board.entity.Board;
import com.example.trelloprojects.board.repository.BoardRepository;
import com.example.trelloprojects.common.error.BusinessException;
import com.example.trelloprojects.common.error.ErrorCode;
import com.example.trelloprojects.workspace.entity.Workspace;
import com.example.trelloprojects.workspace.repository.WorkspaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final WorkspaceRepository workspaceRepository;

    @Transactional
    public void createBoard(BoardRequestDto requestDto, Long workspaceId) {
        Workspace workspace = workspaceRepository.findById(workspaceId).orElseThrow(() ->
                new BusinessException(ErrorCode.WORKSPACE_NOT_FOUND)
        );
        Board board = new Board(requestDto, workspace);
        boardRepository.save(board);
    }

    @Transactional(readOnly = true)
    public BoardColumnResponseDto getOneBoard(Long id) {
        Board board = findBoard(id);
        return new BoardColumnResponseDto(board);
    }

    @Transactional
    public void changeBoardName(Long id, UpdateBoardName requestDto) {
        Board board = findBoard(id);

        board.updateName(requestDto);
    }

    @Transactional
    public void changeBoardDescription(Long id, UpdateBoardDescription requestDto) {
        Board board = findBoard(id);

        board.updateDescription(requestDto);
    }

    @Transactional
    public void deleteBoard(Long id) {
        Board board = findBoard(id);

        boardRepository.delete(board);
    }

    private Board findBoard(Long id) {
        return boardRepository.findById(id).orElseThrow(
                () -> new BusinessException(ErrorCode.BOARD_NOT_FOUND)
        );
    }

    @Transactional
    public void changeBoardColor(Long boardId, UpdateBoardColor requestDto) {
        Board board = findBoard(boardId);

        board.updateColor(requestDto);
    }
}


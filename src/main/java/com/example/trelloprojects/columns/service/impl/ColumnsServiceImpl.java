package com.example.trelloprojects.columns.service.impl;

import com.example.trelloprojects.board.entity.Board;
import com.example.trelloprojects.board.repository.BoardRepository;
import com.example.trelloprojects.columns.dto.AddColumnsRequest;
import com.example.trelloprojects.columns.dto.ReorderRequest;
import com.example.trelloprojects.columns.dto.UpdateColumnsRequest;
import com.example.trelloprojects.columns.entity.Columns;
import com.example.trelloprojects.columns.repository.ColumnsRepository;
import com.example.trelloprojects.columns.service.ColumnsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ColumnsServiceImpl implements ColumnsService {

    private final ColumnsRepository columnsRepository;
    private final BoardRepository boardRepository;


    @Transactional
    @Override
    public Columns addColumns(Long boardId, AddColumnsRequest request) {
        Board findBoard = boardRepository.findById(boardId).orElseThrow(
                () -> new IllegalArgumentException("not found" + boardId)
        );
        Long position = columnsRepository.countColumnsByBoard(findBoard);
        return columnsRepository.save(new Columns(request, position, findBoard));
    }

    @Transactional
    @Override
    public Columns updateColumns(Long columnId, UpdateColumnsRequest request) {
        Columns columns = findColumn(columnId);
        return columns.update(request);
    }

    @Transactional
    @Override
    public void deleteColumns(Long columnId) {
        Columns columns = findColumn(columnId);
        columnsRepository.decrementBelow(columns.getPosition());
        columnsRepository.deleteById(columnId);
    }

    @Transactional
    @Override
    public void reorder(Long columnId, ReorderRequest request) {
        Columns columns = findColumn(columnId);

        Long oldPosition = columns.getPosition();
        Long newPosition = request.getPosition();

        if (newPosition > oldPosition) {
            columnsRepository.decrementAboveToPosition(newPosition, oldPosition);
        } else {
            columnsRepository.incrementBelowToPosition(newPosition, oldPosition);
        }

        columns.setPosition(request.getPosition());
        columnsRepository.save(columns);
    }

    @Override
    public Columns findColumn(Long columnId) {
        return columnsRepository.findById(columnId).orElseThrow(IllegalArgumentException::new);
    }

}

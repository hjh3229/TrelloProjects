package com.example.trelloprojects.columns.service;

import com.example.trelloprojects.board.entity.Board;
import com.example.trelloprojects.board.repository.BoardRepository;
import com.example.trelloprojects.columns.dto.AddColumnsRequest;
import com.example.trelloprojects.columns.dto.ColumnsResponse;
import com.example.trelloprojects.columns.dto.UpdateColumnsRequest;
import com.example.trelloprojects.columns.entity.Columns;
import com.example.trelloprojects.columns.repository.ColumnsRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ColumnsService {

    private final ColumnsRepository columnsRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public Columns addColumns(Long boardId, AddColumnsRequest request) {
        Board findBoard = boardRepository.findById(boardId).orElseThrow(
                () -> new IllegalArgumentException("not found" + boardId)
        );
        return columnsRepository.save(new Columns(request, findBoard));
    }

    @Transactional
    public Columns updateColumns(Long columnId, UpdateColumnsRequest request) {
        Columns findColumns = columnsRepository.findById(columnId).orElseThrow(
                () -> new IllegalArgumentException("not found" + columnId)
        );
        return findColumns.update(request);
    }

    @Transactional
    public void deleteColumns(Long columnId) {
        columnsRepository.deleteById(columnId);
    }

    @Transactional(readOnly = true)
    public List<ColumnsResponse> getColumns() {
        return columnsRepository.findAll().stream().map(ColumnsResponse::new).toList();
    }

}

package com.example.trelloprojects.columns.service;

import com.example.trelloprojects.columns.dto.AddColumnsRequest;
import com.example.trelloprojects.columns.dto.ReorderRequest;
import com.example.trelloprojects.columns.dto.UpdateColumnsRequest;
import com.example.trelloprojects.columns.entity.Columns;
import org.springframework.transaction.annotation.Transactional;

public interface ColumnsService {

    @Transactional
    void addColumns(Long boardId, AddColumnsRequest request);

    @Transactional
    void updateColumns(Long columnId, UpdateColumnsRequest request);

    @Transactional
    void deleteColumns(Long columnId);

    @Transactional
    void reorder(Long columnId, ReorderRequest request);

    Columns findColumn(Long columnId);
}

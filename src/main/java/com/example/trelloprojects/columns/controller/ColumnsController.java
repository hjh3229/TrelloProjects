package com.example.trelloprojects.columns.controller;

import com.example.trelloprojects.columns.dto.AddColumnsRequest;
import com.example.trelloprojects.columns.dto.ColumnsResponse;
import com.example.trelloprojects.columns.dto.UpdateColumnsRequest;
import com.example.trelloprojects.columns.entity.Columns;
import com.example.trelloprojects.columns.service.ColumnsService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ColumnsController {

    private final ColumnsService columnsService;

    @PostMapping("/column/{boardId}")
    public ResponseEntity<Columns> addColumns(@PathVariable Long boardId,
            @RequestBody AddColumnsRequest request) {
        return ResponseEntity.ok().body(columnsService.addColumns(boardId, request));
    }

    @PutMapping("/column/{columnId}")
    public ResponseEntity<Columns> updateColumns(@PathVariable Long columnId, @RequestBody
    UpdateColumnsRequest request) {
        return ResponseEntity.ok().body(columnsService.updateColumns(columnId, request));
    }

    @DeleteMapping("/column/{columnId}")
    public ResponseEntity<Void> deleteColumns(@PathVariable Long columnId) {
        columnsService.deleteColumns(columnId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/column/")
    public ResponseEntity<List<ColumnsResponse>> getColumns() {
        return ResponseEntity.ok().body(columnsService.getColumns());
    }


}

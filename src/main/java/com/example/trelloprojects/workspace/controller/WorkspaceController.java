package com.example.trelloprojects.workspace.controller;


import com.example.trelloprojects.board.dto.BoardResponseDto;

import com.example.trelloprojects.common.dto.MsgResponseDto;
import com.example.trelloprojects.user.entity.UserDetailsImpl;
import com.example.trelloprojects.workspace.dto.CreateWorkspaceRequestDto;
import com.example.trelloprojects.workspace.dto.UpdateWorkspaceRequestDto;
import com.example.trelloprojects.workspace.dto.WorkspaceResponseDto;
import com.example.trelloprojects.workspace.service.WorkspaceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/workspaces")
public class WorkspaceController {

    private final WorkspaceService workspaceService;

    @PostMapping
    public ResponseEntity<MsgResponseDto> createWorkspace(@RequestBody @Valid CreateWorkspaceRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        workspaceService.createWorkspace(requestDto, userDetails.getUser());
        return ResponseEntity.ok(new MsgResponseDto("워크스페이스 생성 성공", HttpStatus.OK.value()));
    }

    @GetMapping("/{workspaceId}")
    public ResponseEntity<WorkspaceResponseDto> getWorkspace(@PathVariable Long workspaceId) {
        WorkspaceResponseDto responseDto = workspaceService.getWorkspace(workspaceId);
        return ResponseEntity.ok().body(responseDto);
    }

    @PutMapping("/{workspaceId}")
    public ResponseEntity<MsgResponseDto> updateWorkspace(@PathVariable Long workspaceId, @RequestBody @Valid UpdateWorkspaceRequestDto requestDto) {
        workspaceService.updateWorkspace(workspaceId, requestDto);
        return ResponseEntity.ok(new MsgResponseDto("워크스페이스 수정 성공", HttpStatus.OK.value()));
    }

    @PutMapping("/{workspaceId}/delete")
    public ResponseEntity<MsgResponseDto> deleteWorkspace(@PathVariable Long workspaceId) {
        workspaceService.deleteWorkspace(workspaceId);

        return ResponseEntity.ok(new MsgResponseDto("워크스페이스 삭제 성공", HttpStatus.OK.value()));
    }

    @PutMapping("/{workspaceId}/reopen")
    public ResponseEntity<MsgResponseDto> reopenWorkspace(@PathVariable Long workspaceId) {
        workspaceService.reopenWorkspace(workspaceId);

        return ResponseEntity.ok(new MsgResponseDto("워크스페이스 복원 성공", HttpStatus.OK.value()));
    }

    @GetMapping("/{workspaceId}/boards")
    public ResponseEntity<List<BoardResponseDto>> getBoards(@PathVariable Long workspaceId) {
        List<BoardResponseDto> boards = workspaceService.getBoards(workspaceId);
        return ResponseEntity.ok(boards);

    }
}
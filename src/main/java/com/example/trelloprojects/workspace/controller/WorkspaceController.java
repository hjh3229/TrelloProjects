package com.example.trelloprojects.workspace.controller;

import com.example.trelloprojects.user.entity.UserDetailsImpl;
import com.example.trelloprojects.workspace.dto.CreateWorkspaceRequestDto;
import com.example.trelloprojects.workspace.dto.UpdateWorkspaceRequestDto;
import com.example.trelloprojects.workspace.dto.WorkspaceResponseDto;
import com.example.trelloprojects.workspace.service.WorkspaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/workspace")
public class WorkspaceController {

    private final WorkspaceService workspaceService;

    @PostMapping
    public ResponseEntity<WorkspaceResponseDto> createWorkspace(CreateWorkspaceRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        WorkspaceResponseDto responseDto = workspaceService.createWorkspace(requestDto, userDetails.getUser());
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{workspaceId}")
    public ResponseEntity<WorkspaceResponseDto> updateWorkspace(@PathVariable Long workspaceId, UpdateWorkspaceRequestDto requestDto) {
        WorkspaceResponseDto responseDto = workspaceService.updateWorkspace(workspaceId, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{workspaceId}")
    public ResponseEntity<Void> deleteWorkspace(@PathVariable Long workspaceId) {
        workspaceService.deleteWorkspace(workspaceId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{workspaceId}")
    public ResponseEntity<Void> reopenWorkspace(@PathVariable Long workspaceId) {
        workspaceService.reopenWorkspace(workspaceId);
        return ResponseEntity.ok().build();
    }
}
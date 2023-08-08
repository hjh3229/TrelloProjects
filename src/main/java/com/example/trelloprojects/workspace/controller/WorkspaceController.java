package com.example.trelloprojects.workspace.controller;

import com.example.trelloprojects.user.entity.UserDetailsImpl;
import com.example.trelloprojects.workspace.dto.CreateWorkspaceRequestDto;
import com.example.trelloprojects.workspace.dto.UpdateWorkspaceRequestDto;
import com.example.trelloprojects.workspace.dto.WorkspaceResponseDto;
import com.example.trelloprojects.workspace.service.WorkspaceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/workspaces")
public class WorkspaceController {

    private final WorkspaceService workspaceService;

    @PostMapping
    public ResponseEntity<WorkspaceResponseDto> createWorkspace(@RequestBody @Valid CreateWorkspaceRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        WorkspaceResponseDto responseDto = workspaceService.createWorkspace(requestDto, userDetails.getUser());
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{workspaceId}")
    public ResponseEntity<WorkspaceResponseDto> getWorkspace(@PathVariable Long workspaceId) {
        WorkspaceResponseDto responseDto = workspaceService.getWorkspace(workspaceId);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{workspaceId}")
    public ResponseEntity<WorkspaceResponseDto> updateWorkspace(@PathVariable Long workspaceId, @RequestBody @Valid UpdateWorkspaceRequestDto requestDto) {
        WorkspaceResponseDto responseDto = workspaceService.updateWorkspace(workspaceId, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{workspaceId}/delete")
    public ResponseEntity<Void> deleteWorkspace(@PathVariable Long workspaceId) {
        workspaceService.deleteWorkspace(workspaceId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{workspaceId}/reopen")
    public ResponseEntity<Void> reopenWorkspace(@PathVariable Long workspaceId) {
        workspaceService.reopenWorkspace(workspaceId);
        return ResponseEntity.ok().build();
    }
}
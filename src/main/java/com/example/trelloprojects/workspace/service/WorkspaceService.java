package com.example.trelloprojects.workspace.service;

import com.example.trelloprojects.user.entity.User;
import com.example.trelloprojects.workspace.dto.CreateWorkspaceRequestDto;
import com.example.trelloprojects.workspace.dto.UpdateWorkspaceRequestDto;
import com.example.trelloprojects.workspace.dto.WorkspaceResponseDto;
import com.example.trelloprojects.workspace.entity.UserWorkspace;
import com.example.trelloprojects.workspace.entity.Workspace;
import com.example.trelloprojects.workspace.enums.WorkspaceStatus;
import com.example.trelloprojects.workspace.repository.UserWorkspaceRepository;
import com.example.trelloprojects.workspace.repository.WorkspaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WorkspaceService {

    private final WorkspaceRepository workspaceRepository;
    private final UserWorkspaceRepository userWorkspaceRepository;

    @Transactional
    public WorkspaceResponseDto createWorkspace(CreateWorkspaceRequestDto requestDto, User user) {
        Workspace workspace = workspaceRepository.save(new Workspace(requestDto.getName(), requestDto.getDescription()));
        userWorkspaceRepository.save(new UserWorkspace(user, workspace));
        return workspace.toDto();
    }

    @Transactional
    public WorkspaceResponseDto updateWorkspace(Long id, UpdateWorkspaceRequestDto requestDto) {
        Workspace workspace = findActiveWorkspace(id);
        workspace.update(requestDto);
        return workspace.toDto();
    }

    @Transactional
    public void deleteWorkspace(Long id) {
        Workspace workspace = findActiveWorkspace(id);
        workspace.delete();
    }

    private Workspace findActiveWorkspace(Long id) {
        return workspaceRepository.findByIdAndStatus(id, WorkspaceStatus.ACTIVE)
                .orElseThrow(() ->
                        new IllegalArgumentException("존재하지 않는 워크스페이스입니다."));
    }

    private Workspace findDeletedWorkspace(Long id) {
        return workspaceRepository.findByIdAndStatus(id, WorkspaceStatus.DELETED)
                .orElseThrow(() ->
                        new IllegalArgumentException("존재하지 않는 워크스페이스입니다."));
    }

    public void reopenWorkspace(Long id) {
        Workspace workspace = findDeletedWorkspace(id);
        workspace.reopen();
    }
}

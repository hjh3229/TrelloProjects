package com.example.trelloprojects.workspace.service;

import com.example.trelloprojects.common.error.BusinessException;
import com.example.trelloprojects.common.error.ErrorCode;
import com.example.trelloprojects.user.entity.User;
import com.example.trelloprojects.workspace.dto.CreateWorkspaceRequestDto;
import com.example.trelloprojects.workspace.dto.UpdateWorkspaceRequestDto;
import com.example.trelloprojects.workspace.dto.WorkspaceResponseDto;
import com.example.trelloprojects.member.entity.UserWorkspace;
import com.example.trelloprojects.workspace.entity.Workspace;
import com.example.trelloprojects.workspace.enums.WorkspaceStatus;
import com.example.trelloprojects.member.repository.UserWorkspaceRepository;
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

    public WorkspaceResponseDto getWorkspace(Long id) {
        Workspace workspace = findActiveWorkspace(id);
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

    @Transactional
    public void reopenWorkspace(Long id) {
        Workspace workspace = findDeletedWorkspace(id);
        workspace.reopen();
    }

    private Workspace findActiveWorkspace(Long id) {
        Workspace workspace = findWorkspace(id);

        if (workspace.getStatus() == WorkspaceStatus.DELETED) {
            throw new BusinessException(ErrorCode.DELETED_WORKSPACE);
        }

        return workspace;
    }

    private Workspace findDeletedWorkspace(Long id) {
        Workspace workspace = findWorkspace(id);

        if (workspace.getStatus() == WorkspaceStatus.ACTIVE) {
            throw new BusinessException(ErrorCode.ALREADY_ACTIVATED_WORKSPACE);
        }

        return workspace;
    }

    private Workspace findWorkspace(Long id) {
        return workspaceRepository.findById(id).orElseThrow(() ->
                new BusinessException(ErrorCode.WORKSPACE_NOT_FOUND));
    }
}

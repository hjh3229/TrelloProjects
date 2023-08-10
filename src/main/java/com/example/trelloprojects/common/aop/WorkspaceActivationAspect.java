package com.example.trelloprojects.common.aop;

import com.example.trelloprojects.common.error.BusinessException;
import com.example.trelloprojects.common.error.ErrorCode;
import com.example.trelloprojects.workspace.entity.Workspace;
import com.example.trelloprojects.workspace.enums.WorkspaceStatus;
import com.example.trelloprojects.workspace.repository.WorkspaceRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class WorkspaceActivationAspect {

    private final WorkspaceRepository workspaceRepository;

    @Before("execution(* com.example.trelloprojects.member.service.MemberService.*(..)) || " +
            "execution(* com.example.trelloprojects.workspace.service.WorkspaceService.*(..)) && args(workspaceId, ..)")
    public void checkWorkspaceActivation(Long workspaceId) {
        Workspace workspace = workspaceRepository.findById(workspaceId)
                .orElseThrow(() -> new BusinessException(ErrorCode.WORKSPACE_NOT_FOUND));

        if (workspace.getStatus() == WorkspaceStatus.DELETED) {
            throw new BusinessException(ErrorCode.DELETED_WORKSPACE);
        }
    }
}

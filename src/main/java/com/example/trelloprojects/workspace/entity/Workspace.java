package com.example.trelloprojects.workspace.entity;

import com.example.trelloprojects.member.entity.UserWorkspace;
import com.example.trelloprojects.workspace.dto.UpdateWorkspaceRequestDto;
import com.example.trelloprojects.workspace.dto.WorkspaceResponseDto;
import com.example.trelloprojects.workspace.enums.WorkspaceStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Table(name = "workspace")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Workspace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Enumerated(EnumType.STRING)
    private WorkspaceStatus status = WorkspaceStatus.ACTIVE;

    @OneToMany(mappedBy = "workspace")
    private List<UserWorkspace> members = new ArrayList<>();

    public Workspace(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void update(UpdateWorkspaceRequestDto requestDto) {
        this.name = requestDto.getName();
        this.description = requestDto.getDescription();
    }

    public void delete() {
        this.status = WorkspaceStatus.DELETED;
    }

    public void reopen() {
        this.status = WorkspaceStatus.ACTIVE;
    }

    public WorkspaceResponseDto toDto() {
        return WorkspaceResponseDto.builder()
                .id(this.id)
                .name(this.name)
                .description(this.description)
                .build();
    }
}

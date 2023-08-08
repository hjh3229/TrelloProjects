package com.example.trelloprojects.workspace.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkspaceResponseDto {
    private Long id;
    private String name;
    private String description;
}

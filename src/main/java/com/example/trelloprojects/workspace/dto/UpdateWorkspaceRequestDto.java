package com.example.trelloprojects.workspace.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateWorkspaceRequestDto {

    @NotBlank
    private String name;

    private String description;
}

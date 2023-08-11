package com.example.trelloprojects.member.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AdminRoleUpdateRequestDto {

    @NotNull
    private Long userId;

    @NotNull
    private Boolean admin;
}

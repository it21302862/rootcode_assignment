package com.assignment.assignment.DTO.ResponseDTO;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserResponseDTO {

    private Integer id;
    private String name;
    private String email;

}

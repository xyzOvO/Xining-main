package com.xyz66.domain.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Gjkt
 * @description
 * @since 2024/1/8 16:50
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "更新用户状态DTO")
public class UserStatusUpdateDTO {
    private Long id;
    private String status;
}

package com.xyz66.domain.vo;

import com.xyz66.domain.entity.Menu;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "路由列表")
public class RoutersVo {

    @ApiModelProperty(notes = "菜单列表")
    private List<Menu> menus;
}

package com.xyz66.domain.vo;

import com.xyz66.domain.entity.Menu;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "路由列表")
public class RoutersVo {

    private List<Menu> menus;
}

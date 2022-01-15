package com.scut.lzx.eduservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QueryPageVo {
    @ApiModelProperty(value = "当前页数", required = true)
    private Long current;
    @ApiModelProperty(value = "每页的限制", required = true)
    private Long limit;
}

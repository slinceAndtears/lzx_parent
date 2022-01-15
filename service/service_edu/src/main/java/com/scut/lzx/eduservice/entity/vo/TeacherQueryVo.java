package com.scut.lzx.eduservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TeacherQueryVo extends QueryPageVo {
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "")
    private Integer level;
    @ApiModelProperty(value = "", example = "2019-01-01 10:10:10")
    private String begin;
    @ApiModelProperty(value = "", example = "2019-12-01 10:10:10")
    private String end;

}

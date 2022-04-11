package com.scut.lzx.eduservice.entity.vo;


import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CourseInfoVo {
    @ApiModelProperty(value = "")
    private String id;
    @ApiModelProperty(value = "")
    private String teacherId;
    @ApiModelProperty(value = "")
    private String subjectId;
    @ApiModelProperty(value = "")
    private String title;
    @ApiModelProperty(value = "")
    private BigDecimal price;
    @ApiModelProperty(value = "")
    private Integer lessonNum;
    @ApiModelProperty(value = "")
    private String cover;
    @ApiModelProperty(value = "")
    private String description;
}

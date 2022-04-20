package com.scut.lzx.eduservice.entity.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CoursePublishVo {

    @ApiModelProperty(value = "课程id")
    private String id;
    @ApiModelProperty(value = "课程标题")
    private String title;
    @ApiModelProperty("课程头像")
    private String cover;
    @ApiModelProperty(value = "")
    private Integer lessonNum;
    @ApiModelProperty(value = "一级标题所属")
    private String subjectLevelOne;
    @ApiModelProperty(value = "二级标题所属")
    private String subjectLevelTwo;
    @ApiModelProperty(value = "老师名称")
    private String teacherName;
    @ApiModelProperty(value = "课程价格")
    private String price;//只用于显示

}

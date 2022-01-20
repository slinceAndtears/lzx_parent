package com.scut.lzx.eduservice.entity.subject;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class OneSubject implements Serializable {
    private String id;
    private String title;
    private List<TwoSubject> children;
}

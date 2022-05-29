package com.xzy._back.model.subject;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class FirstType {
    private String id;
    private String title;
    // 关联属性（一个一级分类含有多个二级分类）
    List<SecondType> children = new ArrayList<SecondType>();
}
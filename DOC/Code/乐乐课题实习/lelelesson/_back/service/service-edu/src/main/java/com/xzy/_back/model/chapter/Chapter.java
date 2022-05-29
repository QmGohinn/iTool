package com.xzy._back.model.chapter;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class Chapter {  // 章节实体类
    private String id;
    private String title;
    // 关联属性：小节集合
    private List<Video> children = new ArrayList<Video>();
}
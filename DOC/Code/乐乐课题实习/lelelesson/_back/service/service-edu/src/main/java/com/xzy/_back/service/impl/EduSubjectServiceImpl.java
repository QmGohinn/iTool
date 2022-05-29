package com.xzy._back.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xzy._back.model.EduSubject;
import com.xzy._back.mapper.EduSubjectMapper;
import com.xzy._back.model.excel.SubjectData;
import com.xzy._back.model.excel.SubjectExcelListener;
import com.xzy._back.model.subject.FirstType;
import com.xzy._back.model.subject.SecondType;
import com.xzy._back.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {
    @Override
    public void saveSubject(MultipartFile file, EduSubjectService subjectService) {
        try {
            // 1、获取文件的输入流
            InputStream inputStream = file.getInputStream();
            // 2、调用方法进行读取 Excel 数据
            EasyExcel.read(inputStream, SubjectData.class, new SubjectExcelListener(subjectService)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<FirstType> getAllSubjects() {
        // 1、查询所有一级分类 parent_id = 0
        QueryWrapper<EduSubject> wrapperFirstType = new QueryWrapper<>();
        wrapperFirstType.eq("parent_id", "0");
        List<EduSubject> firstSubjectList = baseMapper.selectList(wrapperFirstType);
        // 2、查询所有二级分类  parent_id != 0
        QueryWrapper<EduSubject> wrapperSecondType = new QueryWrapper<>();
        wrapperFirstType.ne("parent_id", "0");
        List<EduSubject> secondTypeList = baseMapper.selectList(wrapperSecondType);

        // 创建 List 集合，用于存放最终封装数据
        List<FirstType> finalFirstList = new ArrayList<FirstType>();
        // 3、封装一级分类
        // 查询出来的所有一级分类集合，进行遍历集合，得到每一个一级分类对象，封装到要求的 List 集合中 List<FirstType> finalFirstList
        for (int i = 0; i < firstSubjectList.size(); i++) {
            // 得到 firstTypeList 集合中每个对象
            EduSubject subjectOne = firstSubjectList.get(i);
            // 将 EduSubject 里面的值取出来，放到 FirstType 对象中
            FirstType firstType = new FirstType();
            // 原生实现
            // firstType.setId(subject.getId());
            // firstType.setTitle(subject.getTitle());
            BeanUtils.copyProperties(subjectOne, firstType); // 将 subjectOne 对象的值复制到对应 firstType 对象中
            // 把多个 FirstType 放到 firstTypeList 集合中
            finalFirstList.add(firstType);
            // 在一级分类循环遍历中查询对应的所有二级分类
            // 创建 List 集合，封装每一个一级分类对应的二级分类
            List<SecondType> finalSecondList = new ArrayList<SecondType>();
            // 遍历二级分类 secondTypeList 集合
            for (int j = 0; j < secondTypeList.size(); j++) {
                // 获取每个二级分类对象
                EduSubject subjectTwo = secondTypeList.get(j);
                // 判断二级分类 parentId 和一级分类 id 是否相同
                if(subjectTwo.getParentId().equals(subjectOne.getId())){
                    // 将 subjectTwo 值复制到 secondType 中，放到 finalSecondList 集合中
                    SecondType secondType = new SecondType();
                    BeanUtils.copyProperties(subjectTwo, secondType);
                    finalSecondList.add(secondType);
                }
            }
            // 把一级分类下所有二级分类放到一级分类中
            firstType.setChildren(finalSecondList);
        }
        return finalFirstList;
    }
}
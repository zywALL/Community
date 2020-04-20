package com.example.demo.service;


import com.example.demo.dto.PagenationDTO;
import com.example.demo.dto.QuestionDTO;
import com.example.demo.dto.QuestionPublishDTO;
import com.example.demo.mapper.QuestionPublishMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.Question;
import com.example.demo.model.QuestionPublish;
import com.example.demo.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class QuestionPublishService {
    @Autowired
    public QuestionPublishMapper questionPublishMapper;

    @Autowired
    public UserMapper userMapper;

    public PagenationDTO list(Integer page, Integer size) {
        Integer offset = size * (page - 1);
        List<QuestionPublish> questionPublishes = questionPublishMapper.list(offset, size);
        List<QuestionPublishDTO> questionPublishDTOList = new ArrayList<>();

        PagenationDTO pagenationPublishDTO = new PagenationDTO();
        for (QuestionPublish questionPublish : questionPublishes){
            User user = userMapper.findById(questionPublish.getCreator());
            QuestionPublishDTO questionPublishDTO = new QuestionPublishDTO();
            BeanUtils.copyProperties(questionPublish, questionPublishDTO);
            questionPublishDTO.setUser(user);
            questionPublishDTOList.add(questionPublishDTO);
        }
        pagenationPublishDTO.setQuestionPublishes(questionPublishDTOList);
        Integer totalcount = questionPublishMapper.count();
        pagenationPublishDTO.setPagenation(totalcount, page);
        return pagenationPublishDTO;
    }
}

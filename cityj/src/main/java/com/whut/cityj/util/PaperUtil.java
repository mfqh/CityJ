package com.whut.cityj.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 工具类，辅助测试系统
 */
public class PaperUtil {

    public static List<String> typeAnalysis(String question, int sign){
        List<String> list = new ArrayList<>();
        if(sign == StateUtil.TYPE_COMPLETION){
            //填空题直接返回
            list.add(question);
        }else if(sign == StateUtil.TYPE_CHOICE){
            //选择题，按照分割号进行分割
            String questions[] = question.trim().split(StateUtil.QUES_DIVISION);
            for (int i = 0; i < questions.length; i++) {
                list.add(questions[i]);
            }
        }
        return list;
    }
}

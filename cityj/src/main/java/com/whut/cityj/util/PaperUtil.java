package com.whut.cityj.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * 工具类，辅助测试系统
 */
public class PaperUtil {

    /**
     * 将选择题的题目跟选项分割开
     * @param question 题目
     * @param sign 标记（是否为选择题）
     * @return 题目所在的List容器
     */
    public static List<String> typeAnalysis(List<String> list, String question, int sign){
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

    public static String formatQuestion(String question[]){
        StringBuilder resultQuestion = new StringBuilder();

        //使用自定义的分隔符进行分割
        for (int i = 0; i < question.length; i++) {
            resultQuestion.append(question[i]).append(StateUtil.QUES_DIVISION);
        }
        resultQuestion.deleteCharAt(resultQuestion.length()-1);

        return resultQuestion.toString();
    }

    /**
     * 将字符串日期转换为日期格式
     * @param date 字符串日期（****-**-** **：**：**）
     * @return 日期格式
     */
    public static LocalDateTime date2Format(String date){
        //指定格式
        String format = "YYYY-MM-dd hh:mm:ss";

        LocalDateTime dateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern(format));

        return dateTime;
    }


}

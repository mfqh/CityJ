package com.whut.cityj.controller;

import com.whut.cityj.bean.AfcQuestion;
import com.whut.cityj.bean.ErrorBase;
import com.whut.cityj.bean.User;
import com.whut.cityj.service.ErrorBaseService;
import com.whut.cityj.util.PaperUtil;
import com.whut.cityj.util.StateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class ErrorController {

    @Autowired
    private ErrorBaseService errorBaseServiceImpl;

    /**
     * 查找错题库返回第一题
     * @param session 内置Session
     * @return 第一题
     */
    @GetMapping("/errAllQuestion")
    public List<String> errAllQuestion(HttpSession session){
        User user = (User)session.getAttribute("user");
        List<ErrorBase> allError = errorBaseServiceImpl.getAllError(user);
        //将数据和当前题index存入Session
        session.setAttribute("allError", allError);
        session.setAttribute("errId", 0);
        //将题目格式化进行返回
        List<String> list = new ArrayList<String>();
        //放入题目总数
        list.add(allError.size()+"");
        list = PaperUtil.typeAnalysis(list, allError.get(0).getErrQuestion().getQuestion(),
                allError.get(0).getErrQuestion().getSign());
        return list;
    }

    /**
     * 判断正误，返回解析
     * @param answer 用户输入的答案
     * @param session 内置Session
     * @return 判断结果及题目解析
     */
    @GetMapping("/errAnalyAnswer/{answer}")
    public List<String> errAnalyAnswer(@PathVariable("answer") String answer,
                                       HttpSession session){
        //从session获取所有题目及上一题id
        List<ErrorBase> allError = ( List<ErrorBase>)session.getAttribute("allError");
        Integer id = (Integer)session.getAttribute("afcId");
        List<String> list = new ArrayList<>();
        //防止前后空格影响判断
        if(allError.get(id).getErrQuestion().getAnswer().trim().equals(answer.trim())){
            list.add("true");
        }else{
            list.add("false");
        }
        //获取解析
        list.add(allError.get(id).getErrQuestion().getAnaly());
        //返回结果及解析
        return list;
    }

    /**
     * 根据id返回下一题
     * @param id 下一题id
     * @param session 内置Session
     * @return 下一题题目
     */
    @GetMapping("/errSelQuestion/{id}")
    public List<String> errSelQuestion(@PathVariable("id") Integer id,
                                       HttpSession session){
        //从session中取出所有题目
        List<ErrorBase> allError = ( List<ErrorBase>)session.getAttribute("allError");
        //放入新id
        session.setAttribute("afcID", id);

        //构造返回值
        ArrayList<String> list = new ArrayList<>();
        PaperUtil.typeAnalysis(list, allError.get(id).getErrQuestion().getQuestion(),
                allError.get(id).getErrQuestion().getSign());
        return list;
    }

    /**
     * 已掌握的错题移出数据库
     * @param session 内置Session
     * @return 操作状态码
     */
    @GetMapping("/errMaster")
    public Integer errMaster(HttpSession session){
        //从session获取所有题目、上一题id、用户
        List<ErrorBase> allError = ( List<ErrorBase>)session.getAttribute("allError");
        Integer id = (Integer)session.getAttribute("afcId");
        User user = (User)session.getAttribute("user");

        //从数据库和list容器中移除该题目
        if( errorBaseServiceImpl.removeError(new ErrorBase(user.getId(), id)) &&
                allError.remove(id)){
            //id减一
            session.setAttribute("afcID", id--);
            return StateUtil.SUCCESS_STATE;
        }
        return StateUtil.FAIL_STATE;
    }


}

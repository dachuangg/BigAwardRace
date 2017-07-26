package com.example.administrator.myapplication1;

/**
 * Created by Administrator on 2017/2/21.
 */

public class TureFalse {
    //变量mQuestion是用来保存地理知识问题字符串的资源Id
    private int mQuestion;
    //mTrueQuestion用来确定答案正确与否，需要用到的问题字符串会会稍后处理
    private boolean mTrueQuestion;
    public TureFalse(int question,boolean trueQuestion){
        mQuestion = question;
        mTrueQuestion = trueQuestion;
    }
    public int getmQuestion(){
        return mQuestion;
    }
    public void setmQuestion(int question){
        mQuestion = question;
    }
    public boolean ismTrueQuestion(){
        return mTrueQuestion;
    }
    public void setmTrueQuestion(boolean trueQuestion){
        mTrueQuestion = trueQuestion;
    }
}

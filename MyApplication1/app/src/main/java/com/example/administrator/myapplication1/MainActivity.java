package com.example.administrator.myapplication1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private TextView mQuestionTextView;

    //创建一个TureFalse对象数组
    //创建数组实例
    private TureFalse[] mquestionBank = new TureFalse[]{
            new TureFalse(R.string.question_oceans,true),
            new TureFalse(R.string.question_mideast,false),
            new TureFalse(R.string.question_africa,false),
            new TureFalse(R.string.question_americas,true),
            new TureFalse(R.string.question_asia,true),
    };
    private int mCurrentIndex = 0;

    private void updateQuestion(){
        //这是调用数组中index位置的对象的方法 得到返回值赋值给question
        int question = mquestionBank[mCurrentIndex].getmQuestion();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue = mquestionBank[mCurrentIndex].ismTrueQuestion();
        int messageResId = 0;
        if (userPressedTrue == answerIsTrue){
            messageResId = R.string.correct_toast;
        }else {
            messageResId = R.string.incorrect_toast;
        }
        //Toast方法：public static Toast makeText(Context contest,int resId,int duration)
        //第一个用来指代Activity的一个实例，第二个用来指代字符串的Id,第三个用来指代提示时间的多少
        //this指代监听器
        Toast.makeText(this,messageResId,Toast.LENGTH_SHORT).show();
    }

    //Override是告诉你下面这个方法是从接口或者父类继承过来的
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //首先引用Next按钮，然后为其设置监听器View.OnClickListener.
        // 该监听器的作用是：递增数组索引并相应更新显示TextView的文本内容。
        mQuestionTextView = (TextView)findViewById(R.id.question_text_view);

        //public View findViewById(int id)
        // 该方法接受组件的资源ID作为参数，返回一个视图对象
        // 使用按钮的资源ID获取生成的对象后，赋值给对应的成员变量
        // 赋值前，必须先将返回的View类型（cast）转换为Button
        mTrueButton = (Button)findViewById(R.id.ture_button);
        //为ture按钮设置监听器
        mTrueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                checkAnswer(true);
            }
        });
        mFalseButton = (Button)findViewById(R.id.flase_button);
        //为false按钮设置监听器
        mFalseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                checkAnswer(false);
            }
        });
        //引用组件Next
        mNextButton = (Button)findViewById(R.id.next_button);
        //为Next设置监听器
        mNextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //%在Java中为求余运算符
                mCurrentIndex = (mCurrentIndex + 1) % mquestionBank.length;
                updateQuestion();
            }
        });
        updateQuestion();
    }
}

package com.ginsmile.calculatorpro;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private String expression = "";
    private boolean last_equal = true;//上一个按键是否为等号
    private EditText text1;//第一行，用来显示按过等号之后的完整表达式
    private EditText text2;//第二行，用来显示表达式和结果

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化
        text1 = (EditText)findViewById(R.id.text1);
        text2 = (EditText)findViewById(R.id.text2);
        Button[] buttons = new Button[18];
        init(buttons);//初始化按钮

    }


    //初始化
    private void init(final Button[] buttons){
        buttons[0] = (Button)findViewById(R.id.zero);
        buttons[1] = (Button)findViewById(R.id.one);
        buttons[2] = (Button)findViewById(R.id.two);
        buttons[3] = (Button)findViewById(R.id.three);
        buttons[4] = (Button)findViewById(R.id.four);
        buttons[5] = (Button)findViewById(R.id.five);
        buttons[6] = (Button)findViewById(R.id.six);
        buttons[7] = (Button)findViewById(R.id.seven);
        buttons[8] = (Button)findViewById(R.id.eight);
        buttons[9] = (Button)findViewById(R.id.nine);

        buttons[10] = (Button)findViewById(R.id.empty);
        buttons[11] = (Button)findViewById(R.id.delete);
        buttons[12] = (Button)findViewById(R.id.divide);
        buttons[13] = (Button)findViewById(R.id.multiple);
        buttons[14] = (Button)findViewById(R.id.minus);
        buttons[15] = (Button)findViewById(R.id.plus);
        buttons[16] = (Button)findViewById(R.id.equal);
        buttons[17] = (Button)findViewById(R.id.dot);


        //添加监听事件
        //数字0～9
        for(int i = 0; i < 10; i++){
            final int m = i;
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(last_equal){
                        expression = "";//这次按的数字，如果上次按了等号，则清空表达式
                        last_equal = false;
                    }
                    expression += buttons[m].getText();
                    text2.setText(expression);
                    text2.setSelection(expression.length());
                }
            });
        }
        //empty
        buttons[10].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                last_equal = false;
                expression = "";
                text2.setText("0");
                text1.setText(null);
            }
        });
        //delete
        buttons[11].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                last_equal = false;
                if(expression.length() < 1){
                    return;
                }
                expression = expression.substring(0,expression.length()-1);
                text2.setText(expression);
                text2.setSelection(expression.length());
            }
        });
        //divide
        buttons[12].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                last_equal = false;
                if(expression.length() > 0)
                expression += "/";
                text2.setText(expression);
                text2.setSelection(expression.length());
            }
        });
        //mutiple
        buttons[13].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                last_equal = false;
                expression += "*";
                text2.setText(expression);
                text2.setSelection(expression.length());
            }
        });
        //minus
        buttons[14].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                last_equal = false;
                expression += buttons[14].getText();
                text2.setText(expression);
                text2.setSelection(expression.length());
            }
        });
        //plus
        buttons[15].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                last_equal = false;
                expression += buttons[15].getText();
                text2.setText(expression);
                text2.setSelection(expression.length());
            }
        });
        //equal
        buttons[16].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(last_equal) return;//如果上次还是按的等号，那么什么也不做
                text1.setText(expression + "=");
                text1.setSelection(expression.length()+1);//在第一行显示计算表达式
                try{
                    expression = Calculator.calculate(expression);
                }catch(Exception exception){
                    expression = "表达式错误!";
                }
                text2.setText(expression);//在第二行显示计算结果

                // 为下一次按计算器键盘做准备。
                // 如果下次按的是数字，那么清空第二行重新输入第一个数。
                // 如果是非数字，那就当这次的结果是输入的第一个数，直接参与运算。
                last_equal = true;
            }
        });
        buttons[17].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                last_equal = false;
                expression += buttons[17].getText();
                text2.setText(expression);
                text2.setSelection(expression.length());
            }
        });





    }



    public String calculate(String expression){

        return "0.00";
    }


    //判断最后一个字符是否是数字，
    public static boolean isInteger(char c){
        return false;
    }

}


package com.example.asissment3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView resultTV,solutionTV;
    MaterialButton buttonC, buttonBrackOpen, buttonBrackClose;
    MaterialButton buttonDiv, buttonMul, buttonPlus, buttonMinus, buttonEqual;
    MaterialButton button7, button8 ,button9;
    MaterialButton button4, button5, button6;
    MaterialButton button1, button2, button3;
    MaterialButton buttonAC, button0, buttonDot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        resultTV = findViewById(R.id.result_tv);
        solutionTV = findViewById(R.id.solution);

        assignId(buttonC,R.id.button_c);
        assignId(buttonBrackOpen,R.id.button_NgoacTrai);
        assignId(buttonBrackClose,R.id.button_NgoacPhai);
        assignId(buttonDiv,R.id.button_div);
        assignId(buttonMul,R.id.button_mul);
        assignId(buttonPlus,R.id.button_plus);
        assignId(buttonMinus,R.id.button_minus);
        assignId(buttonEqual,R.id.button_equal);
        assignId(button7,R.id.button_7);
        assignId(button8,R.id.button_8);
        assignId(button9,R.id.button_9);
        assignId(button4,R.id.button_4);
        assignId(button5,R.id.button_5);
        assignId(button6,R.id.button_6);
        assignId(button1,R.id.button_1);
        assignId(button2,R.id.button_2);
        assignId(button3,R.id.button_3);
        assignId(buttonAC,R.id.button_ac);
        assignId(button0,R.id.button_0);
        assignId(buttonDot,R.id.button_dot);



    }

    void assignId(MaterialButton btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataCalculate = solutionTV.getText().toString();

        if(buttonText.equals("AC")) {
            solutionTV.setText("");
            resultTV.setText("0");
            return;
        }
        if(buttonText.equals("=")) {
            solutionTV.setText(resultTV.getText());
            return;
        }
        if(buttonText.equals("C")) {
            dataCalculate = dataCalculate.substring(0,dataCalculate.length() -1);
        }else {
            dataCalculate = dataCalculate + buttonText;
        }
        solutionTV.setText(dataCalculate);
        String finalResult = getResult(dataCalculate);
        if (!finalResult.equals("Err")) {
            resultTV.setText(finalResult);
        }
    }

    String getResult(String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();
            String finalResult = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
            if (finalResult.endsWith(".0")) {
                finalResult = finalResult.replace(".0", "");
            }
            return finalResult;
        } catch (Exception e) {
            return "Err";
        }
    }
}
package com.example.simplecal;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView resultTv,solutionTv;

    MaterialButton buttonc,buttonopenbracket,buttonclosebracket;
    MaterialButton buttondivide,buttoninto,buttonsubtrac,buttonAdd,buttonequal;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
    MaterialButton buttonac,buttondot;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv = findViewById(R.id.result_Tv);
        solutionTv = findViewById(R.id.solution_Tv);

        assignId(buttonc,R.id.button_c);
        assignId(buttonopenbracket,R.id.button_open_bracket);
        assignId(buttonclosebracket,R.id.button_close_bracket);

        assignId(buttondivide,R.id.button_divide);
        assignId(buttoninto,R.id.button_into);
        assignId(buttonsubtrac,R.id.button_Subtract);
        assignId(buttonAdd,R.id.button_Add);
        assignId(buttonequal,R.id.button_equal);

        assignId(button0,R.id.button_Zero);
        assignId(button1,R.id.button_one);
        assignId(button2,R.id.button_two);
        assignId(button3,R.id.button_three);
        assignId(button4,R.id.button_four);
        assignId(button5,R.id.button_five);
        assignId(button6,R.id.button_six);
        assignId(button7,R.id.button_seven);
        assignId(button8,R.id.button_eight);
        assignId(button9,R.id.button_nine);

        assignId(buttonac,R.id.button_AC);
        assignId(buttondot,R.id.button_dot);



    }

    void assignId(MaterialButton btn,int id){
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();

        String dataToCalculate = solutionTv.getText().toString();
        
        if(buttonText.equals("AC")){
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }

        if(buttonText.equals("=")){
            solutionTv.setText(resultTv.getText());
            return;
        }

        if(buttonText.equals("C")){
            dataToCalculate=dataToCalculate.substring(0,dataToCalculate.length()-1);
        }
        else{
            dataToCalculate = dataToCalculate+buttonText;
        }
        solutionTv.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);
        if(!finalResult.equals("Error")){
            resultTv.setText((finalResult));
        }
    }
    String getResult(String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();
            String finalResult = context.evaluateString(scriptable, data, "javascript", 1, null).toString();
           if(finalResult.endsWith(".0")){
               finalResult=finalResult.replace(".0" , "");
           }
            return finalResult;
        } catch (Exception e) {
            return "Error";
        }
    }
}
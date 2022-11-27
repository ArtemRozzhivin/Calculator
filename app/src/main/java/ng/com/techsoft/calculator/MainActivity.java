package ng.com.techsoft.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;


public class MainActivity extends AppCompatActivity {

    private EditText inputtext;
    private TextView displaytext, screen;
    private String currentOperator="", display="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button deleteBtn = (Button) findViewById(R.id.butdelet);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletenumber();
            }
        });

        screen = (TextView)findViewById(R.id.input_box);
        screen.setText(display);
        inputtext = findViewById(R.id.input_box);
        displaytext = findViewById(R.id.result_box);
    }

    public void onClearButton(View v) {
        inputtext.getText().clear();
        displaytext.setText("");
    }

    public void deletenumber() {
        this.inputtext.getText().delete(getinput().length() - 1, getinput().length());
    }

    private void appendToLast(String str) {
        this.inputtext.getText().append(str);
    }

    public void onClickNumber(View v) {
        Button b = (Button) v;
        display += b.getText();
        appendToLast(display);
        display="";
    }

    public void onClickOperator(View v) {
        Button b = (Button) v;
        display += b.getText();
        if(endsWithOperatore())
        {
            replace(display);
            currentOperator = b.getText().toString();
            display = "";
        }
        else {
            appendToLast(display);
            currentOperator = b.getText().toString();

            display = "";
        }

    }

    private boolean endsWithOperatore() {
        return getinput().endsWith("+") || getinput().endsWith("-") || getinput().endsWith("/") || getinput().endsWith("x");
    }

    private void replace(String str) {
        inputtext.getText().replace(getinput().length() - 1, getinput().length(), str);
    }

    private String getinput() {
        return this.inputtext.getText().toString();
    }


    public void equalresult(View v) {
        String input = getinput();

        if(!endsWithOperatore()) {
            Expression expression = new ExpressionBuilder(input).build();
            double result = expression.evaluate();
            displaytext.setText(String.valueOf(result));
        }
        else displaytext.setText("");

    }

}
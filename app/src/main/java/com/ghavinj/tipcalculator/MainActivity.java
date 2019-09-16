package com.ghavinj.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button calculateButton;
    private EditText billAmount;
    private TextView resultTextView;
    private SeekBar tipSeekbar;
    private TextView percentageUpdate;
    private int seekBarPercentage;
    private float enteredBillFloat;
    private TextView totalBillTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        percentageUpdate = (TextView)findViewById(R.id.percentageUpdateTextView);
        billAmount = (EditText)findViewById(R.id.billAmountID);
        resultTextView = (TextView)findViewById(R.id.resultTextView);
        tipSeekbar = (SeekBar)findViewById(R.id.tipSeekBar);
        calculateButton = (Button)findViewById(R.id.calculateTipButton);
        totalBillTextView = (TextView)findViewById(R.id.totalBillTextView);

        calculateButton.setOnClickListener(this);

        tipSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                percentageUpdate.setText(String.valueOf(seekBar.getProgress()) + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekBarPercentage = seekBar.getProgress();
            }
        });


    }

    @Override
    public void onClick(View v){
        calculate();

    }

    public void calculate(){
        float result = 0.0f;

        if (!billAmount.getText().toString().equals("")) {

            enteredBillFloat = Float.parseFloat(billAmount.getText().toString());
            result = enteredBillFloat * seekBarPercentage / 100;
            resultTextView.setText("Your tip will be " + "$" + String.valueOf(result));
            totalBillTextView.setText("Total bill: " + " $" +String.valueOf(enteredBillFloat + result));


        }else{
            Toast.makeText(MainActivity.this, "Please enter a bill amount.", Toast.LENGTH_LONG).show();
        }

    }
}

package com.daclink.ftocconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.daclink.ftocconverter.databinding.ActivityMainBinding;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final String MAIN_ACTIVITY_TEMPERATURE = "com.daclink.ftocconverter.MainActivityTemperature";

    double fahrenheit = 0.0;
    EditText mCtof_edittext;
    Button mCtof_convert_button;
    TextView mCtof_value_display;

    ActivityMainBinding mMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = mMainBinding.getRoot();

        setContentView(view);

        mCtof_edittext = mMainBinding.ctofInputEdittext;
        mCtof_convert_button = mMainBinding.ctofConvertButton;
        mCtof_value_display = mMainBinding.ctofConvertedValueTextview;

        fahrenheit = getIntent().getDoubleExtra(MAIN_ACTIVITY_TEMPERATURE,0.0);

        mCtof_edittext.setText(fahrenheit+"");

//        updateDisplay();

        mCtof_convert_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String celsius = mCtof_edittext.getText().toString();

                try{
                    fahrenheit = Double.parseDouble(celsius);
                    fahrenheit = Util.c_to_f(fahrenheit);
                }catch (NumberFormatException e){
                    Log.d("c_to_f","Could not convert" + celsius);
                }

                updateDisplay();


            }
        });

        mCtof_convert_button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent = Ftoc_Activity.getIntent(getApplicationContext(),fahrenheit);
                startActivity(intent);
                return false;
            }
        });
    }

    private void updateDisplay() {
        mCtof_value_display.setText(String.format(Locale.US, "%.2f",fahrenheit) +"\u00B0F");
    }

    /**
     * An intent factory used to start this (MainActivity) activity.
     *
     * We pass in a context and a temperature, that temperature is used in the next activity
     *
     * @param context the context for the current activity
     * @param temperature the current converted temperature
     * @return an intent that will allow us to start the MainActivity (this activity)
     */
    public static Intent getIntent(Context context, double temperature){
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(MAIN_ACTIVITY_TEMPERATURE,temperature);
        return intent;
    }
}
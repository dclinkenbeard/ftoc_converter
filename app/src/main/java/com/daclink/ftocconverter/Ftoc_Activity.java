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

import com.daclink.ftocconverter.databinding.ActivityFtocBinding;

import java.util.Locale;

public class Ftoc_Activity extends AppCompatActivity {

    private static final String FTOC_ACTIVITY_TEMPERATURE = "com.daclink.ftocconverter.FtocActivityTemperature";
    EditText mFtoC_edittext;
    Button mFtoC_button;
    TextView mFtoC_value_display;

    TextView mFtoC_label_textview;

    ActivityFtocBinding mFtocBinding;

    double celsius = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ftoc);

        mFtocBinding = ActivityFtocBinding.inflate(getLayoutInflater());

        setContentView(mFtocBinding.getRoot());

        mFtoC_edittext = mFtocBinding.ftocInputEdittext;
        mFtoC_button = mFtocBinding.ftocConvertButton;
        mFtoC_value_display = mFtocBinding.ftocConvertedValueTextview;
        mFtoC_label_textview = mFtocBinding.ftocLabelTextview;

        celsius = getIntent().getDoubleExtra(FTOC_ACTIVITY_TEMPERATURE,0.0);

//        updateDisplay();

        mFtoC_edittext.setText(celsius+"");

        mFtoC_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{
                    celsius = Util.f_to_c(Double.parseDouble(mFtoC_edittext.getText().toString()));
                }catch (NumberFormatException e){
                    Log.d("FTOC converter","Could not convert value " + mFtoC_edittext.getText().toString());
                }
                updateDisplay();
            }
        });

        mFtoC_label_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = MainActivity.getIntent(getApplicationContext(),celsius);
                startActivity(intent);
            }
        });

    }

    private void updateDisplay() {
        mFtoC_value_display.setText(String.format(Locale.US, "%.2f",celsius) +"\u00B0C");
    }

    /**
     * An intent factory used to start this (Ftoc_Activity) activity.
     *
     * We pass in a context and a temperature, that temperature is used in the next activity
     *
     * @param context the context for the current activity
     * @param temperature the current converted temperature
     * @return an intent that will allow us to start the Ftoc_Activity (this activity)
     */
    public static Intent getIntent(Context context,double temperature){
        Intent intent = new Intent(context, Ftoc_Activity.class);
        intent.putExtra(FTOC_ACTIVITY_TEMPERATURE,temperature);
        return intent;
    }
}
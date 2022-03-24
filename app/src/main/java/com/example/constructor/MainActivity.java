package com.example.constructor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private View toolBox;
    public CheckBox checkbox;
    public ImageView imageview, door;
    public RadioGroup doors;
    public EditText stageInput;
    public LinearLayout stages;
    public int numStage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolBox = findViewById(R.id.toolbox);
        imageview = findViewById(R.id.hiddenWindow);
        door = findViewById(R.id.door);
        checkbox = findViewById(R.id.roof_window_checkbox);
        doors = findViewById(R.id.radio_group_door_orientation);
        stageInput = findViewById(R.id.stage_num);
        stages = findViewById(R.id.stages);
        checkbox.setOnCheckedChangeListener((buttonView, isChecked) -> imageview.setVisibility(isChecked ? View.VISIBLE: View.GONE));
        doors.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radio_door_left){
                door.setImageResource(R.drawable.door_reverse);
            }
            else {
                door.setImageResource(R.drawable.door);
            }
        });
        stageInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                stages.removeAllViews();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().isEmpty()) {
                    try {
                        numStage = Integer.valueOf(s.toString());
                        if (numStage == 0) {
                            Toast toast = Toast.makeText(getApplicationContext(),
                                    "Этажей не может быть 0!", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                        for (int i = 1; i < numStage; i++) {
                            LayoutInflater inflater = getLayoutInflater();
                            View layout = inflater.inflate(R.layout.stage, stages,true);
                        }
                    } catch (NumberFormatException err) {}
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    public void showSettings(View v){
        if (toolBox.getVisibility() == View.GONE)
            toolBox.setVisibility(View.VISIBLE);
        else
            toolBox.setVisibility(View.GONE);
    }

}
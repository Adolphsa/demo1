package com.dxytech.demo2;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

//    private TimePicker firstTimePicker;
//    private Button button;
//    private TextView textTime;

    private DatePicker datePicker;
    private Button dateButton;
    private TextView dateText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        firstTimePicker = (TimePicker)findViewById(R.id.firstTimePicker); //时间
//        firstTimePicker.setIs24HourView(true);
//        button = (Button)findViewById(R.id.button);
//        textTime = (TextView)findViewById(R.id.timeText);
//
//        TimePickerListener timeListener = new TimePickerListener(); //时间的监听类
//        firstTimePicker.setOnTimeChangedListener(timeListener);
//        ButtonListener buttonListener = new ButtonListener();
//        button.setOnClickListener(buttonListener);

        datePicker = (DatePicker)findViewById(R.id.datePicker);  //日期
        dateButton = (Button)findViewById(R.id.dateButton);
        dateText = (TextView)findViewById(R.id.dateText);
        //datePicker.updateDate(1992,04,23);

        DateListener dateListener = new DateListener();
        dateButton.setOnClickListener(dateListener);

//        btn_intent1 = (Button)findViewById(R.id.btn_intent1);
//        btn_intent2 = (Button)findViewById(R.id.btn_intent2);
//        IntentButtonListener intentButtonListener = new IntentButtonListener();
//        btn_intent1.setOnClickListener(intentButtonListener);
//        btn_intent2.setOnClickListener(intentButtonListener);



    }



//    class TimePickerListener implements TimePicker.OnTimeChangedListener{
//
//        @Override
//        public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
//            System.out.println("时间：" + hourOfDay + "分钟" + "minute");
//        }
//    }
//
//    class ButtonListener implements View.OnClickListener{  //获取时间值的按钮
//        @Override
//        public void onClick(View v) {
//            int hour = firstTimePicker.getCurrentHour();
//            int minute = firstTimePicker.getCurrentMinute();
//            System.out.println("小时" + hour + "分钟" + minute );
//            textTime.setText(hour + ":" + minute + "");
//        }
//    }

    class DateListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            int year = datePicker.getYear();
            int mouth = datePicker.getMonth();
            int day = datePicker.getDayOfMonth();
            dateText.setText(year + "/" + mouth + "/" + day);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

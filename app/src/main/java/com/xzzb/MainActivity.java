package com.xzzb;

import android.app.Activity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends Activity {

    private EditText etName,etNation,etAddress,etId;
    private Button btnSave;
    private TextView mTvUserNamem,mTvBirthday;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName = findViewById(R.id.et_name);
        etNation = findViewById(R.id.et_nation);
        etAddress = findViewById(R.id.et_address);
        etId = findViewById(R.id.et_id);
        mTvUserNamem = findViewById(R.id.user_name);
        mTvBirthday = findViewById(R.id.text_birthday);
        btnSave = findViewById(R.id.btn_save);
        radioGroup = findViewById(R.id.radiogroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_female:

                        break;
                }
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {//保存点击事件
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etName.getText().toString().trim())){
                    Toast.makeText(MainActivity.this,"姓名不能为空",Toast.LENGTH_LONG).show();
                }else if (TextUtils.isEmpty(etNation.getText().toString().trim())){
                    Toast.makeText(MainActivity.this,"民族不能为空",Toast.LENGTH_LONG).show();
                }else if (TextUtils.isEmpty(etAddress.getText().toString().trim())){
                    Toast.makeText(MainActivity.this,"地址不能为空",Toast.LENGTH_LONG).show();
                }else if (TextUtils.isEmpty(etId.getText().toString().trim())){
                    Toast.makeText(MainActivity.this,"身份证号不能为空",Toast.LENGTH_LONG).show();
                }else if (etId.getText().toString().trim().length()!=18){
                    Toast.makeText(MainActivity.this,"身份证号必须18位",Toast.LENGTH_LONG).show();
                }else {
                    mTvBirthday.setText(getBirthday(etId.getText().toString().trim()));
                    String aa = mTvUserNamem.getText().toString().trim();
                    Toast.makeText(MainActivity.this,aa.substring(mTvUserNamem.getText().toString().trim().indexOf('：')+1)+"\n 保存成功",Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    public static String getBirthday(String idCard) {
        if (idCard == null) {
            return null;
        }
        Pattern p1 = Pattern.compile("\\d{6}(\\d{8}).*"); // 用于提取出生日字符串
        Pattern p2 = Pattern.compile("(\\d{4})(\\d{2})(\\d{2})");// 用于将生日字符串进行分解为年月日

        Matcher matcher = p1.matcher(idCard);
        if (matcher.find()) {
            String birthday = matcher.group(1);
            Matcher matcher2 = p2.matcher(birthday);
            if (matcher2.find()) {
                StringBuilder sb = new StringBuilder();
                sb.append(matcher2.group(1));
                sb.append('年');
                sb.append(matcher2.group(2));
                sb.append('月');
                sb.append(matcher2.group(3));
                sb.append("日");
                return sb.toString();
            }

        }
        return null;
    }
}


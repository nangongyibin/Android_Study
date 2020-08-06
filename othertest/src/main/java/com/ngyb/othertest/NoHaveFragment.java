package com.ngyb.othertest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.daquexian.flexiblerichtextview.FlexibleRichTextView;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/8/6 20:31
 */
public class NoHaveFragment extends Fragment {

    private FlexibleRichTextView frtv12, frtv21, frtv83, frtv101, frtv132, frtv142, line22, line32, line52;
    private EditText et1, et2, et3, et4, et5, et6;
    private TextView tvAverage;
    private String str1, str2, str3, str4, str5, str6;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_nohave, null);
        init(view);
        return view;
    }

    private void init(View view) {
        initView(view);
        initData();
        initListener();
    }

    private void initListener() {
        et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                tableOneAverage(s.toString(), null, null, null, null, null);
            }
        });
        et2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                tableOneAverage(null, s.toString(), null, null, null, null);
            }
        });
        et3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                tableOneAverage(null, null, s.toString(), null, null, null);
            }
        });

        et4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                tableOneAverage(null, null, null, s.toString(), null, null);
            }
        });
        et5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                tableOneAverage(null, null, null, null, s.toString(), null);
            }
        });
        et6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                tableOneAverage(null, null, null, null, null, s.toString());
            }
        });

    }

    private void tableOneAverage(String v1, String v2, String v3, String v4, String v5, String v6) {
        if (v1 != null) {
            str1 = v1;
        }
        if (v2 != null) {
            str2 = v2;
        }
        if (v3 != null) {
            str3 = v3;
        }
        if (v4 != null) {
            str4 = v4;
        }
        if (v5 != null) {
            str5 = v5;
        }
        if (v6 != null) {
            str6 = v6;
        }
        String average = average();
        tvAverage.setText(average);
    }

    private String average() {
        Double d = 0.0;
        if (str1 != null && !str1.equals("")) {
            double v = Double.parseDouble(str1);
            d += v;
        }
        if (str2 != null && !str2.equals("")) {
            double v = Double.parseDouble(str2);
            d += v;
        }
        if (str3 != null && !str3.equals("")) {
            double v = Double.parseDouble(str3);
            d += v;
        }
        if (str4 != null && !str4.equals("")) {
            double v = Double.parseDouble(str4);
            d += v;
        }
        if (str5 != null && !str5.equals("")) {
            double v = Double.parseDouble(str5);
            d += v;
        }
        if (str6 != null && !str6.equals("")) {
            double v = Double.parseDouble(str6);
            d += v;
        }
        return d / 6 + "";
    }

    private void initData() {
        frtv12.setText("$H_{S}$");
        frtv21.setText("$H_{S}= K\\cdot c\\cdot V$");
        frtv83.setText("$u_{rel}\\left (\\bar{\\beta } \\right )$");
        frtv101.setText("$u_{rel}\\left (\\bar{\\beta } \\right )= \\frac{s}{\\sqrt{6}}\\times \\frac{1}{\\bar{\\beta }}= " +
                "\\frac{1}{\\sqrt{6}}\\times \\sqrt{\\frac{ \\sum_{i=1}^{6}\\left ( \\beta _{i}-\\bar{\\beta } \\right )^{2}}{6-1}}\\times " +
                "\\frac{1}{\\bar{\\beta }}$");
        frtv132.setText("$\\bar{\\beta }$");
        frtv142.setText("$\\beta _{i}$");
        line32.setText("$\\bar{\\beta }$");
        line22.setText("$\\beta _{i}$");
        line52.setText("$u_{rel}\\left (\\bar{\\beta } \\right )$");
    }

    private void initView(View view) {
        frtv12 = view.findViewById(R.id.frtv1_2);
        frtv21 = view.findViewById(R.id.frtv2_1);
        frtv83 = view.findViewById(R.id.frtv8_3);
        frtv101 = view.findViewById(R.id.frtv10_1);
        frtv132 = view.findViewById(R.id.frtv13_2);
        frtv142 = view.findViewById(R.id.frtv14_2);
        line22 = view.findViewById(R.id.line2_2);
        line32 = view.findViewById(R.id.line3_2);
        line52 = view.findViewById(R.id.line5_2);
        et1 = view.findViewById(R.id.et1);
        et2 = view.findViewById(R.id.et2);
        et3 = view.findViewById(R.id.et3);
        et4 = view.findViewById(R.id.et4);
        et5 = view.findViewById(R.id.et5);
        et6 = view.findViewById(R.id.et6);
        tvAverage = view.findViewById(R.id.tv_average);
    }
}

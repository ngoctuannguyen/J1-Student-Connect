package com.example.j1studentconnect.guide;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.j1studentconnect.R;

public class StudyGuide extends AppCompatActivity {
    TextView link1, link2, link3, link4;
    TextView maths_sub, it_beginning, oop_sub, dsa_sub, db_sub, cv_temp, gain_exp, recruitment, lang_cer, mobile, web_dev, cyber_sec, ai_data;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.study_guide);
        ConstructButton();
        addClickOnMathSub();
    }

    private void openRequest(int gravity, int type) {
        final Dialog guide_dialog = new Dialog(this);
        guide_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        guide_dialog.setContentView(R.layout.dialog_guide);
        guide_dialog.setCanceledOnTouchOutside(true);

        Window window = guide_dialog.getWindow();
        if (window == null) return;

        window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);

        TextView txtTitleOfDialog = guide_dialog.findViewById(R.id.dialog_guide_title);
        switch (type) {

            case 1:
                txtTitleOfDialog.setText("Các môn toán cao cấp");
                break;
            case 2:
                txtTitleOfDialog.setText("Nhập môn lập trình");
                break;
            case 3:
                txtTitleOfDialog.setText("Lập trình hướng đối tượng");
                break;
            case 4:
                txtTitleOfDialog.setText("Cấu trúc dữ liệu giải thuật");
                break;
            case 5:
                txtTitleOfDialog.setText("Cơ ở dữ liệu");
                break;
            case 6:
                txtTitleOfDialog.setText("Phái Android/iOS");
                break;
            case 7:
                txtTitleOfDialog.setText("Phái Web Develop");
                break;
            case 8:
                txtTitleOfDialog.setText("Phái Cyber Security");
                break;
            case 9:
                txtTitleOfDialog.setText("Phái AI & Data");
                break;
            case 10:
                txtTitleOfDialog.setText("Mẫu CV tham khảo");
                break;
            case 11:
                txtTitleOfDialog.setText("Trau dồi kinh nghiệm");
                break;
            case 12:
                txtTitleOfDialog.setText("Doanh nghiệp tuyển dụng");
                break;
            case 13:
                txtTitleOfDialog.setText("Chứng chỉ ngoại ngữ");
                break;
        }

        link1 = guide_dialog.findViewById(R.id.link1_guide);
        link2 = guide_dialog.findViewById(R.id.link2_guide);
        link3 = guide_dialog.findViewById(R.id.link3_guide);
        link4 = guide_dialog.findViewById(R.id.link4_guide);

        guide_dialog.show();
    }

    private void ConstructButton() {
        maths_sub = findViewById(R.id.maths_subject);
        it_beginning = findViewById(R.id.it_beginning);
        oop_sub = findViewById(R.id.oop_subject);
        dsa_sub = findViewById(R.id.dsa_subject);
        db_sub = findViewById(R.id.db_subject);
        mobile = findViewById(R.id.mobile);
        web_dev = findViewById(R.id.web_dev);
        cyber_sec = findViewById(R.id.cyber_sec);
        ai_data = findViewById(R.id.ai_data);
        cv_temp = findViewById(R.id.cv_templates);
        gain_exp = findViewById(R.id.gain_experience);
        recruitment = findViewById(R.id.recruitment);
        lang_cer = findViewById(R.id.language_certificate);
    }

    private void addClickOnMathSub() {
        maths_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRequest(Gravity.CENTER, 1);
                link3.setVisibility(View.GONE);
                link4.setVisibility(View.GONE);
                link1.setText("Euraka Uni");
                link1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String webLink = "https://www.youtube.com/@EurekaUni/playlists";
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webLink));
                        startActivity(intent);
                    }
                });
                link2.setText("DVN Toán cao cấp");
                link2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String webLink = "https://www.youtube.com/@dvntoancaocap3151/playlists";
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webLink));
                        startActivity(intent);
                    }
                });
            }
        });
        it_beginning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRequest(Gravity.CENTER, 2);
                link4.setVisibility(View.GONE);
                link1.setText("Free Code Camp");
                link1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String webLink = "https://www.freecodecamp.org/";
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webLink));
                        startActivity(intent);
                    }
                });
                link2.setText("Code Learn");
                link2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String webLink = "https://codelearn.io/learning/";
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webLink));
                        startActivity(intent);
                    }
                });
                link3.setText("W3Schools");
                link3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.w3schools.com/")));
                    }
                });
            }
        });
        oop_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRequest(Gravity.CENTER, 3);
                link4.setVisibility(View.GONE);
                link1.setText("Free Code Camp");
                link1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String webLink = "https://www.freecodecamp.org/";
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webLink));
                        startActivity(intent);
                    }
                });
                link2.setText("UET OASIS");
                link2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String webLink = "https://oasis.uet.vnu.edu.vn/#/login";
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webLink));
                        startActivity(intent);
                    }
                });
                link3.setText("Code Learn");
                link3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String webLink = "https://codelearn.io/learning/object-oriented-programming-in-java";
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webLink));
                        startActivity(intent);
                    }
                });
            }
        });
        dsa_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRequest(Gravity.CENTER, 4);
                link1.setText("Codeforces");
                link1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String webLink = "https://codeforces.com/";
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webLink));
                        startActivity(intent);
                    }
                });
                link2.setText("VNOI Wiki");
                link2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String webLink = "https://vnoi.info/wiki/Home";
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webLink));
                        startActivity(intent);
                    }
                });
                link3.setText("Hackerank");
                link3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String webLink = "https://www.hackerrank.com/";
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webLink));
                        startActivity(intent);
                    }
                });
                link4.setText("Leetcode");
                link4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String webLink = "https://leetcode.com/problems";
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webLink));
                        startActivity(intent);
                    }
                });
            }
        });
        db_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRequest(Gravity.CENTER, 5);
                link4.setVisibility(View.GONE);
                link1.setText("Hackerank");
                link1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String webLink = "https://www.hackerrank.com/";
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webLink));
                        startActivity(intent);
                    }
                });
                link2.setText("DB-Engines");
                link2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String webLink = "https://db-engines.com/en/";
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webLink));
                        startActivity(intent);
                    }
                });
                link3.setText("Database Jonal");
                link3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String webLink = "https://www.databasejournal.com/";
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webLink));
                        startActivity(intent);
                    }
                });
            }
        });
        mobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRequest(Gravity.CENTER, 6);
                link4.setVisibility(View.GONE);
                link1.setText("Android Studio");
                link1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String webLink = "https://developer.android.com/docs";
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webLink));
                        startActivity(intent);
                    }
                });
                link2.setText("Flutter");
                link2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String webLink = "https://flutter.dev/learn";
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webLink));
                        startActivity(intent);
                    }
                });
                link3.setText("Viblo.asia");
                link3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String webLink = "https://viblo.asia/followings";
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webLink));
                        startActivity(intent);
                    }
                });
            }
        });
        web_dev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRequest(Gravity.CENTER, 7);
                link4.setVisibility(View.GONE);
                link1.setText("Free Code Camp");
                link1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String webLink = "https://www.freecodecamp.org/";
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webLink));
                        startActivity(intent);
                    }
                });
                link2.setText("W3Schools");
                link2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String webLink = "https://www.w3schools.com/";
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webLink));
                        startActivity(intent);
                    }
                });
                link3.setText("Viblo.asia");
                link3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String webLink = "https://viblo.asia/followings";
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webLink));
                        startActivity(intent);
                    }
                });
            }
        });
        cyber_sec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRequest(Gravity.CENTER, 8);
                link4.setVisibility(View.GONE);
                link1.setText("Viblo CTF");
                link1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String webLink = "https://ctf.viblo.asia/";
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webLink));
                        startActivity(intent);
                    }
                });
                link2.setText("CTFtime");
                link2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String webLink = "https://ctftime.org/event/list/";
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webLink));
                        startActivity(intent);
                    }
                });
                link3.setText("Hacker101 CTF");
                link3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String webLink = "https://ctf.hacker101.com/";
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webLink));
                        startActivity(intent);
                    }
                });
            }
        });
        ai_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRequest(Gravity.CENTER, 9);
                link1.setText("Kaggle");
                link1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String webLink = "https://www.kaggle.com/";
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webLink));
                        startActivity(intent);
                    }
                });
                link2.setText("Marchine Learning");
                link2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String webLink = "https://machinelearningcoban.com/";
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webLink));
                        startActivity(intent);
                    }
                });
                link3.setText("Khanh's blog");
                link3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String webLink = "https://phamdinhkhanh.github.io/home";
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webLink));
                        startActivity(intent);
                    }
                });
                link4.setText("Papers With Code");
                link4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String webLink = "https://paperswithcode.com/";
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webLink));
                        startActivity(intent);
                    }
                });
            }
        });
        cv_temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRequest(Gravity.CENTER, 10);
                link4.setVisibility(View.GONE);
                link1.setText("Top CV");
                link1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String webLink = "https://www.topcv.vn/mau-cv";
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webLink));
                        startActivity(intent);
                    }
                });
                link2.setText("resume.io");
                link2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String webLink = "https://resume.io/resume-templates";
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webLink));
                        startActivity(intent);
                    }
                });
                link3.setText("CakeResume");
                link3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String webLink = "https://www.cakeresume.com/?fbclid=IwAR0wUJn5cBcMzlY-l-t3706oDzQxD-8UBcmiga61Kefj8BfROSVH1pgaoKM";
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webLink));
                        startActivity(intent);
                    }
                });
            }
        });
        gain_exp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRequest(Gravity.CENTER, 11);
                link4.setVisibility(View.GONE);
                link1.setText("Stack Overflow");
                link1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String webLink = "https://stackoverflow.com/";
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webLink));
                        startActivity(intent);
                    }
                });
                link2.setText("Medium");
                link2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String webLink = "https://medium.com/";
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webLink));
                        startActivity(intent);
                    }
                });
                link3.setText("Viblo.asia");
                link3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String webLink = "https://viblo.asia/followings";
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webLink));
                        startActivity(intent);
                    }
                });
            }
        });
        recruitment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRequest(Gravity.CENTER, 12);
                link1.setText("TopDev");
                link1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String webLink = "https://topdev.vn/";
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webLink));
                        startActivity(intent);
                    }
                });
                link2.setText("ITviec");
                link2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String webLink = "https://itviec.com/";
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webLink));
                        startActivity(intent);
                    }
                });
                link3.setText("Group Tuyển dụng IT");
                link3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String webLink = "https://www.facebook.com/groups/174764463261090";
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webLink));
                        startActivity(intent);
                    }
                });
                link4.setText("Timviec365");
                link4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String webLink = "https://timviec365.vn/";
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webLink));
                        startActivity(intent);
                    }
                });
            }
        });
        lang_cer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRequest(Gravity.CENTER, 13);
                link1.setText("IELTS Online Tests");
                link1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String webLink = "https://ieltsonlinetests.com/";
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webLink));
                        startActivity(intent);
                    }
                });
                link2.setText("TOEIC - IIG");
                link2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String webLink = "https://toeic.iigvietnam.com/";
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webLink));
                        startActivity(intent);
                    }
                });
                link3.setText("Vnpjclub");
                link3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String webLink = "https://www.vnjpclub.com/";
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webLink));
                        startActivity(intent);
                    }
                });
                link4.setText("Mazii");
                link4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String webLink = "https://mazii.net/vi-VN/search";
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webLink));
                        startActivity(intent);
                    }
                });
            }
        });
    }
}

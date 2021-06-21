package in.manish.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.faendir.rhino_android.RhinoAndroidHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseError;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ast.Scope;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btnPercent,btnPlus,btnMinus,btnMultiply,btnDivision,btnEqual,btnClear,btnDot,btnBracket;
    TextView tvInput,tvOutput;
    String process;
    boolean checkBracket = false;
    private FirebaseAnalytics mFirebaseAnalytics;
    public static final String MyPREFERENCES = "Manish" ;
    public static final String User = "UserName" ;
    public static final String Type = "UserType" ;
    SharedPreferences sharedpreferences;
    String user;
    DatabaseReference userdatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        userdatabase = FirebaseDatabase.getInstance("https://hidden-chat-82514-default-rtdb.asia-southeast1.firebasedatabase.app").getReference();
        userdatabase = userdatabase.child("NileshChat/7518/");


        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);

        btnPlus = findViewById(R.id.btnPlus);
        btnMinus = findViewById(R.id.btnMinus);
        btnDivision = findViewById(R.id.btnDivision);
        btnMultiply = findViewById(R.id.btnMultiply);

        btnEqual = findViewById(R.id.btnEqual);

        btnClear = findViewById(R.id.btnClear);
        btnDot = findViewById(R.id.btnDot);
        btnPercent = findViewById(R.id.btnPercent);
        btnBracket = findViewById(R.id.btnBracket);

        tvInput = findViewById(R.id.tvInput);
        tvOutput = findViewById(R.id.tvOutput);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, this.MODE_PRIVATE);
        user = sharedpreferences.getString(User, "");

        if (user.equalsIgnoreCase("")) {
            showAlertDialogButtonClicked();
        }

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvInput.setText("");
                tvOutput.setText("");
            }
        });


        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process = tvInput.getText().toString();
                tvInput.setText(process + "0");
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process = tvInput.getText().toString();
                tvInput.setText(process + "1");
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process = tvInput.getText().toString();
                tvInput.setText(process + "2");
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process = tvInput.getText().toString();
                tvInput.setText(process + "3");
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process = tvInput.getText().toString();
                tvInput.setText(process + "4");
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process = tvInput.getText().toString();
                tvInput.setText(process + "5");
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process = tvInput.getText().toString();
                tvInput.setText(process + "6");
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process = tvInput.getText().toString();
                tvInput.setText(process + "6");
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process = tvInput.getText().toString();
                tvInput.setText(process + "7");
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process = tvInput.getText().toString();
                tvInput.setText(process + "8");
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process = tvInput.getText().toString();
                tvInput.setText(process + "9");

            }
        });

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process = tvInput.getText().toString();
                tvInput.setText(process + "+");
            }
        });


        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process = tvInput.getText().toString();
                tvInput.setText(process + "-");
            }
        });

        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process = tvInput.getText().toString();
                tvInput.setText(process + "×");
            }
        });

        btnDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process = tvInput.getText().toString();
                tvInput.setText(process + "÷");
            }
        });

        btnDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process = tvInput.getText().toString();
                tvInput.setText(process + ".");
            }
        });

        btnPercent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process = tvInput.getText().toString();
                tvInput.setText(process + "%");
            }
        });

        btnBracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkBracket){
                    process = tvInput.getText().toString();
                    tvInput.setText(process + ")");
                    checkBracket = false;
                }else{
                    process = tvInput.getText().toString();
                    tvInput.setText(process + "(");
                    checkBracket = true;
                }

            }
        });

        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process = tvInput.getText().toString();
                if(process.equalsIgnoreCase(""))return;
                //todo change to pin that you want to use
                if(process.equalsIgnoreCase("7518")  ){
                    if( !user.equalsIgnoreCase("")){
                        startActivity(new Intent(MainActivity.this,ChatActivity.class));
                    }
                    else {
                        showAlertDialogButtonClicked();
                    }

                }

                process = process.replaceAll("×","*");
                process = process.replaceAll("%","/100");
                process = process.replaceAll("÷","/");

                Context rhino = Context.enter();

                rhino.setOptimizationLevel(-1);

                String finalResult = "";

                try {
                    Scriptable scriptable = rhino.initStandardObjects();
                    finalResult = rhino.evaluateString(scriptable,process,"javascript",1,null).toString();
                }catch (Exception e){
                    finalResult="0";
                }

                tvOutput.setText(finalResult);
            }
        });


    }

    public void showAlertDialogButtonClicked()
    {

        Dialog dialog;
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.prompt);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.BOTTOM;
        lp.windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setAttributes(lp);


        dialog.show();
        // Get Data from Alert box

        EditText editTextPrompt = dialog.findViewById(R.id.editTextData);



        Button confirm = dialog.findViewById(R.id.confirm_btn);


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextPrompt.getText().toString();
                if(name.equalsIgnoreCase("")) return;

                Log.d("Manish","Confirm");
                ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(android.content.Context.CONNECTIVITY_SERVICE);
                if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    //we are connected to a network
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    Log.d("Manish","Net On");
                    // check user count
                    userdatabase.child("count").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                            if (task.isSuccessful()) {
                               String count = ""+task.getResult().getValue();
                               count = count.trim();
                                Log.d("Manish","Count: "+count);

                                if (count.equalsIgnoreCase("0")) {
                                    // run some code
                                    userdatabase.child("user1").setValue(name);
                                    userdatabase.child("count").setValue("1");
                                    editor.putString(Type, "user1");
                                    editor.putString(User, name);
                                    editor.commit();
                                    Snackbar.make(getWindow().getDecorView().getRootView(),name +" You Are Ready Now", Snackbar.LENGTH_SHORT).show();
                                    user = name;

                                }else if (count.equalsIgnoreCase("1")) {
                                    // run some code
                                    userdatabase.child("user2").setValue(name);
                                    userdatabase.child("count").setValue("2");
                                    editor.putString(Type, "user2");
                                    editor.putString(User, name);
                                    editor.commit();
                                    Snackbar.make(getWindow().getDecorView().getRootView(),name +" You Are Ready Now", Snackbar.LENGTH_SHORT).show();
                                    user = name;

                                }
                                else {
                                    //do something if not exists
                                    Snackbar.make(getWindow().getDecorView().getRootView(), "Seat Full Please Contact Manish Pal", Snackbar.LENGTH_SHORT).show();
                                    Toast.makeText(MainActivity.this, "Seat Full Please Contact Manish Pal", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                            }
                        }
                    });



                    // check user count



                }
                else{
                    Snackbar.make(getWindow().getDecorView().getRootView(), "Check The Internet Connection", Snackbar.LENGTH_SHORT).show();
                }
                //Toast.makeText(activity, "Prompt: "+dataToBeChange, Toast.LENGTH_SHORT).show();
                dialog.cancel();

            }
        });

    }
}



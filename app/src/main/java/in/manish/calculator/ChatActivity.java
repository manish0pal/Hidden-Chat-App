package in.manish.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static in.manish.calculator.MainActivity.MyPREFERENCES;
import static in.manish.calculator.MainActivity.User;
import static in.manish.calculator.MainActivity.Type;

public class ChatActivity extends AppCompatActivity {
    DatabaseReference database;
    EditText editText;
    TextView otherTv;
    List<Message> messageList;
    RecyclerView recyclerView;
    MessageAdapter messageAdapter;
    SharedPreferences sharedpreferences;
    public static String user,usertype;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, this.MODE_PRIVATE);
        user = sharedpreferences.getString(User, "");
        usertype = sharedpreferences.getString(Type, "");
        // Write a message to the database
         database = FirebaseDatabase.getInstance("https://hidden-chat-82514-default-rtdb.asia-southeast1.firebasedatabase.app").getReference();
         database = database.child("NileshChat/7518/");
         System.out.println(database);
        editText = findViewById(R.id.msget);
        otherTv = findViewById(R.id.other_tv_title);




         recyclerView = findViewById(R.id.recyclerviewchat);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);





        boolean connected;
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;

        }
        else{
            connected = false;
            Snackbar.make(getWindow().getDecorView().getRootView(), "Check The Internet Connection", Snackbar.LENGTH_SHORT).show();
            return;
        }
        String otheruser;
        if(usertype.equalsIgnoreCase("user1")){
            otheruser = "user2";
        }
        else{
            otheruser = "user1";
        }
        database.child(otheruser).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    otherTv.setText(String.valueOf(Objects.requireNonNull(task.getResult()).getValue()));
                }
            }
        });


        System.out.println("connection is "+connected);



        //get message from firebase
        messageList= new ArrayList<>();
        DatabaseReference chatdb = database.child("chat");
        chatdb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                messageList.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Message message = dataSnapshot.getValue(Message.class);
                    messageList.add(message);
                }
                messageAdapter  =  new MessageAdapter(ChatActivity.this,messageList);
                recyclerView.setAdapter(messageAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }
    public  void delChat(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Delete Chat");
        builder.setMessage("You Want to Delete Chat from Both Side");
        builder.setPositiveButton("Confirm",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseReference chatdb = database.child("chat");
                        chatdb.setValue(null);
                        dialog.cancel();
                    }
                });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void sendMsg(View view){
        String msg_to_send = editText.getText().toString();
        if(msg_to_send.equalsIgnoreCase("")){
            return;
        }
        else{
            HashMap<String,Object> hashMap = new HashMap<>();
            hashMap.put("sender",user);
            hashMap.put("msg",msg_to_send);
            database.child("chat").push().setValue(hashMap);
            editText.setText("");
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        messageList.clear();
    }
}
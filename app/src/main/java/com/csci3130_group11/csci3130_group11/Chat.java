package com.csci3130_group11.csci3130_group11;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class Chat extends AppCompatActivity {

    private DatabaseReference firebaseReference;
    private FirebaseDatabase firebaseDBInstance;

    private String userName;
    private Button send;
    private EditText message;
    private TextView postedMessages;
    private String messageKey;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        send = (Button) findViewById(R.id.sendButton);
        message = (EditText) findViewById(R.id.messageEditText);
        postedMessages = (TextView) findViewById(R.id.messageTextView);

        setUserName();

        /*
        Firebase connectivity
         */
        firebaseDBInstance = FirebaseDatabase.getInstance();
        firebaseReference = firebaseDBInstance.getReference().child("chat");

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> m = new HashMap<String, Object>();
                messageKey = firebaseReference.push().getKey();
                firebaseReference.updateChildren(m);
                DatabaseReference message = firebaseReference.child(messageKey);

                Map<String, Object> mContent = new HashMap<String, Object>();
                mContent.put("username", userName);
            }
        });


    }

    /**
     * Have the user set their user name.
     * If they enter a user name, then chat.
     * If they cancel entering a name, then go back to main activity.
     */
    public void setUserName() {
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle("Enter user name:");

        final EditText inputedName = new EditText(this);

        b.setView(inputedName);
        b.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                userName = inputedName.getText().toString();
            }
        });
        b.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                finish();
            }
        });

        b.show();
    }

}

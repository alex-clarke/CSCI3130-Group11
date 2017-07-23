package com.csci3130_group11.csci3130_group11;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * This "Chat" class facilitates the chat portion of the greenhouse monitoring app.
 */
public class Chat extends AppCompatActivity {

    private Button send;
    private EditText message;
    private TextView postedMessages;

    private String userName;

    private DatabaseReference firebaseReference;
    private FirebaseDatabase firebaseDBInstance;

    private String messageKey;

    private String conversationMessage;
    private String conversationUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        send = (Button) findViewById(R.id.sendButton);
        message = (EditText) findViewById(R.id.messageEditText);
        postedMessages = (TextView) findViewById(R.id.messageTextView);

        /*
        Set the username
         */
        setUserName();

        /*
        Firebase connectivity
         */
        firebaseDBInstance = FirebaseDatabase.getInstance();
        firebaseReference = firebaseDBInstance.getReference().child("chat");

        /*
        Listener for button "SEND" to send the inputted message to the database
         */
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String, Object> m = new HashMap<String, Object>();
                messageKey = firebaseReference.push().getKey();
                firebaseReference.updateChildren(m);

                DatabaseReference messageRef = firebaseReference.child(messageKey);
                Map<String, Object> mContent = new HashMap<String, Object>();
                mContent.put("username", userName);
                mContent.put("content", message.getText().toString());

                messageRef.updateChildren(mContent);
                ((EditText) findViewById(R.id.messageEditText)).setText("");
            }
        });

        /*
        Firebase methods to update the conversation when a new message is added to the database
         */
        firebaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                appendConversation(dataSnapshot);
                scrollDown();
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                appendConversation(dataSnapshot);
                scrollDown();
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        /*
        Have the conversation ScrollView start at the bottom. Showing the latest messages
         */
        scrollDown();
    }


    /**
     * Scrolls the scrollview down to the bottom
     */
    private void scrollDown() {
        final ScrollView scrollview = ((ScrollView) findViewById(R.id.scrollview));
        scrollview.postDelayed(new Runnable() {
            @Override
            public void run() {
                scrollview.fullScroll(ScrollView.FOCUS_DOWN);
            }
        }, 100);
    }



    /**
     * This method is called when the database is updated with a new message.
     * It appends that new message to the conversation.
     * @param dataSnapshot
     */
    private void appendConversation(DataSnapshot dataSnapshot) {
        Iterator it = dataSnapshot.getChildren().iterator();
        while(it.hasNext()){
            conversationMessage = (String) ((DataSnapshot)it.next()).getValue();
            conversationUserName = (String) ((DataSnapshot)it.next()).getValue();
            postedMessages.append(conversationUserName +": " + conversationMessage+"\n");
        }
    }

    /**
     * This method enables the chat class to retrieve/set a username for the user.
     *
     * If the user has set a username previously
     *      Then use that previously entered username
     * Else
     *      Have the user set their user name.
     *      If they enter a user name, then chat.
     *      If they cancel entering a name, then go back to main activity.
     */
    public void setUserName() {

        String temp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("USERNAME", "defaultStringIfNothingFound");

        // if user name exist
        if(!(temp.equals("defaultStringIfNothingFound"))) {
            userName = temp;
        }
        //if no username exist
        else if(temp.equals("defaultStringIfNothingFound")){
            AlertDialog.Builder b = new AlertDialog.Builder(this);
            b.setTitle("Enter user name:");

            final EditText inputtedName = new EditText(this);

            b.setView(inputtedName);
            b.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    userName = inputtedName.getText().toString();
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("USERNAME", userName).apply();
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, MainActivity.class));
    }
}

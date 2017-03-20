package com.digzdigital.reminderapp.data.messenger;

import com.digzdigital.reminderapp.data.messenger.model.Forum;
import com.digzdigital.reminderapp.data.messenger.model.MessageObject;
import com.digzdigital.reminderapp.eventbus.EventType;
import com.digzdigital.reminderapp.eventbus.FirebaseEvent;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Map;

public class AppApiHelper implements ApiHelper {

    private DatabaseReference databaseReference;
    private MessageObject newMessage, newForumMessage;
    private ArrayList<MessageObject> forumMessages;
    private ArrayList<Forum> fora;
    private String username, chatWithUsername;

    public AppApiHelper(){
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public void setChatUsers(String username, String chatWithUsername) {
        this.username = username;
        this.chatWithUsername = chatWithUsername;
    }

    @Override
    public void SendMessage(Map<String, String> map) {
        databaseReference.child("messages/" + username + "_" + chatWithUsername).push().setValue(map);
        databaseReference.child("messages/" + chatWithUsername + "_" + username).push().setValue(map);

    }

    @Override
    public void queryForNewMessage() {
        databaseReference.child("messages/" + username + "_" + chatWithUsername).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                newMessage = dataSnapshot.getValue(MessageObject.class);
                postEvent(EventType.NEW_MESSAGE);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

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
        /*addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                newMessage = dataSnapshot.getValue(MessageObject.class);
                postEvent(EventType.NEW_MESSAGE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/
    }

    @Override
    public MessageObject getNewMessage() {
        return newMessage;
    }

    @Override
    public void queryForForums() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                fora = null;
                fora = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Forum forum = snapshot.getValue(Forum.class);
                    fora.add(forum);
                }
                postEvent(EventType.FORUMS);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public ArrayList<Forum> getFora() {
        return fora;
    }



    @Override
    public void queryForForumMessages(String forumName) {
        databaseReference.child(forumName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                forumMessages = null;
                forumMessages = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    MessageObject messageObject = snapshot.getValue(MessageObject.class);
                    forumMessages.add(messageObject);
                }
                postEvent(EventType.FORUM_MESSAGES);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public ArrayList<MessageObject> getForumMessages() {
        return forumMessages;
    }

    @Override
    public void postForumMessage(MessageObject messageObject, String forumName) {
        databaseReference.child("forums/" + forumName).push().setValue(messageObject);
    }

    private void postEvent(EventType eventType) {
        EventBus.getDefault().post(new FirebaseEvent(eventType));
    }
}

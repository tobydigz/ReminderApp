package com.digzdigital.reminderapp.data.messenger;


import com.digzdigital.reminderapp.data.messenger.model.Forum;
import com.digzdigital.reminderapp.data.messenger.model.MessageObject;

import java.util.ArrayList;
import java.util.Map;

public interface ApiHelper {

    void setChatUsers(String username, String chatWithUsername);
    void SendMessage(Map<String, String> map);
    void queryForNewMessage();
    MessageObject getNewMessage();
    void queryForForums();
    ArrayList<Forum> getFora();
    void queryForForumMessages(String forumName);
    ArrayList<MessageObject> getForumMessages();
    void postForumMessage(MessageObject messageObject, String forumName);

}

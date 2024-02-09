package org.example.serverapp;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.LinkedList;


/**
 * Class, which stores info about 10 (or less) last messages in private list
 */

public class Story {

    private LinkedList<String> story = new LinkedList<>();

    public void addMessageToStory(String message){

        if(story.size() >= 10){
            story.removeFirst();
            story.add(message);
        }else{
            story.add(message);
        }
    }

    /**
     * Method, which send every message from story list in the output stream of current client
     * @param writer output stream to send message history
     */

    public void printStory(BufferedWriter writer){
        if(story.size() > 0){

            try {
                writer.write("History of 10 last messages: " + "\n");
                for (String message : story){

                    writer.write(message + "\n");
                }

                writer.write("/...." + "\n");
                writer.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

    }
}

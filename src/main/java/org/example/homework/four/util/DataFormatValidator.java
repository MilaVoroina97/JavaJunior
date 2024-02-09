package org.example.homework.four.util;

import javax.swing.*;

public class DataFormatValidator {

    /**
     * @param text with the text to be checked.
     * @return true if the text is null, false otherwise.
     */
    public static boolean isNull(String text){

        return text.isEmpty();
    }

    /**
     * @param text with strings to be checked.
     * @return true if at least one of the strings in the array equals null.
     */
    public static boolean isNull(String[] text){
        for(String t : text){
            if(t.isEmpty()) return true;
        }

        return false;
    }

    /**
     * @param text to be converted to float
     * @return 0.0 if the text is null or in a format that cannot be converted to double.
     */
    public static double convertToDouble(String text){
        if(isNull(text)) return  0.0;

        double convertedString = 0.0;

        try{

            convertedString = Double.parseDouble(text);

        }catch (NumberFormatException ex){

            JOptionPane.showMessageDialog(null, "Text field with incorrect data types", "Registration!", JOptionPane.ERROR_MESSAGE);
        }

        return convertedString;
    }


    public static int convertToInt(String text){

        if(isNull(text)) return -1;
        int convertedString = -1;

        try {

            convertedString = Integer.parseInt(text);

        }catch (NumberFormatException ex){

            JOptionPane.showMessageDialog(null, "Text field with incorrect data types", "Registration!", JOptionPane.ERROR_MESSAGE);
        }

        return convertedString;
    }
}

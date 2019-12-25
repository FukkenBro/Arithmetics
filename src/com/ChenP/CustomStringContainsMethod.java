package com.ChenP;

public class CustomStringContainsMethod {

    public static boolean contains(String request, String target) {

        boolean result = false;

        if (target == null && target.trim().equals("")) {
            return result;
        }
        if (request == null) {
            return result;
        }

        char targetString[] = target.toCharArray();
        char sub[] = request.toCharArray();
        int counter = 0;

        if (sub.length == 0) {
            result = true;
            return result;
        }

        for (int i = 0; i < targetString.length; i++) {

            if (targetString[i] == sub[counter]) {
                counter++;
            } else {
                counter = 0;
            }

            if (counter == sub.length) {
                result = true;
                return result;
            }

        }
        return result;
    }
}


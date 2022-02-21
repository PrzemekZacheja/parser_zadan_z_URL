package pl.codegym.task.task15.task1527;

/*
Parser żądań
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        //tutaj wpisz swój kod
        BufferedReader breader = new BufferedReader(new InputStreamReader(System.in));
        String lineFromBuffer = breader.readLine().trim();

        checkParseToDoubleFromString(lineFromBuffer);
    }

    private static void checkParseToDoubleFromString(String stringURL) {
        String[] parametersTable = separateParametersFromURL(stringURL);
        printTable(parametersTable);

        String[] stringsDirtParameters = getStringTableByRegex(stringURL);

        for (int i = 0; i < stringsDirtParameters.length; i++) {

            if (parametersTable[i].equals("obj")) {
                String stringToConvertDouble;
                int indexOfSign = stringsDirtParameters[i].lastIndexOf("=");
                stringToConvertDouble = stringsDirtParameters[i].substring(indexOfSign + 1);
                try {
                    double convertFromString = Double.parseDouble(stringToConvertDouble);
                    alert(convertFromString);
                } catch (NumberFormatException e) {
                    alert(stringToConvertDouble);
                }
            }
        }

    }

    private static void printTable(String[] parametersListFromURL) {
        for (int i = 0; i < parametersListFromURL.length; i++) {
            System.out.print(parametersListFromURL[i] + " ");
        }
        System.out.println();
    }

    private static String[] separateParametersFromURL(String lineFromBuffer) {
        String[] stringList = getStringTableByRegex(lineFromBuffer);

        for (int i = 0; i < stringList.length; i++) {
            int indexOfSign = stringList[i].lastIndexOf("=");
            if (indexOfSign == -1) continue;
            stringList[i] = stringList[i].substring(0, indexOfSign);
        }
        return stringList;
    }

    private static String[] getStringTableByRegex(String lineFromBuffer) {
        String[] stringList;

        String stringafterSign = getStringAfterSign(lineFromBuffer, '?');
        stringList = stringafterSign.split("&");
        return stringList;
    }

    private static String getStringAfterSign(String lineFromBuffer, char character) {
        int indexOfStartParametersInURL = lineFromBuffer.lastIndexOf(character);
        String stringafterSign = lineFromBuffer.substring(indexOfStartParametersInURL + 1);
        return stringafterSign;
    }

    public static void alert(double value) {
        System.out.println("double: " + value);
    }

    public static void alert(String value) {
        System.out.println("String: " + value);
    }
}


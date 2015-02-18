package de.marcelkapfer.morseconverter;

/*
    This is a Android application for converting writtenMorse and normal morse code.
    Copyright (C) 2014-2015  Marcel Michael Kapfer

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.
    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

    Marcel Michael Kapfer
    marcelmichaelkapfer@yahoo.co.nz

 */

/*
 * Code for converting morse code into latin letters
 */
public class EncodeNormalMorseManager {

    private String inputMessage;

    EncodeNormalMorseManager(String string) {
        inputMessage = string;
    }

    public void setString(String string){
        inputMessage = string;
    }

    public String getString(){
        return inputMessage;
    }


    public String getEncodedString() {
        // Converts the input string into a StringBuffer
        StringBuffer message = new StringBuffer(inputMessage);
        // Delete the space at the end which is automatically set through some keyboards
        if (message.toString().endsWith(" ")) {
            message = message.deleteCharAt(message.length() - 1);
        }
        // Declaring variables
        String input;
        StringBuffer output = new StringBuffer();
        input = message.toString() + "   ";
        StringBuffer inputToSign = new StringBuffer(input);
        while (!inputToSign.toString().equals("   ")) {
            int d = 0;
            boolean signFull = true;
            StringBuffer sign = new StringBuffer();
            while (signFull) {
                if (inputToSign.toString().startsWith("       ")) {
                    output.append(" ");
                    inputToSign.delete(d, d + 7);
                } else if (inputToSign.toString().substring(d, d + 3).equals("   ")) {
                    if (d == 0) {
                        inputToSign.delete(0, 3);
                    } else {
                        sign.replace(0, sign.length(), inputToSign
                                .toString().substring(0, d));
                        inputToSign.delete(0, d);
                        signFull = false;
                    }
                } else {
                    d++;
                }
            }
            if (sign.toString().equals(".-")) {
                output.append("A");
            } else if (sign.toString().equals("-...")) {
                output.append("B");
            } else if (sign.toString().equals("-.-.")) {
                output.append("C");
            } else if (sign.toString().equals("-..")) {
                output.append("D");
            } else if (sign.toString().equals(".")) {
                output.append("E");
            } else if (sign.toString().equals("..-.")) {
                output.append("F");
            } else if (sign.toString().equals("--.")) {
                output.append("G");
            } else if (sign.toString().equals("....")) {
                output.append("H");
            } else if (sign.toString().equals("..")) {
                output.append("I");
            } else if (sign.toString().equals(".---")) {
                output.append("J");
            } else if (sign.toString().equals("-.-")) {
                output.append("K");
            } else if (sign.toString().equals(".-..")) {
                output.append("L");
            } else if (sign.toString().equals("--")) {
                output.append("M");
            } else if (sign.toString().equals("-.")) {
                output.append("N");
            } else if (sign.toString().equals("---")) {
                output.append("O");
            } else if (sign.toString().equals(".--.")) {
                output.append("P");
            } else if (sign.toString().equals("--.-")) {
                output.append("Q");
            } else if (sign.toString().equals(".-.")) {
                output.append("R");
            } else if (sign.toString().equals("...")) {
                output.append("S");
            } else if (sign.toString().equals("-")) {
                output.append("T");
            } else if (sign.toString().equals("..-")) {
                output.append("U");
            } else if (sign.toString().equals("...-")) {
                output.append("V");
            } else if (sign.toString().equals(".--")) {
                output.append("W");
            } else if (sign.toString().equals("-..-")) {
                output.append("X");
            } else if (sign.toString().equals("-.--")) {
                output.append("Y");
            } else if (sign.toString().equals("--..")) {
                output.append("Z");
            } else if (sign.toString().equals("-----")) {
                output.append(". (zero)");
            } else if (sign.toString().equals(".----")) {
                output.append("-");
            } else if (sign.toString().equals("..---")) {
                output.append("2");
            } else if (sign.toString().equals("...--")) {
                output.append("3");
            } else if (sign.toString().equals("....-")) {
                output.append("4");
            } else if (sign.toString().equals(".....")) {
                output.append("5");
            } else if (sign.toString().equals("-....")) {
                output.append("6");
            } else if (sign.toString().equals("--...")) {
                output.append("7");
            } else if (sign.toString().equals("---..")) {
                output.append("8");
            } else if (sign.toString().equals("----.")) {
                output.append("9");
            } else if (sign.toString().equals(".-.-")) {
                output.append("Ä");
            } else if (sign.toString().equals("---.")) {
                output.append("Ö");
            } else if (sign.toString().equals("..--")) {
                output.append("Ü");
            } else if (sign.toString().equals("...--...")) {
                output.append("ß");
            } else if (sign.toString().equals("----")) {
                output.append("CH");
            } else if (sign.toString().equals(".-.-.-")) {
                output.append(".");
            } else if (sign.toString().equals("--..--")) {
                output.append(",");
            } else if (sign.toString().equals("---...")) {
                output.append(":");
            } else if (sign.toString().equals("-.-.-.")) {
                output.append(";");
            } else if (sign.toString().equals("..--..")) {
                output.append("?");
            } else if (sign.toString().equals("-.-.--")) {
                output.append("!");
            } else if (sign.toString().equals("-....-")) {
                output.append("-");
            } else if (sign.toString().equals("..--.-")) {
                output.append("_");
            } else if (sign.toString().equals("-.--.")) {
                output.append("(");
            } else if (sign.toString().equals("-.--.-")) {
                output.append(")");
            } else if (sign.toString().equals(".----.")) {
                output.append("'");
            } else if (sign.toString().equals("-...-")) {
                output.append("=");
            } else if (sign.toString().equals(".-.-.")) {
                output.append("+ or End of the signal");
            } else if (sign.toString().equals("-..-.")) {
                output.append("/");
            } else if (sign.toString().equals(".--.-.")) {
                output.append("@");
            } else if (sign.toString().equals("-.-.-")) {
                output.append("Begin of the signal");
            } else if (sign.toString().equals("-...-")) {
                output.append("Wait");
            } else if (sign.toString().equals("...-.")) {
                output.append("Understood");
            } else if (sign.toString().equals("...-.-")) {
                output.append("End of work");
            } else if (sign.toString().equals("...---...")) {
                output.append("SOS");
            } else if (sign.toString().equals("........")) {
                output.append("Error");
            } else {
                output.replace(0, output.length(), "Code not listed or wrong.");
            }
        }
        return output.toString();
    }
}
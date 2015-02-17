package de.marcelkapfer.morseconverter;
    /*
	 * Code for converting writtenMorse to latin letters
	 */

public class EncodeWrittenMorseManager {

    private String inputMessage;

    EncodeWrittenMorseManager(String string){
        inputMessage = string;
    }

    public String getString(){
        return inputMessage;
    }

    public void setString(String string){
        inputMessage = string;
    }

    public String getEncodedString() {
        StringBuffer message = new StringBuffer(inputMessage);
        if (message.toString().endsWith(" ")) {
            message = message.deleteCharAt(message.length() - 1);
        }
        // Variables
        String input;
        StringBuffer output = new StringBuffer();
        input = message.toString().toUpperCase() + "#";
        StringBuffer inputToSign = new StringBuffer(input);
        while (!inputToSign.toString().equals("#")) {
            int d = 0;
            boolean signFull = true;
            StringBuffer sign = new StringBuffer();
            while (signFull) {
                if (inputToSign.toString().charAt(d) == '+'
                        || inputToSign.toString().charAt(d) == '#') {
                    if (d == 0) {
                        if (inputToSign.toString().startsWith("+")) {
                            output.append(" ");
                        }
                        inputToSign.deleteCharAt(0);
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
            if (sign.toString().equals("01")) {
                output.append("A");
            } else if (sign.toString().equals("1000")) {
                output.append("B");
            } else if (sign.toString().equals("1010")) {
                output.append("C");
            } else if (sign.toString().equals("100")) {
                output.append("D");
            } else if (sign.toString().equals("0")) {
                output.append("E");
            } else if (sign.toString().equals("0010")) {
                output.append("F");
            } else if (sign.toString().equals("110")) {
                output.append("G");
            } else if (sign.toString().equals("0000")) {
                output.append("H");
            } else if (sign.toString().equals("00")) {
                output.append("I");
            } else if (sign.toString().equals("0111")) {
                output.append("J");
            } else if (sign.toString().equals("101")) {
                output.append("K");
            } else if (sign.toString().equals("0100")) {
                output.append("L");
            } else if (sign.toString().equals("11")) {
                output.append("M");
            } else if (sign.toString().equals("10")) {
                output.append("N");
            } else if (sign.toString().equals("111")) {
                output.append("O");
            } else if (sign.toString().equals("0110")) {
                output.append("P");
            } else if (sign.toString().equals("1101")) {
                output.append("Q");
            } else if (sign.toString().equals("010")) {
                output.append("R");
            } else if (sign.toString().equals("000")) {
                output.append("S");
            } else if (sign.toString().equals("1")) {
                output.append("T");
            } else if (sign.toString().equals("001")) {
                output.append("U");
            } else if (sign.toString().equals("0001")) {
                output.append("V");
            } else if (sign.toString().equals("011")) {
                output.append("W");
            } else if (sign.toString().equals("1001")) {
                output.append("X");
            } else if (sign.toString().equals("1011")) {
                output.append("Y");
            } else if (sign.toString().equals("1100")) {
                output.append("Z");
            } else if (sign.toString().equals("11111")) {
                output.append("0 (zero)");
            } else if (sign.toString().equals("01111")) {
                output.append("1");
            } else if (sign.toString().equals("00111")) {
                output.append("2");
            } else if (sign.toString().equals("00011")) {
                output.append("3");
            } else if (sign.toString().equals("00001")) {
                output.append("4");
            } else if (sign.toString().equals("00000")) {
                output.append("5");
            } else if (sign.toString().equals("10000")) {
                output.append("6");
            } else if (sign.toString().equals("11000")) {
                output.append("7");
            } else if (sign.toString().equals("11100")) {
                output.append("8");
            } else if (sign.toString().equals("11110")) {
                output.append("9");
            } else if (sign.toString().equals("0101")) {
                output.append("Ä");
            } else if (sign.toString().equals("1110")) {
                output.append("Ö");
            } else if (sign.toString().equals("0011")) {
                output.append("Ü");
            } else if (sign.toString().equals("00011000")) {
                output.append("ß");
            } else if (sign.toString().equals("1111")) {
                output.append("CH");
            } else if (sign.toString().equals("010101")) {
                output.append(".");
            } else if (sign.toString().equals("110011")) {
                output.append(",");
            } else if (sign.toString().equals("111000")) {
                output.append(":");
            } else if (sign.toString().equals("101010")) {
                output.append(";");
            } else if (sign.toString().equals("001100")) {
                output.append("?");
            } else if (sign.toString().equals("101011")) {
                output.append("!");
            } else if (sign.toString().equals("100001")) {
                output.append("-");
            } else if (sign.toString().equals("001101")) {
                output.append("_");
            } else if (sign.toString().equals("10110")) {
                output.append("(");
            } else if (sign.toString().equals("101101")) {
                output.append(")");
            } else if (sign.toString().equals("011110")) {
                output.append("'");
            } else if (sign.toString().equals("10001")) {
                output.append("=");
            } else if (sign.toString().equals("01010")) {
                output.append("+ or End of the signal");
            } else if (sign.toString().equals("10010")) {
                output.append("/");
            } else if (sign.toString().equals("011010")) {
                output.append("@");
            } else if (sign.toString().equals("10101")) {
                output.append("Begin of the signal");
            } else if (sign.toString().equals("10001")) {
                output.append("Wait");
            } else if (sign.toString().equals("00010")) {
                output.append("Understood");
            } else if (sign.toString().equals("000101")) {
                output.append("End of work");
            } else if (sign.toString().equals("000111000")) {
                output.append("SOS");
            } else if (sign.toString().equals("00000000")) {
                output.append("Error");
            } else {
                output.replace(0, output.length(), "Code not listed or wrong.");
            }
        }
        return output.toString();
    }

}

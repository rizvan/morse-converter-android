package de.marcelkapfer.morseconverter;

public class DecodeWrittenMorseManager {

    private String inputMessage;

    DecodeWrittenMorseManager(String string) {
        inputMessage = string;
    }

    public void setString(String string) {
        inputMessage = string;
    }

    public String getString() {
        return inputMessage;
    }

    /*
     * Code for converting latin letters into written morse
	 */
    public String getDecodedString() {
        StringBuffer message = new StringBuffer(inputMessage);
        if (message.toString().endsWith(" ")) {
            message = message.deleteCharAt(message.length() - 1);
        }
        // Variables
        StringBuffer input = new StringBuffer();
        input = input.replace(0, input.length(), message.toString().toUpperCase());
        StringBuffer output = new StringBuffer();
        if (input.toString().equals("")) {
            output.replace(0, output.length(), "Please enter at least one character");
        } else if (input.toString().equals("LETTERSPACE")) {
            output.replace(0, output.length(), "#");
        } else if (input.toString().equals("END OF WORK")) {
            output.replace(0, output.length(), "000101");
        } else if (input.toString().equals("ERROR")) {
            output.replace(0, output.length(), "00000000");
        } else if (input.toString().equals("STARTING SIGNAL")) {
            output.replace(0, output.length(), "10101");
        } else if (input.toString().equals("ENDING SIGNAL")) {
            output.replace(0, output.length(), "01010");
        } else if (input.toString().equals("UNDERSTOOD")) {
            output.replace(0, output.length(), "00010");
        } else if (input.toString().equals("WAIT")) {
            output.replace(0, output.length(), "01000");
        } else if (input.toString().equals("SOS")) {
            output.replace(0, output.length(), "000111000");
        } else if (input.toString().equals("LETTER SPACE")) {
            output.replace(0, output.length(), "##");
        } else if (input.toString().equals("WORD SPACE")) {
            output.replace(0, output.length(), "+");
        } else {
            for (int c = input.length(); c > 0; c--) {

                if (input.toString().startsWith(" ")) {
                    if (output.toString().endsWith("#")) {
                        output.delete(output.length() - 1, output.length());
                    }
                    output.append("+");
                    input.delete(0, 1);
                } else if (input.toString().startsWith("A")) {
                    output.append("01#");
                    input.delete(0, 1);
                } else if (input.toString().startsWith("B")) {
                    output.append("1000#");
                    input.delete(0, 1);
                } else if (input.toString().startsWith("C")) {
                    output.append("1010#");
                    input.delete(0, 1);
                } else if (input.toString().startsWith("D")) {
                    output.append("100#");
                    input.delete(0, 1);
                } else if (input.toString().startsWith("E")) {
                    output.append("0#");
                    input.delete(0, 1);
                } else if (input.toString().startsWith("F")) {
                    output.append("0010#");
                    input.delete(0, 1);
                } else if (input.toString().startsWith("G")) {
                    output.append("110#");
                    input.delete(0, 1);
                } else if (input.toString().startsWith("H")) {
                    output.append("0000#");
                    input.delete(0, 1);
                } else if (input.toString().startsWith("I")) {
                    output.append("00#");
                    input.delete(0, 1);
                } else if (input.toString().startsWith("J")) {
                    output.append("0111#");
                    input.delete(0, 1);
                } else if (input.toString().startsWith("K")) {
                    output.append("101#");
                    input.delete(0, 1);
                } else if (input.toString().startsWith("L")) {
                    output.append("0100#");
                    input.delete(0, 1);
                } else if (input.toString().startsWith("M")) {
                    output.append("11#");
                    input.delete(0, 1);
                } else if (input.toString().startsWith("N")) {
                    output.append("10#");
                    input.delete(0, 1);
                } else if (input.toString().startsWith("O")) {
                    output.append("111#");
                    input.delete(0, 1);
                } else if (input.toString().startsWith("P")) {
                    output.append("0110#");
                    input.delete(0, 1);
                } else if (input.toString().startsWith("Q")) {
                    output.append("1101#");
                    input.delete(0, 1);
                } else if (input.toString().startsWith("R")) {
                    output.append("010#");
                    input.delete(0, 1);
                } else if (input.toString().startsWith("S")) {
                    output.append("000#");
                    input.delete(0, 1);
                } else if (input.toString().startsWith("T")) {
                    output.append("1#");
                    input.delete(0, 1);
                } else if (input.toString().startsWith("U")) {
                    output.append("001#");
                    input.delete(0, 1);
                } else if (input.toString().startsWith("V")) {
                    output.append("0001#");
                    input.delete(0, 1);
                } else if (input.toString().startsWith("W")) {
                    output.append("011#");
                    input.delete(0, 1);
                } else if (input.toString().startsWith("X")) {
                    output.append("1001#");
                    input.delete(0, 1);
                } else if (input.toString().startsWith("Y")) {
                    output.append("1011#");
                    input.delete(0, 1);
                } else if (input.toString().startsWith("Z")) {
                    output.append("1100#");
                    input.delete(0, 1);
                } else if (input.toString().startsWith("0")) {
                    output.append("11111#");
                    input.delete(0, 1);
                } else if (input.toString().startsWith("1")) {
                    output.append("01111#");
                    input.delete(0, 1);
                } else if (input.toString().startsWith("2")) {
                    output.append("00111#");
                    input.delete(0, 1);
                } else if (input.toString().startsWith("3")) {
                    output.append("00011#");
                    input.delete(0, 1);
                } else if (input.toString().startsWith("4")) {
                    output.append("00001#");
                    input.delete(0, 1);
                } else if (input.toString().startsWith("5")) {
                    output.append("00000#");
                    input.delete(0, 1);
                } else if (input.toString().startsWith("6")) {
                    output.append("10000#");
                    input.delete(0, 1);
                } else if (input.toString().startsWith("7")) {
                    output.append("11000#");
                    input.delete(0, 1);
                } else if (input.toString().startsWith("8")) {
                    output.append("11100#");
                    input.delete(0, 1);
                } else if (input.toString().startsWith("9")) {
                    output.append("11110#");
                    input.delete(0, 1);
                } else if (input.toString().startsWith("Ä")) {
                    output.append("0101#");
                    input.delete(0, 1);
                } else if (input.toString().startsWith("Ö")) {
                    output.append("1110#");
                    input.delete(0, 1);
                } else if (input.toString().startsWith("Ü")) {
                    output.append("0011#");
                    input.delete(0, 1);
                } else if (input.toString().startsWith("ß")) {
                    output.append("00011000#");
                    input.delete(0, 1);
                } else if (input.toString().startsWith(".")) {
                    output.append("010101#");
                    input.delete(0, 1);
                } else if (input.toString().startsWith(",")) {
                    output.append("110011#");
                    input.delete(0, 1);
                } else if (input.toString().startsWith(":")) {
                    output.append("111000#");
                    input.delete(0, 1);
                } else if (input.toString().startsWith(";")) {
                    output.append("101010#");
                    input.delete(0, 1);
                } else if (input.toString().startsWith("?")) {
                    output.append("001100#");
                    input.delete(0, 1);
                } else if (input.toString().startsWith("!")) {
                    output.append("101011#");
                    input.delete(0, 1);
                } else if (input.toString().startsWith("-")) {
                    output.append("100001#");
                    input.delete(0, 1);
                } else if (input.toString().startsWith("_")) {
                    output.append("001101#");
                    input.delete(0, 1);
                } else if (input.toString().startsWith("(")) {
                    output.append("10110#");
                    input.delete(0, 1);
                } else if (input.toString().startsWith(")")) {
                    output.append("101101#");
                    input.delete(0, 1);
                } else if (input.toString().startsWith("=")) {
                    output.append("10001#");
                    input.delete(0, 1);
                } else if (input.toString().startsWith("+")) {
                    output.append("01010#");
                    input.delete(0, 1);
                } else if (input.toString().startsWith("/")) {
                    output.append("10010#");
                    input.delete(0, 1);
                } else if (input.toString().startsWith("@")) {
                    output.append("011010#");
                    input.delete(0, 1);
                } else if (input.toString().startsWith("'")) {
                    output.append("011110#");
                    input.delete(0, 1);
                } else if (input.toString().startsWith("$")) {
                    output.append("0001001#");
                    input.delete(0, 1);
                } else {
                    output.replace(0, output.length(), "Code not listed or wrong.");
                }
            }
            if (output.toString().endsWith("#")) {
                output.delete(output.length() - 1, output.length());
            }
        }
        return output.toString();
    }
}
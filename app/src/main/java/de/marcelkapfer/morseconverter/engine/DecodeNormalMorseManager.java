package de.marcelkapfer.morseconverter.engine;

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

public class DecodeNormalMorseManager {

    public static String getDecodedString(String inputMessage) {
        if(inputMessage.equals("")){
            return "Please enter at least one character";
        } else {
            StringBuffer message = new StringBuffer(inputMessage);
            if (message.toString().endsWith(" ")) {
                message = message.deleteCharAt(message.length() - 1);
            }
            // Variables
            StringBuffer input = new StringBuffer();
            input = input.replace(0, input.length(), message.toString().toUpperCase());
            StringBuffer output = new StringBuffer();
            if (input.toString().equals("LETTERSPACE")) {
                output.replace(0, output.length(), "   ");
            } else if (input.toString().equals("END OF WORK")) {
                output.replace(0, output.length(), "...-.-");
            } else if (input.toString().equals("ERROR")) {
                output.replace(0, output.length(), "........");
            } else if (input.toString().equals("STARTING SIGNAL")) {
                output.replace(0, output.length(), "-.-.-");
            } else if (input.toString().equals("ENDING SIGNAL")) {
                output.replace(0, output.length(), ".-.-.");
            } else if (input.toString().equals("UNDERSTOOD")) {
                output.replace(0, output.length(), "...-.");
            } else if (input.toString().equals("WAIT")) {
                output.replace(0, output.length(), ".-...");
            } else if (input.toString().equals("SOS")) {
                output.replace(0, output.length(), "...---...");
            } else if (input.toString().equals("LETTER SPACE")) {
                output.replace(0, output.length(), "   ");
            } else if (input.toString().equals("WORD SPACE")) {
                output.replace(0, output.length(), "       ");
            } else {
                for (int c = input.length(); c > 0; c--) {
                    if (input.toString().startsWith(" ")) {
                        if (output.toString().endsWith("   ")) {
                            output.delete(output.length() - 3, output.length());
                        }
                        output.append("       ");
                        input.delete(0, 1);
                    } else if (input.toString().startsWith("A")) {
                        output.append(".-   ");
                        input.delete(0, 1);
                    } else if (input.toString().startsWith("B")) {
                        output.append("-...   ");
                        input.delete(0, 1);
                    } else if (input.toString().startsWith("C")) {
                        output.append("-.-.   ");
                        input.delete(0, 1);
                    } else if (input.toString().startsWith("D")) {
                        output.append("-..   ");
                        input.delete(0, 1);
                    } else if (input.toString().startsWith("E")) {
                        output.append(".   ");
                        input.delete(0, 1);
                    } else if (input.toString().startsWith("F")) {
                        output.append("..-.   ");
                        input.delete(0, 1);
                    } else if (input.toString().startsWith("G")) {
                        output.append("--.   ");
                        input.delete(0, 1);
                    } else if (input.toString().startsWith("H")) {
                        output.append("....   ");
                        input.delete(0, 1);
                    } else if (input.toString().startsWith("I")) {
                        output.append("..   ");
                        input.delete(0, 1);
                    } else if (input.toString().startsWith("J")) {
                        output.append(".---   ");
                        input.delete(0, 1);
                    } else if (input.toString().startsWith("K")) {
                        output.append("-.-   ");
                        input.delete(0, 1);
                    } else if (input.toString().startsWith("L")) {
                        output.append(".-..   ");
                        input.delete(0, 1);
                    } else if (input.toString().startsWith("M")) {
                        output.append("--   ");
                        input.delete(0, 1);
                    } else if (input.toString().startsWith("N")) {
                        output.append("-.   ");
                        input.delete(0, 1);
                    } else if (input.toString().startsWith("O")) {
                        output.append("---   ");
                        input.delete(0, 1);
                    } else if (input.toString().startsWith("P")) {
                        output.append(".--.   ");
                        input.delete(0, 1);
                    } else if (input.toString().startsWith("Q")) {
                        output.append("--.-   ");
                        input.delete(0, 1);
                    } else if (input.toString().startsWith("R")) {
                        output.append(".-.   ");
                        input.delete(0, 1);
                    } else if (input.toString().startsWith("S")) {
                        output.append("...   ");
                        input.delete(0, 1);
                    } else if (input.toString().startsWith("T")) {
                        output.append("-   ");
                        input.delete(0, 1);
                    } else if (input.toString().startsWith("U")) {
                        output.append("..-   ");
                        input.delete(0, 1);
                    } else if (input.toString().startsWith("V")) {
                        output.append("...-   ");
                        input.delete(0, 1);
                    } else if (input.toString().startsWith("W")) {
                        output.append(".--   ");
                        input.delete(0, 1);
                    } else if (input.toString().startsWith("X")) {
                        output.append("-..-   ");
                        input.delete(0, 1);
                    } else if (input.toString().startsWith("Y")) {
                        output.append("-.--   ");
                        input.delete(0, 1);
                    } else if (input.toString().startsWith("Z")) {
                        output.append("--..   ");
                        input.delete(0, 1);
                    } else if (input.toString().startsWith("0")) {
                        output.append("-----   ");
                        input.delete(0, 1);
                    } else if (input.toString().startsWith("1")) {
                        output.append(".----   ");
                        input.delete(0, 1);
                    } else if (input.toString().startsWith("2")) {
                        output.append("..---   ");
                        input.delete(0, 1);
                    } else if (input.toString().startsWith("3")) {
                        output.append("...--   ");
                        input.delete(0, 1);
                    } else if (input.toString().startsWith("4")) {
                        output.append("....-   ");
                        input.delete(0, 1);
                    } else if (input.toString().startsWith("5")) {
                        output.append(".....   ");
                        input.delete(0, 1);
                    } else if (input.toString().startsWith("6")) {
                        output.append("-....   ");
                        input.delete(0, 1);
                    } else if (input.toString().startsWith("7")) {
                        output.append("--...   ");
                        input.delete(0, 1);
                    } else if (input.toString().startsWith("8")) {
                        output.append("---..   ");
                        input.delete(0, 1);
                    } else if (input.toString().startsWith("9")) {
                        output.append("----.   ");
                        input.delete(0, 1);
                    } else if (input.toString().startsWith("Ä")) {
                        output.append(".-.-   ");
                        input.delete(0, 1);
                    } else if (input.toString().startsWith("Ö")) {
                        output.append("---.   ");
                        input.delete(0, 1);
                    } else if (input.toString().startsWith("Ü")) {
                        output.append("..--   ");
                        input.delete(0, 1);
                    } else if (input.toString().startsWith("ß")) {
                        output.append("...--...   ");
                        input.delete(0, 1);
                    } else if (input.toString().startsWith(".")) {
                        output.append(".-.-.-   ");
                        input.delete(0, 1);
                    } else if (input.toString().startsWith(",")) {
                        output.append("--..--   ");
                        input.delete(0, 1);
                    } else if (input.toString().startsWith(":")) {
                        output.append("---...   ");
                        input.delete(0, 1);
                    } else if (input.toString().startsWith(";")) {
                        output.append("-.-.-.   ");
                        input.delete(0, 1);
                    } else if (input.toString().startsWith("?")) {
                        output.append("..--..   ");
                        input.delete(0, 1);
                    } else if (input.toString().startsWith("!")) {
                        output.append("-.-.--   ");
                        input.delete(0, 1);
                    } else if (input.toString().startsWith("-")) {
                        output.append("-....-   ");
                        input.delete(0, 1);
                    } else if (input.toString().startsWith("_")) {
                        output.append("..--.-   ");
                        input.delete(0, 1);
                    } else if (input.toString().startsWith("(")) {
                        output.append("-.--.   ");
                        input.delete(0, 1);
                    } else if (input.toString().startsWith(")")) {
                        output.append("-.--.-   ");
                        input.delete(0, 1);
                    } else if (input.toString().startsWith("=")) {
                        output.append("-...-   ");
                        input.delete(0, 1);
                    } else if (input.toString().startsWith("+")) {
                        output.append(".-.-.   ");
                        input.delete(0, 1);
                    } else if (input.toString().startsWith("/")) {
                        output.append("-..-.   ");
                        input.delete(0, 1);
                    } else if (input.toString().startsWith("@")) {
                        output.append(".--.-.   ");
                        input.delete(0, 1);
                    } else if (input.toString().startsWith("'")) {
                        output.append(".----.   ");
                        input.delete(0, 1);
                    } else if (input.toString().startsWith("$")) {
                        output.append("...-..-   ");
                        input.delete(0, 1);
                    } else {
                        output.replace(0, output.length(), "Code not listed or wrong.");
                    }
                }
                if (output.toString().endsWith("   ")) {
                    output.delete(output.length() - 3, output.length());
                }
            }
            return output.toString();
        }
    }
}
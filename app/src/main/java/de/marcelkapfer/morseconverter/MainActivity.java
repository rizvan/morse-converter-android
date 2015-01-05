/*
 *   This is a Android application for converting writtenMorse and normal morse code.
 *   Copyright (C) 2014-2015  Marcel Michael Kapfer
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 *   Marcel Michael Kapfer
 *   marcelmichaelkapfer@yahoo.co.nz
 *
 *   Version 1.0.1
 */

package de.marcelkapfer.morseconverter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends FragmentActivity {
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mMenuTitles;
    public String[] fragments =  new String[] {"R.id.main", "R.id.morse", "R.id.about"};
    private static int[] fragment = new int[] {R.layout.fragment_main, R.layout.fragment_morse, R.layout.fragment_about };
    public static int mainPosition;

	public final static String EXTRA_MESSAGE = "de.marcelkapfer.morseconverter.MESSAGE";
	public String tfOutput = "";
	public static int lastFragment = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTitle = mDrawerTitle = getTitle();
        mMenuTitles = getResources().getStringArray(R.array.menu_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        
        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mMenuTitles));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        
        // enable ActionBar app icon to behave as action to toggle nav drawer
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        
        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
                ) {
            
            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getActionBar().setTitle(mTitle);
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActionBar().setTitle(mDrawerTitle);
            }

        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        

        if(savedInstanceState == null) {
            changeFragment(lastFragment);
        }
    }
    

    /* The click listner for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        	changeFragment(position);
        	mainPosition = position;
        }

    }


    public void changeFragment(int position){


    		fragment_main newFragment = new fragment_main();
			Bundle args = new Bundle();
			args.putInt(fragments[position], position);
			newFragment.setArguments(args);

			// Insert the fragment by replacing any existing fragment
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.container, newFragment).commit();

        mDrawerList.setItemChecked(position, true);
        setTitle(mMenuTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);

    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
          return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }
    
    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }




    public static class fragment_main extends Fragment {
        public static final String ARG_POSITION = "menu_number";


        public fragment_main() {
        	// Empty subclass required for fragment subclass
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(fragment[MainActivity.mainPosition], container, false);
            //int i = getArguments().getInt(ARG_POSITION);
            //String menu = getResources().getStringArray(R.array.menu_array)[i];
            //getActivity().setTitle(menu);
            return rootView;
        }
    }


    /*
	 * Code for converting latin letters into written morse
	 */
	public void decode(View view) {
    	EditText editText = (EditText) findViewById(R.id.edit_message);
    	StringBuffer message = new StringBuffer(editText.getText());
    	if (message.toString().endsWith(" ")){
    		message = message.deleteCharAt(message.length() - 1);
    	}
			// Variables
			StringBuffer input = new StringBuffer();
			input = input.replace(0, input.length(), message.toString().toUpperCase());
			StringBuffer output = new StringBuffer();
			if (input.toString().equals("")) {
				tfOutput = "Please enter at least one character";
				After(view);
			} else if (input.toString().equals("LETTERSPACE")) {
				tfOutput = "#";
				After(view);
			} else if (input.toString().equals("END OF WORK")) {
				tfOutput = "000101";
				After(view);
			} else if (input.toString().equals("ERROR")) {
				tfOutput = "00000000";
				After(view);
			} else if (input.toString().equals("STARTING SIGNAL")) {
				tfOutput = "10101";
				After(view);
			} else if (input.toString().equals("ENDING SIGNAL")) {
				tfOutput = "01010";
				After(view);
			} else if (input.toString().equals("UNDERSTOOD")) {
				tfOutput = "00010";
				After(view);
			} else if (input.toString().equals("WAIT")) {
				tfOutput = "01000";
				After(view);
			} else if (input.toString().equals("SOS")) {
				tfOutput = "000111000";
				After(view);
			} else if (input.toString().equals("LETTER SPACE")) {
				tfOutput = "#";
				After(view);
			} else if (input.toString().equals("WORD SPACE")) {
				tfOutput = "+";
				After(view);
			} else {
				for (int c = input.length(); c > 0; c--) {

					if (input.toString().startsWith(" ")) {
                        if(output.toString().endsWith("#")){
                            output.delete(output.length() -1 , output.length());
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
						tfOutput = "Code not listed or wrong.";
					}
				}
                if(output.toString().endsWith("#")){
                    output.delete(output.length() -1 , output.length());
                }
				tfOutput = output.toString();
				lastFragment = 0;
				After(view);
			}
	}
	
	/*
	 * Code for converting writtenMorse to latin letters
	 */
	public void encode (View view) {
    	EditText editText = (EditText) findViewById(R.id.edit_message);
    	StringBuffer message = new StringBuffer(editText.getText());
    	if (message.toString().endsWith(" ")){
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
				if (input.equals("°")) {
					break;
				} else if (sign.toString().equals("01")) {
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
					tfOutput = "Code not listed or wrong.";
				}
			}
			tfOutput = output.toString();
			lastFragment = 0;
			After(view);
		/*} catch (Exception e) {
			tfOutput.setText("Some error occured. I'm sorry!");
		}*/
	}
	
	/*
	 * Code for converting normal letters into morse code
	 */

	public void llm(View view) {
    	EditText editText = (EditText) findViewById(R.id.edit_message);
    	StringBuffer message = new StringBuffer(editText.getText());
    	if (message.toString().endsWith(" ")){
    		message = message.deleteCharAt(message.length() - 1);
    	}
			// Variables
		// Variables
		StringBuffer input = new StringBuffer();
		input = input.replace(0, input.length(), message.toString().toUpperCase());
		StringBuffer output = new StringBuffer();
			if (input.toString().equals("")) {
				tfOutput = "Please enter at least one character";
				After(view);
			} else if (input.toString().equals("LETTERSPACE")) {
				tfOutput = "   ";
				After(view);
			} else if (input.toString().equals("END OF WORK")) {
				tfOutput = "...-.-";
				After(view);
			} else if (input.toString().equals("ERROR")) {
				tfOutput = "........";
				After(view);
			} else if (input.toString().equals("STARTING SIGNAL")) {
				tfOutput = "-.-.-";
				After(view);
			} else if (input.toString().equals("ENDING SIGNAL")) {
				tfOutput = ".-.-.";
				After(view);
			} else if (input.toString().equals("UNDERSTOOD")) {
				tfOutput = "...-.";
				After(view);
			} else if (input.toString().equals("WAIT")) {
				tfOutput = ".-...";
				After(view);
			} else if (input.toString().equals("SOS")) {
				tfOutput = "...---...";
				After(view);
			} else if (input.toString().equals("LETTER SPACE")) {
				tfOutput = "   ";
				After(view);
			} else if (input.toString().equals("WORD SPACE")) {
				tfOutput = "       ";
				After(view);
			} else {
				for (int c = input.length(); c > 0; c--) {
					if (input.toString().startsWith(" ")) {
                        if(output.toString().endsWith("   ")){
                            output.delete(output.length() - 3 , output.length());
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
						tfOutput = "Code not listed or wrong.";
					}
				}
                if(output.toString().endsWith("   ")){
                    output.delete(output.length() - 3 , output.length());
                }
				tfOutput = output.toString();
				lastFragment = 1;
				After(view);
			}
	}
	
	/*
	 * Code for converting morse code into latin letters
	 */
	public void mll(View view) {
    	EditText editText = (EditText) findViewById(R.id.edit_message);
    	StringBuffer message = new StringBuffer(editText.getText());
    	if (message.toString().endsWith(" ")){
    		message = message.deleteCharAt(message.length() - 1);
    	}
        String input;
        StringBuffer output = new StringBuffer();
        input = message.toString().toUpperCase() + "#";
        StringBuffer inputToSign = new StringBuffer(input);
        while (!message.toString().equals("   ")) {
            int d = 0;
            boolean signFull = true;
            StringBuffer sign = new StringBuffer();
            while (signFull) {
                if (inputToSign.toString().startsWith("       ")) {
                    output.append(" ");
                    inputToSign.delete(d, d + 9);
                } else if (inputToSign.toString().substring(d, d + 3)
                        .equals("   ")) {
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
					tfOutput = "Code not listed or wrong.";
				}
			}
			tfOutput = output.toString();
			lastFragment = 1;
			After(view);
	}
	

	public void After(View view){
    	Intent intent = new Intent(this, DisplayMessageActivity.class);
		intent.putExtra(EXTRA_MESSAGE, tfOutput );
		startActivity(intent);
		tfOutput = "";
	}
}

package de.marcelkapfer.morseconverter.fragments;

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

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.marcelkapfer.morseconverter.R;

/**
 * Created by mmk on 2/14/15.
 */
public class MorseFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_morse, container, false);
    }
}
package com.lutka.chefcalculator;

import java.util.ArrayList;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.lutka.chefcalculator.Unit.UnitType;

public class ConvertionPage extends Fragment implements OnItemSelectedListener, TextWatcher
{
	ArrayList<Unit> listOfUnits = new ArrayList<Unit>();
	
	Spinner spinnerIn, spinnerOut;
	EditText valueIn;
	TextView valueOut;
	int page;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		
		View layout = inflater.inflate(R.layout.convertion_fragment, null);
		valueIn = (EditText) layout.findViewById(R.id.editAmount);
		valueOut = (TextView) layout.findViewById(R.id.tvResult);
		
		page = getArguments().getInt("page");
		
		listOfUnits = volumeItems(page);
		
		spinnerIn = (Spinner) layout.findViewById(R.id.inMeasurement);
		
		
		//removed ListOfUnits as suggested by eclipse
		ArrayAdapter<Unit> arrayAdapter = new ArrayAdapter<Unit>(getActivity(), android.R.layout.simple_spinner_dropdown_item, listOfUnits);
		spinnerIn.setAdapter(arrayAdapter);
		
		spinnerOut = (Spinner) layout.findViewById(R.id.outMeasurement);
		spinnerOut.setAdapter(arrayAdapter);
		
		valueIn.addTextChangedListener(this);	
		
		spinnerIn.setOnItemSelectedListener(this);
		spinnerOut.setOnItemSelectedListener(this);	
		
		SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
		String stringValueIn;
		int intSpinnerInt, intSpinnerOut;
		
		if(page == 1)
		{			
			stringValueIn = sharedPreferences.getString("valueInVolume", "0");
			intSpinnerInt = sharedPreferences.getInt("spinnerInVolume", 0);
			intSpinnerOut = sharedPreferences.getInt("spinnerOutVolume", 0);
			
			valueIn.setText(stringValueIn);
			spinnerIn.setSelection(intSpinnerInt);
			spinnerOut.setSelection(intSpinnerOut);			
		}
		else
		{			
			stringValueIn = sharedPreferences.getString("valueInWeight", "0");
			intSpinnerInt = sharedPreferences.getInt("spinnerInWeight", 0);
			intSpinnerOut = sharedPreferences.getInt("spinnerOutWeight", 0);
			
			valueIn.setText(stringValueIn);
			spinnerIn.setSelection(intSpinnerInt);
			spinnerOut.setSelection(intSpinnerOut);
		}
		//to keep caret - ta kreske co sie pojawia jak sie pisze, at the end of inputed value when re-entering the app
		valueIn.setSelection(valueIn.getText().length());
		
		return layout;
	}
	
	@Override
	public void onDestroy()
	{
		if(valueIn == null)	return;
			
		Editor preferencesEditor = getActivity().getPreferences(Context.MODE_PRIVATE).edit();
		
		if(page ==1)
		{		
			preferencesEditor.putString("valueInVolume", valueIn.getText().toString());
			preferencesEditor.putInt("spinnerInVolume", spinnerIn.getSelectedItemPosition());
			preferencesEditor.putInt("spinnerOutVolume", spinnerOut.getSelectedItemPosition());
		}		
		else
		{
			preferencesEditor.putString("valueInWeight", valueIn.getText().toString());
			preferencesEditor.putInt("spinnerInWeight", spinnerIn.getSelectedItemPosition());
			preferencesEditor.putInt("spinnerOutWeight", spinnerOut.getSelectedItemPosition());
		}
		
		preferencesEditor.commit();
		
		super.onDestroy();
	}
	
	public ArrayList<Unit> volumeItems(int pageNumber)
	{
		UnitsList unitList = new UnitsList(getActivity());	
			
		if(pageNumber == 1)
		{
			return unitList.getUnits(UnitType.Volume);			
		}
		else
		{
			return unitList.getUnits(UnitType.Weight);
		}
				
	}
	
	public void calculateResult()
	{	
		String stringIn = valueIn.getText().toString();
		float valueInputted;
		
		if(stringIn.length() == 0)
		{
			valueInputted = 0;
		}		
		else
		{				
			valueInputted = Float.parseFloat(stringIn);
		}
		
		Unit unitIn = (Unit) spinnerIn.getSelectedItem();			
		Unit unitOut = (Unit) spinnerOut.getSelectedItem();
		
		float valueResult = unitIn.convertTo(valueInputted, unitOut);
		valueOut.setText(String.valueOf(valueResult));		
	}	

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3)
	{
		calculateResult();
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterTextChanged(Editable s)
	{
		calculateResult();		
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count)
	{
		// TODO Auto-generated method stub
		
	}
	
	
}

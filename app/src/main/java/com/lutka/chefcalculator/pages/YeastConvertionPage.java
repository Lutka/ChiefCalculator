package com.lutka.chefcalculator.pages;

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

import com.lutka.chefcalculator.R;
import com.lutka.chefcalculator.models.Unit;
import com.lutka.chefcalculator.models.Unit.UnitType;
import com.lutka.chefcalculator.models.Yeast;

public class YeastConvertionPage extends Fragment implements OnItemSelectedListener, TextWatcher
{
	private TextView result;
	private Spinner measurementIn, measurementOut, typeIn, typeOut;
	EditText amount;
	
	ArrayList <Unit> listOfYeastUnits;
	ArrayList<Yeast> listOfYeastTypes;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) 
	{
		View layout = inflater.inflate(R.layout.yeast_fragment, container, false);
		
		result = (TextView) layout.findViewById(R.id.tvYeastResult);
		amount = (EditText) layout.findViewById(R.id.etYeastAmount);
		measurementIn = (Spinner) layout.findViewById(R.id.spYeastMeasurementIn);
		measurementOut = (Spinner) layout.findViewById(R.id.spYeastMeasurementOut);
		typeIn = (Spinner) layout.findViewById(R.id.spYeastTypeIn);
		typeOut = (Spinner) layout.findViewById(R.id.spYeastTypeOut);
		
		listOfYeastUnits = listOfUnits();
		
		ArrayAdapter<Unit> arrayAdapter = new ArrayAdapter<Unit>(getActivity(), 
				android.R.layout.simple_list_item_1, listOfYeastUnits);
		
		measurementIn.setAdapter(arrayAdapter);
		measurementOut.setAdapter(arrayAdapter);
		
		listOfYeastTypes = listOfTypes();
		ArrayAdapter<Yeast> adapter = new ArrayAdapter<Yeast>(getActivity(), 
				android.R.layout.simple_list_item_1, listOfYeastTypes);
		
		typeIn.setAdapter(adapter);
		typeOut.setAdapter(adapter);
		
		amount.addTextChangedListener(this);
		measurementIn.setOnItemSelectedListener(this);
		measurementOut.setOnItemSelectedListener(this);
		typeIn.setOnItemSelectedListener(this);
		typeOut.setOnItemSelectedListener(this);
		
		SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
		
		String stringAmount = sharedPreferences.getString("amount", "0");
		int intMeasurementIn = sharedPreferences.getInt("measurementIn", 0);
		int intMeasurementOut = sharedPreferences.getInt("measurementOut", 0);
		int intTypeIn = sharedPreferences.getInt("typeIn", 0);
		int intTypeOut = sharedPreferences.getInt("typeOut", 0);
		
		amount.setText(stringAmount);
		measurementIn.setSelection(intMeasurementIn);
		measurementOut.setSelection(intMeasurementOut);
		typeIn.setSelection(intTypeIn);
		typeOut.setSelection(intTypeOut);
		result.setText("0");
		
		amount.setSelection(stringAmount.length());
		
		return layout;
	}
	
	public ArrayList<Unit>listOfUnits()
	{
		ArrayList<Unit> listOfUnits = new ArrayList<Unit>();
		listOfUnits.add(new Unit(getActivity().getString(R.string.oz_uk), 28.41f, UnitType.Volume));
		listOfUnits.add(new Unit(getActivity().getString(R.string.oz_us), 29.57f, UnitType.Volume));
		listOfUnits.add(new Unit(getActivity().getString(R.string.gram), 1, UnitType.Weight));
		listOfUnits.add(new Unit(getActivity().getString(R.string.tea_spoon), 4, UnitType.Volume));
		
		return listOfUnits;
	}
	
	public ArrayList<Yeast> listOfTypes()
	{
		ArrayList<Yeast> listOfYTypes = new ArrayList<Yeast>();
		listOfYTypes.add(new Yeast(getActivity().getString(R.string.yeast_fresh), 0.96f, 3));		
		listOfYTypes.add(new Yeast(getActivity().getString(R.string.yeast_dry), 0.79f, 1.5f));
		listOfYTypes.add(new Yeast(getActivity().getString(R.string.yeast_instant), 0.85f, 1)); 
		
		return listOfYTypes;
	}
	
	
	@Override
	public void onDestroy() 
	{
			if (amount != null) 
			{			
				Editor preferencesEditor = getActivity().getPreferences(Context.MODE_PRIVATE).edit();
				
				preferencesEditor.putString("amount", amount.getText().toString());
				preferencesEditor.putInt("measurementIn", measurementIn.getSelectedItemPosition());
				preferencesEditor.putInt("measurementOut", measurementOut.getSelectedItemPosition());
				preferencesEditor.putInt("typeIn", typeIn.getSelectedItemPosition());
				preferencesEditor.putInt("typeOut", typeOut.getSelectedItemPosition());
				
				preferencesEditor.commit();
			}
			
			super.onDestroy();
	}
	
	
	
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,long arg3) 
	{
		calculateResult();		
	}
	
	public void calculateResult()
	{
		if(amount == null) return;
		
		String stringIn = amount.getText().toString();
		
		float amount;
		try
		{
			if(stringIn.length() == 0) amount = 0;
			else amount = Float.valueOf(stringIn);
		}
		catch(Exception e)
		{
			amount = 0;
		}
		
		Unit unitIn = (Unit) measurementIn.getSelectedItem();
		Unit unitOut = (Unit) measurementOut.getSelectedItem();
		Yeast yeastIn = (Yeast) typeIn.getSelectedItem();
		Yeast yeastOut = (Yeast) typeOut.getSelectedItem();
		
		float yeastConvertion = yeastIn.convertToYeastTypes(yeastOut, amount);
		
		float valueResult = unitIn.convertTo(yeastConvertion, unitOut, yeastOut);
		
		//float valueResult = unitIn.convertTo(amount, unitOut, productIn, productOut);
		
		result.setText(String.valueOf(valueResult));
	}
	
	
	@Override
	public void onNothingSelected(AdapterView<?> arg0) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterTextChanged(Editable arg0) 
	{
		calculateResult();		
	}

	@Override
	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
			int arg3) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) 
	{
		// TODO Auto-generated method stub
		
	}
}

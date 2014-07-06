package com.lutka.chefcalculator.pages;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

import com.lutka.chefcalculator.DatabaseManager;
import com.lutka.chefcalculator.R;
import com.lutka.chefcalculator.UnitsList;
import com.lutka.chefcalculator.models.Product;
import com.lutka.chefcalculator.models.Unit;


public class AdvancedConvertionPage extends Fragment implements OnItemSelectedListener, TextWatcher
{	
	//public static final String  sharedPreferencesTag = "chefcalculator";
	
	List<Product> listOfProduct;
	ArrayList<Unit> listOfUnits;
	
	Spinner spinnerIn, spinnerOut, spinnerProduct;
	EditText valueIn;
	TextView valueOut;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View layout = inflater.inflate(R.layout.adv_convertion_fragment, container, false);
		
		valueIn = (EditText) layout.findViewById(R.id.editAmount);
		valueOut = (TextView) layout.findViewById(R.id.tvResult);
		
		spinnerIn = (Spinner) layout.findViewById(R.id.inMeasurement);
		spinnerOut = (Spinner) layout.findViewById(R.id.outMeasurement);
		spinnerProduct = (Spinner) layout.findViewById(R.id.productType);

        DatabaseManager dbManager = new DatabaseManager(getActivity());

		listOfProduct = dbManager.selectAll(Product.class);
		
		//adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		ArrayAdapter<Product> adapter = new ArrayAdapter<Product>(getActivity(), android.R.layout.simple_list_item_1, listOfProduct); 
		spinnerProduct.setAdapter(adapter);
		
		listOfUnits = new UnitsList(getActivity()).getUnits();
		
		ArrayAdapter<Unit> arrayAdapter = new ArrayAdapter<Unit>(getActivity(), android.R.layout.simple_list_item_1, listOfUnits); 
		//simple_list_item_1, 
		
		spinnerIn.setAdapter(arrayAdapter);
		spinnerOut.setAdapter(arrayAdapter);	
		
		valueIn.addTextChangedListener(this);
		
		spinnerIn.setOnItemSelectedListener(this);
		spinnerOut.setOnItemSelectedListener(this);
		spinnerProduct.setOnItemSelectedListener(this);	
		
		SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
		String stringValueIn = sharedPreferences.getString("valueIn", "0");
		int intSpinnerProduct = sharedPreferences.getInt("spinnerProduct", 0);
		int intSpinnerIn = sharedPreferences.getInt("spinnerIn", 0);
		int intSpinnerOut = sharedPreferences.getInt("spinnerOut", 0);
		
		valueIn.setText(stringValueIn);
		spinnerProduct.setSelection(intSpinnerProduct);
		spinnerIn.setSelection(intSpinnerIn);
		spinnerOut.setSelection(intSpinnerOut);
		
		valueIn.setSelection(valueIn.getText().length());		
		
		return layout;		
	}
	
	@Override
	public void onDestroy()
	{
		if(valueIn != null) 
		{		
			Editor preferencesEditor = getActivity().getPreferences(Context.MODE_PRIVATE).edit();		
		
			preferencesEditor.putString("valueIn", valueIn.getText().toString());
			preferencesEditor.putInt("spinnerProduct", spinnerProduct.getSelectedItemPosition());
			preferencesEditor.putInt("spinnerIn", spinnerIn.getSelectedItemPosition());
			preferencesEditor.putInt("spinnerOut", spinnerOut.getSelectedItemPosition());
			
			preferencesEditor.commit();
		}
		
		super.onDestroy();
	}

	
	public void calculateResult()
	{
		String stringIn = valueIn.getText().toString();
		float valueInputted;
				
		try
		{
			valueInputted = Float.valueOf(stringIn);
		}		
		catch(Exception e)
		{
			valueInputted = 0;					
		}
		
		Unit unitIn = (Unit) spinnerIn.getSelectedItem();			
		Unit unitOut = (Unit) spinnerOut.getSelectedItem();
		Product product = (Product) spinnerProduct.getSelectedItem();
		
		float valueResult = unitIn.convertTo(valueInputted, unitOut, product);			
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

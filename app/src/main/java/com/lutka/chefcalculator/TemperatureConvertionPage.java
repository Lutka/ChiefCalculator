package com.lutka.chefcalculator;

import java.util.ArrayList;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class TemperatureConvertionPage extends Fragment implements OnItemSelectedListener
{

	ArrayList<Temperature> listOfTemperatures;
	ArrayList<String> listOfTempTypes = new ArrayList<String>();
	
	ArrayList<String> listOfFahrenheitValues = new ArrayList<String>();
	ArrayList<String> listOfCelsiusValues = new ArrayList<String>();
	ArrayList<String> listOfGasMarkValues = new ArrayList<String>();
	ArrayList<String> listOfcookingInstructions = new ArrayList<String>();
	
	Spinner tempType, tempValue;
	TextView fahrenheitValue, celsiusValue, gasValue, cookingInstructionValue;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) 
	{
		
		View layout = inflater.inflate(R.layout.temperature_fragment, container, false);
		
		fahrenheitValue = (TextView) layout.findViewById(R.id.fahrenheitValue);
		celsiusValue = (TextView) layout.findViewById(R.id.celsiusValue);
		gasValue = (TextView) layout.findViewById(R.id.gasValue);
		cookingInstructionValue = (TextView) layout.findViewById(R.id.cooking_instruction_value);
				
		listOfTemperatures = temperatureItems();
		
		fillUpList();
		
		listOfTempTypes.add("Fahrenheit");
		listOfTempTypes.add("Celsius");
		listOfTempTypes.add("Gas Mark");
		
		ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(getActivity(),
					android.R.layout.simple_list_item_1, listOfTempTypes);
		
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), 
					android.R.layout.simple_list_item_1, listOfFahrenheitValues);
		
		tempValue =  (Spinner) layout.findViewById(R.id.temperatureValue);
		tempValue.setAdapter(adapter);
		
		tempType = (Spinner) layout.findViewById(R.id.tempType);
		tempType.setAdapter(typeAdapter);		
		
		tempType.setOnItemSelectedListener(this);
		tempValue.setOnItemSelectedListener(this);
				
		SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
			
		int spinnerType = sharedPreferences.getInt("tempType", 0);
		tempType.setSelection(spinnerType);
		
		int spinnerTempValue = sharedPreferences.getInt("tempValue", 0);
		tempValue.setSelection(spinnerTempValue);	
		
		return layout;
	}
	
	public ArrayList<Temperature> temperatureItems()
	{
		ArrayList<Temperature> listOfTemperatures = new ArrayList<Temperature>();
		
		listOfTemperatures.add(new Temperature("225", "110", "1/4", "Very Slow-Very Low"));
		listOfTemperatures.add(new Temperature("250", "120", "1/2", "Very Slow-Very Low"));
		listOfTemperatures.add(new Temperature("275", "135", "1", "Slow-Low"));
		listOfTemperatures.add(new Temperature("300", "150", "2", "Slow-Low"));
		listOfTemperatures.add(new Temperature("325", "165", "3", "Moderate-Moderately Warm"));
		listOfTemperatures.add(new Temperature("350", "175", "4", "Moderate-Medium"));
		listOfTemperatures.add(new Temperature("375", "190", "5", "Moderate-Moderately Hot"));
		listOfTemperatures.add(new Temperature("400", "200", "6", "Moderately Hot"));
		listOfTemperatures.add(new Temperature("425", "220", "7", "Hot"));
		listOfTemperatures.add(new Temperature("450", "230", "8", "Hot-Very Hot"));
		listOfTemperatures.add(new Temperature("475", "245", "9", "Very Hot"));		
		
		return listOfTemperatures;		
		
	}
	
	
	@Override
	public void onDestroy() 
	{
		Editor preferencesEditor = getActivity().getPreferences(Context.MODE_PRIVATE).edit();
		
		preferencesEditor.putInt("tempType",tempType.getSelectedItemPosition());
		preferencesEditor.putInt("tempValue", tempValue.getSelectedItemPosition());
		
		preferencesEditor.commit();
		
		super.onDestroy();
	}
	
	public void fillUpList ()
	{		
			for(int i = 0; i < listOfTemperatures.size(); i++)
			{
				listOfFahrenheitValues.add(String.valueOf(listOfTemperatures.get(i).getFahrenheit()));
				listOfCelsiusValues.add(String.valueOf(listOfTemperatures.get(i).getCelsius()));
				listOfGasMarkValues.add(listOfTemperatures.get(i).getGasMark());
				listOfcookingInstructions.add(listOfTemperatures.get(i).getCookingInstruction());
				
			}		
	}
	
	@Override
	public void onItemSelected(AdapterView<?> spinner, View arg1, int arg2, long arg3)
	{
		switch (spinner.getId())
		{
			case R.id.tempType: 
				int tempTypeId = arg2; //tempType.getSelectedItemPosition();
				int seletedTempValue = tempValue.getSelectedItemPosition();
				ArrayAdapter<String> adapter;
				switch (tempTypeId)
				{
				case 0:  adapter = new ArrayAdapter<String>(getActivity(), 
						android.R.layout.simple_list_item_1, listOfFahrenheitValues);
					break;
					
				case 1:	adapter = new ArrayAdapter<String>(getActivity(), 
							android.R.layout.simple_list_item_1, listOfCelsiusValues);
					break;
					
				case 2:	adapter = new ArrayAdapter<String>(getActivity(), 
							android.R.layout.simple_list_item_1, listOfGasMarkValues);
					break;
					
				default: return;
				}				
				tempValue.setAdapter(adapter);
				tempValue.setSelection(seletedTempValue);
				break;
			
			case R.id.temperatureValue: setResults();
				break;		
		}
			
	}
	
	public void setResults()
	{
		int tempIndex = (int) tempValue.getSelectedItemId();
		
		fahrenheitValue.setText(listOfTemperatures.get(tempIndex).getFahrenheit());
		celsiusValue.setText(listOfTemperatures.get(tempIndex).getCelsius());
		gasValue.setText(listOfTemperatures.get(tempIndex).getGasMark());
		cookingInstructionValue.setText(listOfTemperatures.get(tempIndex).getCookingInstruction());		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) 
	{
		// TODO Auto-generated method stub		
	}
	
}

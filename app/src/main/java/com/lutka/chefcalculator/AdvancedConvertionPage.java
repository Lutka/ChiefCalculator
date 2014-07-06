package com.lutka.chefcalculator;
import java.util.ArrayList;
import java.util.Collections;

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


public class AdvancedConvertionPage extends Fragment implements OnItemSelectedListener, TextWatcher
{	
	//public static final String  sharedPreferencesTag = "chefcalculator";
	
	ArrayList<Product> listOfProduct;	
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
		
		listOfProduct = productItems();
		
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
		
	public ArrayList<Product> productItems()
	{
		ArrayList<Product> productList =  new ArrayList<Product>();
		
		productList.add(new Product (getActivity().getString(R.string.bean), 1f));
		productList.add(new Product (getActivity().getString(R.string.pea), 1f));
		productList.add(new Product (getActivity().getString(R.string.lentil), 0.84f));
		productList.add(new Product (getActivity().getString(R.string.chickpeas), 1f));
		productList.add(new Product (getActivity().getString(R.string.poppy_seed), 0.61f));
		productList.add(new Product (getActivity().getString(R.string.pumpkin_seed), 0.57f));
		productList.add(new Product (getActivity().getString(R.string.sesame_seeds), 0.66f));
		productList.add(new Product (getActivity().getString(R.string.linseed), 0.7f));
		productList.add(new Product (getActivity().getString(R.string.sunflower_seeds), 0.62f));
		productList.add(new Product (getActivity().getString(R.string.jam), 1.3f));
		productList.add(new Product (getActivity().getString(R.string.blueberries), 0.63f));
		productList.add(new Product (getActivity().getString(R.string.blackberries), 0.61f));
		productList.add(new Product (getActivity().getString(R.string.bread_crumbs), 0.55f));
		productList.add(new Product (getActivity().getString(R.string.buckwheat), 0.72f));
		productList.add(new Product (getActivity().getString(R.string.semolina), 0.71f));
		productList.add(new Product (getActivity().getString(R.string.barley_groats), 0.80f));
		productList.add(new Product (getActivity().getString(R.string.rice), 0.88f));
		productList.add(new Product (getActivity().getString(R.string.sugar), 0.85f));
		productList.add(new Product (getActivity().getString(R.string.sugar_icing), 0.51f));
		productList.add(new Product (getActivity().getString(R.string.vanilla_sugar), 0.87f));
		productList.add(new Product (getActivity().getString(R.string.cinnamon), 0.52f));		
		productList.add(new Product (getActivity().getString(R.string.chocolate_powder), 0.72f));
		productList.add(new Product (getActivity().getString(R.string.cacao), 0.52f));
		productList.add(new Product (getActivity().getString(R.string.coffee_ground), 0.44f));
		productList.add(new Product (getActivity().getString(R.string.ketchup), 1.15f));
		productList.add(new Product (getActivity().getString(R.string.tomato_pure), 1.1f));
		productList.add(new Product (getActivity().getString(R.string.mayonnaise), 0.95f));
		productList.add(new Product (getActivity().getString(R.string.oatmeal), 0.5f));
		productList.add(new Product (getActivity().getString(R.string.flour_wheat), 0.67f));
		productList.add(new Product (getActivity().getString(R.string.flour_potato), 0.76f));
		productList.add(new Product (getActivity().getString(R.string.flour_corn), 0.55f));	
		productList.add(new Product (getActivity().getString(R.string.flour_soy), 0.47f));
		productList.add(new Product (getActivity().getString(R.string.flour_wholemeal), 0.5f));
		productList.add(new Product (getActivity().getString(R.string.honey), 1.39f));
		productList.add(new Product (getActivity().getString(R.string.milk), 1.05f));
		productList.add(new Product (getActivity().getString(R.string.milk_granulated), 0.28f));
		productList.add(new Product (getActivity().getString(R.string.milk_powder), 0.55f));	
		productList.add(new Product (getActivity().getString(R.string.vinegar), 1.05f));
		productList.add(new Product (getActivity().getString(R.string.alcohol), 0.88f));
		productList.add(new Product (getActivity().getString(R.string.wine), 1.05f));
		
		productList.add(new Product (getActivity().getString(R.string.water), 1));
		productList.add(new Product (getActivity().getString(R.string.cream_18), 0.9f));
		productList.add(new Product (getActivity().getString(R.string.margarine), 1));
		productList.add(new Product (getActivity().getString(R.string.butter), 0.96f));
		productList.add(new Product (getActivity().getString(R.string.oil), 0.9f));
		productList.add(new Product (getActivity().getString(R.string.olive_oil), 0.91f));
		productList.add(new Product (getActivity().getString(R.string.lard), 0.98f));
		productList.add(new Product (getActivity().getString(R.string.raisins), 0.73f));
		productList.add(new Product (getActivity().getString(R.string.cranberries), 0.46f));		
		productList.add(new Product (getActivity().getString(R.string.almonds_grated), 0.48f));
		productList.add(new Product (getActivity().getString(R.string.almonds_flakes), 0.39f));
		productList.add(new Product (getActivity().getString(R.string.hazelnuts_grated), 0.51f));		
		productList.add(new Product (getActivity().getString(R.string.walnuts_grated), 0.49f));		
		productList.add(new Product (getActivity().getString(R.string.coconut_grated), 0.75f));
		productList.add(new Product (getActivity().getString(R.string.salt), 1.25f));		
		productList.add(new Product (getActivity().getString(R.string.pepper_ground), 0.49f));
		productList.add(new Product (getActivity().getString(R.string.paprika_ground), 0.6f));	
		productList.add(new Product (getActivity().getString(R.string.ground_spices), 0.55f));	
		productList.add(new Product (getActivity().getString(R.string.baking_powder), 1.22f));
		productList.add(new Product (getActivity().getString(R.string.baking_soda), 0.87f));
		productList.add(new Product (getActivity().getString(R.string.gelatine), 0.71f));
		productList.add(new Product (getActivity().getString(R.string.cheese_grated), 0.52f));
		productList.add(new Product (getActivity().getString(R.string.cottage_cheese), 1.39f));			
		
		return sortProductList(productList);
	}
	
	
	//sort the list alphabetically
	public ArrayList<Product> sortProductList(ArrayList<Product> sortedListOfProducts)
	{
		Collections.sort(sortedListOfProducts);
		return sortedListOfProducts;		
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

package com.lutka.chefcalculator.models;

public class Temperature 
{
	 String fahrenheit,
	 		celsius,
	 		gasMark,
	 		cookingInstruction;
	 
	public Temperature (String fahrenheit, String celsius, String gasMark, String cookingInstruction)
	{				
		this.fahrenheit = fahrenheit;
		this.celsius = celsius;
		this.gasMark = gasMark;
		this.cookingInstruction = cookingInstruction;
	}
	
	@Override
	public String toString() 
	{	
		return fahrenheit;
	}
	
/*	public String toString(int spinnerType) 
	{	
		if(spinnerType == 0) return String.valueOf(fahrenheit);
		else if(spinnerType == 1) return String.valueOf(celsius);
		else return String.valueOf(gasMark);
	}*/
	public String getFahrenheit() 
	{
		return fahrenheit;
	}
	
	public String getCelsius() 
	{
		return celsius;
	}
	
	public String getGasMark() 
	{
		return gasMark;
	}
	
	public String getCookingInstruction() 
	{
		return cookingInstruction;
	}
	
}

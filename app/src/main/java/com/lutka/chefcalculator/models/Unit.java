package com.lutka.chefcalculator.models;

public class Unit
{
	public enum UnitType
	{
		Weight, Volume
	};
	//String fullname;
	String name;	
	float weigth;
	public UnitType unitType;
	
	
	public Unit (String name, float weigth, UnitType unitType)
	{
		//this.fullname = fullname;
		this.name = name;
		this.weigth = weigth;
		this.unitType = unitType;
	}
	
	public float convertTo(float amount, Unit unitTo)
	{	
		return (amount * weigth)/unitTo.weigth;		
	}
	
	public float convertTo (float amount, Unit unitTo, Product product)
	{
		float conversion = convertTo(amount, unitTo);
		
		if(unitTo.unitType != this.unitType)
		{			
			float density = product.getDensity();
						
			if(this.unitType == UnitType.Weight && unitTo.unitType == UnitType.Volume)
			{
				conversion /= density;
			}
			else
			{				
				conversion *= density;
			}			
		}
		
		//result formated to the second significant digit after comma
		return (float) round(conversion);	
	}
	
	/*//converion specialy for yeasts
	public float convertTo (float amount, Unit unitTo, Product productIn, Product productOut)
	{
		float amountIn = amount;
		float result = 0;
		
		float convertionScale = (productOut.density / productIn.density);
		
		if(unitTo.unitType == this.unitType)
		{
			result = amountIn * convertionScale;
		}
		//unit types are diffrent
		else 
		{
			result = convertTo(amountIn, unitTo);
			
			if(this.unitType == UnitType.Weight)
			{				
				result /= convertionScale;
			}
			else
			{
				result *=convertionScale;
			}
		}
			
//		float conversion = amount; //convertTo(amount, unitTo);
//		float convertionScale = productOut.getDensity() / productIn.getDensity();;
//		if(unitTo.unitType != this.unitType)
//		{							
//			if(this.unitType == UnitType.Weight && unitTo.unitType == UnitType.Volume )
//			{
//				conversion /= convertionScale;
//			}
//			else
//			{				
//				conversion *= convertionScale;
//			}		
//		}
//		else
//		{
//			conversion*=convertionScale;
//		}
//		
//		//result formated to the second significant digit after comma
		
		return (float) round(result);	
	}*/
	
	@Override
	public String toString()
	{
		return name;
	}
	
	//to format the result to the second digit after comma
	public static double round(double value ) 
	{
	    long factor = (long) Math.pow(10, 2);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
}

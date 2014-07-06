package com.lutka.chefcalculator;

public class Yeast extends Product
{
	float yeastPower;
	
	Yeast(String name, float density, float yeastPower)
	{
		super(name, density);
		this.yeastPower = yeastPower;
	}
	
	public float getYeastPower()
	{
		return yeastPower;
	}
	
	public float convertToYeastTypes(Yeast yeast, float yeastAmount)
	{		
		float amount;
		
		return amount = yeastAmount * (yeast.yeastPower / this.yeastPower);		
	}
}

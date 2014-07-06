package com.lutka.chefcalculator.models;

import com.michaldabski.msqlite.Annotations;

public class Unit
{
    public static final int
        UNIT_WEIGHT = 0,
        UNIT_VOLUME = 1;

    @Annotations.PrimaryKey
	String name;	
	float weigth;
	public int unitType;

    public Unit()
    {
    }

    public Unit (String name, float weigth, int unitType)
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
						
			if(this.unitType == UNIT_WEIGHT && unitTo.unitType == UNIT_VOLUME)
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

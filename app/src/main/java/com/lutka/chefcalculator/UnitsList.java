package com.lutka.chefcalculator;

import java.util.ArrayList;

import android.content.Context;

import com.lutka.chefcalculator.models.Unit;
import com.lutka.chefcalculator.models.Unit.UnitType;


public class UnitsList
{	
	ArrayList <Unit> unitList;
	
	public UnitsList(Context context)
	{
		unitList = new ArrayList<Unit>();
		UnitType unitType;
			
		//to add volume measurements
		unitType = UnitType.Volume;
		
		unitList.add(new Unit(context.getString(R.string.mililitr),  1, unitType));
		unitList.add(new Unit(context.getString(R.string.tea_spoon),  5, unitType ));
		unitList.add(new Unit(context.getString(R.string.table_spoon),  15, unitType ));		
		unitList.add(new Unit(context.getString(R.string.oz_uk), 28.41f, unitType));
		unitList.add(new Unit(context.getString(R.string.oz_us), 29.57f, unitType));
		unitList.add(new Unit(context.getString(R.string.glass),  250, unitType ));	
		unitList.add(new Unit(context.getString(R.string.litr),  1000, unitType));		
		unitList.add(new Unit(context.getString(R.string.cup_us), 240, unitType));		
		unitList.add(new Unit(context.getString(R.string.cup_uk), 285, unitType));			
		unitList.add(new Unit(context.getString(R.string.pint_us), 473.17f, unitType));
		unitList.add(new Unit(context.getString(R.string.pint_uk), 586.26f, unitType));		
		unitList.add(new Unit(context.getString(R.string.quart_us),  946, unitType));
		unitList.add(new Unit(context.getString(R.string.quart_uk),  1137, unitType));
		
		
		// to add weight measurements
		unitType = UnitType.Weight;
		
		unitList.add(new Unit(context.getString(R.string.miligram),  0.1f, unitType));
		unitList.add(new Unit(context.getString(R.string.gram),  1, unitType));
		unitList.add(new Unit(context.getString(R.string.dekagram),  10, unitType ));
		unitList.add(new Unit(context.getString(R.string.pound),  453.59f, unitType ));
		unitList.add(new Unit(context.getString(R.string.kilogram),  1000, unitType ));	
		unitList.add(new Unit(context.getString(R.string.stone),  6350.29f, unitType ));
	}
	
	public ArrayList<Unit> getUnits(UnitType unitType)
	{
		ArrayList<Unit> listOfRequiredUnit = new ArrayList<Unit>();
		
		//loop for each
		for (Unit unit: unitList)
		{
			if(unit.unitType == unitType)
			{
				listOfRequiredUnit.add(unit); 
			}
		}
		return listOfRequiredUnit;
	}
	
	public ArrayList<Unit> getUnits()
	{
		return unitList;
	}
	
}

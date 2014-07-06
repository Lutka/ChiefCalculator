package com.lutka.chefcalculator.models;

public class Product implements Comparable<Product>
{
	String name;
	float density;  // gram/cm^3 or kg/m^3
	
	public Product(String name, float density)
	{
		this.name = name;
		this.density = density;
	}
	
	@Override
	public String toString()
	{
		return name;
	}
	
	public float getDensity()
	{
		return density;
	}

	@Override
	public int compareTo(Product anotherProduct) 
	{
		return (this.name).compareTo(anotherProduct.name);		
	}
}

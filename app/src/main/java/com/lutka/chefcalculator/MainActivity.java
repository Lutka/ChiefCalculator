package com.lutka.chefcalculator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.lutka.chefcalculator.pages.AdvancedConvertionPage;
import com.lutka.chefcalculator.pages.TemperatureConvertionPage;
import com.lutka.chefcalculator.pages.YeastConvertionPage;

public class MainActivity extends FragmentActivity 
{
	//display tabs on the top similar to action bar
	// ->TabHost tabHost;
	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
//		getSupportActionBar().hide();
		//getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		
		// ->tabHost = (TabHost) findViewById(android.R.id.tabhost);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener()
				{
					@Override
					public void onPageSelected(int position)
					{
						//actionBar.setSelectedNavigationItem(position);
						// ->tabHost.setCurrentTab(position);
						
						//setTitle(mSectionsPagerAdapter.getPageTitle(position));
					}
				});

		// --->   For each of the sections in the app, add a tab to the action bar.
	/*	for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++)
		{
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar.addTab(actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
			
			TabSpec tab = tabHost.newTabSpec(mSectionsPagerAdapter.getPageTitle(i).toString());
			tab.setIndicator(mSectionsPagerAdapter.getPageTitle(i));
			tab.setContent(android.R.id.tabcontent);
			tabHost.addTab(tab);
		}*/
	}

	
	//to not have a menu in the right top corner 
/*	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}*/
/*
	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction)
	{
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction)
	{
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction)
	{
	}*/

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter
	{

		public SectionsPagerAdapter(FragmentManager fm)
		{
			super(fm);
		}

		@Override
		public Fragment getItem(int position)
		{
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			Fragment fragment;
			
			if(position == 0)
			{
				fragment = new AdvancedConvertionPage();
			}
			else if(position == 1)
			{
				fragment = new TemperatureConvertionPage();
			}				
			else
			{
				fragment = new YeastConvertionPage();
			}
			
			Bundle args = new Bundle();
			args.putInt("page", position);
			fragment.setArguments(args);
			return fragment;
			
		}

		@Override
		public int getCount()
		{
			// Show 3 total pages.
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position)
		{
			switch (position)
			{
				case 0:
					return getString(R.string.title_section1).toUpperCase();
				case 1:
					return getString(R.string.title_section2).toUpperCase();
				case 2:
					return getString(R.string.title_section3).toUpperCase();
			}
			return null;
		}
	}

}

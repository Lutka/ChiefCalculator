package com.lutka.chefcalculator;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.lutka.chefcalculator.models.Product;
import com.lutka.chefcalculator.models.Unit;
import com.lutka.chefcalculator.models.Yeast;
import com.michaldabski.msqlite.MSQLiteOpenHelper;

import java.net.MulticastSocket;

/**
 * Created by Lutka on 06/07/2014.
 */
public class DatabaseManager extends MSQLiteOpenHelper
{
    public DatabaseManager(Context context)
    {
        super(context, "kitchen.db", null, 1, new Class[]{Product.class, Yeast.class, Unit.class});
    }
}

package com.lutka.chefcalculator;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.lutka.chefcalculator.models.Product;
import com.lutka.chefcalculator.models.Unit;
import com.lutka.chefcalculator.models.Yeast;
import com.michaldabski.msqlite.MSQLiteOpenHelper;

import java.net.MulticastSocket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Lutka on 06/07/2014.
 */
public class DataBaseManager extends MSQLiteOpenHelper
{
    Context context;
    public DataBaseManager(Context context)
    {
        super(context, "kitchen.db", null, 1, new Class[]{Product.class, Unit.class});
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        super.onCreate(db);
        ArrayList<Product> productList =  productItems(context);
        insert(db,Product.class, productList);
        List<Unit> unitList = unitsList(context);
        insert(db, Unit.class, unitList);
    }

    private static ArrayList<Product> productItems(Context context)
    {
        ArrayList<Product> productList = new ArrayList<Product>();

        productList.add(new Product (context.getString(R.string.bean), 1f));
        productList.add(new Product (context.getString(R.string.pea), 1f));
        productList.add(new Product (context.getString(R.string.lentil), 0.84f));
        productList.add(new Product (context.getString(R.string.chickpeas), 1f));
        productList.add(new Product (context.getString(R.string.poppy_seed), 0.61f));
        productList.add(new Product (context.getString(R.string.pumpkin_seed), 0.57f));
        productList.add(new Product (context.getString(R.string.sesame_seeds), 0.66f));
        productList.add(new Product (context.getString(R.string.linseed), 0.7f));
        productList.add(new Product (context.getString(R.string.sunflower_seeds), 0.62f));
        productList.add(new Product (context.getString(R.string.jam), 1.3f));
        productList.add(new Product (context.getString(R.string.blueberries), 0.63f));
        productList.add(new Product (context.getString(R.string.blackberries), 0.61f));
        productList.add(new Product (context.getString(R.string.bread_crumbs), 0.55f));
        productList.add(new Product (context.getString(R.string.buckwheat), 0.72f));
        productList.add(new Product (context.getString(R.string.semolina), 0.71f));
        productList.add(new Product (context.getString(R.string.barley_groats), 0.80f));
        productList.add(new Product (context.getString(R.string.rice), 0.88f));
        productList.add(new Product (context.getString(R.string.sugar), 0.85f));
        productList.add(new Product (context.getString(R.string.sugar_icing), 0.51f));
        productList.add(new Product (context.getString(R.string.vanilla_sugar), 0.87f));
        productList.add(new Product (context.getString(R.string.cinnamon), 0.52f));
        productList.add(new Product (context.getString(R.string.chocolate_powder), 0.72f));
        productList.add(new Product (context.getString(R.string.cacao), 0.52f));
        productList.add(new Product (context.getString(R.string.coffee_ground), 0.44f));
        productList.add(new Product (context.getString(R.string.ketchup), 1.15f));
        productList.add(new Product (context.getString(R.string.tomato_pure), 1.1f));
        productList.add(new Product (context.getString(R.string.mayonnaise), 0.95f));
        productList.add(new Product (context.getString(R.string.oatmeal), 0.5f));
        productList.add(new Product (context.getString(R.string.flour_wheat), 0.67f));
        productList.add(new Product (context.getString(R.string.flour_potato), 0.76f));
        productList.add(new Product (context.getString(R.string.flour_corn), 0.55f));
        productList.add(new Product (context.getString(R.string.flour_soy), 0.47f));
        productList.add(new Product (context.getString(R.string.flour_wholemeal), 0.5f));
        productList.add(new Product (context.getString(R.string.honey), 1.39f));
        productList.add(new Product (context.getString(R.string.milk), 1.05f));
        productList.add(new Product (context.getString(R.string.milk_granulated), 0.28f));
        productList.add(new Product (context.getString(R.string.milk_powder), 0.55f));
        productList.add(new Product (context.getString(R.string.vinegar), 1.05f));
        productList.add(new Product (context.getString(R.string.alcohol), 0.88f));
        productList.add(new Product (context.getString(R.string.wine), 1.05f));

        productList.add(new Product (context.getString(R.string.water), 1));
        productList.add(new Product (context.getString(R.string.cream_18), 0.9f));
        productList.add(new Product (context.getString(R.string.margarine), 1));
        productList.add(new Product (context.getString(R.string.butter), 0.96f));
        productList.add(new Product (context.getString(R.string.oil), 0.9f));
        productList.add(new Product (context.getString(R.string.olive_oil), 0.91f));
        productList.add(new Product (context.getString(R.string.lard), 0.98f));
        productList.add(new Product (context.getString(R.string.raisins), 0.73f));
        productList.add(new Product (context.getString(R.string.cranberries), 0.46f));
        productList.add(new Product (context.getString(R.string.almonds_grated), 0.48f));
        productList.add(new Product (context.getString(R.string.almonds_flakes), 0.39f));
        productList.add(new Product (context.getString(R.string.hazelnuts_grated), 0.51f));
        productList.add(new Product (context.getString(R.string.walnuts_grated), 0.49f));
        productList.add(new Product (context.getString(R.string.coconut_grated), 0.75f));
        productList.add(new Product (context.getString(R.string.salt), 1.25f));
        productList.add(new Product (context.getString(R.string.pepper_ground), 0.49f));
        productList.add(new Product (context.getString(R.string.paprika_ground), 0.6f));
        productList.add(new Product (context.getString(R.string.ground_spices), 0.55f));
        productList.add(new Product (context.getString(R.string.baking_powder), 1.22f));
        productList.add(new Product (context.getString(R.string.baking_soda), 0.87f));
        productList.add(new Product (context.getString(R.string.gelatine), 0.71f));
        productList.add(new Product (context.getString(R.string.cheese_grated), 0.52f));
        productList.add(new Product (context.getString(R.string.cottage_cheese), 1.39f));

        return productList;
    }

    public List<Unit> unitsList(Context context)
    {
        List<Unit> unitList = new ArrayList<Unit>();
        int unitType;

        //to add volume measurements
        unitType = Unit.UNIT_VOLUME;

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
        unitType = Unit.UNIT_WEIGHT;

        unitList.add(new Unit(context.getString(R.string.miligram),  0.1f, unitType));
        unitList.add(new Unit(context.getString(R.string.gram),  1, unitType));
        unitList.add(new Unit(context.getString(R.string.dekagram),  10, unitType ));
        unitList.add(new Unit(context.getString(R.string.pound),  453.59f, unitType ));
        unitList.add(new Unit(context.getString(R.string.kilogram),  1000, unitType ));
        unitList.add(new Unit(context.getString(R.string.stone),  6350.29f, unitType ));

        return unitList;
    }

    public List <Product> getSortedProducts() {
       return select(Product.class, null, null, Product.FIELD_NAME, null);
    }

}

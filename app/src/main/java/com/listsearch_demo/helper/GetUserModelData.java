package com.listsearch_demo.helper;

import java.util.ArrayList;

/**
 * Created by sonu on 14/02/17.
 */

/*  Get ArrayList  of UserModel type  */
public class GetUserModelData {
    public static ArrayList<UserModel> getUserModelData() {
        ArrayList<UserModel> arrayList = new ArrayList<>();
        arrayList.add(new UserModel("Droid", "9008091580", "droid@gmail.com"));
        arrayList.add(new UserModel("John", "9876543212", "john@gmail.com"));
        arrayList.add(new UserModel("David", "3452456718", "david@gmail.com"));
        arrayList.add(new UserModel("Humpy", "9843156780", "humpy@gmail.com"));
        arrayList.add(new UserModel("Sharma", "1234984378", "sharma@gmail.com"));
        arrayList.add(new UserModel("Dravid", "4325678943", "dravid@gmail.com"));
        arrayList.add(new UserModel("Android", "8876554433", "android@gmail.com"));
        arrayList.add(new UserModel("Amrish", "3344559988", "amrish@gmail.com"));
        arrayList.add(new UserModel("Allena", "3344990044", "allena@gmail.com"));
        arrayList.add(new UserModel("Smith", "1144553355", "smith@gmail.com"));
        arrayList.add(new UserModel("Guru", "5678493002", "guru@gmail.com"));
        arrayList.add(new UserModel("Rashid", "4450987456", "rashid@gmail.com"));
        arrayList.add(new UserModel("Prophec", "8967589433", "prphec@gmail.com"));
        arrayList.add(new UserModel("Diljit", "9058443399", "diljit@gmail.com"));
        arrayList.add(new UserModel("Bohemia", "5768950484", "bohemia@gmail.com"));
        return arrayList;
    }
}

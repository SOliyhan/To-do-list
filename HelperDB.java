package com.example.computer_ji.todolist;


import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by Computer_Ji on 21-11-2018.
 */

public class HelperDB {

    private static final String file_name= "listinfo.dat";

    public static void writeData(ArrayList<String> items, Context context)
    {
        try (FileOutputStream fos = context.openFileOutput(file_name, Context.MODE_PRIVATE))
        {
            ObjectOutputStream oos= new ObjectOutputStream(fos);
            oos.writeObject(items);
            oos.close();
        }
        catch (FileNotFoundException fe)
        {
            fe.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

        public static ArrayList<String> readData(Context context)
        {
            ArrayList<String> itemList= null;
            try {
                FileInputStream fis = context.openFileInput(file_name);
                ObjectInputStream ois = new ObjectInputStream(fis);
                itemList= (ArrayList<String>) ois.readObject();
            }
            catch (FileNotFoundException fe) {
                itemList = new ArrayList<>();
                fe.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

            return itemList;
        }

}

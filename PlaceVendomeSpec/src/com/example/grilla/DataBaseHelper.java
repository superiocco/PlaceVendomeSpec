package com.example.grilla;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper
{
private static String TAG = "DataBaseHelper"; // Tag just for the LogCat window
//destination path (location) of our database on device
private static String DB_PATH = "/data/data/com.example.placevendomespec/databases/"; 
private static String DB_NAME ="Grilla2";// Database name
private SQLiteDatabase mDataBase; 
private final Context mContext;

public DataBaseHelper(Context context) 
{
    super(context, DB_NAME, null, 1);// 1? its Database Version
    DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
    this.mContext = context;
}   

public void createDataBase() throws IOException
{
    //If database not exists copy it from the assets

    boolean mDataBaseExist = checkDataBase();
    if(!mDataBaseExist)
    {
        this.getReadableDatabase();
        this.close();
        try 
        {
            //Copy the database from assests
            copyDataBase();
            Log.e(TAG, "createDatabase database created");
        } 
        catch (IOException mIOException) 
        {
            throw new Error("ErrorCopyingDataBase");
        }
    }
}
    //Check that the database exists here: /data/data/your package/databases/Da Name
    private boolean checkDataBase()
    {
        File dbFile = new File(DB_PATH + DB_NAME);
        //Log.v("dbFile", dbFile + "   "+ dbFile.exists());
        return dbFile.exists();
    }

    //Copy the database from assets
    private void copyDataBase() throws IOException
    {
        InputStream mInput = mContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream mOutput = new FileOutputStream(outFileName);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer))>0)
        {
            mOutput.write(mBuffer, 0, mLength);
        }
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

    //Open the database, so we can query it
    public boolean openDataBase() throws SQLException
    {
        String mPath = DB_PATH + DB_NAME;
        //Log.v("mPath", mPath);
        mDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        //mDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        return mDataBase != null;
    }

    @Override
    public synchronized void close() 
    {
        if(mDataBase != null)
            mDataBase.close();
        super.close();
    }

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
	public float getBase(String material, float cilindro, float esfera){
		
		//mDataBase.execSQL("SELECT Base FROM Grilla");
		//mDataBase.query("Grilla", "Base", "Base], "0.5", null, null, null, null);
		Cursor cursor = mDataBase.rawQuery("Select Base FROM Grilla WHERE Material  = ? AND Cilindro = ? AND Esfera = ?", new String[]{material,Float.toString(cilindro),Float.toString(esfera)});
		if (cursor.getCount()==0){
			return 0;
		}
		else{
		
		Log.d("DB", Integer.toString(cursor.getColumnIndex("Base")));
		cursor.moveToNext();
		Log.d("DB", cursor.getString(cursor.getColumnIndex("Base")));
		
		
		return cursor.getFloat(cursor.getColumnIndex("Base"));
		}
	}

	public String getMaterials(float cilindroDerecho, float esfera, float cilindroIzquierdo){
		
		String posibles ="";
		int primero = 0;
		Cursor cursor = mDataBase.rawQuery("Select Material From Grilla WHERE Cilindro = ? AND Esfera = ?", new String[]{Float.toString(cilindroDerecho), Float.toString(esfera)});
		Cursor cursor2 = mDataBase.rawQuery("Select Material From Grilla WHERE Cilindro = ? AND Esfera = ?", new String[]{Float.toString(cilindroIzquierdo), Float.toString(esfera)});
		
		while (cursor.moveToNext()){
			while(cursor2.moveToNext()){
			if (cursor.getString(0).equals(cursor2.getString(0))){
				if(primero==0){
				posibles = cursor.getString(0);
				primero=1;
				}
				else{
					posibles = posibles +", " + cursor.getString(0);
				}
				
			}
			}
			cursor2.moveToPosition(-1);
			
		}
		
		
		return posibles;
	}
	
public String getBases(float cilindroDerecho, float esferaDer, float esferaIzq, float cilindroIzquierdo){
		
		String posibles ="";
		int primero = 0;
		float abs;
		if(cilindroDerecho<0){
			
			esferaDer = cilindroDerecho + esferaDer;
			cilindroDerecho = -cilindroDerecho;
			
		}
		if(cilindroIzquierdo<0){
			
			esferaIzq = cilindroIzquierdo + esferaIzq;
			cilindroIzquierdo= -cilindroIzquierdo;
			
		}
		
		
		Cursor cursor = mDataBase.rawQuery("Select Material, Base From Grilla WHERE Cilindro = ? AND Esfera = ?", new String[]{Float.toString(cilindroDerecho), Float.toString(esferaDer)});
		Cursor cursor2 = mDataBase.rawQuery("Select Material, Base From Grilla WHERE Cilindro = ? AND Esfera = ?", new String[]{Float.toString(cilindroIzquierdo), Float.toString(esferaIzq)});
		
		
		while (cursor.moveToNext()){
			while(cursor2.moveToNext()){
				
				if(cursor.getString(0).equals(cursor2.getString(0))){
				abs = Math.abs(Float.parseFloat(cursor.getString(1))- Float.parseFloat(cursor2.getString(1)));
			
			if (1>=abs){
				if(primero==0){
					
					posibles = cursor.getString(1);

				if(abs==0){
					
					posibles = posibles + "," + Float.toString((float) (Float.parseFloat(cursor.getString(1))-0.5));
					posibles = posibles + "," + Float.toString((float) (Float.parseFloat(cursor.getString(1))+0.5));
					posibles = posibles + "," + Float.toString((float) (Float.parseFloat(cursor.getString(1))-1.0));
					posibles = posibles + "," + Float.toString((float) (Float.parseFloat(cursor.getString(1))+1.0));

				}
				if(abs== 1){
					posibles = cursor.getString(1) + "," + cursor.getString(2);
					if(Float.parseFloat(cursor.getString(1))>Float.parseFloat(cursor2.getString(2))){
						
						posibles = posibles + "," +Float.toString((float) (Float.parseFloat(cursor.getString(1))-0.5));
						
					}
					else{
						
						posibles = posibles + "," +Float.toString((float) (Float.parseFloat(cursor.getString(1))-0.5));

						
					}
					
				}
				if(abs== 0.5){
					posibles = cursor.getString(1) + "," + cursor.getString(2);
					if(Float.parseFloat(cursor.getString(1))>Float.parseFloat(cursor2.getString(2))){
						
						posibles = posibles + "," +Float.toString((float) (Float.parseFloat(cursor.getString(1))+0.5));
						posibles = posibles + "," +Float.toString((float) (Float.parseFloat(cursor.getString(1))-1.0));

					}
					else{
						
						posibles = posibles + "," +Float.toString((float) (Float.parseFloat(cursor.getString(1))-0.5));
						posibles = posibles + "," +Float.toString((float) (Float.parseFloat(cursor.getString(1))+1.0));
						
					}
					
				}
				
				primero=1;
				}
				else{
					
					if(!posibles.contains( cursor.getString(1))){
					posibles = posibles +", " + cursor.getString(1);
					}
					if(!posibles.contains( cursor2.getString(1))){
						posibles = posibles +", " + cursor2.getString(1);
					}
					

					if(abs==0){
						if(!posibles.contains(Float.toString((float) (Float.parseFloat(cursor.getString(1))-0.5)))){

						posibles = posibles + "," + Float.toString((float) (Float.parseFloat(cursor.getString(1))-0.5));
						
						}
						if(!posibles.contains(Float.toString((float) (Float.parseFloat(cursor.getString(1))+0.5)))){
						posibles = posibles + "," + Float.toString((float) (Float.parseFloat(cursor.getString(1))+0.5));
						
						}
						if(!posibles.contains(Float.toString((float) (Float.parseFloat(cursor.getString(1))-1.0)))){
						posibles = posibles + "," + Float.toString((float) (Float.parseFloat(cursor.getString(1))-1.0));
						}
						
						if(!posibles.contains(Float.toString((float) (Float.parseFloat(cursor.getString(1))+1.0)))){
						posibles = posibles + "," + Float.toString((float) (Float.parseFloat(cursor.getString(1))+1.0));
						}
					}
					if(abs== 1){
						if(Float.parseFloat(cursor.getString(1))>Float.parseFloat(cursor2.getString(1))){
							if(!posibles.contains(Float.toString((float) (Float.parseFloat(cursor.getString(1))-0.5)))){
							posibles = posibles + "," +Float.toString((float) (Float.parseFloat(cursor.getString(1))-0.5));
							}
						}
						else{
							if(!posibles.contains(Float.toString((float) (Float.parseFloat(cursor.getString(1))+0.5)))){
							posibles = posibles + "," +Float.toString((float) (Float.parseFloat(cursor.getString(1))+0.5));
							}
							
						}
						
					}
					if(abs== 0.5){
						posibles = cursor.getString(1) + "," + cursor2.getString(1);
						if(Float.parseFloat(cursor.getString(1))>Float.parseFloat(cursor2.getString(1))){
							
							if(!posibles.contains(Float.toString((float) (Float.parseFloat(cursor.getString(1))+0.5)))){
							posibles = posibles + "," +Float.toString((float) (Float.parseFloat(cursor.getString(1))+0.5));
							}
							if(!posibles.contains(Float.toString((float) (Float.parseFloat(cursor.getString(1))-1.0)))){
							posibles = posibles + "," +Float.toString((float) (Float.parseFloat(cursor.getString(1))-1.0));
							}

						}
						else{
							if(!posibles.contains(Float.toString((float) (Float.parseFloat(cursor.getString(1))-0.5)))){
							posibles = posibles + "," +Float.toString((float) (Float.parseFloat(cursor.getString(1))-0.5));
							}
							if(!posibles.contains(Float.toString((float) (Float.parseFloat(cursor.getString(1))+1.0)))){
							posibles = posibles + "," +Float.toString((float) (Float.parseFloat(cursor.getString(1))+1.0));
							}
						}
						
					}
					
				
					}
				}
				}
			}
			cursor2.moveToPosition(-1);
			
		}
		
		Log.d("Posibles", posibles);
		
		return posibles;
	}
	
}
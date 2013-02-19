package com.example.placeVendome.tiendas;

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

public class DataBaseTiendaHelper extends SQLiteOpenHelper {

	private static String TAG = "DataBaseHelper"; // Tag just for the LogCat window
	//destination path (location) of our database on device
	private static String DB_PATH = "/data/data/com.example.placevendomespec/databases/"; 
	private static String DB_NAME ="tiendas";// Database name
	private SQLiteDatabase mDataBase; 
	private final Context mContext;

	public DataBaseTiendaHelper(Context context) 
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
		
		public Tienda getTienda(String codigo){
			Tienda tienda = new Tienda();

			Cursor cursor = mDataBase.rawQuery("Select Nombre,Direccion,Ubicacion,Latitud,Longitud FROM Tiendas Where Codigo = ?", new String[]{codigo});
			if(cursor.moveToNext()){
			tienda.setNombre(cursor.getString(0));
			tienda.setDireccion(cursor.getString(1));
			tienda.setUbicacion(cursor.getString(2));
			tienda.setLatitud(cursor.getFloat(3));
			tienda.setLongitud(cursor.getFloat(4));
			Log.d("TIENDA CON STOCK", codigo);
			
			
			return tienda;
			}
			
			else{
				
				
			return null;
			}
			
			
		}

		
		

}

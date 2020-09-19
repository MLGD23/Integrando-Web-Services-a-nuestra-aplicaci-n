package com.example.w4118project.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.w4118project.pojo.Mascota;

import java.util.ArrayList;

public class BaseDatos extends SQLiteOpenHelper {

    private Context context;

    public BaseDatos(@Nullable Context context) {
        super(context, ConstantesDB.DB_NAME, null, ConstantesDB.DB_VERSION);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query_CrearTablaMascota = "CREATE TABLE " + ConstantesDB.TABLA_MASCOTA +
                "(" +
                ConstantesDB.TABLA_MASCOTA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesDB.TABLA_MASCOTA_NOMBRE + " TEXT, " +
                ConstantesDB.TABLA_MASCOTA_FOTO + " INTEGER" +
                ")";

        String query_CrearTablaRating = "CREATE TABLE " + ConstantesDB.TABLA_RATING +
                "(" +
                ConstantesDB.TABLA_RATING_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesDB.TABLA_RATING_ID_MASCOTA + " INTEGER, " +
                ConstantesDB.TABLA_RATING_NUMERO_RATING + " INTEGER, " +
                "FOREIGN KEY ( " + ConstantesDB.TABLA_RATING_ID_MASCOTA + ") " +
                "REFERENCES " + ConstantesDB.TABLA_MASCOTA + "(" + ConstantesDB.TABLA_RATING + ")" +
                ")";

        sqLiteDatabase.execSQL(query_CrearTablaMascota);
        sqLiteDatabase.execSQL(query_CrearTablaRating);
    }

    public void crearTablas() {
        Log.i("TAG", "BaseDatos.crearTablas()...");
        SQLiteDatabase db = this.getWritableDatabase();
        String query_CrearTablaMascota = "CREATE TABLE " + ConstantesDB.TABLA_MASCOTA +
                "(" +
                ConstantesDB.TABLA_MASCOTA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesDB.TABLA_MASCOTA_NOMBRE + " TEXT, " +
                ConstantesDB.TABLA_MASCOTA_FOTO + " INTEGER" +
                ")";

        String query_CrearTablaRatingMascota = "CREATE TABLE " + ConstantesDB.TABLA_RATING +
                "(" +
                ConstantesDB.TABLA_RATING_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesDB.TABLA_RATING_ID_MASCOTA + " INTEGER, " +
                ConstantesDB.TABLA_RATING_NUMERO_RATING + " INTEGER, " +
                "FOREIGN KEY ( " + ConstantesDB.TABLA_RATING_ID_MASCOTA + ") " +
                "REFERENCES " + ConstantesDB.TABLA_MASCOTA + "(" + ConstantesDB.TABLA_RATING + ")" +
                ")";

        db.execSQL(query_CrearTablaMascota);
        db.execSQL(query_CrearTablaRatingMascota);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ConstantesDB.TABLA_MASCOTA);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ConstantesDB.TABLA_RATING);
    }

    public ArrayList<Mascota> obtenerTodasLasMascotas() {
        ArrayList<Mascota> mascotas = new ArrayList<Mascota>();

        String query = "SELECT * FROM " + ConstantesDB.TABLA_MASCOTA;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while (registros.moveToNext()) {
            Mascota mascotaActual = new Mascota();
            mascotaActual.setId(registros.getString(0));
            mascotaActual.setNombre(registros.getString(1));
            mascotaActual.setUrlFoto(registros.getString(2));
            mascotaActual.setRating(obtenerRating(mascotaActual));



            mascotas.add(mascotaActual);
        }
        db.close();

        return mascotas;
    }

    public ArrayList<Mascota> obtenerMascotasFavoritas() {
        ArrayList<Mascota> mascotas = new ArrayList<Mascota>();

        String query = "SELECT " +
                ConstantesDB.TABLA_RATING_ID + "," + ConstantesDB.TABLA_RATING_ID_MASCOTA +
                " FROM " + ConstantesDB.TABLA_RATING +
                " GROUP BY " + ConstantesDB.TABLA_RATING_ID_MASCOTA +
                " ORDER BY " + ConstantesDB.TABLA_RATING_ID + " DESC " +
                "LIMIT 5;";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while (registros.moveToNext()) {
            Mascota mascotaActual = obtenerMascota(registros.getInt(1));
            mascotas.add(mascotaActual);
        }
        db.close();

        return mascotas;
    }

    public void insertarMascota(ContentValues contentValues) {
        SQLiteDatabase db = getWritableDatabase();
        db.insert(ConstantesDB.TABLA_MASCOTA, null, contentValues);
        db.close();
    }

    public void  borrarTablas() {
        Log.i("TAG", "BaseDatos.borrarTablas()...");

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + ConstantesDB.TABLA_MASCOTA);
        db.execSQL("DROP TABLE IF EXISTS " + ConstantesDB.TABLA_RATING);
        db.close();
    }

    public void borrarTodasLasMascotas() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + ConstantesDB.TABLA_MASCOTA);
        db.close();
    }

    public void insertarRatingMascota(ContentValues contentValues) {
        SQLiteDatabase db = getWritableDatabase();
        db.insert(ConstantesDB.TABLA_RATING, null, contentValues);
        db.close();
    }

    public int obtenerRating(Mascota mascota) {
        int rating = 0;

        String query = "SELECT COUNT(" + ConstantesDB.TABLA_RATING_NUMERO_RATING + ")" +
                " FROM " + ConstantesDB.TABLA_RATING +
                " WHERE " + ConstantesDB.TABLA_RATING_ID_MASCOTA + "=" + mascota.getId();

        SQLiteDatabase db = getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        if (registros.moveToNext()) {
            rating = registros.getInt(0);
        }

        db.close();

        return rating;
    }

    public Mascota obtenerMascota(int id) {
        Mascota mascota = null;

        String query = "SELECT * FROM " + ConstantesDB.TABLA_MASCOTA +
                " WHERE " + ConstantesDB.TABLA_MASCOTA_ID + "=" + id;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        if (registros.moveToNext()) {
            mascota= new Mascota();
            mascota.setId(registros.getString(0));
            mascota.setNombre(registros.getString(1));
            mascota.setUrlFoto(registros.getString(2));
            mascota.setRating(obtenerRating(mascota));
        }

        db.close();

        return mascota;
    }

    public boolean existeTabla(String nombreTabla) {
        boolean exists = false;
        String query = "SELECT name FROM sqlite_master WHERE type='table' AND name='" + nombreTabla + "';";

        SQLiteDatabase db = getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        if (registros.moveToNext()) {
            exists = true;
        }

        db.close();
        return exists;
    }

    public boolean inicializarDB(boolean dropTables) {
        Log.i("TAG", "BaseDatos.inicializarDB(...)...");
        if (dropTables) {
            borrarTablas();
        }
        if (!existeTabla(ConstantesDB.TABLA_MASCOTA)) {
            crearTablas();
            return true;
        }
        return false;
    }
}

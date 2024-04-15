package com.example.homework1pam;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "GardeningClub.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_MEMBERS = "members";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_IMAGE_URL = "imageUrl";
    private static final String COLUMN_SHORT_DESCRIPTION = "shortDescription";
    private static final String COLUMN_DETAILED_DESCRIPTION = "detailedDescription";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MEMBERS_TABLE = "CREATE TABLE " + TABLE_MEMBERS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_IMAGE_URL + " TEXT,"
                + COLUMN_SHORT_DESCRIPTION + " TEXT,"
                + COLUMN_DETAILED_DESCRIPTION + " TEXT" + ")";
        db.execSQL(CREATE_MEMBERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEMBERS);
        onCreate(db);
    }//this method is called when the database needs to be upgraded.

    //method for adding a new member
    public void addMember(Member member) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, member.getName());
        values.put(COLUMN_IMAGE_URL, member.getImageUrl());
        values.put(COLUMN_SHORT_DESCRIPTION, member.getShortDescription());
        values.put(COLUMN_DETAILED_DESCRIPTION, member.getDetailedDescription());
        db.insert(TABLE_MEMBERS, null, values);
        db.close();
    }

    //method for updating a member
    public void updateMember(Member member) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, member.getName());
        values.put(COLUMN_IMAGE_URL, member.getImageUrl());
        values.put(COLUMN_SHORT_DESCRIPTION, member.getShortDescription());
        values.put(COLUMN_DETAILED_DESCRIPTION, member.getDetailedDescription());
        db.update(TABLE_MEMBERS, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(member.getId())});
        db.close();
    }

    //method for deleting a member
    public void deleteMember(Member member) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MEMBERS, COLUMN_ID + " = ?",
                new String[]{String.valueOf(member.getId())});
        db.close();
    }

    //fetching members
    public List<Member> getAllMembers() {
        List<Member> memberList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_MEMBERS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        int idIndex = cursor.getColumnIndex(COLUMN_ID);
        int nameIndex = cursor.getColumnIndex(COLUMN_NAME);
        int imageUrlIndex = cursor.getColumnIndex(COLUMN_IMAGE_URL);
        int shortDescriptionIndex = cursor.getColumnIndex(COLUMN_SHORT_DESCRIPTION);
        int detailedDescriptionIndex = cursor.getColumnIndex(COLUMN_DETAILED_DESCRIPTION);

        // Check if any of the indexes are -1, indicating a column name was not found
        if (idIndex == -1 || nameIndex == -1 || imageUrlIndex == -1 || shortDescriptionIndex == -1 || detailedDescriptionIndex == -1) {
            cursor.close();
            db.close();
            return memberList; // Return empty list or handle error as appropriate
        }

        if (cursor.moveToFirst()) {
            do {
                Member member = new Member();
                member.setId(cursor.getInt(idIndex));
                member.setName(cursor.getString(nameIndex));
                member.setImageUrl(cursor.getString(imageUrlIndex));
                member.setShortDescription(cursor.getString(shortDescriptionIndex));
                member.setDetailedDescription(cursor.getString(detailedDescriptionIndex));
                memberList.add(member);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return memberList;
    }


}

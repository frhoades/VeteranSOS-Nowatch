public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "panic_events.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "events";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Called when database is created
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                             "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                             "latitude REAL, " +
                             "longitude REAL, " +
                             "timestamp INTEGER, " +
                             "sent_flag INTEGER DEFAULT 0)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        // Handle schema changes if needed
    }

    public void insertPanicEvent(double lat, double lon, long time, boolean sent) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("latitude", lat);
        values.put("longitude", lon);
        values.put("timestamp", time);
        values.put("sent_flag", sent ? 1 : 0);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    // Retrieve unsent events
    public Cursor getUnsentEvents() {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE sent_flag = 0";
        return db.rawQuery(query, null);
    }

    public void markEventSent(int id) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("sent_flag", 1);
        db.update(TABLE_NAME, values, "id = ?", new String[]{ String.valueOf(id) });
        db.close();
    }
}

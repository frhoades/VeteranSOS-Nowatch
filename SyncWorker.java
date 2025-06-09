public class SyncWorker extends Worker {
    private DatabaseHelper dbHelper;

    public SyncWorker(@NonNull Context context, @NonNull WorkerParameters params) {
        super(context, params);
        dbHelper = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public Result doWork() {
        Cursor cursor = dbHelper.getUnsentEvents();
        try {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                double lat = cursor.getDouble(cursor.getColumnIndex("latitude"));
                double lon = cursor.getDouble(cursor.getColumnIndex("longitude"));
                long timestamp = cursor.getLong(cursor.getColumnIndex("timestamp"));
                // TODO: Send HTTP request or other upload method
                boolean success = uploadToServer(lat, lon, timestamp);
                if (success) {
                    dbHelper.markEventSent(id);
                } else {
                    return Result.retry();
                }
            }
        } finally {
            cursor.close();
        }
        return Result.success();
    }
    
    private boolean uploadToServer(double lat, double lon, long time) {
        // TODO: Implement network call. Return true on success.
        return true;
    }
}

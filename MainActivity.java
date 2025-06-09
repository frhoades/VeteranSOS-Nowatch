public class MainActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private List<String> emergencyContacts = Arrays.asList("1234567890", "0987654321"); // example

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DatabaseHelper(this);
        // TODO: Check first-run flag and request permissions if needed (CALL_PHONE, LOCATION, PHONE_STATE)
        // If permissions granted, initialize map and button listeners.
        initializeGoogleMap();
        Button panicButton = findViewById(R.id.panicButton);
        panicButton.setOnClickListener(v -> onPanicButtonPressed());
    }

    private void onPanicButtonPressed() {
        // 1. Get current location (latitude, longitude)
        double lat = 0.0, lon = 0.0;
        // TODO: Use FusedLocationProviderClient or GoogleMap to get location.

        long timestamp = System.currentTimeMillis();
        dbHelper.insertPanicEvent(lat, lon, timestamp, false);  // save to SQLite (sent_flag=false)
        
        // 2. Call contacts in sequence
        for (String number : emergencyContacts) {
            initiateCall(number);
            // wait or listen for call state change...
            if (wasCallAnswered()) {
                break;
            }
        }

        // 3. Sync events if online
        if (isNetworkConnected()) {
            startSyncWorker();
        }
    }

    private void initiateCall(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private boolean wasCallAnswered() {
        // TODO: Implement using PhoneStateListener to detect OFFHOOK -> IDLE transition.
        return false;
    }

    private void startSyncWorker() {
        // Example: enqueue a WorkManager job to sync DB.
        // e.g. WorkManager.getInstance(this).enqueue(new OneTimeWorkRequest.Builder(SyncWorker.class).build());
    }

    private void initializeGoogleMap() {
        // TODO: Set up MapFragment and OnMapReadyCallback per Google Maps API:contentReference[oaicite:25]{index=25}.
    }
}

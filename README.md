# VeteranSOS-Nowatch

**Description:** A simple Android app that lets a user trigger a specific “panic” alert by measuring real-time physiological data read from a "Nowatch" wearable monitor and when a panic attack is detected, the app fetches the user’s location, logs an event, and automatically calls a list of emergency contacts in sequence until one answers or give immediate directions to the users saved "Home" location. All events are saved locally in SQLite and synced when internet is available. No personal data is stored (anonymous operation). 

**This Repo is NOT production ready**

**Features:** 
- **One-tap SOS:** Press the panic button to initiate the alert. **This event will be able to be triggered by a signal from the "Nowatch" proprietory application.**
- **Automatic Calls:** Calls each emergency number in turn (uses `Intent.ACTION_CALL`:contentReference[oaicite:26]{index=26}).
- **Location Logging:** Captures GPS location (via Google Maps API:contentReference[oaicite:27]{index=27}) and saves to local DB.
- **Offline Support:** If offline, events queue locally; syncs with server when online using WorkManager:contentReference[oaicite:28]{index=28}.
- **Permission Onboarding:** Requests CALL_PHONE, LOCATION, and PHONE_STATE permissions on first run (then runs silently):contentReference[oaicite:29]{index=29}.

**Setup:** 
1. Clone this repo.  
2. Replace `YOUR_GOOGLE_MAPS_API_KEY` in `AndroidManifest.xml` with your API key.  
3. Build and run on an Android device (supports API 21+).  
4. Grant requested permissions when prompted.  

**Usage:** 
- Tap the red “PANIC” button to start an alert.  
- The app will dial emergency contacts until someone answers, and send event data if connected.  
- In Settings (not yet implemented), users can configure phone numbers.

**License:** No license. This is to help people, please ask if you want to use this in your own project!

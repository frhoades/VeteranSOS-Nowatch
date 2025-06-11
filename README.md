# VeteranSOS-Nowatch

**Description:** A simple Android app that lets a user trigger a specific “panic” alert by measuring real-time physiological data from a "Nowatch" wearable monitor and when a panic attack is detected, the app fetches the user’s location, logs an event, and automatically calls a list of emergency contacts in sequence until one answers or gives immediate directions to the users saved "Home" location. All events are saved locally in SQLite and synced when internet is available. No personal data is stored (anonymous operation). Halliday AI glasses will be used as a HUD for directions and the automatic emergency phone calls. Essential equipment when dealing with a TBI.

**This Repo is NOT production ready**

**Features:** 
- **One-tap SOS: This event will be triggered by a signal from the "Nowatch" proprietory application or manually.**
- **Automatic Calls:** Calls each emergency number in turn (uses `Intent.ACTION_CALL`:contentReference[oaicite:26]{index=26}).
- **Location Logging and automatic Navigation:** Captures GPS location (via Google Maps API:contentReference[oaicite:27]{index=27}) and saves to local DB. 
- **Offline Support:** If offline, events queue locally; syncs with server when online using WorkManager:contentReference[oaicite:28]{index=28}.
- **Permission Onboarding:** Requests CALL_PHONE, LOCATION, and PHONE_STATE permissions on first run (then runs silently):contentReference[oaicite:29]{index=29}.

**Usage:** 
- Tap the red “PANIC” button to start an alert. **Wear the Nowatch**. 
- The app will dial emergency contacts until someone answers, and send event data if connected.  
- In Settings (not yet implemented), users can configure phone numbers.
- **Use the Halliday AI glasses, they are prescription compatible and not goofy looking** 

**License:** No license. This is to **help PTSD/TBI military veterans** specifically, please ask if you want to use this in your own project!

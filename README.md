# Smart Pantry Manager

An Android app (Java) that helps reduce food waste by tracking pantry ingredients and suggesting recipes you can make immediately only when you already have every required ingredient.

## Database Choice: SQLite

This project uses **SQLite** via `SQLiteOpenHelper` because:
- It works fully offline on the device
- It matches the persistent data storage approach covered in Mobile App Development 700
- Pantry and recipe data are local and do not require a network connection
- Full CRUD is straightforward for a single-user pantry app

## Features

- **Pantry management** — add, edit, and delete ingredients (name, quantity, unit, optional expiry date)
- **Suggested Recipes** — strict-matching logic shows only recipes where every ingredient is in the pantry in sufficient quantity
- **Recipe detail** — view full ingredient list and preparation steps
- **Settings** — expiry alerts toggle and default unit preference
- **18 pre-loaded recipes** seeded on first run

## Screens

1. **Pantry List** (main tab) — RecyclerView of pantry items
2. **Add/Edit Ingredient** — form with validation (via Intent)
3. **Suggested Recipes** — filtered recipe list
4. **Recipe Detail** — full recipe view (via Intent)
5. **Settings** — user preferences

## Requirements

- Android Studio (Hedgehog or newer recommended)
- JDK 17
- Android SDK 34
- Min SDK 24 (Android 7.0)

## Setup & Run

1. Open Android Studio
2. Select **File → Open** and choose the `SmartPantryManager` folder
3. Wait for Gradle sync to finish (Android Studio will download dependencies)
4. If prompted, accept the SDK license and install any missing SDK components
5. Create/start an emulator (API 24+) or connect a physical device with USB debugging enabled
6. Click **Run** (green play button) or press `Shift+F10`

## Testing Strict Matching

1. Open the **Pantry** tab and add ingredients for a simple recipe, e.g.:
   - `egg` — 3 — piece
   - `butter` — 1 — tablespoon
   - `salt` — 1 — pinch
2. Go to **Suggested** — "Scrambled Eggs" should appear
3. Delete `salt` from the pantry — the recipe should disappear
4. Close and reopen the app — pantry data should still be there

## Project Structure



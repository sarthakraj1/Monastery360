# Monastery 360 - Android Tourism App

A modern Android tourism app built with Kotlin that showcases monasteries around the world with immersive 3D views and interactive maps.

## Features

- **Home Page**: 3D view placeholder with map integration
- **Monasteries List**: Scrollable cards with beautiful monastery information
- **Info/About**: App information and contact details
- **Modern UI**: Clean, minimal design with smooth transitions
- **Bottom Navigation**: Easy navigation between main sections

## App Structure

### Main Components

1. **MainActivity**: Main activity with bottom navigation
2. **HomeFragment**: 3D view placeholder and map button
3. **MonasteriesFragment**: List of monastery cards
4. **InfoFragment**: App information and contact details

### Data Models

- **Monastery**: Data class representing monastery information
- **MonasteryRepository**: Sample data repository with 6 beautiful monasteries

## Setup Instructions

### Prerequisites

- Android Studio Arctic Fox or later
- Android SDK 24 or higher
- Kotlin 1.9.10 or later

### Installation

1. **Clone or Download** the project files to your local machine
2. **Open Android Studio**
3. **Import Project**: Select "Open an existing Android Studio project"
4. **Navigate** to the project folder and select it
5. **Wait** for Gradle sync to complete
6. **Build** the project (Build → Make Project)

### Running the App

1. **Connect** an Android device or start an emulator
2. **Click** the "Run" button (green play icon) in Android Studio
3. **Select** your target device
4. **Wait** for the app to install and launch

### Building APK

1. **Build** → **Build Bundle(s) / APK(s)** → **Build APK(s)**
2. **Wait** for build to complete
3. **Find** the APK in `app/build/outputs/apk/debug/`

## Project Structure

```
app/
├── src/main/
│   ├── java/com/monastery360/tourism/
│   │   ├── MainActivity.kt
│   │   ├── data/
│   │   │   ├── Monastery.kt
│   │   │   └── MonasteryRepository.kt
│   │   └── ui/
│   │       ├── home/HomeFragment.kt
│   │       ├── monasteries/
│   │       │   ├── MonasteriesFragment.kt
│   │       │   └── MonasteryAdapter.kt
│   │       └── info/InfoFragment.kt
│   ├── res/
│   │   ├── layout/
│   │   ├── drawable/
│   │   ├── values/
│   │   └── anim/
│   └── AndroidManifest.xml
├── build.gradle
└── proguard-rules.pro
```

## Key Features Implemented

### ✅ Home Page
- 3D view placeholder with gradient background
- Map button that opens Google Maps
- Feature highlights section
- Modern card-based design

### ✅ Monasteries Page
- RecyclerView with monastery cards
- Image loading with Glide
- Location icons and descriptions
- Smooth scrolling and animations

### ✅ Info/About Page
- App information and features
- Contact details
- Version information
- Professional layout

### ✅ Navigation
- Bottom navigation bar
- Smooth fragment transitions
- Material Design 3 components

## Customization

### Adding New Monasteries

Edit `MonasteryRepository.kt` to add new monasteries:

```kotlin
Monastery(
    id = 7,
    name = "Your Monastery Name",
    location = "City, Country",
    description = "Description here",
    imageUrl = "https://your-image-url.com/image.jpg",
    latitude = 0.0,
    longitude = 0.0
)
```

### Styling

- Colors: Edit `colors.xml`
- Themes: Edit `themes.xml`
- Layouts: Modify XML files in `res/layout/`

## Dependencies

- **Material Design 3**: Modern UI components
- **Glide**: Image loading and caching
- **Navigation Components**: Fragment navigation
- **RecyclerView**: List management
- **ConstraintLayout**: Flexible layouts

## Future Enhancements

- [ ] Implement actual 3D rendering
- [ ] Add AR functionality
- [ ] Include audio guides
- [ ] Add offline support
- [ ] Implement user favorites
- [ ] Add search functionality

## Troubleshooting

### Common Issues

1. **Gradle Sync Failed**: 
   - Check internet connection
   - Update Android Studio
   - Clean and rebuild project

2. **App Crashes on Launch**:
   - Check device/emulator Android version (min SDK 24)
   - Verify all dependencies are properly added

3. **Images Not Loading**:
   - Check internet connection
   - Verify image URLs are accessible

## Support

For issues or questions:
- Email: support@monastery360.com
- Website: www.monastery360.com

## License

© 2024 Monastery 360. All rights reserved.

---

**Ready to explore sacred places around the world!** 🏛️✨

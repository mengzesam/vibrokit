setSupportActionBar(Toolbar toolbar):
(1)In order to use a Toolbar within the Activity's window content the application must not request
the window feature FEATURE_SUPPORT_ACTION_BAR.
(2)Base class for activities that wish to use some of the newer platform features on older Android
devices. Some of these backported features include:
   Using the action bar, including action items, navigation modes and more with the
   setSupportActionBar(Toolbar) API.
   Built-in switching between light and dark themes by using the Theme.AppCompat.DayNight theme and
   AppCompatDelegate.setDefaultNightMode(int) API.
   Integration with DrawerLayout by using the getDrawerToggleDelegate() API.
   Note that every activity that extends this class has to be themed with Theme.AppCompat
   or a theme that extends that theme.
 (3)app/src/main/AndroidManifest.xml
<application android:theme="@style/AppTheme"
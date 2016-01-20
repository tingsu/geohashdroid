/**
 * GHDConstants.java
 * Copyright (C)2009 Nicholas Killewald
 * 
 * This file is distributed under the terms of the BSD license.
 * The source package should have a LICENSE file at the toplevel.
 */
package net.exclaimindustries.geohashdroid.util;

import java.text.DecimalFormat;

/**
 * The <code>GHDConstants</code> class doesn't do anything directly.  All it
 * does is serve as a place to store project-wide statics.
 * 
 * @author Nicholas Killewald
 */
public final class GHDConstants {
    /**
     * What was once the preferences base.  Now it shouldn't be used except in
     * cases where we need to convert the user's old preferences into the new
     * default SharedPreferences object.
     */
    public static final String PREFS_BASE = "GeohashDroid";

    /**
     * The Intent action used to start the radar.  That's... a thing people
     * still use, right?
     */
    public static final String SHOW_RADAR_ACTION = "com.google.android.radar.SHOW_RADAR";
    
    /** Dummy Graticule that uses the 30W rule (51N, 0W). */
    public static final Graticule DUMMY_YESTERDAY = new Graticule(51, false, 0, true);
    /** Dummy Graticule that doesn't use the 30W rule (38N, 84W). */
    public static final Graticule DUMMY_TODAY = new Graticule(38, false, 84, true);

    /** Prefs key specifying coordinate units. */
    public static final String PREF_COORD_UNITS = "CoordUnits";
    /** Prefs key specifying distance units. */
    public static final String PREF_DIST_UNITS = "Units";
    /** Prefs key specifying whether or not to auto-zoom on reloads. */
    public static final String PREF_AUTOZOOM = "AutoZoom";
    /** Prefs key specifying info box visibility. */
    public static final String PREF_INFOBOX = "InfoBox";
    /** Prefs key specifying stock cache size. */
    public static final String PREF_STOCK_CACHE_SIZE = "StockCacheSize";
    /** Prefs key specifying to show nearby meetup points. */
    public static final String PREF_NEARBY_POINTS = "NearbyPoints";
    /** Prefs key specifying wiki user name. */
    public static final String PREF_WIKI_USER = "WikiUserName";
    /** Prefs key specifying wiki user pass. */
    public static final String PREF_WIKI_PASS = "WikiPassword";
    /** Prefs key specifying how the app starts up. */
    public static final String PREF_STARTUP_BEHAVIOR = "StartupBehavior";
    /**
     * Prefs key indicating the most recent version the user's seen in the
     * VersionHistoryDialog activity.  If this is lower than the current one
     * when CentralMap starts, it will throw up the version history, which in
     * turn updates this.
     */
    public static final String PREF_LAST_SEEN_VERSION = "LastSeenVersion";
    /**
     * Prefs key for the last-used latitude in a graticule.  Remember, this is a
     * STRING, not an INT, as it can be negative zero.
     */
    public static final String PREF_DEFAULT_GRATICULE_LATITUDE = "LastLatitude";
    /**
     * Prefs key for the last-used longitude in a graticule.  Remember, this is
     * a STRING, not an INT, as it can be negative zero.
     */
    public static final String PREF_DEFAULT_GRATICULE_LONGITUDE = "LastLongitude";
    /**
     * Prefs key for whether or not the last-used graticule was, in fact, a
     * globalhash and not a graticule at all.  Check this first.  If this is
     * true, it overrides anything in the other defaults.
     */
    public static final String PREF_DEFAULT_GRATICULE_GLOBALHASH = "LastGlobalhash";

    /**
     * Prefs key specifying if the background StockAlarm should be used.  Yes,
     * the name's from an older time when AlarmService was called StockService.
     */
    public static final String PREF_STOCK_ALARM = "UseStockService";

    /** Prefs value for metric distances. */
    public static final String PREFVAL_DIST_METRIC = "Metric";
    /** Prefs value for not-metric distances. */
    public static final String PREFVAL_DIST_IMPERIAL = "Imperial";
    
    /** Prefs value for coordinates in degrees. */
    public static final String PREFVAL_COORD_DEGREES = "Degrees";
    /** Prefs value for coordinates in minutes. */
    public static final String PREFVAL_COORD_MINUTES = "Minutes";
    /** Prefs value for coordinates in minutes and seconds. */
    public static final String PREFVAL_COORD_SECONDS = "Seconds";

    /** Prefs value to start with the graticule with the closest hashpoint. */
    public static final String PREFVAL_STARTUP_CLOSEST = "UseClosest";
    /**
     * Prefs value to start with the most recently-used graticule (defaults to
     * UseClosest behavior if there is none set).
     */
    public static final String PREFVAL_STARTUP_LAST_USED = "LastUsed";
    /** Prefs value to start with the graticule picker. */
    public static final String PREFVAL_STARTUP_PICKER = "GraticulePicker";
    
    /** Threshold for the "Accuracy Low" warning (currently 64m). **/
    public static final int LOW_ACCURACY_THRESHOLD = 64;
    /** Threshold for the "Accuracy Really Low" warning (currently 200m). **/
    public static final int REALLY_LOW_ACCURACY_THRESHOLD = 200;

    /** The decimal format for most distances. */
    public static final DecimalFormat DIST_FORMAT = new DecimalFormat("###.######");
    /** The decimal format for most accuracy readouts. */
    public static final DecimalFormat ACCURACY_FORMAT = new DecimalFormat("###.##");
}

package creationalpatterns;

// source code from java core java.util.ResourceBundle getBundle()
//(recognizeable by creational methods returning an implementation of an abstract/interface type)
public class Factory {
    public static Calendar getInstance() {
        Locale aLocale = Locale.getDefault(Locale.Category.FORMAT);
        return createCalendar(defaultTimeZone(aLocale), aLocale);
    }

    private static Calendar createCalendar(TimeZone zone, Locale aLocale) {
        CalendarProvider provider = LocaleProviderAdapter.getAdapter(CalendarProvider.class, aLocale)
                .getCalendarProvider();
        if (provider != null) {
            try {
                return provider.getInstance(zone, aLocale);
            } catch (IllegalArgumentException iae) {
// fall back to the default instantiation
            }
        }

        Calendar cal = null;

        if (aLocale.hasExtensions()) {
            String caltype = aLocale.getUnicodeLocaleType("ca");
            if (caltype != null) {
                cal = switch (caltype) {
                case "buddhist" -> new BuddhistCalendar(zone, aLocale);
                case "japanese" -> new JapaneseImperialCalendar(zone, aLocale);
                case "gregory" -> new GregorianCalendar(zone, aLocale);
                default -> null;
                };
            }
        }
        if (cal == null) {
// If no known calendar type is explicitly specified,
// perform the traditional way to create a Calendar:
// create a BuddhistCalendar for th_TH locale,
// a JapaneseImperialCalendar for ja_JP_JP locale, or
// a GregorianCalendar for any other locales.
// NOTE: The language, country and variant strings are interned.
            if (aLocale.getLanguage() == "th" && aLocale.getCountry() == "TH") {
                cal = new BuddhistCalendar(zone, aLocale);
            } else if (aLocale.getVariant() == "JP" && aLocale.getLanguage() == "ja" && aLocale.getCountry() == "JP") {
                cal = new JapaneseImperialCalendar(zone, aLocale);
            } else {
                cal = new GregorianCalendar(zone, aLocale);
            }
        }
        return cal;
    }
}

package com.reba.api.person.help;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CountryList {

    private static List<String> countryNames;

    public static List<String> getCountryNames() {

        if (countryNames == null) {
            String[] locales = Locale.getISOCountries();
            List<String> countries = new ArrayList<>();

            for (String countryCode : locales) {
                Locale obj = new Locale("", countryCode);
                countries.add(obj.getDisplayCountry());
            }

            countryNames = countries;
        }

        return countryNames;
    }
}

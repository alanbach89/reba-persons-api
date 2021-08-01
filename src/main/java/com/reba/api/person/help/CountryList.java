package com.reba.api.person.help;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CountryList {

    public static List<String> getCountryNames() {

        String[] locales = Locale.getISOCountries();
        List<String> countries = new ArrayList<>();

        for (String countryCode : locales) {
            Locale obj = new Locale("", countryCode);
            countries.add(obj.getDisplayCountry());
        }

        return countries;
    }
}

package com.metasoft.claim.util;

import java.util.Comparator;

import org.joda.time.LocalDate;

public class LocalDateComparator implements Comparator<LocalDate> {

    public int compare(LocalDate d1, LocalDate d2) {
        return d1.compareTo(d2);
    }
}

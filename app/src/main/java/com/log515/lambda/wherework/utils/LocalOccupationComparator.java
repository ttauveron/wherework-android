package com.log515.lambda.wherework.utils;

import android.util.Log;

import com.log515.lambda.wherework.model.LocalOccupation;

import org.joda.time.DateTime;

import java.util.Comparator;

/**
 * Created by ttauveron on 12/11/17.
 */

public class LocalOccupationComparator implements Comparator<LocalOccupation> {
    @Override
    public int compare(LocalOccupation l1, LocalOccupation l2) {

        DateTime dateTime = new DateTime();
        int today = dateTime.dayOfWeek().get();

        int jour1 = l1.getDayOfWeek();
        int jour2 = l2.getDayOfWeek();

        int jour1Mod = ((jour1 - today) % 7 + 7) % 7;
        int jour2Mod = ((jour2 - today) % 7 + 7) % 7;

        if (jour1Mod < jour2Mod) return -1;
        if (jour1Mod > jour2Mod) return 1;

        int sumAvail1 = 0, sumAvail2 = 0;
        sumAvail1 += (l1.isMorning() ? 0 : 1) + (l1.isAfternoon() ? 0 : 1) + (l1.isEvening() ? 0 : 1);
        sumAvail2 += (l2.isMorning() ? 0 : 1) + (l2.isAfternoon() ? 0 : 1) + (l2.isEvening() ? 0 : 1);

        if (sumAvail1 < sumAvail2) return 1;
        if (sumAvail1 > sumAvail2) return -1;

        return 0;
    }
}

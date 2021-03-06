package da345af1.ai2530.mah.se.p1_stivan;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;


public class DateConverter {
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        if (date == null) {
            return null;
        } else {
            return date.getTime();
        }
    }

}

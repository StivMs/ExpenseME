package da345af1.ai2530.mah.se.p1_stivan;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

@Database(entities = {Income.class}, version = 2)
@TypeConverters({DateConverter.class})

public abstract class IncomesDatabase extends RoomDatabase {
    public abstract IncomeDAO incomeDao();
}
package da345af1.ai2530.mah.se.p1_stivan;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.Date;
import java.util.List;

@Dao
public interface OutcomeDAO {

    @Query(" SELECT * FROM outcomes")
    List<Outcomes> getAllOutcomes();

    @Insert
    void insertAll(Outcomes... outcomes);

    @Insert
    void addOutcome(Outcomes outcome);

    // Query här för att hantera datum?
    @Query("SELECT * FROM outcomes WHERE outcome_date BETWEEN :startDate AND :endDate")
    List<Outcomes> loadAllOutcomeFromDate(Date startDate, Date endDate);

}

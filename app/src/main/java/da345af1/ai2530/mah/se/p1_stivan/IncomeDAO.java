package da345af1.ai2530.mah.se.p1_stivan;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.Date;
import java.util.List;

@Dao
public interface IncomeDAO {


    @Query(" SELECT * FROM income")
    List<Income> getAllIncomes();

    @Insert
    void insertAll(Income... income);

    @Insert
    void addIncome(Income income);

    @Query("SELECT * FROM income WHERE income_date BETWEEN :startDate AND :endDate")
    List<Income> loadAllIncomeFromDate(Date startDate, Date endDate);

}

package da345af1.ai2530.mah.se.p1_stivan;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;

@Entity(tableName = "income")
public class Income {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "income_category")
    private String category;

    @ColumnInfo(name = "income_type")
    private String type;

    @ColumnInfo(name = "income_amount")
    private Double amount;

    @ColumnInfo(name = "income_date")
    @TypeConverters({DateConverter.class})
    private Date date;

    public Income(String category, String type, Double amount, Date date) {
        this.category = category;
        this.type = type;
        this.amount = amount;
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

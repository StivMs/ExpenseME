package da345af1.ai2530.mah.se.p1_stivan;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;

@Entity(tableName = "outcomes")
class Outcomes {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "outcome_category")
    private String category;

    @ColumnInfo(name = "outcome_type")
    private String type;

    @ColumnInfo(name = "outcome_amount")
    private double amount;

    @ColumnInfo(name = "outcome_date")
    @TypeConverters({DateConverter.class})
    private Date date;

    @ColumnInfo(name = "outcome_img")
    private int image;

    public Outcomes(String category, String type, double amount, Date date, int image) {
        this.category = category;
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.image = image;
    }

    public String getCategory() {
        return category;
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

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}

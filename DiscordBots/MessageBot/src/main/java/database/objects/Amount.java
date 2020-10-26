package database.objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "amount", schema = "guild")
public class Amount {
    @Id
    private int amount;

    protected Amount() {
    }

    public Amount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    protected void setAmount(int amount){
        this.amount = amount;
    }

    @Override
    public String toString(){
        return amount + "";
    }
}

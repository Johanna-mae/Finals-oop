package finalp;

public class LoanTypeItem {
    private int loanTypeID;
    private String loanTypeName;

    public LoanTypeItem(int loanTypeID, String loanTypeName){
        this.loanTypeID = loanTypeID;
        this.loanTypeName = loanTypeName;
    }

    public int getloanTypeID(){
        return loanTypeID;
    }

    public String getLoanTypeName() {
        return loanTypeName;
    }

    @Override
    public String toString() {
        return loanTypeName;
    }
}

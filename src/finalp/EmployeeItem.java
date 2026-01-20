package finalp;

public class EmployeeItem {
    private int employeeID;
    private String fullName;
    
    public EmployeeItem(int employeeID, String fullName){
        this.employeeID = employeeID;
        this.fullName = fullName;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public String toString() {
        return fullName;
    }
}

package finalp;

public class ClientItem {
    private int clientID;
    private String fullName;

    public ClientItem(int clientID, String fullName){
        this.clientID = clientID;
        this.fullName = fullName;
    }

    public int getClientID() {
        return clientID;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public String toString(){
        return fullName;
    }
}

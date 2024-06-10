package orders;

import javafx.beans.property.StringProperty;

public class Orders {
    public StringProperty ID;
    
    public String getID() {
        return ID.get();
    }
    public StringProperty IDProperty() {
        return ID;
    }
    public void setID(String ID) {
        this.ID.set(ID);
    }
}

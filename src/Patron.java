public class Patron {
    public String id; //7 DIGIT ID
    public String name; //FIRST AND LAST NAME
    public String address; //ADDRESS
    public double fineAmount; //FINE AMOUNT

    public Patron(String id, String name, String address, double fineAmount) { //object created for patron
        this.id = id;
        this.name = name;
        this.address = address;
        this.fineAmount = fineAmount;
    }

    //Object Getter for id
    public String getId() {
        return id;
    }

    //toString method
    @Override
    public String toString() {
        return "ID:" + id + "\nName:" + name +  "\nAddress:" + address + "\nFine:$" + String.format("%.2f", fineAmount);
    }

    //Object getter for name
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
    public double getFineAmount() {
        return fineAmount;
    }
}

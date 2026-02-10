public class Patron {
    public String id; //String for name
    public String name; //String for ID

    public Patron(String id, String name) { //object created for patron
        this.id = id;
        this.name = name;
    }

    //Object Getter for id
    public String getId() {
        return id;
    }

    //toString method
    @Override
    public String toString() {
        return "ID: " + id + "\nName: " + name;
    }

    //Object getter for name
    public String getName() {
        return name;
    }
}

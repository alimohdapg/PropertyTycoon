public abstract class BoardSpace
{
    private String name;
    private int location;

    public BoardSpace(String name, int location)
    {
        this.name = name;
        this.location = location;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

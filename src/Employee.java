public class Employee implements IEmployee{
    private String name;
    private String affil;

    public Employee(String name, String affil){
        this.name = name;
        this.affil = affil;
    }

    public String getName(){
        return this.name;
    }

    public String getAffiliation(){
        return this.affil;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setAffil(String affil){
        this.affil = affil;
    }

    public String toString(){
        return name + ", " + affil;
    }
}

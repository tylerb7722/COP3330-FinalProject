abstract class Employee extends Person {
    private String department;

    public Employee(String name, String id, String department) {
        super(name, id);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    // Override toString()
    @Override
    public String toString(){
        return super.toString();
    }
}
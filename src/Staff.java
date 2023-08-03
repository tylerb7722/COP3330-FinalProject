class Staff extends Employee {
    private String status;

    public Staff(String name, String id, String department, String status) {
        super(name, id, department);
        this.status = status;
        if (status.equals("F")){
            status = "Full Time";
        }
        else{
            status = "Part Time";
        }
    }

    public String getStatus() {
        return status;
    }

    @Override
    public void print(){
        System.out.println("\n---------------------------------------------------------------------------\n");
        System.out.println(getName() + "\t" + getId());
        System.out.println("\n" + getDepartment() + " Department, " + status);
        System.out.println("\n----------------------------------------------------------------------------\n");
    
    }

    // Override toString()
    @Override
    public String toString(){
        if (status.equals("F")){
            status = "Full Time";
        }
        else{
            status = "Part Time";
        }
        return super.toString() + "\n\t" + getDepartment() + ", " + status;
    }
}

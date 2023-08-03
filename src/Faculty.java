class Faculty extends Employee {
    private String rank;

    public Faculty(String name, String id, String rank, String department) {
        super(name, id, department);
        this.rank = rank;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    // Abstract print() method override
    @Override
    public void print(){
        System.out.println("\n---------------------------------------------------------------------------\n");
        System.out.println(getName() + "\t" + getId());
        System.out.println("\n" + getDepartment() + " Department, " + getRank());
        System.out.println("\n----------------------------------------------------------------------------\n");
    }

    // Override toString()
    @Override
    public String toString() {
        return super.toString() + "\n\t" + rank + ", " + getDepartment();
    }
}
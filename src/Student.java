public class Student extends Person {
    private double gpa;
    private int creditHours;

    public Student(String name, String id, double gpa, int creditHours) {
        super(name, id);
        this.gpa = gpa;
        this.creditHours = creditHours;
    }

    public double getGpa() {
        return gpa;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public double calculateTuition() {
        return creditHours * 236.45 + 52;
    }

    public double calculateDiscount() {
        if (gpa >= 3.85) {
            return calculateTuition() * 0.25;
        }
        return 0;
    }

    @Override
    public void print(){
        System.out.println("\n---------------------------------------------------------------------------\n");
        System.out.println(getName() + "\t\t" + getId());
        System.out.println("\nCredit Hours: " + getCreditHours() + " ($236.45/credit hour)");
        System.out.println("\nFees: $52\n");
        double totalPayment = calculateTuition();
        double discount = calculateDiscount();
        System.out.printf("\nTotal payment (after discount): $");
        System.out.printf("%.2f",totalPayment);
        System.out.print("\t($" + discount + " discount applied)\n");
        System.out.println("\n----------------------------------------------------------------------------\n");
    }

    @Override
    public String toString() {
        return super.toString() + "\n\tGPA:" + gpa + "\n\tCredit hours: " + creditHours + "\n";
    }
}
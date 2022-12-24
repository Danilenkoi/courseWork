public class Employee {
    private double salary;
    private String name;
    private int department;
    private int id;
    public static int counter = 0;

    public Employee(String name, double salary, int department) {
        this.name = name;
        this.salary = salary;
        this.department = department;
        this.id = counter ++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }
}

public class Employee {
    private double salary;
    private String name;
    private int department;
    public int id;

    public Employee(String name, double salary, int department, int id) {
        this.name = name;
        this.salary = salary;
        this.department = department;
        this.id = id;
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

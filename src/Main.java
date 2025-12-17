import java.util.Arrays;

class Employee {
    private static int nextId = 1;
    private int id;
    private String name;
    private int department;
    private double salary;

    public Employee(String name, int department, double salary) {
        this.id = nextId++;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getDepartment() { return department; }
    public double getSalary() { return salary; }

    public void setDepartment(int department) {
        this.department = department;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Employee) {
            return this.salary == ((Employee)obj).salary;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("ID:%d %s, отдел:%d, зарплата:%.2f",
                id, name, department, salary);
    }

    public void printShortInfo() {
        System.out.printf("%s: %.2f\n", name, salary);
    }
}

class EmployeeBook {
    private Employee[] employees;
    private int size;

    public EmployeeBook() {
        employees = new Employee[10];
        size = 0;
    }
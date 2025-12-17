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
    // 1. Все сотрудники
    public void printAll() {
        System.out.println("Все сотрудники:");
        for (Employee emp : employees) {
            if (emp != null) {
                System.out.println(emp);
            }
        }
    }

    // 2. Средняя зарплата
    public double averageSalary() {
        if (size == 0) return 0;
        double sum = 0;
        for (Employee emp : employees) {
            if (emp != null) sum += emp.getSalary();
        }
        return sum / size;
    }

    // 3. Налоги
    public void calculateTaxes(String type) {
        System.out.println("Налоги (" + type + "):");
        for (Employee emp : employees) {
            if (emp != null) {
                double tax = 0;
                double salary = emp.getSalary();

                switch (type) {
                    case "PROPORTIONAL":
                        tax = salary * 0.13;
                        break;
                    case "PROGRESSIVE":
                        if (salary <= 150) tax = salary * 0.13;
                        else if (salary <= 350) tax = salary * 0.17;
                        else tax = salary * 0.21;
                        break;
                }
                System.out.printf("%s - налог: %.2f\n", emp.getName(), tax);
            }
        }
    }

    // 4. Индексация отдела
    public void indexSalary(int department, double percent) {
        for (Employee emp : employees) {
            if (emp == null) continue;
            if (emp.getDepartment() == department) {
                double newSalary = emp.getSalary() * (1 + percent/100);
                emp.setSalary(newSalary);
            }
        }
        System.out.println("Зарплаты отдела " + department + " проиндексированы");
    }

    // 5. Найти первого с зарплатой выше
    public void findFirstInDept(int department, double minSalary) {
        for (int i = 0; i < employees.length; i++) {
            Employee emp = employees[i];
            if (emp != null && emp.getDepartment() == department
                    && emp.getSalary() > minSalary) {
                System.out.print("Найден: ");
                emp.printShortInfo();
                return;
            }
        }
        System.out.println("Не найден");
    }

    // 6. Найти с зарплатой ниже
    public void findWithLowerSalary(double maxSalary, int count) {
        System.out.println("С зарплатой ниже " + maxSalary + ":");
        int found = 0;
        for (Employee emp : employees) {
            if (emp != null && emp.getSalary() < maxSalary) {
                emp.printShortInfo();
                found++;
                if (found == count) break;
            }
        }
    }

    // 7. Проверить наличие
    public boolean contains(Employee other) {
        for (Employee emp : employees) {
            if (emp != null && emp.equals(other)) {
                return true;
            }
        }
        return false;
    }

    // 8. Добавить сотрудника
    public boolean add(Employee emp) {
        if (size < employees.length) {
            employees[size] = emp;
            size++;
            return true;
        }
        return false;
    }

    // 9. Найти по ID
    public Employee getById(int id) {
        for (Employee emp : employees) {
            if (emp != null && emp.getId() == id) {
                return emp;
            }
        }
        return null;
    }
}
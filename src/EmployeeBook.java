import java.text.DecimalFormat;
import java.util.ArrayList;

public class EmployeeBook {
    private ArrayList<Employee> theBook = new ArrayList<>();
    // Счетчик id.
    private int id = 0;
    // количество департаментов.
    private int departmentsAmount;
    // Определение формата для денег.
    private DecimalFormat moneyFormat = new DecimalFormat("###,###.##");

    public EmployeeBook(int departmentsAmount) {
        this.departmentsAmount = departmentsAmount;
    }
    // Сумма ЗП: выводит результат работы метода summingSalary (среди всех или отдела).
    public void sumSalary(int department) {
        checkDepartment(department, 0);
        String filler = department == 0 ? "all employees" : department + " department";
        System.out.printf("Salary sum of %s is: %s\n", filler, moneyFormat.format(summingSalary(department)));
    }
    // Средняя ЗП (среди всех или отдела).
    public void findAverageSalary(int department) {
        checkDepartment(department, 0);
        int peoplesAmount = 0;
        for (int i = 0; i < theBook.size(); i++) {
            if (theBook.get(i).getDepartment() == department || department == 0) {
                peoplesAmount++;
            }
        }
        String filler = department == 0 ? "all employees" : department + " department";
        System.out.printf("Average salary of %s is: %s\n", filler, moneyFormat.format(summingSalary(department) / peoplesAmount));
    }
    // Ищет самую низкую ЗП (среди всех или отдела).
    public void findSmallestSalary(int department) {
        checkDepartment(department, 0);
        double minimal = -1;
        for (int i = 0; i < theBook.size(); i++) {
            if (theBook.get(i).getDepartment() == department || department == 0) {
                if (minimal > theBook.get(i).getSalary() || minimal == -1)
                    minimal = theBook.get(i).getSalary();
            }
        }
        if (minimal == -1) {
            System.out.printf("Department %s is empty, smallest salary can't be found there.\n", department);
            return;
        }
        String filler = department == 0 ? "overall" : "in department " + department;
        System.out.printf("The smallest salary %s is: %s\n", filler, moneyFormat.format(minimal));
    }
    // Ищет самую большую ЗП (среди всех или отдела).
    public void findBiggestSalary(int department) {
        checkDepartment(department, 0);
        double max = -1;
        for (int i = 0; i < theBook.size(); i++) {
            if (theBook.get(i).getDepartment() == department || department == 0) {
                if (max < theBook.get(i).getSalary()) {
                    max = theBook.get(i).getSalary();
                }
            }
        }
        if (max == -1) {
            System.out.printf("Department %s is empty, biggest salary can't be found there.\n", department);
            return;
        }
        String filler = department == 0 ? "overall" : "in department " + department;
        System.out.printf("The biggest salary %s is: %s\n", filler, moneyFormat.format(max));
    }
    // Список данных сотрудников (всех или отдела).
    public void printEmployeesDataList(int department) {
        checkDepartment(department, 0);
        System.out.println(department == 0 ? "All employees data:" : department + " department employees data:");
        for (int i = 0; i < theBook.size(); i++) {
            if (theBook.get(i).getDepartment() == department || department == 0) {
                String filler = department != 0 ? "" : " department: " + theBook.get(i).getDepartment() + ",";
                System.out.printf("  id: %s,%s %s, salary: %s\n", theBook.get(i).getId(), filler, theBook.get(i).getName(), moneyFormat.format(theBook.get(i).getSalary()));
            }
        }
    }
    // Вывод имен сотрудников (всех или отдела).
    public void printEmployeesNamesList(int department) {
        checkDepartment(department, 0);
        System.out.println(department == 0 ? "All employees names list:" : department + " department employees names list:");
        for (int i = 0; i < theBook.size(); i++) {
            if (theBook.get(i).getDepartment() == department || department == 0) {
                System.out.println("  " + theBook.get(i).getName());
            }
        }
    }
    // Индексация ЗП (всех или отдела).
    public void indexingSalary(int department, double percent) {
        checkDepartment(department, 0);
        for (int i = 0; i < theBook.size(); i++) {
            if (theBook.get(i).getDepartment() == department || department == 0) {
                theBook.get(i).setSalary(theBook.get(i).getSalary() + theBook.get(i).getSalary() * percent / 100);
            }
        }
        String filler = department == 0 ? "to all employees" : "to department " + department;
        System.out.println("Indexation applied " + filler);
    }
    // Выводит всех, у кого ЗП меньше границы.
    public void printListWithSalaryBelow(double salaryBorder) {
        checkNegative(salaryBorder);
        System.out.printf("These employees have salary below %s:\n", moneyFormat.format(salaryBorder));
        for (int i = 0, switcher = 1; i < theBook.size(); i++) {
            if (theBook.get(i).getSalary() < salaryBorder) {
                System.out.printf("  id: %s, %s, salary: %s\n", theBook.get(i).getId(), theBook.get(i).getName(), moneyFormat.format(theBook.get(i).getSalary()));
                switcher = 0;
            } else if (i == (theBook.size() - 1) * switcher) {
                System.out.printf("No one has salary smaller than %s\n", moneyFormat.format(salaryBorder));
            }
        }
    }
    // Выводит всех, у кого ЗП выше границы.
    public void printListWithSalaryAbove(double salaryBorder) {
        checkNegative(salaryBorder);
        System.out.printf("These employees have salary above %s:\n", moneyFormat.format(salaryBorder));
        for (int i = 0, switcher = 1; i < theBook.size(); i++) {
            if (theBook.get(i).getSalary() >= salaryBorder) {
                System.out.printf("  id: %s, %s, salary: %s\n", theBook.get(i).getId(), theBook.get(i).getName(), moneyFormat.format(theBook.get(i).getSalary()));
                switcher = 0;
            } else if (i == (theBook.size() - 1) * switcher) {
                System.out.printf("No one has salary bigger than %s\n", moneyFormat.format(salaryBorder));
            }
        }
    }
    // Добавляет сотрудника.
    public void addNewEmployee(String name, double salary, int department) {
        checkName(name);
        checkNegative(salary);
        checkDepartment(department, 1);
        Employee newEmployee = new Employee(name, salary, department, id);
        theBook.add(newEmployee);
        System.out.printf("The new employee %s was added\n", name);
        id++;
    }
    // Убирает сотрудника через имя.
    public void removeEmployee(String name) {
        for (int i = 0; i < theBook.size(); i++) {
            if (name.equals(theBook.get(i).getName())) {
                theBook.remove(i);
                System.out.println("Employee " + name + " has been deleted");
                return;
            }
        }
        searchFail("name");
    }
    // Убирает сотрудника через id.
    public void removeEmployee(int id) {
        checkNegative(id);
        for (int i = 0; i < theBook.size(); i++) {
            if (id == theBook.get(i).getId()) {
                System.out.printf("Employee %s has been deleted\n", theBook.get(i).getName());
                theBook.remove(i);
                return;
            }
        }
        searchFail("id");
    }
    // Меняет ЗП по имени.
    public void changeSalary(String name, double newSalary) {
        checkName(name);
        checkNegative(newSalary);
        for (int i = 0; i < theBook.size(); i++) {
            if (name.equals(theBook.get(i).getName())) {
                theBook.get(i).setSalary(newSalary);
                System.out.printf("New salary for %s has been set: %s\n", name, moneyFormat.format(newSalary));
                return;
            }
        }
        searchFail("name");
    }
    // Меняет отдел по имени.
    public void changeDepartment(String name, int newDepartment) {
        checkName(name);
        checkDepartment(newDepartment, 1);
        for (int i = 0; i < theBook.size(); i++) {
            if (name.equals(theBook.get(i).getName())) {
                theBook.get(i).setDepartment(newDepartment);
                System.out.printf("New department for %s is now %s\n", name, newDepartment);
            }
        }
        searchFail("name");
    }

    public void modifyDepartments(int newDepartmentsAmount) {
        checkNegative(newDepartmentsAmount);
        if (newDepartmentsAmount < departmentsAmount) {
            for (Employee employee : theBook) {
                if (employee.getDepartment() > newDepartmentsAmount) {
                    throw new IllegalArgumentException("Departments being reduced are not empty! Before changing departments amount to "
                            + newDepartmentsAmount + " remove employees from departments " + (newDepartmentsAmount + 1) + "..." + (departmentsAmount));
                }
            }
        }
        departmentsAmount = newDepartmentsAmount;
        System.out.println("New amount of departments is: " + departmentsAmount);
    }
    // считает сумму ЗП (всех или отдела).
    private double summingSalary(int department) {
        checkDepartment(department, 0);
        double salarySumCurrent = 0;
        for (int i = 0; i < theBook.size(); i++) {
            if (theBook.get(i).getDepartment() == department || department == 0) {
                salarySumCurrent += theBook.get(i).getSalary();
            }
        }
        return salarySumCurrent;
    }

    // Проверяет значение департамента и не позволяет установить его ниже допустимого.
    private void checkDepartment(int department, int minDepartmentValue) {
        checkNegative(department);
        if (department > departmentsAmount) {
            throw new IllegalArgumentException("Department cant be bigger than " + departmentsAmount);
        } else if (department < minDepartmentValue) {
            throw new IllegalArgumentException("Department cant be smaller than " + minDepartmentValue + ". Employee can't have department 0");
        }
    }
    // Проверяет значения для полей, которые должны быть положительными.
    private void checkNegative(double value) {
        if (value < 0) {
            throw new IllegalArgumentException("Negative value! Salary, department and id must be positive");
        }
    }
    // Проверяет ФИО на соответствие формату Фамилиия Имя Отчество.
    private void checkName(String name) {
        if (!name.matches("[А-Я][а-я]+(-?[А-Я][а-я]+)*\\s[А-Я][а-я]+\\s[А-Я][а-я]+")) {
            throw new IllegalArgumentException("Wrong name! Use russian keys with format: Фамилия Имя Отчество");
        }
    }
    // Сообщение об ошибке при остутствии записи в БД.
    private void searchFail(String argument) {
        throw new IllegalArgumentException("This employee does not exist! Make sure that " + argument + " is correct!");
    }
}

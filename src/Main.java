public class Main {
    public static void main(String[] args) {
        EmployeeBook eBook = new EmployeeBook(5);
        eBook.addNewEmployee("Мун Евгений Михайлович", 150000, 4);
        eBook.addNewEmployee("Фетисова Валерия Тимофеевна", 30000, 3);
        eBook.addNewEmployee("Степанов Вячеслав Максимович", 35000, 3);
        eBook.addNewEmployee("Сухарева Карина Матвеевна", 100000, 1);
        eBook.addNewEmployee("Морозова Маргарита Анатольевна", 70000, 2);
        eBook.addNewEmployee("Вешняков Василий Родионович", 65000, 2);
        eBook.addNewEmployee("Лаптев Давид Артемович", 90000, 1);

        eBook.findSmallestSalary(5);
    }
}
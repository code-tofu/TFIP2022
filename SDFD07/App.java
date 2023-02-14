package sdfd07.rev;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class App {
    private App() {
    }

    public static void main(String[] args) {
        if(args[0].equalsIgnoreCase("exit")){
            System.exit(0);
        }

        List<Employee> employeeList = getEmployees();
        System.out.println(employeeList);

        employeeList.sort(Comparator.comparing( e -> e.getFirstName()));
        System.out.println(employeeList);

        System.out.println(employeeList);
        
    
    }
    
    public static List<Employee> getEmployees(){
        List<Employee> employees = new ArrayList<>();
        
        employees.add(new Employee(1, "Cow", "Moo",10));
        employees.add(new Employee(2, "Sheep", "Baa",11));
        employees.add(new Employee(3, "Dog", "Wolf",12));
        employees.add(new Employee(4, "Chicken", "Cluck",13));
        employees.add(new Employee(5, "Pig", "Oink",14));

        return employees;
    }

    
}

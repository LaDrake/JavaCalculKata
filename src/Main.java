import java.util.Scanner;

public class Main {
    private static boolean arabicNumbers = true;
    public static void main(String[] args) {
        Scanner inputValueSC = new Scanner(System.in);
        System.out.print("Введите выражение: ");
        String input = inputValueSC.nextLine();

        while (!input.isEmpty()) {
            arabicNumbers = true;
            String[] parserInput = Main.pars(input);
            String operation = parserInput[1];
            int value1 = 0;
            int value2 = 0;
            Operation values;
            try {
                value1 = Integer.parseInt(parserInput[0]);
                value2 = Integer.parseInt(parserInput[2]);
            } catch (NumberFormatException e) {
                arabicNumbers = false;
                System.out.println("Введены римские цифры");
            }


            if (arabicNumbers) {
                if ((value1 < 1 || value1 > 10) || (value2 < 1 || value2 > 10)){
                    throw new IllegalArgumentException("Введены некорректные данные");
                }
               values = new Arabic(value1, value2);
            } else {

                values = new Rome(parserInput[0], parserInput[2]);
                if (( ((Rome) values).romesValue1Int < 1 || ((Rome) values).romesValue1Int > 10)
                        || (((Rome) values).romesValue2Int< 1 || ((Rome) values).romesValue2Int > 10)){
                    throw new IllegalArgumentException("Введены некорректные данные");
                }
            }



            if (operation.equals("+")) {
               values.sum();
            } else if (operation.equals("-")) {
                values.sub();
            } else if (operation.equals("/") || operation.equals(":")) {
                values.div();
            } else if (operation.equals("*") || operation.equals("x")) {
                values.mul();
            }
            if (arabicNumbers){
                System.out.println("Ответ: " + values.getResult());
            } else {
                System.out.println("Ответ: " + values.getStringResult());
            }
            System.out.println();
            System.out.print("Введите следующее выражение: ");
            input = inputValueSC.nextLine();
        }
    }
    private static String[] pars(String input) {
        String[] parserInput = input.split(" ");
        while (parserInput.length != 3) {
            Scanner inputValue = new Scanner(System.in);
            System.out.println("Неверный формат ввода данных. Введите выражение, разделяя каждый символ пробелом");
            input = inputValue.nextLine();
            parserInput = input.split(" ");
        }
        return parserInput;
    }

}
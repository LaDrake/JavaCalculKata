import java.util.HashMap;
import java.util.Map;

public class Rome extends Operation {
    private String romesValue1;
    private String romesValue2;
    int romesValue1Int;
    int romesValue2Int;
    private int resultInt;
    private String resultString;
    private final String[] romeFigures = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

    Rome(String value1, String value2) {
        this.romesValue1 = value1;
        this.romesValue2 = value2;
        this.romesValue1Int = convert_to_int(romesValue1);
        this.romesValue2Int = convert_to_int(romesValue2);
    }
    private String convert_result_to_Romes(int resultInt) {
        String s = "";
        if (resultInt < 0){
            s="-";
            resultInt *= -1;
        }
        while (resultInt >= 100) {
            s += "C";
            resultInt -= 100;
        }
        while (resultInt >= 90) {
            s += "XC";
            resultInt -= 90;
        }
        while (resultInt >= 50) {
            s += "L";
            resultInt -= 50;
        }
        while (resultInt >= 10) {
            s += "X";
            resultInt -= 10;
        }
        while (resultInt >= 9) {
            s += "IX";
            resultInt -= 9;
        }
        while (resultInt >= 5) {
            s += "V";
            resultInt -= 5;
        }
        while (resultInt >= 4) {
            s += "IV";
            resultInt -= 4;
        }
        while (resultInt >= 1) {
            s += "I";
            resultInt -= 1;
        }
        return s;
    }

    @Override
    public void sum() {
        resultInt = romesValue1Int + romesValue2Int;
        resultString = convert_result_to_Romes(resultInt);
    }

    @Override
    public void sub() {
        resultInt = romesValue1Int - romesValue2Int;
        resultString = convert_result_to_Romes(resultInt);
    }

    @Override
    public void div() {
        try {
            resultInt = romesValue1Int / romesValue2Int;
            resultString = convert_result_to_Romes(resultInt);
        } catch (ArithmeticException e) {
            System.out.print("Проверьте правильность ввода римских цифр. Вероятно введены и арабские и римские одновременно. ");
            return;
        }
    }

    @Override
    public void mul() {
        resultInt = romesValue1Int * romesValue2Int;
        resultString = convert_result_to_Romes(resultInt);
    }

    @Override
    public int getResult() {
        return resultInt;
    }
    public String getStringResult() throws IllegalArgumentException {
        if (resultInt == 0) {
            throw new IllegalArgumentException("0 в римской системе нет");
        }
        return resultString;
    }

    private int convert_to_int(String romes_value) {
        Map<Character, Integer> romanMap = new HashMap<>();
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);

        int result = 0;
        int prevValue = 0;
        for (int i = romes_value.length() - 1; i >= 0; i--) {
            char romanChar = romes_value.charAt(i);
            int currentValue = romanMap.getOrDefault(romanChar, -1);
            if (currentValue == -1) {
                throw new IllegalArgumentException("Содержится неверный символ. Проверьте правильность ввода римских цифр:" + "\n" +
                        "I = 1" + "\n" +
                        "V = 5" + "\n" +
                        "X = 10");
            }

            if (currentValue < prevValue) {
                result -= currentValue;
            } else {
                result += currentValue;
            }
            prevValue = currentValue;
        }

        if (!convert_result_to_Romes(result).equals(romes_value)) {
            throw new IllegalArgumentException("Некорректная римская цифра: " + romes_value);
        }
        return result;
    }

    public String getRomes_value1() {
        return romesValue1;
    }

    public String getRomes_value2() {
        return romesValue2;
    }

    public void setRomes_value1(String romes_value1) {
        this.romesValue1 = romes_value1;
    }

    public void setRomes_value2(String romes_value2) {
        this.romesValue2 = romes_value2;
    }

    public int getRomes_value1_int() {
        return romesValue1Int;
    }

    public int getRomes_value2_int() {
        return romesValue2Int;
    }

    public void setRomes_value1_int(int romes_value1_int) {
        this.romesValue1Int = romes_value1_int;
    }

    public void setRomes_value2_int(int romes_value2_int) {
        this.romesValue2Int = romes_value2_int;
    }
}

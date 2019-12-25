package com.ChenP;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.Arrays;

public class Arith {

    //        Написать класс для работы с длинными числами которые заданы как String,
    //        без использования стандартных библиотечных методов типа BigDecimal.
    //        Реализовать основые операции: +,-,/,*,%, корень квадратный, возведение в степень, округление.
    //        Пример:
    //        String result = LongArithmetic.add("234234234234234234.45234234", "-345345345234234234234.6723423456");


    //valid
    public static int line() {
        //Отладка - Возвращает номер строки в которой метод был вызван
        return new Throwable().getStackTrace()[1].getLineNumber();
    }

    //valid
    public static String numberValidator(String a) {
        /**
         * Только проверка чисел и форматирование
         * Возвращает "NaN" - если не число
         */
        // Если есть запятая заменить на точку
        String num = a;
        if (a.contains(",")) {
//            System.out.println(line() + " " + num);
            num = a.replace(",", ".");
//            System.out.println(line() + " " + num);
        }

        //Перевод формы записи .xxx в 0.ххх
        if (num.contains(".") && (num.indexOf(".") == 0)) {
            num.replace(".", "0.");
        }
        //Перевод формы записи -.xxx в -0.ххх
        if (num.contains(".") && num.contains("-") && (num.indexOf(".") == 1)) {
            num.replace("-.", "-0.");
        }
        String result = num;
        for (int i = 0; i <= 9; i++) {
            result = result.replace(String.valueOf(i), "");
        }
        if (result.isEmpty() || result.equals(".") || result.equals("-") || result.equals("-.")) {
//            System.out.println(line() + "Valid");
            return num;
        }
        System.out.println(line() + "Invalid");
        return "NaN";
    }

    //valid
    public static boolean ifPositive(String a) {
        /**
         * 0 - НЕ ОТРИЦАТЕЛЬНОЕ число
         */
        try {
            if (a.charAt(0) == '-') {
                return false;
            }
            return true;
        } catch (Exception e) {
            System.out.println("ifPositive Error");
            return false;
        }
    }

    //valid
    public static String intArrToString(String ifPositive, int[] arrA, int periodIndex) {
        /**
         * Этот метод добавляет "-" если необходимо вывести отрицательное число
         * Возвращает "" - если произошла ошибка
         * Вставляет точку в указанный индекс
         */
        try {
            StringBuilder sb = new StringBuilder("");

            for (int i = 0; i < arrA.length; i++) {
                //вставка точки
                if (i == periodIndex) {
                    sb.append(".");
                }
                sb.append(arrA[i]);
            }
            // trim
            while (sb.charAt(0) == '0') {
                sb.deleteCharAt(0);
            }
            while (sb.charAt(sb.length() - 1) == '0') {
                sb.deleteCharAt(sb.length() - 1);
            }
            if (sb.charAt(sb.length() - 1) == '.') {
                sb.append("0");
            }
            if (sb.charAt(0) == '.') {
                sb.insert(0, "0");
            }
            if (ifPositive.equals("-")) {
                sb.insert(0, "-");
            }
            if (sb.toString().equals("-0.0")) {
                sb.deleteCharAt(0);
            }
            return sb.toString();
        } catch (Exception e) {
            System.out.println(line() + "intArrToString error");
            return "";
        }
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////


    public static String add(String a, String b) {
        try {
            // Инициализация
            a = numberValidator(a);
            b = numberValidator(b);
            if (numberValidator(a) != "NaN" && numberValidator(b) != "NaN") {
                // Если ++
                if (ifPositive(a) && ifPositive(b)) {
                    Composer pair = new Composer(a, b);
                    int[] fullA = pair.getFullA();
                    int[] fullB = pair.getFullB();
                    Summator sum = new Summator(fullA, fullB, pair.getPeriodIndex(), "+");
                    return sum.result();
                }
                // Если --
                if (!ifPositive(a) && !ifPositive(b)) {
                    Composer pair = new Composer(a, b);
                    int[] fullA = pair.getFullA();
                    int[] fullB = pair.getFullB();
                    Summator sum = new Summator(fullA, fullB, pair.getPeriodIndex(), "-");
                    return sum.result();
                }
                //if +- or -+
                if ((ifPositive(a) && !ifPositive(b)) || (!ifPositive(a) && ifPositive(b))) {
                    Composer pair = new Composer(a, b);
                    int[] fullA = pair.getFullA();
                    int[] fullB = pair.getFullB();
                    Subtracktor diff = new Subtracktor(fullA, fullB, pair.getPeriodIndex());
                    return diff.result();
                }
            }
        } catch (Exception e) {
            return "add Method Error";
        }
        return "add Method Error";
    }

    public static String sub(String a, String b) {
        /**
         * A - B
         */
        try {
            // Инициализация
            a = numberValidator(a);
            b = numberValidator(b);
            if (numberValidator(a) != "NaN" && numberValidator(b) != "NaN") {
                // Если ++
                if (ifPositive(a) && ifPositive(b)) {
                    Composer pair = new Composer(a, b);
                    int[] fullA = pair.getFullA();
                    int[] fullB = pair.getFullB();

                    Subtracktor diff = new Subtracktor(fullA, fullB, pair.getPeriodIndex());
                    return diff.result();
                }
                // Если --
                if (!ifPositive(a) && !ifPositive(b)) {
                    Composer pair = new Composer(a, b);
                    int[] fullA = pair.getFullA();
                    int[] fullB = pair.getFullB();
                    Subtracktor diff = new Subtracktor(fullA, fullB, pair.getPeriodIndex(), "-");
                    return diff.result();
                }
                //if +- or -+
                if ((ifPositive(a) && !ifPositive(b)) || (!ifPositive(a) && ifPositive(b))) {
                    return add(a, b);
                }

            }
        } catch (Exception e) {
            return "sub Method Error";
        }
        return "sub Method Error";
    }


}

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
            System.out.println(line() + " " + num);
            num = a.replace(",", ".");
            System.out.println(line() + " " + num);
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
            System.out.println(line() + "Valid");
            return num;
        }
        System.out.println(line() + "Invalid");
        return "NaN";
    }

    //valid
    private static boolean ifPositive(String a) {
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
    private static boolean compareLengths(String a, String b) {
        /**
         * Если TRUE - a - длинее или равно b, иначе - FALSE
         **/
        if (a.length() - b.length() >= 0) {
            return true;
        }
        return false;
    }

    //valid
    private static String intPart(String a) {
        /**
         * Возвращает "NaN" - если произошла ошибка
         */
        try {
            StringBuilder sb = new StringBuilder("");
            for (int i = 0; i < a.length(); i++) {
                if (a.charAt(i) == '.') {
                    return sb.toString();
                }
                sb.append(a.charAt(i));
            }
            return sb.toString();
        } catch (Exception e) {
            System.out.println(line() + "intPart Error");
            return "NaN";
        }
    }

    //valid
    private static String fractionPart(String a) {
        /**
         * Возвращает "NaN" - если произошла ошибка
         */
        try {
            StringBuilder sb = new StringBuilder("");
            for (int i = a.length() - 1; i >= 0; i--) {
                //При встрече точки
                if (a.charAt(i) == '.') {
                    return sb.reverse().toString();
                }
                sb.append(a.charAt(i));
            }
            //если число не имеет дробной части
            if (sb.toString().length() == a.length()) {
                return "";
            }
        } catch (Exception e) {
            System.out.println(line() + "fractionPart Error");
            return "NaN";
        }
        System.out.println(line() + "fractionPart Error");
        return "NaN";
    }

    //???
    //!!! Этот метод производит удаление "-", любые операции выполняются только с резульататами данного метода !!!
    private static int[] stringToIntArr(String a, int length, String bufferPosition) {
        System.out.println(line() + " stringToIntArr " + a);
        /**
         * a - исходная строка
         * length - длинна исходящего массива
         * bufferPosition - указывает на положение раширительной ячейки в исходящем массиве и направление заполнения
         * Если String передаёт отрицательное число, метод вернёт массив без знака "-"
         */
        try {
            //Если пуста строка
            if (a.equals("")) {
                int[] intA = new int[2];
                System.out.println(line() + Arrays.toString(intA));
                return intA;
            }


            //Если методу передаёться 0 length массив будет равен длинне исходной строки
            if (length == 0) {
                length = a.length();
            }

            StringBuilder sb = new StringBuilder(a);

            //удаление минуса
            if (sb.charAt(0) == '-') {
                sb.deleteCharAt(0);
            }

            char[] arrA = sb.toString().toCharArray();

            //Расширительная ячейка
            length++;
            //Заполнение справа - налево
            if (bufferPosition.equals("Start")) {
                int[] intA = new int[length];
                //
                for (int i = intA.length - 1, j = arrA.length - 1; j >= 0; i--, j--) {
                    intA[i] = Integer.parseInt(String.valueOf(arrA[j]));
                }
                System.out.println(line() + Arrays.toString(intA));
                return intA;
            }
            //Заполнение слева - направо
            if (bufferPosition.equals("End")) {
                int[] intA = new int[length];
                for (int i = 0, j = 0; j < arrA.length; i++, j++) {
                    intA[i] = Integer.parseInt(String.valueOf(arrA[j]));
                }
                System.out.println(line() + Arrays.toString(intA));
                return intA;
            }

        } catch (Exception e) {
            System.out.println("parseToIntArray Error");
            return null;
        }
        return null;
    }

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
            if (ifPositive.equals("-")) {
                return sb.insert(0, "-").toString();
            }
            return sb.toString();
        } catch (Exception e) {
            System.out.println(line() + "intArrToString error");
            return "";
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////

    private static int[] merge(int[] intPart, int[] fracPart) {
        try {
            int[] full = new int[intPart.length + fracPart.length];
            System.arraycopy(intPart, 0, full, 0, intPart.length);
            System.arraycopy(fracPart, 0, full, intPart.length, fracPart.length);
            return full;
        } catch (Exception e) {
            System.out.println(line() + "merge Error");
            return null;
        }
    }

    private static int[] summator(int[] a, int[] b) {
        int[] memory = new int[a.length];
        int[] result = new int[a.length];
        System.out.println(Arrays.toString(memory));
        for (int i = result.length - 1, j = a.length - 1; j >= 0; j--, i--) {
            if (a[j] + b[j] + memory[i] >= 10) {
                result[i] += ((a[j] + b[j] + memory[i]) % 10);
                memory[i - 1]++;
            } else result[i] = a[j] + b[j] + memory[j];
        }
        return result;
    }

    private static int[] subtracktor(int[] a, int[] b) {
        /**
         *   A - B
         */
        int[] memory = new int[a.length];
        int[] result = new int[a.length];
        System.out.println(line() + Arrays.toString(memory));
        System.out.println(line() + Arrays.toString(a));
        System.out.println(line() + Arrays.toString(b));
        for (int i = result.length - 1, j = a.length - 1; j >= 0; j--, i--) {
            if (a[j] + memory[i] - b[j] < 0) {
                memory[i] += 10;
                result[i] = a[j] + memory[i] - b[j];
                memory[i - 1] -= 1;
            } else result[i] = a[j] + memory[j] - b[j];
        }
        System.out.println(line() + Arrays.toString(result));
        return result;
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
                    //  если целая часть а длинее b или равны
                    if (compareLengths(intPart(a), intPart(b))) {
                        int[] intA = stringToIntArr(intPart(a), 0, "Start");
                        int[] intB = stringToIntArr(intPart(b), intA.length - 1, "Start");
                        // если дробная часть a длинее b или равны
                        if (compareLengths(fractionPart(a), fractionPart(b))) {
                            int[] fracA = stringToIntArr(fractionPart(a), 0, "End");
                            int[] fracB = stringToIntArr(fractionPart(b), fracA.length - 1, "End");

                            int[] fullA = merge(intA, fracA);
                            int[] fullB = merge(intB, fracB);
                            /*
                            //Отладка
                            System.out.println(line() + " intA length " + intA.length + "  ");
                            System.out.println(line() + Arrays.toString(intA));
                            System.out.println(line() + " fracA.length " + fracA.length + "  ");
                            System.out.println(line() + Arrays.toString(fracA));
                            System.out.println(line() + " Full A " + Arrays.toString(fullA));
                            System.out.println();
                            System.out.printf(line() + " intB.length " + intB.length + "  ");
                            System.out.println(line() + Arrays.toString(intB));
                            System.out.printf(line() + " fracB.length " + fracB.length + "  ");
                            System.out.println(line() + Arrays.toString(fracB));
                            System.out.println(line() + " Full B " + Arrays.toString(fullB));
                            System.out.println();
*/
                            Summator sum = new Summator(fullA, fullB, intA.length, "+");
//                            int[] result = summator(fullA, fullB);
//                            System.out.println(line() + " result is " + sum.result());
                            return sum.result();
                            /*
                            int periodIndex = intA.length;

                            //вставка точки
                            StringBuilder sb = new StringBuilder(intArrToString("+", result, periodIndex));
                            System.out.println(line() + "result with dot is " + sb.toString());
                            return sb.toString();
*/

                        }
                        // если дробная часть b длинее a
                        else if (!compareLengths(fractionPart(a), fractionPart(b))) {
                            int[] fracA = stringToIntArr(fractionPart(b), 0, "End");
                            int[] fracB = stringToIntArr(fractionPart(a), fracA.length - 1, "End");

                            int[] fullA = merge(intA, fracA);
                            int[] fullB = merge(intB, fracB);

                            Summator sum = new Summator(fullA, fullB, intA.length, "+");
                            return sum.result();

                        }

                    }
                    //  если целая часть b длинее a
                    if (!compareLengths(intPart(a), intPart(b))) {
                        int[] intA = stringToIntArr(intPart(b), 0, "Start");
                        int[] intB = stringToIntArr(intPart(a), intA.length - 1, "Start");
                        // если дробная часть a длинее b или равны
                        if (compareLengths(fractionPart(a), fractionPart(b))) {
                            int[] fracA = stringToIntArr(fractionPart(a), 0, "End");
                            int[] fracB = stringToIntArr(fractionPart(b), fracA.length - 1, "End");

                            int[] fullA = merge(intA, fracA);
                            int[] fullB = merge(intB, fracB);

                            Summator sum = new Summator(fullA, fullB, intA.length, "+");
                            return sum.result();

                        }
                        // если дробная часть b длинее a
                        else if (!compareLengths(fractionPart(a), fractionPart(b))) {
                            int[] fracA = stringToIntArr(fractionPart(b), 0, "End");
                            int[] fracB = stringToIntArr(fractionPart(a), fracA.length - 1, "End");

                            int[] fullA = merge(intA, fracA);
                            int[] fullB = merge(intB, fracB);

                            Summator sum = new Summator(fullA, fullB, intA.length, "+");
                            return sum.result();

                        }

                    }
                }
                // Если --
                if (!ifPositive(a) && !ifPositive(b)) {
                    //  если целая часть а длинее b или равны
                    if (compareLengths(intPart(a), intPart(b))) {
                        int[] intA = stringToIntArr(intPart(a), 0, "Start");
                        int[] intB = stringToIntArr(intPart(b), intA.length - 1, "Start");
                        // если дробная часть a длинее b или равны
                        if (compareLengths(fractionPart(a), fractionPart(b))) {
                            int[] fracA = stringToIntArr(fractionPart(a), 0, "End");
                            int[] fracB = stringToIntArr(fractionPart(b), fracA.length - 1, "End");

                            int[] fullA = merge(intA, fracA);
                            int[] fullB = merge(intB, fracB);

                            Summator sum = new Summator(fullA, fullB, intA.length, "-");
                            return sum.result();

                        }
                        // если дробная часть b длинее a
                        else if (!compareLengths(fractionPart(a), fractionPart(b))) {
                            int[] fracA = stringToIntArr(fractionPart(b), 0, "End");
                            int[] fracB = stringToIntArr(fractionPart(a), fracA.length - 1, "End");

                            int[] fullA = merge(intA, fracA);
                            int[] fullB = merge(intB, fracB);

                            Summator sum = new Summator(fullA, fullB, intA.length, "-");
                            return sum.result();

                        }

                    }
                    //  если целая часть b длинее a
                    if (!compareLengths(intPart(a), intPart(b))) {
                        int[] intA = stringToIntArr(intPart(b), 0, "Start");
                        int[] intB = stringToIntArr(intPart(a), intA.length - 1, "Start");
                        // если дробная часть a длинее b или равны
                        if (compareLengths(fractionPart(a), fractionPart(b))) {
                            int[] fracA = stringToIntArr(fractionPart(a), 0, "End");
                            int[] fracB = stringToIntArr(fractionPart(b), fracA.length - 1, "End");

                            int[] fullA = merge(intA, fracA);
                            int[] fullB = merge(intB, fracB);

                            Summator sum = new Summator(fullA, fullB, intA.length, "-");
                            return sum.result();

                        }
                        // если дробная часть b длинее a
                        else if (!compareLengths(fractionPart(a), fractionPart(b))) {
                            int[] fracA = stringToIntArr(fractionPart(b), 0, "End");
                            int[] fracB = stringToIntArr(fractionPart(a), fracA.length - 1, "End");

                            int[] fullA = merge(intA, fracA);
                            int[] fullB = merge(intB, fracB);

                            Summator sum = new Summator(fullA, fullB, intA.length, "-");
                            return sum.result();

                        }

                    }
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
                    //  если целая часть а длинее b или равны
                    if (compareLengths(intPart(a), intPart(b))) {

                        int[] intA = stringToIntArr(intPart(a), 0, "Start");
                        int[] intB = stringToIntArr(intPart(b), intA.length - 1, "Start");
                        // если дробная часть a длинее b или равны
                        if (compareLengths(fractionPart(a), fractionPart(b))) {
                            int[] fracA = stringToIntArr(fractionPart(a), 0, "End");
                            int[] fracB = stringToIntArr(fractionPart(b), fracA.length - 1, "End");
                            int[] fullA = merge(intA, fracA);
                            int[] fullB = merge(intB, fracB);
                            //Отладка
                            System.out.println(line() + " intA length " + intA.length + "  ");
                            System.out.println(line() + Arrays.toString(intA));
                            System.out.println(line() + " fracA.length " + fracA.length + "  ");
                            System.out.println(line() + Arrays.toString(fracA));
                            System.out.println(line() + " Full A " + Arrays.toString(fullA));
                            System.out.println();
                            System.out.printf(line() + " intB.length " + intB.length + "  ");
                            System.out.println(line() + Arrays.toString(intB));
                            System.out.printf(line() + " fracB.length " + fracB.length + "  ");
                            System.out.println(line() + Arrays.toString(fracB));
                            System.out.println(line() + " Full B " + Arrays.toString(fullB));
                            System.out.println();
                            int[] result = subtracktor(fullA, fullB);
                            System.out.println(line() + " result is " + Arrays.toString(result));
                            int periodIndex = intA.length;

                            //вставка точки
                            StringBuilder sb = new StringBuilder(intArrToString("+", result, periodIndex));
                            System.out.println(line() + "result with dot is " + sb.toString());
                            return sb.toString();

                        }
                        // если дробная часть b длинее a
                        else if (!compareLengths(fractionPart(a), fractionPart(b))) {
                            int[] fracA = stringToIntArr(fractionPart(b), 0, "End");
                            int[] fracB = stringToIntArr(fractionPart(a), fracA.length - 1, "End");

                            int[] fullA = merge(intA, fracA);
                            int[] fullB = merge(intB, fracB);

                            int[] result = subtracktor(fullA, fullB);
                            System.out.println(line() + " result is " + Arrays.toString(result));
                            int periodIndex = intA.length;

                            //вставка точки
                            StringBuilder sb = new StringBuilder(intArrToString("+", result, periodIndex));
                            System.out.println(line() + "result with dot is " + sb.toString());
                            return sb.toString();

                        }

                    }
                    //  если целая часть b длинее a
                    if (!compareLengths(intPart(a), intPart(b))) {
                        int[] intA = stringToIntArr(intPart(b), 0, "Start");
                        int[] intB = stringToIntArr(intPart(a), intA.length - 1, "Start");
                        // если дробная часть a длинее b или равны
                        if (compareLengths(fractionPart(a), fractionPart(b))) {
                            int[] fracA = stringToIntArr(fractionPart(a), 0, "End");
                            int[] fracB = stringToIntArr(fractionPart(b), fracA.length - 1, "End");

                            int[] fullA = merge(intA, fracA);
                            int[] fullB = merge(intB, fracB);

                            int[] result = subtracktor(fullA, fullB);
                            System.out.println(line() + " result is " + Arrays.toString(result));
                            int periodIndex = intA.length;

                            //вставка точки
                            StringBuilder sb = new StringBuilder(intArrToString("+", result, periodIndex));
                            System.out.println(line() + "result with dot is " + sb.toString());
                            return sb.toString();

                        }
                        // если дробная часть b длинее a
                        else if (!compareLengths(fractionPart(a), fractionPart(b))) {
                            int[] fracA = stringToIntArr(fractionPart(b), 0, "End");
                            int[] fracB = stringToIntArr(fractionPart(a), fracA.length - 1, "End");

                            int[] fullA = merge(intA, fracA);
                            int[] fullB = merge(intB, fracB);

                            int[] result = subtracktor(fullA, fullB);
                            System.out.println(line() + " result is " + Arrays.toString(result));
                            int periodIndex = intA.length;

                            //вставка точки
                            StringBuilder sb = new StringBuilder(intArrToString("+", result, periodIndex));
                            System.out.println(line() + "result with dot is " + sb.toString());
                            return sb.toString();

                        }

                    }
                }
                // Если --
                if (!ifPositive(a) && !ifPositive(b)) {
                    //  если целая часть а длинее b или равны
                    if (compareLengths(intPart(a), intPart(b))) {
                        int[] intA = stringToIntArr(intPart(a), 0, "Start");
                        int[] intB = stringToIntArr(intPart(b), intA.length - 1, "Start");
                        // если дробная часть a длинее b или равны
                        if (compareLengths(fractionPart(a), fractionPart(b))) {
                            int[] fracA = stringToIntArr(fractionPart(a), 0, "End");
                            int[] fracB = stringToIntArr(fractionPart(b), fracA.length - 1, "End");

                            int[] fullA = merge(intA, fracA);
                            int[] fullB = merge(intB, fracB);
                            /*
                            //Отладка
                            System.out.println(line() + " intA length " + intA.length + "  ");
                            System.out.println(line() + Arrays.toString(intA));
                            System.out.println(line() + " fracA.length " + fracA.length + "  ");
                            System.out.println(line() + Arrays.toString(fracA));
                            System.out.println(line() + " Full A " + Arrays.toString(fullA));
                            System.out.println();
                            System.out.printf(line() + " intB.length " + intB.length + "  ");
                            System.out.println(line() + Arrays.toString(intB));
                            System.out.printf(line() + " fracB.length " + fracB.length + "  ");
                            System.out.println(line() + Arrays.toString(fracB));
                            System.out.println(line() + " Full B " + Arrays.toString(fullB));
                            System.out.println();
*/
                            int[] result = subtracktor(fullA, fullB);
                            System.out.println(line() + " result is " + Arrays.toString(result));
                            int periodIndex = intA.length;

                            //вставка точки
                            StringBuilder sb = new StringBuilder(intArrToString("-", result, periodIndex));
                            System.out.println(line() + "result with dot is " + sb.toString());
                            return sb.toString();

                        }
                        // если дробная часть b длинее a
                        else if (!compareLengths(fractionPart(a), fractionPart(b))) {
                            int[] fracA = stringToIntArr(fractionPart(b), 0, "End");
                            int[] fracB = stringToIntArr(fractionPart(a), fracA.length - 1, "End");

                            int[] fullA = merge(intA, fracA);
                            int[] fullB = merge(intB, fracB);

                            int[] result = subtracktor(fullA, fullB);
                            System.out.println(line() + " result is " + Arrays.toString(result));
                            int periodIndex = intA.length;

                            //вставка точки
                            StringBuilder sb = new StringBuilder(intArrToString("-", result, periodIndex));
                            System.out.println(line() + "result with dot is " + sb.toString());
                            return sb.toString();

                        }

                    }
                    //  если целая часть b длинее a
                    if (!compareLengths(intPart(a), intPart(b))) {
                        int[] intA = stringToIntArr(intPart(b), 0, "Start");
                        int[] intB = stringToIntArr(intPart(a), intA.length - 1, "Start");
                        // если дробная часть a длинее b или равны
                        if (compareLengths(fractionPart(a), fractionPart(b))) {
                            int[] fracA = stringToIntArr(fractionPart(a), 0, "End");
                            int[] fracB = stringToIntArr(fractionPart(b), fracA.length - 1, "End");

                            int[] fullA = merge(intA, fracA);
                            int[] fullB = merge(intB, fracB);

                            int[] result = subtracktor(fullA, fullB);
                            System.out.println(line() + " result is " + Arrays.toString(result));
                            int periodIndex = intA.length;

                            //вставка точки
                            StringBuilder sb = new StringBuilder(intArrToString("-", result, periodIndex));
                            System.out.println(line() + "result with dot is " + sb.toString());
                            return sb.toString();

                        }
                        // если дробная часть b длинее a
                        else if (!compareLengths(fractionPart(a), fractionPart(b))) {
                            int[] fracA = stringToIntArr(fractionPart(b), 0, "End");
                            int[] fracB = stringToIntArr(fractionPart(a), fracA.length - 1, "End");

                            int[] fullA = merge(intA, fracA);
                            int[] fullB = merge(intB, fracB);

                            int[] result = subtracktor(fullA, fullB);
                            System.out.println(line() + " result is " + Arrays.toString(result));
                            int periodIndex = intA.length;

                            //вставка точки
                            StringBuilder sb = new StringBuilder(intArrToString("-", result, periodIndex));
                            System.out.println(line() + "result with dot is " + sb.toString());
                            return sb.toString();

                        }

                    }
                }
            }
        } catch (Exception e) {
            return "ADDwPer Method Error";
        }
        return "ADD Method Error";
    }


}

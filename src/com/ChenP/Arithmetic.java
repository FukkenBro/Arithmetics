package com.ChenP;

import java.util.Arrays;

public class Arithmetic {

//        Написать класс для работы с длинными числами которые заданы как String,
//        без использования стандартных библиотечных методов типа BigDecimal.
//        Реализовать основые операции: +,-,/,*,%, корень квадратный, возведение в степень, округление.
//        Пример:
//        String result = LongArithmetic.add("234234234234234234.45234234", "-345345345234234234234.6723423456");


    /**
     * Все public методы вызывают numberValidator вначале.
     **/
    //valid
    public static String numberValidator(String a) {
        // Если есть запятая заменить на точку
        if (a.contains(",")) {
            String num = a.replace(",", ".");
        }
        String num = a;
        //Перевод формы записи .xxx в 0.ххх
        if (num.contains(".") && (num.indexOf(" .") == 0)) {
            num.replace(".", "0");
        }
        //Перевод формы записи -.xxx в -0.ххх
        if (num.contains(".") && num.contains("-") && (num.indexOf(".") == 1)) {
            num.replace("-.", "-0.");
        }
        String result = num;
        for (int i = 0; i <= 9; i++) {
            result = result.replace(String.valueOf(i), "");
        }
        System.out.println(result);
        if (result.isEmpty() || result.equals(".") || result.equals("-") || result.equals("-.")) {
            System.out.println("Valid");
            return num;
        }
        System.out.println("Invalid");
        return "NaN";
    }

    public static String add(String a, String b) {
        try {
            if (numberValidator(a) != "NaN" && numberValidator(b) != "NaN") {
                // Если ++
                if (ifPositive(a).equals("+") && ifPositive(b).equals("+")) {
                    //если целая часть а длинее b или равны
                    if (compareLengths(intPart(a), intPart(b))) {
                        int[] intA = parseToIntArray(intPart(a), 0, "Start");
                        int[] intB = parseToIntArray(intPart(b), intA.length - 1, "Start");
                        // если дробная часть a длинее b или равны
                        if (compareLengths(fractionPart(a), fractionPart(b))) {
                            int[] fracA = parseToIntArray(fractionPart(a), 0, "End");
                            int[] fracB = parseToIntArray(fractionPart(b), fracA.length - 1, "End");
                            int[] fullA = new int[intA.length + fracB.length];
                            int[] fullB = new int[intA.length + fracB.length];
                            //Отладка
                            System.arraycopy(intA, 0, fullA, 0, intA.length);
                            System.arraycopy(fracA, 0, fullA, intA.length, fracA.length);
                            System.arraycopy(intB, 0, fullB, 0, intB.length);
                            System.arraycopy(fracB, 0, fullB, intB.length, fracB.length);
                            System.out.printf("156 intA.length " + intA.length + "  ");
                            System.out.println(Arrays.toString(intA));
                            System.out.printf("158 fracA.length " + fracA.length + "  ");
                            System.out.println(Arrays.toString(fracA));
                            System.out.println("161 Full A " + Arrays.toString(fullA));
                            System.out.println();
                            System.out.printf("165 intB.length " + intB.length + "  ");
                            System.out.println(Arrays.toString(intB));
                            System.out.printf("167 fracB.length " + fracB.length + "  ");
                            System.out.println(Arrays.toString(fracB));
                            System.out.println("169 Full B " + Arrays.toString(fullB));
                            System.out.println();
                            //служебные массивы
                            int[] memory = new int[fullA.length];
                            int[] result = new int[fullA.length];
                            System.out.println(Arrays.toString(memory));
                            for (int i = result.length - 1, j = fullA.length - 1; j >= 0; j--, i--) {
                                if (fullA[j] + fullB[j] + memory[i] >= 10) {
                                    result[i] += ((fullA[j] + fullB[j] + memory[i]) % 10);
                                    memory[i - 1]++;
                                } else result[i] = fullA[j] + fullB[j] + memory[j];
                            }
                            System.out.println(Arrays.toString(memory));
                            System.out.println("result is " + Arrays.toString(result));
                            StringBuilder sb = new StringBuilder(intToString("+", result));
                            // вставка точки
                            if (result[0] == 0) {
                                sb.insert(intA.length - 1, ".");
                                return sb.toString();
                            }
                            sb.insert(intA.length, ".");
                            System.out.println(sb.toString());
                            return sb.toString();
                        }
                        // если дробная часть b длинее a
                        if (!compareLengths(fractionPart(a), fractionPart(b))) {
                            //тут ленивая замена аккуратно! int[] frac!!!! A !!!! = parseToIntArray(fractionPart(!!!! b !!!!!),
                            int[] fracA = parseToIntArray(fractionPart(b), 0, "End");
                            int[] fracB = parseToIntArray(fractionPart(a), fracA.length - 1, "End");
                            int[] fullA = new int[intA.length + fracB.length];
                            int[] fullB = new int[intA.length + fracB.length];
                            //Отладка
                            System.arraycopy(intA, 0, fullA, 0, intA.length);
                            System.arraycopy(fracA, 0, fullA, intA.length, fracA.length);
                            System.arraycopy(intB, 0, fullB, 0, intB.length);
                            System.arraycopy(fracB, 0, fullB, intB.length, fracB.length);
                            System.out.printf("156 intA.length " + intA.length + "  ");
                            System.out.println(Arrays.toString(intA));
                            System.out.printf("158 fracA.length " + fracA.length + "  ");
                            System.out.println(Arrays.toString(fracA));
                            System.out.println("161 Full A " + Arrays.toString(fullA));
                            System.out.println();
                            System.out.printf("165 intB.length " + intB.length + "  ");
                            System.out.println(Arrays.toString(intB));
                            System.out.printf("167 fracB.length " + fracB.length + "  ");
                            System.out.println(Arrays.toString(fracB));
                            System.out.println("169 Full B " + Arrays.toString(fullB));
                            System.out.println();
                            //служебные массивы
                            int[] memory = new int[fullA.length];
                            int[] result = new int[fullA.length];
                            System.out.println(Arrays.toString(memory));
                            for (int i = result.length - 1, j = fullA.length - 1; j >= 0; j--, i--) {
                                if (fullA[j] + fullB[j] + memory[i] >= 10) {
                                    result[i] += ((fullA[j] + fullB[j] + memory[i]) % 10);
                                    memory[i - 1]++;
                                } else result[i] = fullA[j] + fullB[j] + memory[j];
                            }
                            System.out.println(Arrays.toString(memory));
                            System.out.println("result is " + Arrays.toString(result));
                            StringBuilder sb = new StringBuilder(intToString("+", result));
                            // вставка точки
                            if (result[0] == 0) {
                                sb.insert(intA.length - 1, ".");
                                return sb.toString();
                            }
                            sb.insert(intA.length, ".");
                            System.out.println(sb.toString());
                            return sb.toString();
                        }
                    }
                    //если целая часть b длинее a
                    if (!compareLengths(intPart(a), intPart(b))) {
                        //тут ленивая замена аккуратно! int[] int!!!! A !!!! = parseToIntArray(intPart(!!!! b !!!!!),
                        int[] intA = parseToIntArray(intPart(b), 0, "Start");
                        int[] intB = parseToIntArray(intPart(a), intA.length - 1, "Start");
                        // если дробная часть a длинее b или равны
                        if (compareLengths(fractionPart(a), fractionPart(b))) {
                            int[] fracA = parseToIntArray(fractionPart(a), 0, "End");
                            int[] fracB = parseToIntArray(fractionPart(b), fracA.length - 1, "End");
                            int[] fullA = new int[intA.length + fracB.length];
                            int[] fullB = new int[intA.length + fracB.length];
                            //Отладка
                            System.arraycopy(intA, 0, fullA, 0, intA.length);
                            System.arraycopy(fracA, 0, fullA, intA.length, fracA.length);
                            System.arraycopy(intB, 0, fullB, 0, intB.length);
                            System.arraycopy(fracB, 0, fullB, intB.length, fracB.length);
                            System.out.printf("156 intA.length " + intA.length + "  ");
                            System.out.println(Arrays.toString(intA));
                            System.out.printf("158 fracA.length " + fracA.length + "  ");
                            System.out.println(Arrays.toString(fracA));
                            System.out.println("161 Full A " + Arrays.toString(fullA));
                            System.out.println();
                            System.out.printf("165 intB.length " + intB.length + "  ");
                            System.out.println(Arrays.toString(intB));
                            System.out.printf("167 fracB.length " + fracB.length + "  ");
                            System.out.println(Arrays.toString(fracB));
                            System.out.println("169 Full B " + Arrays.toString(fullB));
                            System.out.println();
                            //служебные массивы
                            int[] memory = new int[fullA.length];
                            int[] result = new int[fullA.length];
                            System.out.println(Arrays.toString(memory));
                            for (int i = result.length - 1, j = fullA.length - 1; j >= 0; j--, i--) {
                                if (fullA[j] + fullB[j] + memory[i] >= 10) {
                                    result[i] += ((fullA[j] + fullB[j] + memory[i]) % 10);
                                    memory[i - 1]++;
                                } else result[i] = fullA[j] + fullB[j] + memory[j];
                            }
                            System.out.println(Arrays.toString(memory));
                            System.out.println("result is " + Arrays.toString(result));
                            StringBuilder sb = new StringBuilder(intToString("+", result));
                            // вставка точки
                            if (result[0] == 0) {
                                sb.insert(intA.length - 1, ".");
                                return sb.toString();
                            }
                            sb.insert(intA.length, ".");
                            System.out.println(sb.toString());
                            return sb.toString();
                        }
                        // если дробная часть b длинее a
                        if (!compareLengths(fractionPart(a), fractionPart(b))) {
                            //тут ленивая замена аккуратно! int[] frac!!!! A !!!! = parseToIntArray(fractionPart(!!!! b !!!!!),
                            int[] fracA = parseToIntArray(fractionPart(b), 0, "End");
                            int[] fracB = parseToIntArray(fractionPart(a), fracA.length - 1, "End");
                            int[] fullA = new int[intA.length + fracB.length];
                            int[] fullB = new int[intA.length + fracB.length];
                            //Отладка
                            System.arraycopy(intA, 0, fullA, 0, intA.length);
                            System.arraycopy(fracA, 0, fullA, intA.length, fracA.length);
                            System.arraycopy(intB, 0, fullB, 0, intB.length);
                            System.arraycopy(fracB, 0, fullB, intB.length, fracB.length);
                            System.out.printf("156 intA.length " + intA.length + "  ");
                            System.out.println(Arrays.toString(intA));
                            System.out.printf("158 fracA.length " + fracA.length + "  ");
                            System.out.println(Arrays.toString(fracA));
                            System.out.println("161 Full A " + Arrays.toString(fullA));
                            System.out.println();
                            System.out.printf("165 intB.length " + intB.length + "  ");
                            System.out.println(Arrays.toString(intB));
                            System.out.printf("167 fracB.length " + fracB.length + "  ");
                            System.out.println(Arrays.toString(fracB));
                            System.out.println("169 Full B " + Arrays.toString(fullB));
                            System.out.println();
                            //служебные массивы
                            int[] memory = new int[fullA.length];
                            int[] result = new int[fullA.length];
                            System.out.println(Arrays.toString(memory));
                            for (int i = result.length - 1, j = fullA.length - 1; j >= 0; j--, i--) {
                                if (fullA[j] + fullB[j] + memory[i] >= 10) {
                                    result[i] += ((fullA[j] + fullB[j] + memory[i]) % 10);
                                    memory[i - 1]++;
                                } else result[i] = fullA[j] + fullB[j] + memory[j];
                            }
                            System.out.println(Arrays.toString(memory));
                            System.out.println("result is " + Arrays.toString(result));
                            StringBuilder sb = new StringBuilder(intToString("+", result));
                            // вставка точки
                            if (result[0] == 0) {
                                sb.insert(intA.length - 1, ".");
                                return sb.toString();
                            }
                            sb.insert(intA.length, ".");
                            System.out.println(sb.toString());
                            return sb.toString();
                        }
                    }
                }
                // Если --
                if (ifPositive(a).equals("-") && ifPositive(b).equals("-")) {
                    //если целая часть а длинее b или равны
                    if (compareLengths(intPart(a), intPart(b))) {
                        int[] intA = parseToIntArray(intPart(a), 0, "Start");
                        int[] intB = parseToIntArray(intPart(b), intA.length - 1, "Start");
                        // если дробная часть a длинее b или равны
                        if (compareLengths(fractionPart(a), fractionPart(b))) {
                            int[] fracA = parseToIntArray(fractionPart(a), 0, "End");
                            int[] fracB = parseToIntArray(fractionPart(b), fracA.length - 1, "End");
                            int[] fullA = new int[intA.length + fracB.length];
                            int[] fullB = new int[intA.length + fracB.length];
                            //Отладка
                            System.arraycopy(intA, 0, fullA, 0, intA.length);
                            System.arraycopy(fracA, 0, fullA, intA.length, fracA.length);
                            System.arraycopy(intB, 0, fullB, 0, intB.length);
                            System.arraycopy(fracB, 0, fullB, intB.length, fracB.length);
                            System.out.printf("156 intA.length " + intA.length + "  ");
                            System.out.println(Arrays.toString(intA));
                            System.out.printf("158 fracA.length " + fracA.length + "  ");
                            System.out.println(Arrays.toString(fracA));
                            System.out.println("161 Full A " + Arrays.toString(fullA));
                            System.out.println();
                            System.out.printf("165 intB.length " + intB.length + "  ");
                            System.out.println(Arrays.toString(intB));
                            System.out.printf("167 fracB.length " + fracB.length + "  ");
                            System.out.println(Arrays.toString(fracB));
                            System.out.println("169 Full B " + Arrays.toString(fullB));
                            System.out.println();
                            //служебные массивы
                            int[] memory = new int[fullA.length];
                            int[] result = new int[fullA.length];
                            System.out.println(Arrays.toString(memory));
                            for (int i = result.length - 1, j = fullA.length - 1; j >= 0; j--, i--) {
                                if (fullA[j] + fullB[j] + memory[i] >= 10) {
                                    result[i] += ((fullA[j] + fullB[j] + memory[i]) % 10);
                                    memory[i - 1]++;
                                } else result[i] = fullA[j] + fullB[j] + memory[j];
                            }
                            System.out.println(Arrays.toString(memory));
                            System.out.println("result is " + Arrays.toString(result));
                            StringBuilder sb = new StringBuilder(intToString("-", result));
                            // вставка точки
                            if (result[0] == 0) {
                                sb.insert(intA.length - 1, ".");
                                return sb.toString();
                            }
                            sb.insert(intA.length, ".");
                            System.out.println(sb.toString());
                            return sb.toString();
                        }
                        // если дробная часть b длинее a
                        if (!compareLengths(fractionPart(a), fractionPart(b))) {
                            //тут ленивая замена аккуратно! int[] frac!!!! A !!!! = parseToIntArray(fractionPart(!!!! b !!!!!),
                            int[] fracA = parseToIntArray(fractionPart(b), 0, "End");
                            int[] fracB = parseToIntArray(fractionPart(a), fracA.length - 1, "End");
                            int[] fullA = new int[intA.length + fracB.length];
                            int[] fullB = new int[intA.length + fracB.length];
                            //Отладка
                            System.arraycopy(intA, 0, fullA, 0, intA.length);
                            System.arraycopy(fracA, 0, fullA, intA.length, fracA.length);
                            System.arraycopy(intB, 0, fullB, 0, intB.length);
                            System.arraycopy(fracB, 0, fullB, intB.length, fracB.length);
                            System.out.printf("156 intA.length " + intA.length + "  ");
                            System.out.println(Arrays.toString(intA));
                            System.out.printf("158 fracA.length " + fracA.length + "  ");
                            System.out.println(Arrays.toString(fracA));
                            System.out.println("161 Full A " + Arrays.toString(fullA));
                            System.out.println();
                            System.out.printf("165 intB.length " + intB.length + "  ");
                            System.out.println(Arrays.toString(intB));
                            System.out.printf("167 fracB.length " + fracB.length + "  ");
                            System.out.println(Arrays.toString(fracB));
                            System.out.println("169 Full B " + Arrays.toString(fullB));
                            System.out.println();
                            //служебные массивы
                            int[] memory = new int[fullA.length];
                            int[] result = new int[fullA.length];
                            System.out.println(Arrays.toString(memory));
                            for (int i = result.length - 1, j = fullA.length - 1; j >= 0; j--, i--) {
                                if (fullA[j] + fullB[j] + memory[i] >= 10) {
                                    result[i] += ((fullA[j] + fullB[j] + memory[i]) % 10);
                                    memory[i - 1]++;
                                } else result[i] = fullA[j] + fullB[j] + memory[j];
                            }
                            System.out.println(Arrays.toString(memory));
                            System.out.println("result is " + Arrays.toString(result));
                            StringBuilder sb = new StringBuilder(intToString("-", result));
                            // вставка точки
                            if (result[0] == 0) {
                                sb.insert(intA.length - 1, ".");
                                return sb.toString();
                            }
                            sb.insert(intA.length, ".");
                            System.out.println(sb.toString());
                            return sb.toString();
                        }
                    }
                    //если целая часть b длинее a
                    if (!compareLengths(intPart(a), intPart(b))) {
                        //тут ленивая замена аккуратно! int[] int!!!! A !!!! = parseToIntArray(intPart(!!!! b !!!!!),
                        int[] intA = parseToIntArray(intPart(b), 0, "Start");
                        int[] intB = parseToIntArray(intPart(a), intA.length - 1, "Start");
                        // если дробная часть a длинее b или равны
                        if (compareLengths(fractionPart(a), fractionPart(b))) {
                            int[] fracA = parseToIntArray(fractionPart(a), 0, "End");
                            int[] fracB = parseToIntArray(fractionPart(b), fracA.length - 1, "End");
                            int[] fullA = new int[intA.length + fracB.length];
                            int[] fullB = new int[intA.length + fracB.length];
                            //Отладка
                            System.arraycopy(intA, 0, fullA, 0, intA.length);
                            System.arraycopy(fracA, 0, fullA, intA.length, fracA.length);
                            System.arraycopy(intB, 0, fullB, 0, intB.length);
                            System.arraycopy(fracB, 0, fullB, intB.length, fracB.length);
                            System.out.printf("156 intA.length " + intA.length + "  ");
                            System.out.println(Arrays.toString(intA));
                            System.out.printf("158 fracA.length " + fracA.length + "  ");
                            System.out.println(Arrays.toString(fracA));
                            System.out.println("161 Full A " + Arrays.toString(fullA));
                            System.out.println();
                            System.out.printf("165 intB.length " + intB.length + "  ");
                            System.out.println(Arrays.toString(intB));
                            System.out.printf("167 fracB.length " + fracB.length + "  ");
                            System.out.println(Arrays.toString(fracB));
                            System.out.println("169 Full B " + Arrays.toString(fullB));
                            System.out.println();
                            //служебные массивы
                            int[] memory = new int[fullA.length];
                            int[] result = new int[fullA.length];
                            System.out.println(Arrays.toString(memory));
                            for (int i = result.length - 1, j = fullA.length - 1; j >= 0; j--, i--) {
                                if (fullA[j] + fullB[j] + memory[i] >= 10) {
                                    result[i] += ((fullA[j] + fullB[j] + memory[i]) % 10);
                                    memory[i - 1]++;
                                } else result[i] = fullA[j] + fullB[j] + memory[j];
                            }
                            System.out.println(Arrays.toString(memory));
                            System.out.println("result is " + Arrays.toString(result));
                            StringBuilder sb = new StringBuilder(intToString("-", result));
                            // вставка точки
                            if (result[0] == 0) {
                                sb.insert(intA.length - 1, ".");
                                return sb.toString();
                            }
                            sb.insert(intA.length, ".");
                            System.out.println(sb.toString());
                            return sb.toString();
                        }
                        // если дробная часть b длинее a
                        if (!compareLengths(fractionPart(a), fractionPart(b))) {
                            //тут ленивая замена аккуратно! int[] frac!!!! A !!!! = parseToIntArray(fractionPart(!!!! b !!!!!),
                            int[] fracA = parseToIntArray(fractionPart(b), 0, "End");
                            int[] fracB = parseToIntArray(fractionPart(a), fracA.length - 1, "End");
                            int[] fullA = new int[intA.length + fracB.length];
                            int[] fullB = new int[intA.length + fracB.length];
                            //Отладка
                            System.arraycopy(intA, 0, fullA, 0, intA.length);
                            System.arraycopy(fracA, 0, fullA, intA.length, fracA.length);
                            System.arraycopy(intB, 0, fullB, 0, intB.length);
                            System.arraycopy(fracB, 0, fullB, intB.length, fracB.length);
                            System.out.printf("156 intA.length " + intA.length + "  ");
                            System.out.println(Arrays.toString(intA));
                            System.out.printf("158 fracA.length " + fracA.length + "  ");
                            System.out.println(Arrays.toString(fracA));
                            System.out.println("161 Full A " + Arrays.toString(fullA));
                            System.out.println();
                            System.out.printf("165 intB.length " + intB.length + "  ");
                            System.out.println(Arrays.toString(intB));
                            System.out.printf("167 fracB.length " + fracB.length + "  ");
                            System.out.println(Arrays.toString(fracB));
                            System.out.println("169 Full B " + Arrays.toString(fullB));
                            System.out.println();
                            //служебные массивы
                            int[] memory = new int[fullA.length];
                            int[] result = new int[fullA.length];
                            System.out.println(Arrays.toString(memory));
                            for (int i = result.length - 1, j = fullA.length - 1; j >= 0; j--, i--) {
                                if (fullA[j] + fullB[j] + memory[i] >= 10) {
                                    result[i] += ((fullA[j] + fullB[j] + memory[i]) % 10);
                                    memory[i - 1]++;
                                } else result[i] = fullA[j] + fullB[j] + memory[j];
                            }
                            System.out.println(Arrays.toString(memory));
                            System.out.println("result is " + Arrays.toString(result));
                            StringBuilder sb = new StringBuilder(intToString("-", result));
                            // вставка точки
                            if (result[0] == 0) {
                                sb.insert(intA.length - 1, ".");
                                return sb.toString();
                            }
                            sb.insert(intA.length, ".");
                            System.out.println(sb.toString());
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

    public static String sub(String a, String b) {
        try {
            if (numberValidator(a) != "NaN" && numberValidator(b) != "NaN") {
                int aPeriodIndex = Integer.parseInt(digitsAfterPeriod(a));
                int bPeriodIndex = Integer.parseInt(digitsAfterPeriod(b));
                // Если ++
                if (ifPositive(a).equals("+") && ifPositive(b).equals("+")) {
                    //если а длинее b
                    if (compareLengths(intPart(a), intPart(b))) {
                        int[] intA = parseToIntArray(intPart(a), 0, "Start");
                        int[] intB = parseToIntArray(intPart(b), intA.length - 1, "Start");
                        int[] plusOne = new int[intA.length];
                        int[] result = new int[intA.length];
                        System.out.println("rra" + Arrays.toString(result));
                        for (int i = result.length - 1, j = intA.length - 1; j >= 0; j--, i--) {
                            if (intA[j] - intB[j] - plusOne[i] < 0) {
                                result[i] += (10 + intA[j] - intB[j] - plusOne[i]);
                                plusOne[i - 1] += 1;
                            } else result[i] = intA[j] - intB[j] - plusOne[j];
                        }
                        System.out.println(Arrays.toString(result));
                        return intToString("+", result);
                    }

                    //если b длинее a
                    if (!compareLengths(intPart(a), intPart(b))) {
                        int[] intB = parseToIntArray(intPart(b), 0, "Start");
                        int[] intA = parseToIntArray(intPart(a), intB.length - 1, "Start");
                        int[] plusOne = new int[intB.length];
                        int[] result = new int[intA.length];
                        System.out.println("rra" + Arrays.toString(result));
                        for (int i = result.length - 1, j = intA.length - 1; j >= 0; j--, i--) {
                            if (intB[j] - intA[j] - plusOne[i] < 0) {
                                result[i] += (10 + intB[j] - intA[j] - plusOne[i]);
                                plusOne[i - 1] += 1;
                            } else result[i] = intB[j] - intA[j] - plusOne[j];
                        }
                        System.out.println(Arrays.toString(result));
                        return intToString("-", result);
                    }

                }
            }
        } catch (Exception e) {
            return "SUB Method Error";
        }
        return "SUB Method Error";
    }

    public static String time(String a, String b) {
        try {
            double x = Double.parseDouble(numberValidator(a));
            double y = Double.parseDouble(numberValidator(b));
            String result = String.valueOf(x * y);
            return result;
        } catch (Exception e) {
            return "NaN";
        }
    }

/*    public static String div(String nominator, String denominator) {
        try {
            double x = Double.parseDouble(numberValidator(nominator));
            double y = Double.parseDouble(numberValidator(denominator));
            if (y == 0) {
                return "Infinity";
            }
            String result = String.valueOf(x / y);
            return result;
        } catch (Exception e) {
            return "NaN";
        }
    }*/

/*    public static String power(String base, String power) {
        try {
            double x = Double.parseDouble(numberValidator(base));
            double y = Double.parseDouble(numberValidator(power));
            if (y == 0) {
                return "1";
            }
            if (y == 1) {
                return base;
            }
            double result = x;
            for (int i = 2; i <= y; i++) {
                result *= x;
            }
            return String.valueOf(result);
        } catch (Exception e) {
            return "NaN";
        }
    }*/

//    public static String absolute(String a) {
//        try {
//            String absolute = a;
//            if (numberValidator(a) == "NaN") {
//                return "NaN";
//            }
//            if (a.charAt(0) == '-') {
//                absolute = absolute.replace("-", "");
//                return absolute;
//            }
//            return a;
//        } catch (Exception e) {
//            return "NaN";
//        }
//    }
//
//    public static String round(String number, String roundBy) {
//        try {
//            double x = Double.parseDouble(numberValidator(number));
//            double n = Double.parseDouble(Arithmetic.power("10", roundBy));
//            if (x < 0) {
//                x = Double.parseDouble(Arithmetic.absolute(number));
//                double round = (int) (x * n + 0.5) / n;
//                String result = "-" + String.valueOf(round);
//                return result;
//            }
//            double round = (int) (x * n + 0.5) / n;
//            String result = String.valueOf(round);
//            return result;
//        } catch (Exception e) {
//            return "NaN";
//        }
//    }

    public static String squareRoot(String a) {
        try {
            double x = Double.parseDouble(numberValidator(a));
            if (x < 0) {
                return "NaN";
            }
            double tmp;
            double sqrt = x / 2;
            do {
                tmp = sqrt;
                sqrt = (tmp + (x / tmp)) / 2;
            } while ((tmp - sqrt) != 0);
            String result = String.valueOf(sqrt);
            return result;
        } catch (Exception e) {
            return "NaN";
        }
    }

    public static String divWRamain(String nominator, String denominator) {
        try {
            double x = Double.parseDouble(numberValidator(nominator));
            double y = Double.parseDouble(numberValidator(denominator));
            if (y == 0) {
                return "Infinity";
            }
            String result = String.valueOf(x / y);
            return result;
        } catch (Exception e) {
            return "NaN";
        }
    }

    /**
     * private методы не вызывают number validator.
     **/

    //valid
    private static String ifPositive(String a) {
        try {
            if (a.charAt(0) == '-') {
                return "-";
            }
            return "+";
        } catch (Exception e) {
            return "ifPositive Error";
        }
    }

    //valid
    private static int[] parseToIntArray(String a, int length, String bufferPosition) {
        System.out.println("377 incoming String to Array - " + a);
        /**
         * Если String передаёт отрицательное число, метод вернёт массив без знака "-"
         */
        try {
            //Если методу передаёться 0 length
            if (length == 0) {
                length = a.length();
            }

            //Если пуста строка
            if (a.equals("")) {
                int[] intA = new int[length+2];
                return intA;
            }

            StringBuilder sb = new StringBuilder(a);
            if (sb.charAt(0) == '-') {
                sb.deleteCharAt(0);
            }

            char[] arrA = sb.toString().toCharArray();

            //Расширительная ячейка
            length++;

            if (bufferPosition.equals("Start")) {
                int[] intA = new int[length];
                for (int i = intA.length - 1, j = arrA.length - 1; j >= 0; i--, j--) {
                    intA[i] = Integer.parseInt(String.valueOf(arrA[j]));
                }
                System.out.println("658" + Arrays.toString(intA));
                return intA;
            }
            if (bufferPosition.equals("End")) {
                {
                    int[] intA = new int[length];
                    for (int i = 0, j = 0; j < arrA.length; i++, j++) {
                        intA[i] = Integer.parseInt(String.valueOf(arrA[j]));
                    }
                    System.out.println("468" + Arrays.toString(intA));
                    return intA;
                }
            }

        } catch (Exception e) {
            System.out.println("parseToIntArray Error");
            return null;
        }
        return null;
    }

    //valid
    private static String intToString(String ifPositive, int[] arrA) {
        try {
            StringBuilder sb = new StringBuilder("");
            for (int i = 0; i < arrA.length; i++) {
                if (i == 0 && arrA[i] == 0) {
                    sb.append("");
                    continue;
                }
                sb.append(String.valueOf(arrA[i]));
            }
            if (sb.charAt(0) == '0') {
                sb.deleteCharAt(0);
            }
            if (ifPositive.equals("-")) {
                return sb.insert(0, "-").toString();
            }
            return sb.toString();
        } catch (Exception e) {
            return "intToString Error";
        }
    }

    //valid
    private static String digitsAfterPeriod(String a) {
        try {
            if (!a.contains(".")) {
                return "0";
            }
            int result = a.length() - a.indexOf(".");
            String s = Integer.toString(result);
            return s;

        } catch (Exception e) {
            return "digitsAfterPeriod Error";
        }
    }

    //valid
    private static String intPart(String a) {
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
            return "digitsAfterPeriod Error";
        }
    }

    //valid
    private static String fractionPart(String a) {
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
            return "digitsBeforePeriod Error";
        }
        return "digitsBeforePeriod Error";
    }

    //valid
    //Потенциально ненужный
    private static int periodIndex(String a) {
        try {
            return a.indexOf('.');

        } catch (Exception e) {
            System.out.println("periodIndex Eror");
            return -1;
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


}

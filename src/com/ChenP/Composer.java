package com.ChenP;

public class Composer {

    private static String a;
    private static String b;
    private static int[] fullA;
    private static int[] fullB;
    private static int periodIndex;

    public Composer(String a, String b) {
        this.a = a;
        this.b = b;
    }

    public static void setFullA(int[] fullA) {
        Composer.fullA = fullA;
    }

    public static void setPeriodIndex(int periodIndex) {
        Composer.periodIndex = periodIndex;
    }

    public static void setFullB(int[] fullB) {
        Composer.fullB = fullB;
    }

    public static int getPeriodIndex() {
        return periodIndex;
    }

    public static int[] getFullA() {
        composer();
        return fullA;
    }

    public static int[] getFullB() {
        composer();
        return fullB;
    }

    //valid
    static boolean compareLengths(String a, String b) {
        /**
         * Если TRUE - a - длинее или равно b, иначе - FALSE
         **/
        if (a.length() - b.length() >= 0) {
            return true;
        }
        return false;
    }

    //valid
    public static int line() {
        //Отладка - Возвращает номер строки в которой метод был вызван
        return new Throwable().getStackTrace()[1].getLineNumber();
    }

    //valid
    static String intPart(String a) {
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
    static String fractionPart(String a) {
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

    //valid
    //!!! Этот метод производит удаление "-", любые операции выполняются только с резульататами данного метода !!!
    static int[] stringToIntArr(String a, int length, String bufferPosition) {
//        System.out.println(line() + " stringToIntArr " + a);
        /**
         * a - исходная строка
         * length - длинна исходящего массива
         * bufferPosition - указывает на положение раширительной ячейки в исходящем массиве и направление заполнения
         * Если String передаёт отрицательное число, метод вернёт массив без знака "-"
         */
        try {
            //Если пуста строка
            if (a.equals("")) {
                int[] intA = new int[2 + length - 1];
//                System.out.println(line() + Arrays.toString(intA));
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
//                System.out.println(line() + Arrays.toString(intA));
                return intA;
            }
            //Заполнение слева - направо
            if (bufferPosition.equals("End")) {
                int[] intA = new int[length];
                for (int i = 0, j = 0; j < arrA.length; i++, j++) {
                    intA[i] = Integer.parseInt(String.valueOf(arrA[j]));
                }
                return intA;
            }

        } catch (Exception e) {
            System.out.println("parseToIntArray Error");
            return null;
        }
        return null;
    }

    static int[] merge(int[] intPart, int[] fracPart) {
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

    public static void composer() {
        //  если целая часть а длинее b или равны
        if (compareLengths(intPart(a), intPart(b))) {
            int[] intA = stringToIntArr(intPart(a), 0, "Start");
            int[] intB = stringToIntArr(intPart(b), intA.length - 1, "Start");
            // если дробная часть a длинее b или равны
            if (compareLengths(fractionPart(a), fractionPart(b))) {
                int[] fracA = stringToIntArr(fractionPart(a), 0, "End");
                int[] fracB = stringToIntArr(fractionPart(b), fracA.length - 1, "End");

                int[] fullA = merge(intA, fracA);
                setFullA(fullA);
                int[] fullB = merge(intB, fracB);
                setFullB(fullB);
                setPeriodIndex(intA.length);
                return;
            }
            // если дробная часть b длинее a
            else if (!compareLengths(fractionPart(a), fractionPart(b))) {
                int[] fracB = stringToIntArr(fractionPart(b), 0, "End");
                int[] fracA = stringToIntArr(fractionPart(a), fracB.length - 1, "End");

                int[] fullA = merge(intA, fracA);
                setFullA(fullA);
                int[] fullB = merge(intB, fracB);
                setFullB(fullB);
                setPeriodIndex(intA.length);
                return;
            }
        }
        //  если целая часть b длинее a
        if (!compareLengths(intPart(a), intPart(b))) {
            int[] intB = stringToIntArr(intPart(b), 0, "Start");
            int[] intA = stringToIntArr(intPart(a), intB.length - 1, "Start");
            // если дробная часть a длинее b или равны
            if (compareLengths(fractionPart(a), fractionPart(b))) {
                int[] fracA = stringToIntArr(fractionPart(a), 0, "End");
                int[] fracB = stringToIntArr(fractionPart(b), fracA.length - 1, "End");

                int[] fullA = merge(intA, fracA);
                setFullA(fullA);
                int[] fullB = merge(intB, fracB);
                setFullB(fullB);
                setPeriodIndex(intB.length);
                return;
            }
            // если дробная часть b длинее a
            else if (!compareLengths(fractionPart(a), fractionPart(b))) {
                int[] fracB = stringToIntArr(fractionPart(b), 0, "End");
                int[] fracA = stringToIntArr(fractionPart(a), fracB.length - 1, "End");

                int[] fullA = merge(intA, fracA);
                setFullA(fullA);
                int[] fullB = merge(intB, fracB);
                setFullB(fullB);
                setPeriodIndex(intB.length);
                return;
            }
        }
    }

}


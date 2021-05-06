class FixingExceptions {

    public static void calculateSquare(int[] array, int index) {
        // write your code here
//        System.out.println(index >= 0 && index < (array.length - 1) ? (int) Math.pow(array[index], 2) : "Exception!");
        if (index >= 0 && array != null && index <= array.length - 1) {
            System.out.println((int) Math.pow(array[index], 2));
        } else {
            System.out.println("Exception!");
        }
    }
}
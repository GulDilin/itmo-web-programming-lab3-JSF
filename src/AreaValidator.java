public class AreaValidator {

    public static String checkArea(String x, String y, String r) {
        double xValue = Double.parseDouble(x);
        double yValue = Double.parseDouble(y);
        double rValue = Double.parseDouble(r);
        String result;
        if ((checkRectangle(xValue, yValue, rValue) || checkCircle(xValue, yValue, rValue) || checkTriangle(xValue, yValue, rValue))) {
            result = "TRUE";
        } else {
            result = "FALSE";
        }
        return result;
    }

    private static boolean checkRectangle(double x, double y, double r) {
        return (x >= -r) && (x <= 0) && (y >= -0.5 * r) && (y <= 0);
    }

    private static boolean checkCircle(double x, double y, double r) {
        return (x * x + y * y <= r * r / 4) && (x <= 0) && (y >= 0);
    }

    private static boolean checkTriangle(double x, double y, double r) {
        return (-2 * x + r >= y) && (x >= 0) && (y >= 0);
    }

    public static String validateX(String x) {
        double value = Double.parseDouble(x);
        if (value < -5 || value > 5) {
            throw new NumberFormatException();
        }
        return x;
    }

    public static String validateY(String y) {
        double value = Double.parseDouble(y);
        if (value < -2 || value > 2) {
            throw new NumberFormatException();
        }
        return y;
    }


}

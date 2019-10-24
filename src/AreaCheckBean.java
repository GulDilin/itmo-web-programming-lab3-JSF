import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "validation", eager = true)
@ApplicationScoped
public class AreaCheckBean {

    private String x = "";
    private String y = "0";
    private String r = "";
    private boolean r1 = false;
    private boolean r2 = false;
    private boolean r3 = false;
    private boolean r4 = false;
    private boolean r5 = false;
    private String result = "";

    public AreaCheckBean() {
    }

    public void checkArea(String y) {
        try {
            setY(y);
            setR();
            setX(x);
        } catch (NumberFormatException e) {
            this.result = "Incorrect value(s)!";
        }
        double xValue = Double.parseDouble(x);
        double yValue = Double.parseDouble(y);

        double rValue = Double.parseDouble(r);
        if (result.equals("") && (checkRectangle(xValue, yValue, rValue) || checkCircle(xValue, yValue, rValue) || checkTriangle(xValue, yValue, rValue))) {
            result = "TRUE";
        } else if (result.equals("")) {
            result = "FALSE";
        }
        //TODO write result and arguments to DB
        System.out.println("X: " + x + "\nY: " + y + "\nR: " + r + "\nResult: " + result);
        result = "";
    }

    private boolean checkRectangle(double x, double y, double r) {
        return (x >= -r) && (x <= 0) && (y >= -0.5 * r) && (y <= 0);
    }

    private boolean checkCircle(double x, double y, double r) {
        return (x * x + y * y <= r * r / 4) && (x <= 0) && (y >= 0);
    }

    private boolean checkTriangle(double x, double y, double r) {
        return (-2 * x + r >= y) && (x >= 0) && (y >= 0);
    }

    public void setX(String x) throws NumberFormatException {
        double value = Double.parseDouble(x);
        if (value < -5 || value > 5) {
            throw new NumberFormatException();
        }
        this.x = x;
    }

    public String getX() {
        return x;
    }

    public void setY(String y) throws NumberFormatException{
        double value = Double.parseDouble(y);
        if (value < -5 || value > 5) {  
            throw new NumberFormatException();
        }
        this.y = y;
    }

    public String getY() {
        return y;
    }

    private void setR() throws NumberFormatException {
        if (r1) {
            r = "1";
        } else if (r2) {
            r = "2";
        } else if (r3) {
            r = "3";
        } else if (r4) {
            r = "4";
        } else if (r5) {
            r = "5";
        } else {
            throw new NumberFormatException();
        }
    }

    public void setR1(boolean value) {
        r1 = value;
    }

    public void setR2(boolean value) {
        r2 = value;
    }

    public void setR3(boolean value) {
        r3 = value;
    }

    public void setR4(boolean value) {
        r4 = value;
    }

    public void setR5(boolean value) {
        r5 = value;
    }

    public boolean getR1() {
        return r1;
    }

    public boolean getR2() {
        return r2;
    }

    public boolean getR3() {
        return r3;
    }

    public boolean getR4() {
        return r4;
    }

    public boolean getR5() {
        return r5;
    }

    public String getResult() {
        return result;
    }
}

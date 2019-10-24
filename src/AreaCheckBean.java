import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "validation", eager = true)
@ApplicationScoped
public class AreaCheckBean {

    private String x = "";
    private String y = "0";
    private String[] r;
    private int currR = 0;
    private int oldR = 0;
    private boolean r1 = false;
    private boolean r2 = false;
    private boolean r3 = false;
    private boolean r4 = false;
    private boolean r5 = false;
    private String result = "";

    public AreaCheckBean() {
    }

    public void quack() {
        System.out.println("QUACK");
    }

    public void checkArea() {
        setR();
        setX(x);
        double xValue = Double.parseDouble(x);
        double yValue = Double.parseDouble(y);
        for (int i = 0; i < r.length; ++i) {
            double rValue = Double.parseDouble(r[i]);
            if (result.equals("") && (checkRectangle(xValue, yValue, rValue) || checkCircle(xValue, yValue, rValue) || checkTriangle(xValue, yValue, rValue))) {
                result = "TRUE";

                //TODO write result and arguments to DB
            } else if (result.equals("")) {
                result = "FALSE";
            }
            System.out.println("X: " + x + "\nY: " + y + "\nR: " + r[i] + "\nResult: " + result);

        }
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

    public void setX(String x) {
        try {
            double value = Double.parseDouble(x);
            if (value >= -5 || value <= 5) {
                this.x = value + "";
            } else {
                this.x = x;
                this.result = "Incorrect value(s)!";
            }
        } catch (NumberFormatException e) {
            this.x = "0";
            this.result = "Incorrect value(s)!";
        }
    }

    public String getX() {
        return x;
    }

    public void setY(String y) {
        try {
            double value = Double.parseDouble(y);
            if (value >= -2 || value <= 2) {
                this.y = value + "";
            } else {
                this.y = y;
                this.result = "Incorrect value(s)!";
            }
        } catch (NumberFormatException e) {
            this.y = "0";
            this.result = "Incorrect value(s)!";
        }
        checkArea();
    }

    public String getY() {
        return y;
    }

    private void setR() {
        int requestNumber = 0;
        if (r1) {
            requestNumber++;
        }
        if (r2) {
            requestNumber++;
        }
        if (r3) {
            requestNumber++;
        }
        if (r4) {
            requestNumber++;
        }
        if (r5) {
            requestNumber++;
        }
        r = new String[requestNumber];
        for (int i = 0; i < requestNumber; ++i) {
            if (r1) {
                r[i] = "1";
                r1 = false;
            } else if (r2) {
                r[i] = "2";
                r2 = false;
            } else if (r3) {
                r[i] = "3";
                r3 = false;
            } else if (r4) {
                r[i] = "4";
                r4 = false;
            } else if (r5) {
                r[i] = "5";
                r5 = false;
            }
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

    public void setOldR(int oldR) {
        this.oldR = oldR;
    }

    public void setCurrR(int currR) {
        this.currR = currR;
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

    public int getOldR() {
        return oldR;
    }

    public int getCurrR() {
        return currR;
    }

    public String getResult() {
        return result;
    }
}

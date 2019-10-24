import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.util.Map;

@ManagedBean(name = "controller", eager = true)
@ApplicationScoped
public class ControllerBean {

    private String x = "0";
    private String y = "0";
    private String r = "1";
    private boolean r1 = false;
    private boolean r2 = false;
    private boolean r3 = false;
    private boolean r4 = false;
    private boolean r5 = false;
    private String result = "";

    private DataBaseManager manager = null;

    public ControllerBean() {
        manager = new DataBaseManager("studs", 8454);
    }

    public void checkArea(String y) {
        try {
            y = AreaValidator.validateY(y);
            setR();
            x = AreaValidator.validateX(x);
            result = AreaValidator.checkArea(x, y, r);
        } catch (NumberFormatException e) {
            result = "Incorrect value(s)!";
            resetBean();
        }
        System.out.println("X: " + x + "\nY: " + y + "\nR: " + r + "\nResult: " + result);
        //manager.addDot(Double.parseDouble(x),  Double.parseDouble(y),  Integer.getInteger(x), result);
        resetBean();
    }

    public void plotCheckArea() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        String plotX = params.get("x");
        String plotY = params.get("y");
        String plotR = params.get("r");

        try {
            plotY = AreaValidator.validateY(plotY);
            plotX = AreaValidator.validateX(plotX);
            result = AreaValidator.checkArea(plotX, plotY, plotR);
        } catch (NumberFormatException e) {
            result = "Incorrect value(s)!";
            resetBean();
        }
        System.out.println("X: " + plotX + "\nY: " + plotY + "\nR: " + plotR + "\nResult: " + result);
        //manager.addDot(Double.parseDouble(plotX),  Double.parseDouble(plotY),  Integer.getInteger(plotR), result);
        resetBean();
    }

    public void resetBean() {
        x = "0";
        y = "0";
        r = "1";
        result = "";
    }


    public void setX(String x) {
        this.x = x;
    }

    public String getX() {
        return x;
    }

    public void setY(String y){
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

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "info", eager = true)
@RequestScoped
public class InformationBean {

//    @ManagedProperty(value = "#{name}")
    private String name = "Гурин Евгений Иванович и Ефаринов Павел";
    private String groupName = "P3212";
    private String var = "Вариант";

    public InformationBean(){

    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

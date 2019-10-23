import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "db", eager = true)
@ApplicationScoped
public class DBBean {

    private DataBaseManager manager = null;
    private String resultAll;

    public DBBean(){
        manager = new DataBaseManager("studs", 8454);
    }

    public String getResultAll() {
        return manager.getAll();
    }

    public void setResultAll(String resultAll) {
        this.resultAll = resultAll;
    }
}

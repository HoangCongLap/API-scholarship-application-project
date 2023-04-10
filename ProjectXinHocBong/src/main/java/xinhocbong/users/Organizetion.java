package xinhocbong.users;



public class Organizetion {
    String id,tenToChuc;

    public String getId() {
        return id;
    }

    public String getTenToChuc() {
        return tenToChuc;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTenToChuc(String tenToChuc) {
        this.tenToChuc = tenToChuc;
    }

    public Organizetion(String id, String tenToChuc) {
        this.id = id;
        this.tenToChuc = tenToChuc;
    }

    @Override
    public String toString() {
        return  id + " - " +tenToChuc ;
    }
}

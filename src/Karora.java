public class Karora {

    private int id;
    private String marka;
    private String modell;
    private String szin;
    private int ar;

    public Karora(int id, String marka, String modell, String szin, int ar) {
        this.id = id;
        this.marka = marka;
        this.modell = modell;
        this.szin = szin;
        this.ar = ar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModell() {
        return modell;
    }

    public void setModell(String modell) {
        this.modell = modell;
    }

    public String getSzin() {
        return szin;
    }

    public void setSzin(String szin) {
        this.szin = szin;
    }

    public int getAr() {
        return ar;
    }

    public void setAr(int ar) {
        this.ar = ar;
    }

    @Override
    public String toString() {
        return String.format("%5d %-15s %-15s %15s %7d €",id,marka,modell,szin,ar);
    }
}

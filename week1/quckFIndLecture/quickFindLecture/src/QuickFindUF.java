
public class QuickFindUF {
    private int[] id;

    public QuickFindUF(int N){
        id = new int[N];
        for (int i = 0; i< N; i++){
            id[i] = i;
        }
    }

    public void union(int p, int q) {
        int idAtP = this.id[p];
        int idAtQ = this.id[q];
        for (int i = 0; i < this.id.length; i++) {
           if (this. id[i] == idAtP) id[i] = idAtQ;
        }
    }

    public boolean isConnected(int p, int q) {
        return this.id[p] == this.id[q];
    }

    public void printID() {
      for (int i = 0; i< this.id.length; i++) {
          System.out.println("Index: " + i + " " + "ID: " + this.id[i]);
      }
    }
}

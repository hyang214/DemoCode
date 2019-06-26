public class ChooseNear {
    public int chooseNear(int i) {
        switch (i) {
            case 100: return 0;
            case 101: return 1;
            case 104: return 4;
            default: return -1;
        }
    }

   public int chooseDiverse(int i) {
        switch (i) {
            case 1: return 0;
            case 100: return 1;
            case 1000: return 4;
            default: return -1;
        }
    }
}

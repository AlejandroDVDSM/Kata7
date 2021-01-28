package view;

public interface BlockDisplay {
    
    public static final int SIZE = 100;
    
    public void paintBlock(int x, int y);
    public void on(Moved moved);
    
    public interface Moved {
        void to(int x, int y);
    }
    
}
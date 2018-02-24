public interface Animal {
    default void makeASound(){
        System.out.println("bark");
    }
}

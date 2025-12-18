public class Counter {
    private int value=0;

    public Counter(int initialValue){
        this.value=initialValue;
    }
    // add number to current count.
    void increase(int number){
        this.value+=number;

    }
    // subtract number from current count.
    void decrease(int number){
        this.value-=number;
    }
    // get current count.
    int getValue(){
        return this.value;
    }
}
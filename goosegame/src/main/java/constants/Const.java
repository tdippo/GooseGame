package constants;

public enum Const {
	
	THEGOOSE_FIVE(5), THEGOOSE_NINE(9), THEGOOSE_FOURTEEN(14), THEGOOSE_EIGHTEEN(18), THEGOOSE_TWENTYTHREE(23),THEGOOSE_TWENTYTSEVEN(27),
	TARGET_SPACE(63),BRIGDE_TRIGGER(6),BRIGDE_TARGET(12);
    
    private int value;

    private Const (int value) {
            this.value = value;
    }
    
    public int getValue() {
        return value;
    }

}

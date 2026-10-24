package fitness.domain;

public class Member {
    private final double discountRate = 0.10;
    private final int id;
    private final String name;
    private final String phone;
    private final boolean hasDiscountCard;

    private Member(int id, String name, String phone, boolean hasDiscountCard) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.hasDiscountCard = hasDiscountCard;
    }

    public static Member create(String name) {
        return new Member(42, name, "+380509998877", true);
    }

    public double discountOn(double amount) {
        return hasDiscountCard ? amount * discountRate : 0.0;
    }

    public String name() {
        return name;
    }
}

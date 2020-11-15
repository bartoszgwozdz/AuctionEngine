
/**
  *   This is strategy desing pattern.
  *   Because of possible code changes i decided to use it.*
  *
  *   */

public interface FeeCalculateStrategy {
    public double calculateFee(double price);
}

class OneDayFee implements FeeCalculateStrategy{

    @Override
    public double calculateFee(double price) {
        return (price * 0.001) + 1;
    }
}

class ThreeDayFee implements FeeCalculateStrategy{

    @Override
    public double calculateFee(double price) {
        return (price * 0.001) + 2;
    }
}

class SevenDayFee implements FeeCalculateStrategy{

    @Override
    public double calculateFee(double price) {
        return (price * 0.001) + 3;
    }
}

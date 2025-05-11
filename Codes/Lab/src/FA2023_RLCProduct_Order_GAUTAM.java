import java.text.SimpleDateFormat;
import java.util.Date;

public class FA2023_RLCProduct_Order_GAUTAM {
    private int[] productUnits;
    private float[] productUnitPrices;

    public FA2023_RLCProduct_Order_GAUTAM() {
        productUnits = new int[3];
        productUnitPrices = new float[]{124.29f, 236.59f, 348.79f};
    }

    public void calculateSaleMoney(float[] money) {
        for (int i = 0; i < 3; i++) {
            money[i] = productUnitPrices[i] * productUnits[i];
        }
    }

    public float calculateTax(float[] money) {
        float saleTax = 0.0825f * (money[0] + money[1] + money[2]);
        return saleTax;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();

        StringBuilder receipt = new StringBuilder();
        receipt.append("SALE RLC PRODUCT RECEIPT - GAUTAM\n");
        receipt.append("------------------------------------------\n");
        receipt.append("Sale Day: ").append(dateFormat.format(date)).append("\n");
        receipt.append("------------------------------------------\n");
        receipt.append("SIZE                   PRICE   UNITS       MONEY\n");

        String[] sizes = {"Small", "Medium", "Large"};
        float[] money = new float[3];
        calculateSaleMoney(money);

        for (int i = 0; i < 3; i++) {
            receipt.append("Product ").append(sizes[i]).append(":")
                    .append(String.format("%10.2f", productUnitPrices[i]))
                    .append(String.format("%7d", productUnits[i]))
                    .append(String.format("%13.2f", money[i])).append("\n");
        }

        receipt.append("------------------------------------------\n");
        float subtotal = money[0] + money[1] + money[2];
        receipt.append("Subtotal:").append(String.format("%35.2f", subtotal)).append("\n");

        float tax = calculateTax(money);
        receipt.append("Sale Tax:").append(String.format("%35.2f", tax)).append("\n");

        float total = subtotal + tax;
        receipt.append("Total:").append(String.format("%38.2f", total)).append("\n");

        return receipt.toString();
    }

    // Getter for productUnits
    public int[] getProductUnits() {
        return productUnits;
    }
}

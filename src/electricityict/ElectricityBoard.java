package electricityict;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ElectricityBoard {

	public static void main(String[] args) throws FileNotFoundException,IOException,InvalidConsumerNumberException{
		ElectricityBoard eb = new ElectricityBoard();
		String filePath = "C:\\Users\\Kallal\\eclipse-workspace\\electricityict\\src\\electricityict\\ebbill.txt";

		eb.addBill(eb.generateBill(filePath));
	}

	public List<ElectricityBill> generateBill(String filePath) throws FileNotFoundException, IOException {

		List<ElectricityBill> billList = new ArrayList<>();
		ElectricityBoard eb = new ElectricityBoard();

		try (BufferedReader br = new BufferedReader(
				new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8));) {
			String line;
			while ((line = br.readLine()) != null) {

				String[] consumer = line.split(",");

				try {
					if (eb.validate(consumer[0])) {
						System.out.println("valid");

						ElectricityBill bill = new ElectricityBill(consumer[0], consumer[1], consumer[2],
								Integer.parseInt(consumer[3]));

						billList.add(bill);

					} else {
						throw new InvalidConsumerNumberException(getMessage());
					}
				} catch (NumberFormatException | InvalidConsumerNumberException e) {

					System.out.println(getMessage());
				}
			}
		}

		return billList;
	}

	public static String getMessage() {
		return "Invalid Consumer Number !";
	}

	public boolean validate(String consumerNumber) throws InvalidConsumerNumberException {

		Pattern pattern = Pattern.compile("^0\\d{9}$");
		Matcher matcher = pattern.matcher(consumerNumber);
		return matcher.matches();

	}

	public void addBill(List<ElectricityBill> billList) {

		try {
			Connection connection = DBHandler.establishConnection();

			for (ElectricityBill bill : billList) {
				int unitsConsumed = bill.getUnitsConsumed();

				// System.out.println(bill.getUnitsConsumed()+" "+bill.calculateBillAmount());

				PreparedStatement preparedStatement = connection.prepareStatement(
						"insert into ElectricityBill (consumer_number,name,address,units_consumed,bill_amount)values(?,?,?,?,?);");
				preparedStatement.setString(1, bill.getConsumerNumber());
				preparedStatement.setString(2, bill.getConsumerName());
				preparedStatement.setString(3, bill.getConsumerAddress());
				preparedStatement.setInt(4, unitsConsumed);
				preparedStatement.setDouble(5, bill.calculateBillAmount());

				preparedStatement.execute();
				System.out.println("Inserted Sucessfully !");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
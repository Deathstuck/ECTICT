package electricityict;
public class ElectricityBill {
	private String consumerNumber;
	private String consumerName;
	private String consumerAddress;
	private int unitsConsumed;
	private double billAmount;
	
	public ElectricityBill(String consumerNumber, String consumerName, String consumerAddress, int unitsConsumed) {
		super();
		this.consumerNumber = consumerNumber;
		this.consumerName = consumerName;
		this.consumerAddress = consumerAddress;
		this.unitsConsumed = unitsConsumed;
		
	}

	public void ElectricityBill1(String consumerNumber2, String consumerName2, String consumerAddress2, int parseInt) {
		// TODO Auto-generated constructor stub
	}

	public String getConsumerNumber() {
		return consumerNumber;
	}
	public void setConsumerNumber(String consumerNumber) {
		this.consumerNumber = consumerNumber;
	}
	public String getConsumerName() {
		return consumerName;
	}
	public void setConsumerName(String consumerName) {
		this.consumerName = consumerName;
	}
	public String getConsumerAddress() {
		return consumerAddress;
	}
	public void setConsumerAddress(String consumerAddress) {
		this.consumerAddress = consumerAddress;
	}
	public int getUnitsConsumed() {
		return unitsConsumed;
	}
	public void setUnitsConsumed(int unitsConsumed) {
		this.unitsConsumed = unitsConsumed;
	}
	public double getBillAmount() {
		return billAmount;
	}
	public  void setBillAmount(double billAmount) {
		this.billAmount = billAmount;
	}
    
	
	public double calculateBillAmount()  {
		
//		System.out.println(unitsConsumed);
		
		double cost=0;
		int rem=0;
		int[] slab1= {0,100};   //0
		int[] slab2= {101,300}; //1.5
		int[] slab3= {301,600};  //3.5
		int[] slab4= {601,1000};  //5.5
	//	int[] slab5= {1001};  //7.5
		

			if(unitsConsumed>1000) {
				rem=unitsConsumed-slab4[1];
				cost +=(rem*7.5);
				unitsConsumed=slab4[1];
				
				rem=unitsConsumed-slab3[1];
				cost +=(rem*5.5);
				unitsConsumed=slab3[1];
				
				rem=unitsConsumed-slab2[1];
				cost +=(rem*3.5);
				unitsConsumed=slab2[1];
				
				rem=unitsConsumed-slab1[1];
				cost +=(rem*1.5);
				unitsConsumed=slab1[1];
				
				
			}
			else if (unitsConsumed>600) {
				rem=unitsConsumed-slab3[1];
				cost +=(rem*5.5);
				unitsConsumed=slab3[1];
				
				rem=unitsConsumed-slab2[1];
				cost +=(rem*3.5);
				unitsConsumed=slab2[1];
				
				rem=unitsConsumed-slab1[1];
				cost +=(rem*1.5);
				unitsConsumed=slab1[1];
			}
			else if(unitsConsumed>300) {
				rem=unitsConsumed-slab2[1];
				cost +=(rem*3.5);
				unitsConsumed=slab2[1];
				
				rem=unitsConsumed-slab1[1];
				cost +=(rem*1.5);
				unitsConsumed=slab1[1];
			}
			else if(unitsConsumed>100) {
				rem=unitsConsumed-slab1[1];
				cost +=(rem*1.5);
				unitsConsumed=slab1[1];
			}else {
				cost=0;
			}
		
			setBillAmount(cost);
			return cost;
		
	}
}
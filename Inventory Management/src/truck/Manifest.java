// Jesse

package truck;

import java.util.ArrayList;

public class Manifest {
	
	private ArrayList<Truck> truckList;
	
	public Manifest() {
		
		truckList = new ArrayList<Truck>();
	}
	
	public void addTruck(Truck truck) {
		truckList.add(truck);
	}
	
	public int totalTrucks() {
		return truckList.size();
	}
	
	public double getTotalCost() {
		double cost = 0;
		
		for (int i = 0; i < truckList.size(); i++) {
			cost += truckList.get(i).getCost();
		}
		
		return cost;
		
	}
	
	

}

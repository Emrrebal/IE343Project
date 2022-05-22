import java.lang.Math;
import java.util.ArrayList;
import java.util.HashMap;

import medianProblem.*;
public class App {
    public static void main(String[] args){
        Operators operator = new Operators();
        double[][] facs_coordinates = operator.createDistanceMatrix(5,2);
        double[][] points_coordinates = operator.createDistanceMatrix(20,2);
        Facility[] facs = new Facility[5];
        Point[] points = new Point [20];
        for (int i=0; i<facs.length;i++){
            Facility f1 = new Facility(i, facs_coordinates[i][0], facs_coordinates[i][1], 20*Math.random()+50);
            facs[i] = f1;
        }
        for (int i=0; i<points.length;i++){
            Point p1 = new Point (i, points_coordinates[i][0], points_coordinates[i][1], 2*Math.random()+1);
            points[i] = p1;
        }
        double[][] distanceMatrix = operator.distanceMatrix(facs, points);
        ArrayList<Facility> openedFacilities = new ArrayList<>();
        ArrayList<Point> unassignedPoints = new ArrayList<>();
        ArrayList<Point> assignedPoints = new ArrayList<>();
        ArrayList<ArrayList<Point>> assignmentList = new ArrayList<>();
        HashMap<Point,Facility> assignedPointstoFac = new HashMap<>();
        
        double[][] sumofdistance = new double[facs.length][1]; ;
        int pmedianindex = 0;
        double min = Integer.MAX_VALUE;
        int P = 5;
       
       
 
     	while(openedFacilities.size()<P) {
     		unassignedPoints.clear();
     		 for(int i = 0;i<points.length;i++) {
             	unassignedPoints.add(points[i]);

             }
     		assignedPoints.clear();
     		for (int i=0;i<distanceMatrix.length;i++){
     			min = Integer.MAX_VALUE;
            	for (int j=0;j<distanceMatrix[0].length;j++){
            		if(openedFacilities.contains(facs[i])) {
            		break;
            		}
            		else {
            		sumofdistance[i][0] = sumofdistance[i][0] + distanceMatrix[i][j];
            			if(sumofdistance[i][0]< min){
             			min =sumofdistance[i][0];
             			pmedianindex = i;
             			}
            		}
            	}
     	}
     		
     		openedFacilities.add(facs[pmedianindex]);
     		Facility[] opened = new Facility[openedFacilities.size()];
     		double capacity = 0;
     		
     		int index = 0;
         	for(int i =0;i<facs.length;i++) {
         		if(openedFacilities.contains(facs[i])) {
         			opened[index]=facs[i];
         			capacity = capacity + facs[i].getSupply();
         			index++;
         		}
         		
         	}
         	
         	while(capacity > 0 && unassignedPoints.size() > 0) {
         		int x = 0;
         		Point[] unassigned = new Point[unassignedPoints.size()];
             	for(int i = 0;i<points.length;i++) {
             		if(unassignedPoints.contains(points[i])) {
             			unassigned[x]=points[i];
             			x++;
             		}
             	
             	}
             	
         	int[][] result = operator.findMinIndex(operator.distanceMatrix(opened, unassigned));
         	if(unassigned[result[0][1]].getDemand() <= opened[result[0][0]].getSupply()) {
         	capacity = capacity - unassigned[result[0][1]].getDemand();
         	unassignedPoints.remove(unassigned[result[0][1]]);
         	assignedPoints.add(unassigned[result[0][1]]);
         	assignedPointstoFac.put(unassigned[result[0][1]],opened[result[0][0]]);
         	
         	
         	}
         	else{
         		for(int i =0;i<openedFacilities.size();i++) {
             		if(unassigned[result[0][1]].getDemand() <= opened[i].getSupply()) {
             			capacity = capacity - unassigned[result[0][1]].getDemand();
                     	unassignedPoints.remove(unassigned[result[0][1]]);
                     	assignedPoints.add(unassigned[result[0][1]]);
                     	assignedPointstoFac.put(unassigned[result[0][1]],opened[result[0][0]]);
             		}
             		else{
             			unassignedPoints.remove(unassigned[result[0][1]]);
             		}
         		}
         	}
         	
     	}
         	assignmentList.add(assignedPoints);
         	}
     	
            System.out.print("END");
        
        
        
        
        
        
        
     	}
    }






import java.lang.Math;
import java.util.ArrayList;

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
        ArrayList<ArrayList<Point>> assignmentList = new ArrayList<>();
        
        double[][] sumofdistance = new double[facs.length][1]; ;
        int pmedianindex = 0;
        double min = Integer.MAX_VALUE;
        
        
     	for (int i=0;i<distanceMatrix.length;i++){
        	for (int j=0;j<distanceMatrix[0].length;j++){
        		
        		sumofdistance[i][0] = sumofdistance[i][0] + distanceMatrix[i][j];
        		if(sumofdistance[i][0]< min){
         			min =sumofdistance[i][0];
         			pmedianindex = i;
         			}
        		}
        	}
     	openedFacilities.add(facs[pmedianindex]);
     	operator.print2d(distanceMatrix);
//     	while(openedFacilities.size()<facs.length) {
//     	
//     	
//     	
//     	}
     	
        
            System.out.print("stop");
        }
        
        
        
        
        
        
    }





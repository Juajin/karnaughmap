package application;

public class Rank {

	public static void main(String[] args) {
		rank(split());
	}
	public static String bool = ("D.B.C.A +A'.C.B+A.D'.C'.B'");            /////// exp i parça parça arraye atma 
	
	public static String[][] split(){
		String[] arr=bool.split("\\+");
		String[][] arr2=new String[arr.length][];
		String[][] tempArr2=new String[arr.length][];
		for (int i = 0; i < arr.length; i++) {
			arr2[i]=arr[i].split("\\.");
		}
		return arr2;
	}
	public static void display(String[][] arr){
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j]);
			}
			System.out.println();
		}
	}
	
	public static String[][] addX(String[][] arr2){
		
		String[][] arr=new String[arr2.length][4];
		for (int i = 0; i < arr2.length; i++) {
			for (int k = 0; k < arr2[i].length; k++) {
				
					arr[i][k]=arr2[i][k];
				
			}
			
		}
		System.out.println(arr2[1].length);
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (arr[i][j]==null) {
					arr[i][j]="X";
				}
			}
		}
		return arr;
	}
	
	public static void rank(String[][] arr2){
		arr2=addX(arr2);
		
		for (int i = 0; i < arr2.length; i++) {
			for (int j = 0; j < arr2[0].length; j++) {
				System.out.print(arr2[i][j]);
			}
			System.out.println();
		}String temp;
		System.out.println("\n \n");
		for (int i1 = 0; i1 < arr2[0].length; i1++) {
		for (int i = 1; i < arr2.length; i++) {
			for (int j = 0; j < arr2[0].length; j++) {
				if (arr2[0][i1].equalsIgnoreCase(arr2[i][j].substring(0, 1))) {
					temp=arr2[i][j];
					arr2[i][j]=arr2[i][i1];
					arr2[i][i1]=temp;
					
				}
				else  {
					if (arr2[i][j].equalsIgnoreCase("x")) {
						temp=arr2[i][j];
						arr2[i][j]=arr2[i][i1];
						arr2[i][i1]=temp;
						
					}
					
				}
			}
		}
		}
		display(arr2);
		
		
	}

}

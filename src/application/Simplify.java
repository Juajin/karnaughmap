package application;

public class Simplify {
	public static boolean Control=false;
	
	public static void simplifier(String bool){

		//ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		System.out.println(bool);
//		String bool = ("A'.X.C.X+A'.B.X.X+X.B'.C.X+A.X.C.X+A.B.X.X+A.X.C'.X");            /////// exp i parça parça arraye atma 
//		String bool2="A.B.C.X+A.B.C'.X+A'.B.C.X";
//		String bool3=("A'.B'.C.D+A'.B'.C.D'+A'.B'.C'.D'+A'.B'.C'.D'");
//		String bool4=("A'.B'.C'.D'+A'.B'.C'.D+A'.B.C'.D'+A'.B.C'.D+A.B.C'.D'+A.B'.C'.D'");
		String[] arr=bool.split("\\+");
		String[][] arr2=new String[arr.length][];
		String[][] tempArr2=new String[arr.length][];
//		String[][] arr3=new String[2][4];
//		String[][] arr4=new String[2][4];
		for (int i = 0; i < arr.length; i++) {
			arr2[i]=arr[i].split("\\.");
		}
		//displayArray(arr2);
		
		
		while(Control==false){
		 Control=true;
		int controlCount=4;
		boolean conflictControl=false;
		int i=0;
		int k =i+1;
		int Count=0;
		int reverseCount = 0;
		
		
		
			
		while(controlCount!=0){
			
			while(i<arr2.length-1){
				
				
				reverseCount=0;
				for (int j = 0; j < arr2[i].length; j++) {
					if (arr2[i][j]!=null&&arr2[k][j]!=null) {
						
					
				  if (arr2[i][j].equals(arr2[k][j])&&!(arr2[k][j].equalsIgnoreCase("x"))&&////////////////
						  !(arr2[i][j].equalsIgnoreCase("x"))) {        // benzerlik oraný 
					Count++;		
				} 
				  if (!(arr2[i][j].equals(arr2[k][j]))){                     // benzersizlik oraný
					reverseCount++;		
				}
					
					}
					
				  }////for (int j = 0; j < arr2[i].length; j++) bitiyor 
				
				if (Count==controlCount) { 
					conflictControl=true;
					
					break;
				}
				if (controlCount==1) {
					Control=false;
					break;
				}
				Count=0;
				k++;
	           if (k==arr2.length) {
					
					i++;
					k=i+1;
					
					
				}
			}   ///while(i<arr2.length-1) BITIYOR
			
			if (conflictControl==true) {          /// çakýþma durumund döngüden çýkmasý
				break;
			}
			i=0;
			k=i+1;
			controlCount--;
			Count=0;
			
		}///while(controlCount!=0) BITIYOR
//		if (Control==false) {
//			break;
//		}
		int x=0;
		int y=0;
		String[][] arr3=new String[2][4];
		for (int j = 0; j <arr2.length ; j++) {
			if ((j==k||j==i)&&reverseCount<2) {/////////////////////////////////////////////////////sadece sadeleþebilenler atýlýyor 
				//finalControl=true;
				for (int j2 = 0; j2 < arr2[j].length; j2++) {
					
						arr3[x][y]=arr2[j][j2];                         //arr3 e atma 
						arr2[j][j2]=null;
						y++;
				}
				y=0;
				x++;
				
			}
		}
		//arr2=null;
		
		displayArray(arr3);
		boolean control2=true;
		if(arr3[0][0]!=null){
			control2=true;
		displayArray(simplifyArray(arr3));
		String[][] arr4=simplifyArray(arr3);
		String[][] arr5=arrayMaker(arr2);
		int x1 =0;
		boolean a=false;
		for (int j = 0; j < arr2.length; j++) {  /////////ARR5 en son durumu barýndýrýyor
			for (int j2 = 0; j2 < arr2[0].length; j2++) {
				if (arr2[j][0]!=null) {
					arr5[x1][j2]=arr2[j][j2];
					//Control=false;
					a=true;
				}
				
				
				
			}
			
			if (a==true) {
				x1++;
				a=false;
			}
			
		}
		for (int j = 0; j < arr5.length; j++) {
			for (int j2 = 0; j2 < arr5[0].length; j2++) {
				if (arr5[j][j2]==null) {
					arr5[j][j2]=arr4[0][j2];
				}
			}
		}
		System.out.println("ARRAY : >>>>>>>>>>>>>>>>>>>");
		displayArray(arr5);
		arr=null;
		arr2=null;
		arr3=null;
		arr4=null;
		arr2=new String[arr5.length][arr5[0].length];
		

		for (int j = 0; j < arr5.length; j++) {
			for (int j2 = 0; j2 < arr5[0].length; j2++) {
				arr2[j][j2]=arr5[j][j2];
				
			}
			
		}
		
		arr5=null;
		}
		else {
			control2=false;
		}
		if (control2==false) {
			Control=true;
		}
		else {
			Control=false;
		}
	}//While Contrl bitiyor !!
	}
	
	public static String[][] arrayMaker(String[][] array){
		int x=0;
		int y=0;
		for (int i = 0; i < array.length; i++) {
			if (array[i][0]!=null) {
				x++;
			}
		}
		String[][] arr4=new String[x+1][4];
		return arr4;
		
	}
	
	public static String[][]simplifyArray(String[][] array){
		String[][] simpli = new String[1][4];
		
			for (int j = 0; j < array[0].length; j++) {
				if (array[0][j]!=null&&array[array.length-1][j]!=null) {
					if (array[0][j].equalsIgnoreCase(array[array.length-1][j])) {
						simpli[0][j]=array[0][j];
					}
					else if (array[0][j].equalsIgnoreCase("x")||array[array.length-1][j].equalsIgnoreCase("x")) {
						if (array[0][j].equalsIgnoreCase("x")) {
							simpli[0][j]=array[array.length-1][j];
						}
						else if (array[array.length-1][j].equalsIgnoreCase("x")) {
							simpli[0][j]=array[0][j];
						}
					}
					else {
						simpli[0][j]="X";
						//Control=true;
						
					}
				}
			
		}
		return simpli;
	}
	
   public static void displayArray(String[][] array){
	   for (int i = 0; i < array.length; i++) {
		for (int j = 0; j < array[0].length; j++) {
			System.out.print(array[i][j]);
		}
		System.out.println();
	}
   }
	
   public static String[][] organizeArray(String [][]arr2)         /// array düzenleme 
	{
		
		int lastPosition=0;
		String [][] ret=new String[arr2.length][arr2[0].length];
		for (int i = 0; i < arr2.length; i++)
		{
			if(arr2[i][0]!=null)
			{
				for(int j=0;j<arr2[i].length;j++)
				{
					
						ret[lastPosition][j]=arr2[i][j];
				
					
				}
				lastPosition++;
			}	
		}
		
		return ret;
		
	}
}

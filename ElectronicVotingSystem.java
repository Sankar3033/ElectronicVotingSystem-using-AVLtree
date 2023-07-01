package project;
import java.util.*;
public class ElectronicVotingSystem {
		//Common variables
		static Scanner s = new Scanner(System.in);
		static AVLtree a = new AVLtree();
		static final int PASSWORD =12345678;
		static int noofcand;
		static int noofvot;
		static int votes[];
		static int noofvotes;
		static String candNames[];
		static boolean databaseFlag = false;
		
		//MainProgram
		public static void main(String args[]) {
			while(true) {
				int temp;
				System.out.println("	----------------------------------------");
				System.out.println("		ELECTRONIC VOTING SYSTEM ");
				System.out.println("	----------------------------------------");
				System.out.println("	Enter choice ");
				System.out.println("	1.Voter ");
				System.out.println("	2.Election commision ");
				
				int ch = s.nextInt();
				switch(ch) {
				case 1:{
					if(databaseFlag) {
					System.out.println("	Enter your details !! ");
					System.out.println("	Enter your VoterID :  ");
					temp=s.nextInt();
					voterCheck(temp);
					if(votesCheck()) {
						System.out.println("	ELECTION COMPLETED   ");
						for(int i=0;i<votes.length;i++) {
							System.out.println("	"+candNames[i]+" got "+votes[i]+" Number votes ");
						}
						if(sameVotes()) {
							System.out.println("\tELECTIOIN DRAW :(");
							System.exit(0);
						}
						int winner=findWinner();
						System.out.println("\n\n");
						System.out.println("	-------------------------------- ");
						System.out.println("	W I N N E R     I S   "+candNames[winner].toUpperCase());
						System.out.println("	-------------------------------- ");
						System.exit(0);
					}
					}
					else
						System.out.println("	Data base Not created ");
					break;
				}
				case 2:{
					System.out.println("	Enter your password :  ");
					temp=s.nextInt();
					if(temp==PASSWORD) {
						electionCommission();
					}
					else {
						System.out.println("	Authorization Revoked :( ");
						break;
					}	
				break;
				}
				default:
					System.out.println("	Enter valid choice ");
				}
			}
			
		}
		
		
		


		//Voter portion
		
		private static void voterCheck(int voterID) {
			System.out.println("	Enter your Aadhar Number ");
			int adNo=s.nextInt();
			System.out.println("	Enter your Mobile Number ");
			int mobNo=s.nextInt();
			project.AVLtree.Node voter = a.searchVoter(voterID) ;
			if(voter==null) System.out.println("	Authorizatioin revoked due to mismatched information\n\tEnter your details properly ");
			else 
			{	if(voter.voted) {
				System.out.println("	You have already voted !! ");
				return;
				}
				if(voter.aadharNo == adNo && voter.mobNo == mobNo) {
					vote();
					voter.voted=true;
				}
			}
		}
	

		private static void vote() {
			System.out.println("	The Candidated list is Show below : ");
			for(int i=0;i<candNames.length;i++) {
				System.out.println("	"+(i+1)+". "+candNames[i]);
			}
			System.out.println("	Enter your choice to Vote ");
			int choice=s.nextInt();
			System.out.println("	Are you sure to vote "+candNames[choice-1]+"\n\tPress 1 to vote OR 2 to change choice !");
			int ch=s.nextInt();
			if(ch==2) {
				vote();
				return;
			}
			else {
				votes[choice-1]++;
				System.out.println("	****YOU HAVE SUCCESSFULLY VOTED**** ");
			}
		}


		private static boolean votesCheck() {
			int sum=0;
			for(int i=0;i<votes.length;i++) {
				sum+=votes[i];
			}
			return (sum==noofvot);
		}
		
		private static int findWinner() {
			int max=0;
			int maxIndex=0;
			for(int i=0;i<votes.length;i++) {
				if(max<votes[i]) {
					max=votes[i];maxIndex=i;
				}
			}
			return maxIndex;
		}
		
		private static boolean sameVotes() {
			for(int i=1;i<votes.length;i++) {
				if(votes[i]!=votes[i-1])
					return false;
			}
			return true;
		}

		
		
		
		//ElectionCommission Portion
		
		private static void electionCommission() {
			
			System.out.println("	Enter your choice \n");
			System.out.println("	1.Create database ");
			System.out.println("	2.Show status ");
			System.out.println("	3.Show database ");
			int ch = s.nextInt();
			switch(ch) {
			case 1:{
				if(!databaseFlag)createDatabase();
				else System.out.println("	Database already created :) ");
				databaseFlag=true;
				break;
			}
			case 2:{
				if(noofcand<1) {
					System.out.println("	Database Not created :) ");
					electionCommission();
					break;
				}
				for(int i=0;i<candNames.length;i++) {
					System.out.println("	Party "+candNames[i]+" got "+votes[i]+" Votes");
				}
				break;
			}
			case 3:{
				if(databaseFlag) {
					System.out.println("	VOTERS DATABASE  ");
					a.inOrder();
				}
				else {
					System.out.println("	Database not created !");
					electionCommission();
				}
				break;
			}
			default:
				{	
					System.out.println("	Enter valid choice ");
					electionCommission();
				}
			}
			return;
		}
		private static void createDatabase() {
			System.out.println("	Enter No of Candidates ");
			noofcand=s.nextInt();
			votes= new int[noofcand];
			candNames=new String[noofcand];
			s.nextLine();
			for(int i=0;i<votes.length;i++) {
				System.out.println("	Enter "+(i+1)+" party name ");
				candNames[i]=s.nextLine();
			}
			System.out.println("	Candidates Details Successfully Uploaded ");
			System.out.println("	----------------------------------------\n");
			System.out.println("	Upload Voter deitails --> ");
			System.out.println("	Enter no of voters : ");
			noofvot=s.nextInt();
			for(int i=0;i<noofvot;i++) {
				System.out.println("	Enter "+(i+1)+" person VoterID ");
				int votID=s.nextInt();
				System.out.println("	Enter "+(i+1)+" person AadharNo ");
				int aadharID=s.nextInt();
				System.out.println("	Enter "+(i+1)+" person MobileNo ");
				int mobNo=s.nextInt();
				a.insert(votID, aadharID, mobNo);
			}
			System.out.println("	Voters Details Successfully Uploaded ");
			System.out.println("\n	Database created successfully ");
			System.out.println("	----------------------------------------\n");
			
			
		}
}

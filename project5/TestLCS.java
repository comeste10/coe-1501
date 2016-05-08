// Steve Comer
// Project 5
// Dr. Aronis's Test File
// 15 April 2013

import java.util.Date ;
import java.util.Random ;

public class TestLCS {

	public static int START_LENGTH = 10 ;
	public static int INCREMENT_LENGTH = 10 ;
	public static int ITERATIONS = 100 ;

	public static void main(String[] args) {
		
		String word1, word2 ;
		word1 = "h<werefdsll<oxcsfd_vwoadsrlt<rdsdaas++dqwe" ;
		word2 = "iophl>kelil>iop_owoklrplkm=poi=ljkll=kjppd" ;
		
		System.out.println("A SIMPLE TEST THAT YOU WILL RECOGNIZE...") ;
		System.out.println( "  " + LCS.findLCS(word1,word2) ) ;
		System.out.println("THIS SHOULD DEMONSTRATE THAT TIME/LENGTH^2 IS (ROUGHLY) CONSTANT...") ;
		for (int length=START_LENGTH ; true ; length+=INCREMENT_LENGTH ) {
			word1 = word(length) ;
			word2 = word(length) ;
			start() ;
			for (int iteration=0 ; iteration<ITERATIONS ; iteration++) {
				LCS.findLCS(word1,word2) ;
			}
			System.out.printf("  Length = %4d     Time/Length^2 = %10f \n", length, (float)(elapsed())/(float)(length*length)) ;
		}

	}

	public static String word(int length) {
		String result = "" ;
		Random r = new Random() ;
		char c ;
		for (int i=0 ; i<length ; i++) { result += (char)(97 + r.nextInt(26)) ; }
		return result ;
	}

	public static long zero ;
	public static void start() { zero = (new Date()).getTime() ; }
	public static long elapsed() { return (new Date()).getTime() - zero ; }

}

package funding;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Community_DAO dao = new Community_DAO();
	
		Survey_DAO sdao = new Survey_DAO();
		
		//int num = sdao.getNext();
		//System.out.println("serialnum+1:"+num);
		int scount = sdao.getSurveyCount();
		System.out.println("survey 개수 :"+scount);
		
		ArrayList<Survey> surveylist = sdao.getSurveyList(10, 1);
		for(Survey s : surveylist) {
			System.out.println(s.getBbsNum());
		}
		
		boolean next = sdao.nextPage(10, 2);
		System.out.println(next);
	}

}

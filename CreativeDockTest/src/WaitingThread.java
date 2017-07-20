import java.text.SimpleDateFormat;
import java.util.Date;

public class WaitingThread implements Runnable{

	Logic logic = new Logic();
	int sleepTimeMin = 5 * 60 * 1000;
	
	public void run() {
		
		System.out.println("Thread running");
		while(true){
		try{
			Date date=new Date();    
			System.out.println("Looking for new loans " + new SimpleDateFormat("yyyy.MM.dd  HH:mm:ss").format(date));
			this.logic.loansProcessing();
			Thread.sleep(this.sleepTimeMin);
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
		}
	}
}

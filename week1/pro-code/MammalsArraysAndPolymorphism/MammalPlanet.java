public class MammalPlanet {

	public static void main(String [] args) {	
		
		Mammal [] mammalArrayObject = new Mammal[3];
			
		/*for (int i = 0; i < mammalArrayObject.length; ++i)
		{
			mammalArrayObject[i] = new Mammal();
		}*/
		mammalArrayObject[0] = new Lion();
		mammalArrayObject[1] = new Dog();			
		mammalArrayObject[2] = new Dolphin();	
	
		for(Mammal mCurr : mammalArrayObject)
		{
			mCurr.stateAttributes();
			mCurr.makeNoise();
		}
		
	}	
}

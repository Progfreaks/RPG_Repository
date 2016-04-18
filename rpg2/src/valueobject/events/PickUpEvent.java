package valueobject.events;

public class PickUpEvent extends GameEvent {
	@Override
	public void process(){
		System.out.println("PickUp, der Picknicker von Leibnitz");
	}

}

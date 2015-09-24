package logic;

public class Wall extends AbstractObject
{

	public Wall(int i, int j) {
		super(i, j);
	}
	
	@Override
	public String toString() {
		return "WALL";
	}
	
}

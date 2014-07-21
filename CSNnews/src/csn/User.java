package csn;

public class User{
	private int edad;
	private String nombre;
	public User(int ed, String name)
	{
		edad = ed;
		nombre = name;
	}
	public int getedad()
	{
		return edad;
	}
	
	public String getname()
	{
		return nombre;
	}
	@Override
	public String toString() {  
		return nombre;
	}
}
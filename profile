public class Item 
{
	double cost;
	String Name;
	int id;
	int quantity;
	Item left;
	Item right;
	
	Item() 
    {
		Name = "";
		cost = 0;
		id = 0;
		quantity = 1;
		left = null;
		right = null;
	}
	
	public Item(double cost, String Name, int id, int quantity, Item left, Item right) 
	{
		this.cost = cost;
		this.Name = Name;
		this.id = id;
		this.quantity = quantity;
		this.left = left;
		this.right = right;
	}

	public int getid() 
	{
		return id;
	}

	public void setid(int id) 
	{
		this.id = id;
	}

	public Item getLeft() 
	{
		return left;
	}

	public void setLeft(Item left) 
	{
		this.left = left;
	}

	public Item getRight() 
	{
		return right;
	}

	public void setRight(Item right) 
	{
		this.right = right;
	}
	public double getCost() 
	{
		return cost;
	}

	public void setCost(double cost) 
	{
		this.cost = cost;
	}

	public String getName() 
	{
		return Name;
	}

	public void setName(String name) 
	{
		Name = name;
	}
	public int getQuantity() 
	{
		return quantity;
	}

	public void setQuantity(int quantity) 
	{
		this.quantity = quantity;
	}
	
}

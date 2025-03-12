public class Item 
{
	String name;
	int id;
	Item left;
	Item right;

	
	Item() 
    {
		name = "";
		id = 0;
		left = null;
		right = null;
	}
	
	public Item(String name, int id, Item left, Item right) 
	{
		this.name = name;
		this.id = id;
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

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		Name = name;
	}
	
}

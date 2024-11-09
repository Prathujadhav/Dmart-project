import java.util.*;
class Dmart
{
	String address ;
	long contact ;
	String email ;
	String website ;
	ArrayList<Product> productList = new ArrayList<>();
	Cashier cashier ;
	Customer customer;
	Cart cart;

	Dmart (String address ,long contact ,String email ,String website)
	{
	this.address=address;
	this.contact=contact;
	this.email=email;
	this.website=website;
	}
	public void displayDmart()
	{
		System.out.println("Dmart [Address : "+address+",Contact : "+contact+",Email : "
			                        +email+",Website : "+website+"]");
	}
	public void addProduct(String cate,String name, double orgPrice,Barcode barcode)
	{
		Product product = new Product(cate,name,orgPrice,barcode);
		productList.add(product);
	}
	public void addCart(String type)
	{
		cart = new Cart(type);
	}
	public void addCashier(String name, String id, Long contact, int counterNum)
	{
		cashier = new Cashier(name, id, contact, counterNum);
	}
	public void addCustomer(String name,Long contact )
	{
		if (cashier!=null && productList.size()!=0)
		{
			customer = new Customer(name,contact);
		}
		else
		{
			System.out.println("add cashier and product first");
		}
	}

}
class Product
{
	String cate;
	String name;
	double orgPrice;
	double dmartPrice;
	Barcode barcode;

	Product(String cate, String name,double orgPrice, Barcode barcode )
	{
		this.cate = cate;
		this.name = name;
		this.orgPrice = orgPrice;
		this.barcode = barcode;
	}
	public void displayProduct()
	{
		System.out.println("Barcode [ cate : "+cate+",Name :"+name+",Original Price :"+orgPrice+"]");
	}
	public void addBarcode(String productId, double dmartPrice)
	{
		barcode = new Barcode (productId ,dmartPrice);
	}
}
class Barcode
{
	String productId;
	double dmartPrice;
	Barcode(String productId, double damrtPrice)
	{
		this.productId = productId;
		this.dmartPrice = dmartPrice;
	}
	public void displayBarcode()
	{
		System.out.println("Barcode [ProductId : "+",Dmart Price :"+dmartPrice+"]");
	}
}
class Customer
{
	String name;
	Long contact;
	String paymentMode;
	String billNo;
	double totalBill;
	ArrayList<Product> customerCart = new ArrayList<>();
	Customer(String name, Long contact)
	{
		this.name = name;
		this.contact = contact;
	
	}
	public void displayCustomer()
	{
		System.out.println("Customer[ Name : "+name+", Contact :"+contact
			                    +",PaymentMode :"+paymentMode
			                    +", Bill No : "+billNo+",Total Bill : "+totalBill+"]");
	}
}
class Cashier
{
	String name;
	String id;
	Long contact;
	int counterNum;

	Cashier(String name, String id, Long contact, int counterNum)
	{
		this.name = name;
		this.id = id;
		this.contact = contact;
		this.counterNum = counterNum;
	}
	public void displayCashier()
	{
		System.out.println("Cashier[name : "+name+", Id : "+id+",Contact : "
			           +contact+", CounterNumber : "+counterNum+"]");
	}
}
class Cart
{
	String type;
	ArrayList<Product> cart = new  ArrayList<>();
	Cart (String type)
	{
		this.type = type;
	}
	public void displayCart()
	{
		System.out.println("Cart [Type :"+type+", capacity :"+cart.size());
		for (Product i : cart)
		{
			i.displayProduct();
			i.barcode.displayBarcode();
		}
	}
}
class DmartDriver
{
	public static void main(String []args)
	{
	  Scanner sc = new Scanner(System.in);
      Dmart dmart = new Dmart("Deccan",7385552872L,"dmart@gmail.com","www.dmart.com");
      dmart.displayDmart();
      for(int i =1; i <=5; i++)
      {
      	System.out.println("Add Product :");
      	System.out.println("Cate :");
      	sc.nextLine();
      	String cate = sc.nextLine();
      	System.out.println(" Product Name:");
      	String name = sc.nextLine();
      	System.out.println("MRP :");
      	double price  = sc.nextDouble();
      	System.out.println("Product Id :");
      	sc.nextLine();
      	String id = sc.nextLine();
      	System.out.println("Dmart Price :");
      	double dprice = sc.nextDouble();
      	dmart.addProduct(cate,name,price,(new Barcode(id,dprice)));
      }
      for(Product i : dmart.productList)
      {
      	i.displayProduct();
      	i.barcode.displayBarcode();
      }

      dmart.addCashier("Jadhav Prathamesh","DCASH123",7385552872L,2);
      dmart.cashier.displayCashier();
      dmart.addCart("basket");
      dmart.cart.displayCart();
      dmart.addCustomer("Adi Ghodke", 9852627353L);
      dmart.customer.displayCustomer();
      for(; ;)
      {
      	System.out.println("Enter the product id");
      	sc.nextLine();
      	String pid = sc.nextLine();
      	boolean flag = false;
      	for (Product i : dmart.productList)
      	{
      		if(pid.equals(i.barcode.productId))
      		{
      			dmart.customer.customerCart.add(i);
      			flag = true;
      		}
      	}
      	if(!flag)
      	{
      		System.out.println("Product Not Found");
      	}
      	System.out.println("Do you want to continue shopping(Y/N) :");
      	char ch = sc.next().charAt(0);
      	if(ch=='Y')
      	{
      		continue;
      	}
      	else
      	{
      		break;
      	   }
      	}
      	double bill = 0;
      	for (Product i : dmart.customer.customerCart)
      	{
      	    i.displayProduct();
      	    i.barcode.displayBarcode();
      	    dmart.customer.totalBill+= i.barcode.dmartPrice;
      	    bill+= i.orgPrice;
      }

      System.out.println("Total Bill is : "+ bill);
      System.out.println("Discounted Price : "+ dmart.customer.totalBill);
	}
}


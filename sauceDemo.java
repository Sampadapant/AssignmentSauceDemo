package sauceDemo;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class sauceDemo {
	 ChromeDriver driver;
	 
	@DataProvider (name = "data-provider")
	 public Object [] [] dataMethod() {
		return new Object[][] {
			{"standard_user","secret_sauce"},
			{"locked_out_user","secret_sauce"},
			{"problem_user","secret_sauce"},
			{"performance_glitch_user","secret_sauce"},
			
		};
	}
		
		@Test(dataProvider ="data-provider", enabled=false) 
		public void Login(String username , String password) {
    	
		 driver = new ChromeDriver();
		
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

      

        List<WebElement> inventoryItems = driver.findElements(By.className("inventory_item"));
  

        Assert.assertEquals(inventoryItems.size(), 6);

        driver.quit();
    }
		
		@Test(enabled = false)
		
		public void verifyLogo() {
			 driver = new ChromeDriver();
				
		        driver.get("https://www.saucedemo.com/");
		        driver.manage().window().maximize();

		        driver.findElement(By.id("user-name")).sendKeys("standard_user");
		        driver.findElement(By.id("password")).sendKeys("secret_sauce");
		        driver.findElement(By.id("login-button")).click();
		        
		        String displayLogo = driver.findElement(By.className("app_logo")).getText();
		        Assert.assertEquals(displayLogo, "Swag Labs");
		        
		        driver.quit();

		}
		
		//3)verify number of items in navigation bar  
		
		@Test(enabled = false)
		
		public void getItems() {
			 driver = new ChromeDriver();
		        driver.get("https://www.saucedemo.com/");
		        driver.manage().window().maximize();

		        driver.findElement(By.id("user-name")).sendKeys("standard_user");
		        driver.findElement(By.id("password")).sendKeys("secret_sauce");
		        driver.findElement(By.id("login-button")).click();
		        
		       List<WebElement>  navItems = driver.findElements(By.cssSelector("a[class=\"bm-item menu-item\"]"));
		       
		       Assert.assertEquals(navItems.size(), 4);
		       
		       driver.quit();
			
		}
		 //4)verify the add to cart functionality

        @Test(enabled = false)
		
		public void addToCart() {
			 driver = new ChromeDriver();
				
		        driver.get("https://www.saucedemo.com/");
		        driver.manage().window().maximize();

		        driver.findElement(By.id("user-name")).sendKeys("standard_user");
		        driver.findElement(By.id("password")).sendKeys("secret_sauce");
		        driver.findElement(By.id("login-button")).click();
		        
		        driver.findElement(By.cssSelector("button[name=\"add-to-cart-sauce-labs-backpack\"]")).click();
		        driver.findElement(By.cssSelector("span[class='shopping_cart_badge']")).click();
		        
		        Assert.assertEquals(driver.findElement(By.cssSelector("div[class='inventory_item_name']")).getText(), "Sauce Labs Backpack");
		        driver.quit();
        }
		      //6)verify the complete order flow
		        @Test(enabled = false)
		        public void getorder() {
		        
		       
		      driver = new ChromeDriver ();
		        
		      driver.get("https://www.saucedemo.com/");
		      driver.manage().window().maximize();
		      driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("standard_user");		   
		      driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("secret_sauce");
		      driver.findElement(By.xpath("//input[@type='submit']")).submit();  
		      
		      driver.findElement(By.cssSelector("button[id='add-to-cart-sauce-labs-backpack']")).click();
		      driver.findElement(By.cssSelector("span[class='shopping_cart_badge']")).click();
		      driver.findElement(By.cssSelector("button[id='checkout']")).click();
		      driver.findElement(By.cssSelector("input[placeholder='First Name']")).sendKeys("Sampada");
		      driver.findElement(By.cssSelector("input[placeholder='Last Name']")).sendKeys("Pant");
		      driver.findElement(By.cssSelector("input[placeholder='Zip/Postal Code']")).sendKeys("76005");
		      driver.findElement(By.cssSelector("input[type='submit']")).submit();
		      driver.findElement(By.cssSelector("button[class='btn btn_action btn_medium cart_button']")).click();
		      Assert.assertEquals(driver.findElement(By.cssSelector("h2[class='complete-header']")).getText(), "Thank you for your order!");
		      driver.quit();
		      
		      //filter A to Z
	}
	 @Test (enabled = false)
	 public void filterAtoZ() {
		 driver = new ChromeDriver();
		 driver.get("https://www.saucedemo.com/");
	      driver.manage().window().maximize();
	      driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("standard_user");		   
	      driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("secret_sauce");
	      driver.findElement(By.xpath("//input[@type='submit']")).submit();  
	      
	     List<WebElement> inventoryName = driver.findElements(By.cssSelector(".inventory_item_name"));
	     
	     //storing the product names
	     List<String> inventoryList = new ArrayList<String>();
	     
	     for(int i = 0 ; i <inventoryName.size(); i ++) {
	    	 
	    	 String names = inventoryName.get(i).getText();
	    	 
	    	 //System.out.println(names);
	    	 
	    	 inventoryList.add(names);
	    	 
	     }
	     
	     //sorting the list alphabetically
	     
	     Collections.sort(inventoryList);
	     System.out.println(inventoryList); //====> expected sorted
	     
	     //  Retrieve the list of products again after sorting

	     List<WebElement> inventoryAsListed = driver.findElements(By.cssSelector(".inventory_item_name"));
	     
	     // Create a list to store the sorted product names
	     List<String> listProducts = new ArrayList<String> ();
	  // Add the sorted product names to the list
	     
	     for (int i = 0; i <inventoryAsListed.size(); i++) {
	    	String shownProducts =  inventoryAsListed.get(i).getText();
	    	listProducts.add(shownProducts);
	    	
	    
	     }
	     //comparing two for assertion
	     Assert.assertEquals(listProducts, inventoryList);
	     
	     driver.quit();

	 }
	 //filter low to high
	 @Test (enabled = false)
	 public void lowToHigh() throws InterruptedException {
		 driver = new ChromeDriver();
		 driver.get("https://www.saucedemo.com/");
	      driver.manage().window().maximize();
	      driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("standard_user");		   
	      driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("secret_sauce");
	      driver.findElement(By.xpath("//input[@type='submit']")).click();  
		 
	      //before filter capture prices
	     List<WebElement> beforeFilterPrice = driver.findElements(By.className("inventory_item_price"));
	     
	     //remove the dollar and convert the string into double
	     List<Double> beforeFilterPriceList = new ArrayList<Double>();
	     
	     for(WebElement ss :beforeFilterPrice) {
	    	 beforeFilterPriceList.add(Double.valueOf(ss.getText().replace("$", "")));
	     }
	   
	     //filtering the price from the drop down
	     WebElement drop = driver.findElement(By.cssSelector(".product_sort_container"));
	      Select dropDown = new Select(drop);
	      dropDown.selectByValue("lohi");
	     
	     //capturing price
	      List<WebElement> afterFilterPrice = driver.findElements(By.className("inventory_item_price"));
	      
	      //remove dollar symbol from price and convert the string into double
	      
	      List<Double> afterFilterPriceList = new ArrayList<Double>();
	      
	      for(WebElement pp:afterFilterPrice) {
	    	  afterFilterPriceList.add(Double.valueOf(pp.getText().replace("$", "")));
	      }
	      
	      //aserting the values
	      Collections.sort(beforeFilterPriceList); //sort the values in the list
	      System.out.println(beforeFilterPriceList);
	      Assert.assertEquals(beforeFilterPriceList, afterFilterPriceList);
	      
	      Thread.sleep(3000);
	      driver.quit();
	 }
	 
	 //filter high to low
	 
	 @Test(enabled=false)
	 public void highToLow() {
		 driver = new ChromeDriver();
		 driver.get("https://www.saucedemo.com/");
	      driver.manage().window().maximize();
	      driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("standard_user");		   
	      driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("secret_sauce");
	      driver.findElement(By.xpath("//input[@type='submit']")).click();  
	     
	      //capturing the price before the filter
		 List <WebElement> beforeFilterPrice =  driver.findElements(By.className("inventory_item_price"));
		 
		 //remove the dollar and convert the string into double
	     List<Double> beforeFilterPriceList = new ArrayList<Double>();
	     
	     for(WebElement aa : beforeFilterPrice) {
	    	 beforeFilterPriceList.add(Double.valueOf(aa.getText().replace("$", "")));
	     }
	    
	     //filtering the price from the dropdown
          WebElement dd1 = driver.findElement(By.cssSelector(".product_sort_container"));
	     
	     Select dropDn = new Select (dd1);
	     dropDn.selectByValue("hilo");
	     
	     //capturing the prices after the filter
	     
	     List<WebElement> afterFilterPrice = driver.findElements(By.cssSelector(".inventory_item_price"));
	     
	   //remove the dollar and convert the string into double
	     List<Double> afterFilterPriceList = new ArrayList<Double>();
	     
	     for(WebElement bb :afterFilterPrice) {
	    	 afterFilterPriceList.add(Double.valueOf(bb.getText().replace("$", "")));
	     }
	   //aserting the values
	     Collections.sort(beforeFilterPriceList);
	     
	     Collections.reverse(beforeFilterPriceList);
	     System.out.println(beforeFilterPriceList);
	     
	     Assert.assertEquals(beforeFilterPriceList,afterFilterPriceList);
	     driver.quit();
	 }
	 
	 //z to A filter
	 
	 @Test(enabled = false)
	 public void zToAfilter() {
		 driver = new ChromeDriver();
		 driver.get("https://www.saucedemo.com/");
	      driver.manage().window().maximize();
	      driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("standard_user");		   
	      driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("secret_sauce");
	      driver.findElement(By.xpath("//input[@type='submit']")).submit();  
	      
	      //selecting the element through value
	     WebElement dropp  = driver.findElement(By.cssSelector(".product_sort_container"));
	      Select dd = new Select(dropp);
	      dd.selectByValue("za");
	      
	      //Retrieving the lists of product displayed
	     List< WebElement> descInventory = driver.findElements(By.cssSelector(".inventory_item_name"));
	      
	     
	     // created list to store product names
	      List<String> descInventoryList = new ArrayList <String>();
	      
	      //looping to get list through text
	      for(WebElement inventoryList: descInventory) {
	    	  descInventoryList.add(inventoryList.getText());
	      }
	      //sorting list of products in descending order (alphabet)
	      Collections.sort(descInventoryList, Collections.reverseOrder());
	      System.out.println(descInventoryList);
	      
	     // Retrieve the list of products again after sorting
	      List< WebElement> descInventorySorted = driver.findElements(By.xpath("//div[@class='inventory_item_name']"));
	      
	   // created list to store product names
	      List<String> descInventorySortedList = new ArrayList<String>();
	      
	      //looping again
	      for (WebElement finalInventory: descInventorySorted) {
	    	  descInventorySortedList.add(finalInventory.getText());
	      }
	      
	      //comparing for assertion
	      Assert.assertEquals(descInventoryList,descInventorySortedList);
	      
	      driver.quit();

	 }
	 
	 // verify the calculation of price on checkout page
	 
	 @Test()
	 public void checkOutPage() throws InterruptedException {
		 driver = new ChromeDriver();
		 driver.get("https://www.saucedemo.com/");
	      driver.manage().window().maximize();
	      driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("standard_user");		   
	      driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("secret_sauce");
	      driver.findElement(By.xpath("//input[@type='submit']")).submit();  
	      
	      driver.findElement(By.cssSelector("button[id='add-to-cart-sauce-labs-backpack']")).click();
	      driver.findElement(By.cssSelector("button[id='add-to-cart-sauce-labs-bike-light']")).click();
	      driver.findElement(By.cssSelector("button[id='add-to-cart-sauce-labs-bolt-t-shirt']")).click();
	      driver.findElement(By.cssSelector("span[class='shopping_cart_badge']")).click();

	      driver.findElement(By.cssSelector("#checkout")).click();
	      driver.findElement(By.cssSelector("input[placeholder='First Name']")).sendKeys("Sampada");
	      driver.findElement(By.cssSelector("input[placeholder='Last Name']")).sendKeys("Pant");
	      driver.findElement(By.cssSelector("input[placeholder='Zip/Postal Code']")).sendKeys("76005");
	      driver.findElement(By.cssSelector("input[type='submit']")).submit();
	      
	      List<WebElement>  priceTotal =driver.findElements(By.xpath("//div[@class='inventory_item_price']"));
	      
	      double sumTotal =0;
	        for(int i=0; i<priceTotal.size(); i++ ) {
	        //  System.out.println(priceTotal.get(i).getText().replace("$", ""));
	          sumTotal= sumTotal+Double.valueOf(priceTotal.get(i).getText().replace("$", ""));  
	        }
	        //System.out.println(sumTotal);
	       
	        
	        Double TaxAmount = sumTotal * 0.08; // I am assuming 10% tax
	      
	      
	        DecimalFormat df = new DecimalFormat("#.##"); // rounding off the values to 2 decimal places
	        String firstPrice= df.format(sumTotal+TaxAmount);// this is Expected
	        System.out.println(firstPrice);
	        
       // Verify that the calculated sum of prices and tax amount match with the displayed values
	      
	     String ActualPrice=  driver.findElement(By.xpath("//div[@class='summary_info_label summary_total_label']")).getText().replace("Total: $","" );
	    
	        System.out.println(ActualPrice);
	     
	    Assert.assertEquals(firstPrice,ActualPrice, "prices are not correct");

	      driver.quit();
	 } 
		        
}

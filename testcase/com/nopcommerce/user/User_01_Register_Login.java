package com.nopcommerce.user;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;


public class User_01_Register_Login {
	// Biến toàn cục: phạm vi dùng trong cả class test (màu xanh)
	String homePageUrl ="";
	
	@BeforeClass
	public void beforeClass() {
	  
	}

  @Test
  public void TC_01() {
	  // Biến cục bộ: chỉ sử dụng trong phạm vi testcase 01 (Hàm) này (màu đỏ)
	  String homePageUrl = "";
	  System.out.println(homePageUrl);
	  
	  // Block code
	  if (3<5) {
		  // Biến cục bộ: chỉ dùng trong hàm IF
		  String homePageTitle ="";
		  System.out.println(homePageTitle);
		
	}
  }
  
  
  @Test
  public void TC_02() {
	  System.out.println(homePageUrl);
  }
  
  
  @AfterClass
  public void afterClass() {
  }

}

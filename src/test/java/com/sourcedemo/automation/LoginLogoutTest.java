package com.sourcedemo.automation;

import com.saucedemo.POJOS.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertTrue;

public class LoginLogoutTest extends BaseTest {
    private LoginPage loginPage;

    @Test
    public void testLoginAndLogout() {
        WebDriver driver = getDriver();
        driver.get("https://www.saucedemo.com/");
        loginPage = PageFactory.initElements(driver, LoginPage.class);

        // Login
        loginPage.login("standard_user", "secret_sauce");
        assertTrue(driver.getCurrentUrl().contains("inventory"));
        actions.sendKeys(Keys.ESCAPE).build().perform();

        driver.findElement(By.id("react-burger-menu-btn")).click();
        driver.findElement(By.id("logout_sidebar_link")).click();
        assertTrue(driver.getCurrentUrl().contains("saucedemo.com"));
    }

    @Test
    public void testCodingRound() {
        // Online Java Compiler
// Use this editor to write, compile and run your Java code online
        // given as array find the longest subarray whose sum is the highest
        int arr[] = new int[]{1, 6, 2, 9, 2, 55, 11, 99, 24, 2, 3, 4}; //300,299
//        int arr[] = new int[]{-7,-9,-99,-1}; -117 ,-1
//        int arr[] = new int[]{1, 6, -2, 9, -2, 55, 11, -99, 24, 2, 3, -4};
//        int arr[] = new int[]{};
//        int arr[] = null;
        int len = arr.length;
        if(arr == null ) {
            System.out.println("Array is empty or null");
            return;
        }
        int sum = 0;
        int sumSubArray = 0;
        List<List<Integer>> list = new ArrayList<>();
        Map<Integer, List<List<Integer>>> map = new HashMap<>();
        for (int n : arr)
            sum += n;
        sumSubArray = sum+0;
        int maxSub = 0;
        for (int i = 0; i < len; i++) {
            sumSubArray = sumSubArray - arr[i];
            int nextSubSum = sumSubArray+0;
            for (int j = len - 1; j >= i; j--) {
                nextSubSum = nextSubSum - arr[j];
                maxSub = Math.max(nextSubSum, maxSub);
                if (!map.containsKey(maxSub)) {
                    map.put(maxSub, List.of(List.of(i,j)));
                }
            }
        }
        System.out.println(map);
        //just for printing the subarrays
        for (int k : map.keySet()) {
            for (List<Integer> ls : map.get(k)) {
                for (int i = ls.get(0); i <= ls.get(1); i++)
                    System.out.print(arr[i] + ",");
                System.out.println();
            }
        }
    }
}